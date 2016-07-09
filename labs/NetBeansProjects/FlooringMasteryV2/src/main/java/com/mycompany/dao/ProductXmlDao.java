/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Product;
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
public class ProductXmlDao {

    FlooringData fd = new FlooringData();
    List<Product> productList = fd.productDecode();

    public ProductXmlDao() {

        create();

    }

    public void create() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = (Element) doc.createElement("productList");
            doc.appendChild(rootElement);

            for (Product productOnList : productList) {

                // product elements
                Element product = doc.createElement("product");
                rootElement.appendChild(product);

                // productType elements
                Element productType = doc.createElement("producttype");
                productType.appendChild(doc.createTextNode(productOnList.getProductType()));
                product.appendChild(productType);

                // cost/sqft elements
                Element costSqFt = doc.createElement("costpersqft");
                Double costDub = productOnList.getCostPerSqFt();
                String costString = costDub.toString();
                costSqFt.appendChild(doc.createTextNode(costString));
                product.appendChild(costSqFt);

                // labor elements
                Element laborCost = doc.createElement("laborcost");
                Double laborDub = productOnList.getLaborCostPerSqFt();
                String laborString = laborDub.toString();
                laborCost.appendChild(doc.createTextNode(laborString));
                product.appendChild(laborCost);

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("File" + File.separator + "productList.xml"));

                // StreamResult result = new StreamResult(System.out);
                transformer.transform(source, result);
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public List readProducts() {

        List<Product> products = new ArrayList();

        try {

            File fXmlFile = new File("File" + File.separator + "productList.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Node tempNode = doc.getChildNodes().item(0);
            NodeList productsNode = tempNode.getChildNodes();

            for (int count = 0; count < productsNode.getLength(); count++) {

                NodeList nodeMap = productsNode.item(count).getChildNodes();
                Product myProduct = new Product();

                for (int i = 0; i < 4; i++) {

                    Node node = nodeMap.item(i);
                    switch (i) {
                        case 0:
                            myProduct.setProductType(node.getTextContent());
                            break;
                        case 1:
                            double costSqFt = Double.parseDouble(node.getTextContent());
                            myProduct.setCostPerSqFt(costSqFt);
                            break;
                        case 2:
                            double laborCost = Double.parseDouble(node.getTextContent());
                            myProduct.setLaborCostPerSqFt(laborCost);
                            break;
                        default:
                            products.add(myProduct);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;

    }
}
