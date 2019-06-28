//package com.chenjh.util.neo4j;
//
//import com.chenjh.util.Config;
//import org.apache.commons.lang3.StringUtils;
//import org.neo4j.driver.v1.*;
//import org.neo4j.driver.v1.types.Node;
//import org.neo4j.driver.v1.types.Relationship;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//public class Neo4jDriver implements AutoCloseable {
//    private Driver driver = GraphDatabase.driver(Config.get("neo4j_server"),
//            AuthTokens.basic(Config.get("neo4j_user"), Config.get("neo4j_password")));
//    private static String label = Config.get("neo4j_label");
//    private static Neo4jDriver neo4jDriver = null;
//    public static Map<String, Object> recordToNode(Record record) {
//        return record.get("n").asNode().asMap();
//    }
//
//    public static Map<String, Object> recordToLink(Record record) {
//        Map<String, Object> linkMap = new HashMap<>();
//        linkMap.put("linkType", record.get("r").asRelationship().type());
//        linkMap.put("node1", record.get("n").asNode().asMap());
//        linkMap.put("node2", record.get("m").asNode().asMap());
//        return linkMap;
//    }
//
//    public static Map<String, Object> recordToLinkedNode(Record record) {
//        Map<String, Object> map = new HashMap<>();
//        Node node = record.get("n").asNode();
//        Relationship r = record.get("r").asRelationship();
//        map.put("linkType", r.type());
//        map.put("dir", r.endNodeId()==node.id()?"FORWARD":"BACKWORD");
//        map.put("node", node.asMap());
//        return map;
//    }
//
//    public synchronized static Neo4jDriver instance() {
//        if (neo4jDriver == null)
//            neo4jDriver = new Neo4jDriver();
//        return neo4jDriver;
//    }
//    public Map<String, Object> getNode(String id) {
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(String.format("MATCH (n:%s) WHERE n._id='%s' RETURN n", label, id));
//            return recordToNode(result.single());
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public List<Map<String, Object>> queryNode(String... conditions) {
//        Map<String, String> qmap = new HashMap<>();
//        for (String c : conditions) {
//            int pos = c.indexOf(':');
//            if (pos == -1) continue;
//            qmap.put(c.substring(0, pos), c.substring(pos + 1));
//        }
//        return queryNode(qmap);
//    }
//
//    public List<Map<String, Object>> queryNode(Map<String, String> qmap) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("MATCH (n:%s) WHERE ", label));
//        boolean isFirst = true;
//        for (Map.Entry<String, String> entry : qmap.entrySet()) {
//            if (isFirst) {
//                isFirst = false;
//            } else {
//                sb.append("AND ");
//            }
//            sb.append(String.format("n.%s='%s' ", entry.getKey(), entry.getValue()));
//        }
//        sb.append("RETURN n");
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(sb.toString());
//            return result.list().stream().map(Neo4jDriver::recordToNode).collect(Collectors.toList());
//        }
//    }
//
//    public List<Map<String, Object>> getLinkedNodes(String nodeId, String... links) {
//        Set<String> linkSet = new HashSet<>(Arrays.asList(links));
//        if (linkSet.contains("*")) linkSet.clear();
//        String query = String.format("MATCH (m:%s)-[r]-(n:%s) WHERE m._id='%s' RETURN r,n", label, label, nodeId);
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(query);
//            List<Record> records = result.list();
//            return records.stream()
//                    .map(Neo4jDriver::recordToLinkedNode)
//                    .filter(e -> {
//                        if (linkSet.isEmpty()) return true;
//                        String linkType = e.get("linkType").toString();
//                        String dir = e.get("dir").equals("FORWARD")?"/f":"/b";
//                        return linkSet.contains(linkType) || linkSet.contains(linkType+dir) || linkSet.contains("*"+dir);
//                    })
//                    .collect(Collectors.toList());
//        }
//    }
//
//
//    public List<Map<String, Object>> followLinks(String nodeId, int skip, int limit, String... links) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("MATCH (m:%s {_id:'%s'})", label, nodeId));
//        for (int i = 0; i < links.length; i++) {
//            String r = links[i];
//            int dir = 0;
//            if (r.endsWith("/b")) {
//                r = r.substring(0, r.length()-2);
//                dir = -1;
//            }
//            else if (r.endsWith("/f")) {
//                r = r.substring(0, r.length()-2);
//                dir = 1;
//
//            }
//            if (dir == -1) sb.append('<');
//            sb.append("-[");
//            if (!r.equals("*")) {
//                sb.append(":");
//                sb.append(r);
//            }
//            sb.append("]-");
//            if (dir==1) sb.append(">");
//            sb.append("(");
//            if (i+1 == links.length) {
//                sb.append("n");
//            }
//            sb.append(":");
//            sb.append(label);
//            sb.append(")");
//        }
//        sb.append(String.format(" RETURN n SKIP %d LIMIT %s", skip, limit));
//        System.out.println(sb.toString());
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(sb.toString());
//            return result.list().stream().map(Neo4jDriver::recordToNode).collect(Collectors.toList());
//        }
//    }
//    public List<Map<String, Object>> queryLink(String node1, String node2) {
//        String query = String.format("MATCH (n:%s)-[r]-(m:%s) WHERE n._id='%s' AND m._id='%s' RETURN n,r,m", label, label, node1, node2);
//
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(query);
//            List<Record> records = result.list();
//            return records.stream().map(Neo4jDriver::recordToLink).collect(Collectors.toList());
//        }
//    }
//
//    public List<Map<String, Object>> findAllLinks(String... ids) {
//        String idstr = StringUtils.join(Arrays.asList(ids).stream().map(id->"'"+id+"'").collect(Collectors.toList()), ',');
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("MATCH (n:%s)-[r]-(m:%s) WHERE n._id IN [", label, label));
//        sb.append(idstr);
//        sb.append("] AND m._id IN [");
//        sb.append(idstr);
//        sb.append("] RETURN r,m,n");
//        String query = sb.toString();
//        System.out.println(query);
//        try (Session session = driver.session()) {
//            StatementResult result = session.run(query);
//            List<Record> records = result.list();
//            return records.stream().map(Neo4jDriver::recordToLink).collect(Collectors.toList());
//        }
//    }
//    @Override
//    public void close() {
//        driver.close();
//    }
//
//    public static void main(String[] args) {
//        try (Neo4jDriver driver = new Neo4jDriver()) {
//            List<Map<String, Object>> records = driver.getLinkedNodes("5wCQ", "b1/f");
//            System.out.println(records);
//        }
//    }
//}
