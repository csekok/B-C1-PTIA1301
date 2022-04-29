package register;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

// https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

public class XmlReader {
    /**
     * Task 0: Update the project:
     *          - From menu: Git / "Update Project...", OR
     *          - In right upper corner: blue arrow, OR
     *          - Keyboard shortcut: Ctrl+T
     * In "Update the project" popup click on Ok button.
     */

    /**
     * Task 1:
     * Create a readUsersFromXml method to:
     *       - read a given file
     *       - create User objects from data, which is stored in the file
     *       - build a list of created Users
     *       - return with the list of created Users
     * Create a main method.
     * In main method:
     *       - call readUsersFromXml method with "src/main/resources/users.xml"
     *         parameter
     *       - print all data of users in structured way
     * Hint: Use DocumentBuilderFactory.newInstance().newDocumentBuilder().parse()
     *       method to read the XML in structured way.
     * Hint: To print you can override the toString method of User class.
     */

    public static void main(String[] args) {
        ArrayList<User> users = readUsersFromXml("src/main/resources/users.xml");
        System.out.println(users.size());
        System.out.println(users);
        int numberOfOccurrence = numberOfOccurrence("src/main/resources/users.xml", "name");
        System.out.println(numberOfOccurrence);
        numberOfOccurrence = numberOfOccurrence("src/main/resources/users.xml", "nme");
        System.out.println(numberOfOccurrence);
    }

    public static ArrayList<User> readUsersFromXml(String filepath) {
        ArrayList<User> users = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            /*System.out.println(rootElement.getNodeName());
            System.out.println(rootElement.getNodeType());
            System.out.println("Element node short value: " + Node.ELEMENT_NODE);
            System.out.println("Text node short value: " + Node.TEXT_NODE);*/
            //System.out.println(rootElement.getTextContent());
            NodeList childNodesList = rootElement.getChildNodes();
            /*System.out.println(childNodesList.getLength());
            System.out.println("---------------");*/
            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //System.out.println(node.getNodeName());
                    //System.out.println(node.getTextContent());
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", birthYear = "", address = "", eyeColor = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        /*System.out.println(childNodesOfUserTag.item(j).getNodeType()
                                  + " " + childNodesOfUserTag.item(j).getNodeName());*/
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "birthYear" -> birthYear = childNodesOfUserTag.item(j).getTextContent();
                                case "address" -> address = childNodesOfUserTag.item(j).getTextContent();
                                case "eyeColor" -> eyeColor = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    users.add(new User(name, Integer.parseInt(birthYear), address,
                              EyeColor.valueOf(eyeColor)));
                }
            }
            //System.out.println("Number of element nodes: " + numberOfElementNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Task 2:
     * Create a numberOfOccurrence(filepath, tagName) method to:
     *       - read a given file
     *       - return how many times the given tag name occurs in the file
     * Call numberOfOccurrence method with
     *       "src/main/resources/users.xml" and "name" parameters in main method.
     */

    public static int numberOfOccurrence(String filepath, String tagName) {
        int numberOfOccurrence = 0;
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);
            Element rootElement = document.getDocumentElement();
            NodeList tagNameNodeList = rootElement.getElementsByTagName(tagName);
            numberOfOccurrence = tagNameNodeList.getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfOccurrence;
    }

    /**
     * Task 3:
     * Create a getValuesOf(filepath, tagName) method to:
     *       - read a given file
     *       - build a String with values of the given tag
     *       - return with the String
     * Call getValuesOf method with
     *       "src/main/resources/users.xml" and "name" parameters in main method.
     */

    /**
     * Task 4:
     * Create an XML file to store Song objects.
     * Create a readSongsFromXml method to generate a list of Songs of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 5:
     * Create an XML file to store PublicVehicle objects.
     * Create a readPublicVehiclesFromXml method to generate a list of PublicVehicles of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 6:
     * Create an XML file to store Note objects.
     * Create a readNotesFromXml method to generate a list of Notes of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 7:
     * Create an XML file to store Goods objects.
     * Create a readGoodsFromXml method to generate a list of Goods of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 8:
     * Create an XML file to store Fruit objects.
     * Create a readFruitsFromXml method to generate a list of Fruits of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 9:
     * Create an XML file to store Employee objects.
     * Create a readEmployeesFromXml method to generate a list of Employees of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */

    /**
     * Task 10:
     * Create an XML file to store Book objects.
     * Create a readBooksFromXml method to generate a list of Books of an XML file.
     * Note: There is no test to this task because you must create your own XML
     *       structure within this task.
     */
}
