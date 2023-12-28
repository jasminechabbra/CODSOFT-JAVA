import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    // Constructor
    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // Setter methods
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    // Method to add a contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    // Method to remove a contact
    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
        System.out.println("Contact removed successfully.");
    }

    // Method to search for a contact
    public void searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                System.out.println("Contact found: " + contact);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    // Method to display all contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts in the address book.");
        } else {
            System.out.println("List of Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    // Method to save contact data to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(contacts);
            System.out.println("Data saved to " + fileName + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load contact data from a file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (ArrayList<Contact>) inputStream.readObject();
            System.out.println("Data loaded from " + fileName + " successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search for Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");

            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(scanner, addressBook);
                    break;
                case 2:
                    removeContact(scanner, addressBook);
                    break;
                case 3:
                    searchContact(scanner, addressBook);
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    saveToFile(scanner, addressBook);
                    break;
                case 6:
                    loadFromFile(scanner, addressBook);
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void addContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter contact name: ");
        String name = scanner.next();

        // Input validation for phone number
        String phoneNumber;
        while (true) {
            System.out.print("Enter contact phone number: ");
            phoneNumber = scanner.next();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number format. Please try again.");
            }
        }

        // Input validation for email address
        String emailAddress;
        while (true) {
            System.out.print("Enter contact email address: ");
            emailAddress = scanner.next();
            if (isValidEmailAddress(emailAddress)) {
                break;
            } else {
                System.out.println("Invalid email address format. Please try again.");
            }
        }

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);
    }

    private static void removeContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the name of the contact to remove: ");
        String name = scanner.next();
        addressBook.removeContact(name);
    }

    private static void searchContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the name of the contact to search: ");
        String name = scanner.next();
        addressBook.searchContact(name);
    }

    private static void saveToFile(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the file name to save data: ");
        String fileName = scanner.next();
        addressBook.saveToFile(fileName);
    }

    private static void loadFromFile(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the file name to load data: ");
        String fileName = scanner.next();
        addressBook.loadFromFile(fileName);
    }

    // Basic validation for phone number (you might want to improve this)
    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    // Basic validation for email address (you might want to improve this)
    private static boolean isValidEmailAddress(String emailAddress) {
        return emailAddress.matches("\\S+@\\S+\\.\\S+");
    }
}


