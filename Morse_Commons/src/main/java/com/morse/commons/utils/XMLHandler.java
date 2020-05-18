package com.morse.commons.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLHandler {

	public static Document getDocument(String xml) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);

		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document documentXML = documentBuilder.parse(new InputSource(new StringReader(xml)));

		return documentXML;
	}

	public static Node getNode(String nodeName, Document documentXML) {
		if (documentXML != null && nodeName != null) {
			NodeList nodeList = documentXML.getChildNodes();

			return getNode(nodeName, nodeList);
		}

		return null;
	}

	public static Node getNode(String nodeName, Node node) {
		if (node != null && nodeName != null) {
			for (int index = 0; index < node.getChildNodes().getLength(); index++) {
				Node nodeChild = node.getChildNodes().item(index);

				if (nodeChild.getNodeName().equals(nodeName))
					return nodeChild;
			}
		}

		return null;
	}

	public static List<Node> getNodes(String nodeName, Node node) {
		List<Node> nodes = new ArrayList<Node>();

		if (node != null && nodeName != null) {
			for (int index = 0; index < node.getChildNodes().getLength(); index++) {
				Node nodeChild = node.getChildNodes().item(index);

				if (nodeChild.getNodeName().equals(nodeName))
					nodes.add(nodeChild);
			}
		}

		return nodes;
	}

	public static Node getNode(String nodeName, NodeList nodeList) {
		if (nodeList != null && nodeName != null) {
			for (int index = 0; index < nodeList.getLength(); index++) {
				Node node = nodeList.item(index);

				if (node.getNodeName().equalsIgnoreCase(nodeName))
					return node;
			}
		}

		return null;
	}

	public static String getTagValue(String tagName, Node node) {
		if (node != null && tagName != null) {
			Node nodeTag = getNode(tagName, node.getChildNodes());

			if (nodeTag != null && nodeTag.getChildNodes().item(0) != null)
				return nodeTag.getChildNodes().item(0).getNodeValue();
		}

		return null;
	}
}
