package register;

// https://mkyong.com/java/how-to-create-xml-file-in-java-dom/

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XmlWriter {
    /**
     * Task 0: Update the project:
     *          - From menu: Git / "Update Project...", OR
     *          - In right upper corner: blue arrow, OR
     *          - Keyboard shortcut: Ctrl+T
     * In "Update the project" popup click on Ok button.
     */

    /**
     * Task 1:
     * Create a saveUsersToXml(users, filepath) method to store
     * the list of User objects in a given XML file.
     * Create a main method.
     * In main method:
     *       - create a list of User objects
     *       - call saveUsersToXml method with the list and
     *              "src/main/resources/users.xml" as parameters
     * Hint: Use the readUsersFromXml method from previous class to
     *       create list of User objects.
     * Hint: Use TransformerFactory.newInstance().newTransformer()
     *       method to write XML files.
     * Hint: Create method to add elements to the hierarchy.
     */

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<User> users = XmlReader.readUsersFromXml("src/main/resources/users.xml");

        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listUsers(users);
                case 2 -> addNewUser(users);
                case 3 -> modifyUser(users);
                case 4 -> deleteUser(users);
            }
            System.out.println("1 - List users\r\n2 - Add new user\r\n"
                    + "3 - Modify a user\r\n4 - Delete a user\r\n0 - Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        saveUsersToXml(users, "src/main/resources/users.xml");
    }

    private static void modifyUser(ArrayList<User> users) {
        System.out.print("Enter name of user what you want to modify: ");
        String name = scanner.nextLine();
        for (User user : users) {
            if (user.getName().equals(name)) {
                int birthYear = inputBirthYear();
                System.out.print("Enter address of new user: ");
                String address = scanner.nextLine();
                EyeColor eyeColor = inputEyeColor();
                users.set(users.indexOf(user), new User(name, birthYear, address, eyeColor));
                System.out.println("User is successfully modified.");
                return;
            }
        }
        System.out.println("There is no user with this name.");
    }

    private static void deleteUser(ArrayList<User> users) {
        System.out.print("Enter name of user what you want to delete: ");
        String name = scanner.nextLine();
        for (User user : users) {
            if (user.getName().equals(name)) {
                users.remove(user);
                System.out.println("User is successfully deleted.");
                return;
            }
        }
        System.out.println("There is no user with this name.");
    }

    private static void addNewUser(ArrayList<User> users) {
        System.out.print("Enter name of new user: ");
        String name = scanner.nextLine();
        int birthYear = inputBirthYear();
        System.out.print("Enter address of new user: ");
        String address = scanner.nextLine();
        EyeColor eyeColor = inputEyeColor();
        users.add(new User(name, birthYear, address, eyeColor));
    }

    private static int inputBirthYear() {
        int birthYear = 0;
        while (1900 > birthYear || 2022 < birthYear) {
            try {
                System.out.print("Enter birth year of new user: ");
                birthYear = scanner.nextInt();
                scanner.nextLine();
                if (birthYear < 1900 || birthYear > 2022) {
                    System.out.println("Birth year cannot be less than" +
                            " 1900 or greater 2022.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid birth year.");
                scanner.nextLine();
            }
        }
        return birthYear;
    }

    private static EyeColor inputEyeColor() {
        EyeColor eyeColor = EyeColor.BROWN;
        String eyeColorString = "";
        while (eyeColorString.isEmpty()) {
            try {
                System.out.print("Enter eye color of new user: ");
                eyeColorString = scanner.nextLine();
                eyeColor = EyeColor.valueOf(eyeColorString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Not valid eye color.");
                eyeColorString = "";
            }
        }
        return eyeColor;
    }

    private static void listUsers(ArrayList<User> users) {
        System.out.println(users);
    }

    public static void saveUsersToXml(ArrayList<User> users, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("users");
            document.appendChild(rootElement);

            for (User user : users) {
                Element userElement = document.createElement("user");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "name", user.getName());
                createChildElement(document, userElement, "birthYear",
                                             String.valueOf(user.getBirthYear()));
                createChildElement(document, userElement, "address", user.getAddress());
                createChildElement(document, userElement, "eyeColor", user.getEyeColor().toString());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    /**
     * Task 2:
     * Create a console application in main method. When the application
     * starts read data of users from an XML file and build up a list of
     * User objects. Create a console menu with (1) list users and (0)
     * exit options. When your user enters 1, your application should
     * print all data of users. When your user enters 0, your application
     * should terminate.
     * Hint: Use the readUsersFromXml method from previous class to
     *       create list of User objects.
     */

    /**
     * Task 3:
     * Add new option to your applications: (2) add new user.
     * When your user chooses this option, your application
     * should ask and validate all data of a new user one by
     * one. Before your application terminates, you should
     * update the list of users in the XML file.
     * Hint: Use the saveUsersToXml method from Task 1.
     */

    /**
     * Task 4:
     * Add new option to your applications: (3) modify user.
     * When your user chooses this option, your application
     * should:
     *      - define the user with her/his name
     *      - ask and validate all data of the user one by one
     * Hint: Define an id attribute to User objects, so you can
     *       also support name modifications. Do not forget to
     *       add is to listing and XML file so your user will
     *       know it.
     */

    /**
     * Task 5:
     * Add new option to your applications: (4) delete user.
     * When your user chooses this option, your application
     * should define the user with her/his name and remove it.
     */

    /**
     * Homework:
     * Create similar register applications to Song, PublicVehicle,
     * Note, Goods, Fruit, Employee and Book objects.
     * Note: There is no test to this task.
     */
}
