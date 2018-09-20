package com.seatmanagement.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.seatmanagement.model.Employee;

public class XMLParser {
	
	public static void parseEmployees() {
		try {
			File inputFile = new File("D:\\Vijay\\DBXML\\employees.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputFile);

			System.out.println("Root element :" + document.getRootElement().getName());

			Element classElement = document.getRootElement();

			List<Node> nodes = document.selectNodes("/employees/employee");
			System.out.println("----------------------------");

			for (Node node : nodes) {
				System.out.println("Marks : " + node.selectSingleNode("empId").getText());
				
				Employee employee = new Employee();
				
				employee.setFirstName(node.selectSingleNode("firstName").getText());
				employee.setLastName(node.selectSingleNode("lastName").getText());
				employee.setEmployeeRoll(node.selectSingleNode("empId").getText());
				
				System.out.println(employee.getFirstName());
				System.out.println(employee.getLastName());
				System.out.println(employee.getEmployeeRoll());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
