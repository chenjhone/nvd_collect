package com.chenjh.util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {

	public void getAllUserNames(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(fileName);
			if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();
				NodeList studentList = docEle.getElementsByTagName("student");

				if (studentList != null && studentList.getLength() > 0) {
					for (int i = 0; i < studentList.getLength(); i++) {

						Node node = studentList.item(i);

						if (node.getNodeType() == Node.ELEMENT_NODE) {

							System.out.println("=====================");

							Element e = (Element) node;
							NodeList nodeList = e.getElementsByTagName("name");
							System.out.println("Name: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());

							nodeList = e.getElementsByTagName("grade");
							System.out.println("Grade: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());

							nodeList = e.getElementsByTagName("age");
							System.out.println("Age: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				} else {
					System.exit(1);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		XmlUtil parser = new XmlUtil();
		parser.getAllUserNames("c:\\test.xml");
	}

}
