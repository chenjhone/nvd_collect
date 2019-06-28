//package com.chenjh.util.mongo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MongoService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public <T> Page<T> paginationQuery(Integer currentPage, Integer pageSize, Class<T> objClass, Criteria criteria) {
//        SpringbootPageable pageable = new SpringbootPageable();
//        PageModel pm = new PageModel();
//        Query query = new Query(criteria);
//        pm.setCurrentPage(currentPage);
//        pm.setPagesize(pageSize);
//        // 排序
//        List<Order> orders = new ArrayList<Order>();  //排序
//        orders.add(new Order(Direction.DESC, "_id"));
//        Sort sort = new Sort(orders);
//        pm.setSort(sort);
//        pageable.setPage(pm);
//        Long count = mongoTemplate.count(query, objClass);
//        List<T> list = (List<T>) mongoTemplate.find(query.with(pageable), objClass);
//        // 将集合与分页结果封装
//        Page<T> pagelist = new PageImpl<T>(list, pageable, count);
//        return (Page<T>) pagelist;
//    }
//}
