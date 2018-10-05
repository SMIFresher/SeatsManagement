package com.seatmanagement.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seatmanagement.model.Employee;

/**
 * 
 * @author Vijayakumar Selvaraj
 *
 *         Util class to parse XML documents using Dom4j API
 */
public class XMLParser {

	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);

	/**
	 * 
	 * Parses Employee objects from XML file
	 * 
	 * @return List<Employee>
	 */
	public static List<Employee> parseEmployees() {

		logger.info("Begining parsing employees from XML");

		List<Employee> employees = null;

		try {
			// Must replace with rest call
			File inputFile = new File("D:\\Vijay\\DBXML\\employees.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputFile);

			// Select all employee Nodes
			List<Node> nodes = document.selectNodes("/employees/employee");

			if (null != nodes && !nodes.isEmpty()) {
				employees = new ArrayList<Employee>();

				for (Node node : nodes) {
					Employee employee = new Employee();

					employee.setFirstName(node.selectSingleNode("firstName").getText());
					employee.setLastName(node.selectSingleNode("lastName").getText());
					employee.setEmployeeRoll(node.selectSingleNode("empId").getText());
					// Must add remaining employee fields

					employees.add(employee);
				}
			}

			logger.info("Parsing employees from XML finished");
		} catch (DocumentException e) {
			logger.error("Exception during Employees XML parsing : " + e.getMessage());
			logger.error("Exception : ", e);
		}
		return employees;
	}

	public static void main(String args[]) {
		logger.info("info log");
		logger.error("error log");
		logger.debug("debug log");
		logger.warn("warn log");
	}
}
