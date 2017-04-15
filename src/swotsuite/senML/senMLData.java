package swotsuite.senML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class senMLData {

	public static void writingsenML() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("zone");
			Attr zone = doc.createAttribute("name");
			zone.setValue("health");
			rootElement.setAttributeNode(zone);
			doc.appendChild(rootElement);

			// supercars element
			Element bn = doc.createElement("senml");
			rootElement.appendChild(bn);

			// setting attribute to element
			Attr attr = doc.createAttribute("bn");
			attr.setValue("urn:body:uuid:c68ad78b-09eb-4303-ae3c-d5d23149ee96");
			bn.setAttributeNode(attr);

			// e1 element
			Element e1name = doc.createElement("e");
			Attr n1Type = doc.createAttribute("n");
			n1Type.setValue("blood pressure");
			e1name.setAttributeNode(n1Type);
			Attr t1Type = doc.createAttribute("t");
			t1Type.setValue("0");
			e1name.setAttributeNode(t1Type);
			Attr u1Type = doc.createAttribute("u");
			u1Type.setValue("Pa");
			e1name.setAttributeNode(u1Type);
			Attr v1Type = doc.createAttribute("v");
			v1Type.setValue("56");
			e1name.setAttributeNode(v1Type);
			bn.appendChild(e1name);

			// e2 element

			Element e2name = doc.createElement("e");
			Attr n2Type = doc.createAttribute("n");
			n2Type.setValue("cholesterol");
			e2name.setAttributeNode(n2Type);
			Attr t2Type = doc.createAttribute("t");
			t2Type.setValue("0");
			e2name.setAttributeNode(t2Type);
			Attr u2Type = doc.createAttribute("u");
			u2Type.setValue("g/L");
			e2name.setAttributeNode(u2Type);
			Attr v2Type = doc.createAttribute("v");
			v2Type.setValue("5");
			e2name.setAttributeNode(v2Type);
			bn.appendChild(e2name);

			// e3 element

			Element e3name = doc.createElement("e");
			Attr n3Type = doc.createAttribute("n");
			n3Type.setValue("heartbeat");
			e3name.setAttributeNode(n3Type);
			Attr t3Type = doc.createAttribute("t");
			t3Type.setValue("0");
			e3name.setAttributeNode(t3Type);
			Attr u3Type = doc.createAttribute("u");
			u3Type.setValue("beet/m");
			e3name.setAttributeNode(u3Type);
			Attr v3Type = doc.createAttribute("v");
			v3Type.setValue("155");
			e3name.setAttributeNode(v3Type);
			bn.appendChild(e3name);

			// e4 element

			Element e4name = doc.createElement("e");
			Attr n4Type = doc.createAttribute("n");
			n4Type.setValue("skin conductance");
			e4name.setAttributeNode(n4Type);
			Attr t4Type = doc.createAttribute("t");
			t4Type.setValue("1382019143284");
			e4name.setAttributeNode(t4Type);
			Attr u4Type = doc.createAttribute("u");
			u4Type.setValue("S");
			e4name.setAttributeNode(u4Type);
			Attr v4Type = doc.createAttribute("v");
			v4Type.setValue("50");
			e4name.setAttributeNode(v4Type);
			bn.appendChild(e4name);

			// e5 element

			Element e5name = doc.createElement("e");
			Attr n5Type = doc.createAttribute("n");
			n5Type.setValue("temperature");
			e5name.setAttributeNode(n5Type);
			Attr t5Type = doc.createAttribute("t");
			t5Type.setValue("1374069830362");
			e5name.setAttributeNode(t5Type);
			Attr u5Type = doc.createAttribute("u");
			u5Type.setValue("Cel");
			e5name.setAttributeNode(u5Type);
			Attr v5Type = doc.createAttribute("v");
			v5Type.setValue(String
					.valueOf(swotsuite.pubsubmiddleware.mqttSubscriber.tempValue));
			e5name.setAttributeNode(v5Type);
			bn.appendChild(e5name);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"Input/senMLHealthData.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}