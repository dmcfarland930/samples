/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author apprentice
 */
public class TaxesXmlDao {

    FlooringData fd = new FlooringData();
    List<Taxes> taxList = fd.taxDecode();

    public TaxesXmlDao() {

        create();

    }

    public void create() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = (Element) doc.createElement("taxlist");
            doc.appendChild(rootElement);

            for (Taxes taxOnList : taxList) {

                // tax elements
                Element tax = doc.createElement("statetaxrate");
                rootElement.appendChild(tax);

                // state elements
                Element state = doc.createElement("state");
                state.appendChild(doc.createTextNode(taxOnList.getState()));
                tax.appendChild(state);

                //tax rate elements
                Element taxRate = doc.createElement("taxrate");
                Double taxDub = taxOnList.getTaxRate();
                String taxString = taxDub.toString();
                taxRate.appendChild(doc.createTextNode(taxString));
                tax.appendChild(taxRate);

     
                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("File" + File.separator + "taxesList.xml"));

                // StreamResult result = new StreamResult(System.out);
                transformer.transform(source, result);
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    
    public List readTaxes() {

        List<Taxes> taxes = new ArrayList();

        try {

            File fXmlFile = new File("File" + File.separator + "taxesList.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Node tempNode = doc.getChildNodes().item(0);
            NodeList productsNode = tempNode.getChildNodes();

            for (int count = 0; count < productsNode.getLength(); count++) {

                NodeList nodeMap = productsNode.item(count).getChildNodes();
                Taxes myTax = new Taxes();

                for (int i = 0; i < 3; i++) {

                    Node node = nodeMap.item(i);
                    switch (i) {
                        case 0:
                            myTax.setState(node.getTextContent());
                            break;
                        case 1:
                            double taxRate = Double.parseDouble(node.getTextContent());
                            myTax.setTaxRate(taxRate);
                            break;
                        default:
                            taxes.add(myTax);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxes;

    }
}
