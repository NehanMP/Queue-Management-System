package com.Task2_and_Task3;

import com.Task4.MainApplication;

import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {
    private static int remainingBurgerStock = 50;
    private static FoodQueue customerQueue1;
    private static FoodQueue customerQueue2;
    private static FoodQueue customerQueue3;
    private static boolean javaFXApplicationLaunched = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        new StringBuilder();
        FoodQueue waitingQueue = new FoodQueue();

        customerQueue1 = new FoodQueue();
        customerQueue2 = new FoodQueue();
        customerQueue3 = new FoodQueue();

        System.out.println("------------------------");
        System.out.println("Foodies Fave Food Center");
        System.out.println("------------------------");
        System.out.println();

        // Menu options
        System.out.println("100 or VFQ: View all queues.");
        System.out.println("101 or VEQ: View all empty queues.");
        System.out.println("102 or ACQ: Add customer to a queue.");
        System.out.println("103 or RCQ: Remove a customer from a queue.(Give specific location)");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View customers sorted in alphabetical order.");
        System.out.println("106 or SPD: Store program data into a file.");
        System.out.println("107 or LPD: Load program data from file.");
        System.out.println("108 or STK: View remaining burgers stock.");
        System.out.println("109 or AFS: Add burgers to Stock.");
        System.out.println("110 or IFQ: View Income of each queue.");
        System.out.println("112 or GUI: Get GUI version of the program.");
        System.out.println("999 or EXT: Exit the Program.");

        while (true) {
            try {
                System.out.print("Enter your menu option: "); // Getting user menu option
                String option = scanner.nextLine();

                if (option.equals("999") || option.equalsIgnoreCase("EXT")) { // option to exit the program
                    System.out.println("Thank you for dining at Foodies Fave Food Center");
                    System.exit(0);
                }

                if (option.equals("100") || option.equalsIgnoreCase("VFQ")) { // option to view all queues
                    System.out.println("****************************");
                    System.out.println("*         Cashiers         *");
                    System.out.println("****************************");
                    System.out.println("Queue 1   Queue 2   Queue 3");

                    String q1Occupency, q2Occupency, q3Occupency;

                    for (int i = 0; i < 5; i++){
                        q1Occupency = "";
                        q2Occupency = "";
                        if(i < 2){
                            q1Occupency = customerQueue1.getOccupancy(i);
                        }
                        if(i < 3){
                            q2Occupency = customerQueue2.getOccupancy(i);
                        }
                        q3Occupency = customerQueue3.getOccupancy(i);
                        System.out.printf("%4s %9s %9s %n", q1Occupency, q2Occupency, q3Occupency);
                    }
                }

                if (option.equals("101") || option.equalsIgnoreCase("VEQ")) { // option to view all empty queues
                    System.out.println("****************************");
                    System.out.println("*      Empty Cashiers      *");
                    System.out.println("****************************");

                    if (customerQueue1.isQueueEmpty()) {
                        output.append("Queue1    ");
                    }
                    if (customerQueue2.isQueueEmpty()) {
                        output.append("Queue2    ");
                    }
                    if (customerQueue3.isQueueEmpty()) {
                        output.append("Queue3    ");
                    }
                    System.out.println(output);
                }

                if (option.equals("102") || option.equalsIgnoreCase("ACQ")) { // option to add customers to the queue
                    System.out.print("Enter customer's first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter customer's last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter number of burgers customer ordered: ");
                    int burgersRequired = scanner.nextInt();
                    scanner.nextLine();

                    // Add customers to the queue with the lowest queue size
                    if (customerQueue1.getQueueSize() < 2) {
                        if (customerQueue1.addCustomer(firstName, lastName, burgersRequired, remainingBurgerStock)) {
                            remainingBurgerStock -= burgersRequired;
                            System.out.println("Customer has been added to the queue successfully.");
                            // Check if remaining burger stock is less than 10
                            if (remainingBurgerStock <= 10 && remainingBurgerStock >= 0) {
                                System.out.println("WARNING: Low burger stock! Remaining burger count is: " + remainingBurgerStock);
                            }
                        }

                    } else if (customerQueue2.getQueueSize() < 3) {
                        if (customerQueue2.addCustomer(firstName, lastName, burgersRequired, remainingBurgerStock)) {
                            remainingBurgerStock -= burgersRequired;
                            System.out.println("Customer has been added to the queue successfully.");
                            // Check if remaining burger stock is less than 10
                            if (remainingBurgerStock <= 10 && remainingBurgerStock >= 0) {
                                System.out.println("WARNING: Low burger stock! Remaining burger count is: " + remainingBurgerStock);
                            }
                        }

                    } else if (customerQueue3.getQueueSize() < 5) {
                        if (customerQueue3.addCustomer(firstName, lastName, burgersRequired, remainingBurgerStock)) {
                            remainingBurgerStock -= burgersRequired;
                            System.out.println("Customer has been added to the queue successfully.");
                            // Check if remaining burger stock is less than 10
                            if (remainingBurgerStock <= 10 && remainingBurgerStock >= 0) {
                                System.out.println("WARNING: Low burger stock! Remaining burger count is: " + remainingBurgerStock);
                            }
                        }
                    } else {
                        System.out.println("All queues are currently full. Customer will be added to a waiting queue.");
                        waitingQueue.addCustomer(firstName, lastName, burgersRequired, remainingBurgerStock);
                        if (remainingBurgerStock <= 10 && remainingBurgerStock >= 0) {
                            System.out.println("WARNING: Low burger stock! Remaining burger count is: " + remainingBurgerStock);
                        }
                    }
                }

                if (option.equals("103") || option.equalsIgnoreCase("RCQ")) { // option to remove a customer from a specific location
                    System.out.print("Enter the queue number from which the customer should be removed (1, 2, or 3): ");
                    int queueNumber = scanner.nextInt();
                    scanner.nextLine();

                    // Assigning the user given queueNumber to the selectedQueue variable
                    if (queueNumber >= 1 && queueNumber <= 3) {
                        FoodQueue selectedQueue = switch (queueNumber) {
                            case 1 -> customerQueue1;
                            case 2 -> customerQueue2;
                            case 3 -> customerQueue3;
                            default -> null;
                        };

                        System.out.print("Enter the position of the customer to remove from nearest to the counter: ");
                        int position = scanner.nextInt();
                        scanner.nextLine();

                        // Removing customer from the position of the selectedQueue
                        if (position >= 1 && position <= selectedQueue.getQueueSize()) {
                            Customer removedCustomer = selectedQueue.removeSpecificCustomer(position - 1);
                            remainingBurgerStock += removedCustomer.burgersRequired();
                            System.out.println(removedCustomer.burgersRequired() + " burgers were added back to the stock.");

                            // Adding a customer to the removed queue from the waiting queue if there are customer's in it
                            if (!waitingQueue.isQueueEmpty()) {
                                // Remove the first customer in the waiting queue
                                Customer waitingCustomer = waitingQueue.removeFirstCustomer();
                                if (selectedQueue.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock)) {
                                    remainingBurgerStock -= waitingCustomer.burgersRequired();
                                    System.out.println("Customer from the waiting queue is added to the " + queueNumber + ".");
                                } else {
                                    System.out.println("Queue " + queueNumber + " is currently full. Customers from the waiting queue can't be added.");
                                    waitingQueue.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock);
                                    System.out.println("Customer added back to the waiting queue.");
                                }
                            } else {
                                System.out.println(" ");
                            }
                        } else {
                            System.out.println("Invalid position. Please enter a valid position.");
                        }
                    } else {
                        System.out.println("Invalid queue number. Please enter a valid queue number.");
                    }
                }

                if (option.equals("104") || option.equalsIgnoreCase("PCQ")) { // option to remove a served customer
                    Customer removedCustomer;
                    System.out.print("Enter the queue number from which the customer should be removed (1, 2, or 3): ");
                    int queueNumber = scanner.nextInt();
                    scanner.nextLine();

                    if (queueNumber == 1) {
                        removedCustomer = customerQueue1.removeFirstCustomer();
                        if (removedCustomer != null) {
                            System.out.println("Customer removed from Queue 1: " + removedCustomer.firstName() + " " + removedCustomer.lastName());

                        } else {
                            System.out.println("Queue 3 is empty. No customers to remove.");
                        }

                    } else if (queueNumber == 2) {
                        removedCustomer = customerQueue2.removeFirstCustomer();
                        if (removedCustomer != null) {
                            System.out.println("Customer removed from Queue 2: " + removedCustomer.firstName() + " " + removedCustomer.lastName());

                        } else {
                            System.out.println("Queue 2 is empty. No customers to remove.");
                        }

                    } else if (queueNumber == 3) {
                        removedCustomer = customerQueue3.removeFirstCustomer();
                        if (removedCustomer != null) {
                            System.out.println("Customer removed from Queue 3: " + removedCustomer.firstName() + " " + removedCustomer.lastName());
                        } else {
                            System.out.println("Queue 3 is empty. No customers to remove.");
                        }
                    } else {
                        System.out.println("Invalid queue number. Please enter a valid queue number (1, 2, or 3).");
                    }

                    // Adding customer to the removed queue from the waiting queue.
                    if (!waitingQueue.isQueueEmpty()) {
                        Customer waitingCustomer = waitingQueue.removeFirstCustomer();
                        if (queueNumber == 1 && customerQueue1.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock)) {
                            remainingBurgerStock -= waitingCustomer.burgersRequired();
                            System.out.println("Customer from the waiting queue is added to Queue 1.");

                        } else if (queueNumber == 2 && customerQueue2.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock)) {
                            remainingBurgerStock -= waitingCustomer.burgersRequired();
                            System.out.println("Customer from the waiting queue is added to Queue 2.");

                        } else if (queueNumber == 3 && customerQueue3.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock)) {
                            remainingBurgerStock -= waitingCustomer.burgersRequired();
                            System.out.println("Customer from the waiting queue is added to Queue 3.");
                        } else {
                            System.out.println("Queue " + queueNumber + " is currently full. Customers from the waiting queue can't be added.");
                            waitingQueue.addCustomer(waitingCustomer.firstName(), waitingCustomer.lastName(), waitingCustomer.burgersRequired(), remainingBurgerStock);
                            System.out.println("Customer added back to the waiting queue.");
                        }
                        System.out.println(" ");
                    }
                    System.out.println(" ");
                }

                if (option.equals("105") || option.equalsIgnoreCase("VCS")) { // option to view customers details
                    System.out.println("Customer Details:");
                    System.out.println(" ");

                    // Sort customers in queue1 based on first name
                    ArrayList<Customer> queue1Customers = customerQueue1.getCustomers();
                    queue1Customers.sort((c1, c2) -> c1.firstName().compareToIgnoreCase(c2.firstName()));

                    // Display customer details for Queue 1
                    System.out.println("Queue 1:");
                    System.out.println("--------------------");
                    for (Customer customer : queue1Customers) {
                        System.out.println("Name: " + customer.firstName() + " " + customer.lastName());
                        System.out.println("Number of Burgers: " + customer.burgersRequired());
                        System.out.println("--------------------");
                    }

                    // Sort customers in queue2 based on first name
                    ArrayList<Customer> queue2Customers = customerQueue2.getCustomers();
                    queue2Customers.sort((c1, c2) -> c1.firstName().compareToIgnoreCase(c2.firstName()));

                    // Display customer details for Queue 2
                    System.out.println("Queue 2:");
                    System.out.println("--------------------");
                    for (Customer customer : queue2Customers) {
                        System.out.println("Name: " + customer.firstName() + " " + customer.lastName());
                        System.out.println("Number of Burgers: " + customer.burgersRequired());
                        System.out.println("--------------------");
                    }

                    // Sort customers in queue3 based on first name
                    ArrayList<Customer> queue3Customers = customerQueue3.getCustomers();
                    queue3Customers.sort((c1, c2) -> c1.firstName().compareToIgnoreCase(c2.firstName()));

                    // Display customer details for Queue 3
                    System.out.println("Queue 3:");
                    System.out.println("--------------------");
                    for (Customer customer : queue3Customers) {
                        System.out.println("Name: " + customer.firstName() + " " + customer.lastName());
                        System.out.println("Number of Burgers: " + customer.burgersRequired());
                        System.out.println("--------------------");
                    }
                }

                if (option.equals("106") || option.equalsIgnoreCase("SPD")) { // option to store the program data into a file
                    System.out.print("Enter the file name to save program data: ");
                    String fileName = scanner.nextLine();
                    storeProgramData(fileName);
                    System.out.println("Program data stored in the file successfully.");
                }

                if (option.equals("107") || option.equalsIgnoreCase("LPD")) { // option to load previous program data from a file
                    System.out.print("Enter the file name to load program data: ");
                    String fileName = scanner.nextLine();
                    loadProgramData(fileName);
                    System.out.println("Program data loaded from the file successfully.");
                }

                if (option.equals("108") || option.equalsIgnoreCase("STK")) { // option to view the remaining burger stock
                    System.out.println("Remaining Burgers in Stock: " + remainingBurgerStock);
                }

                if (option.equals("109") || option.equalsIgnoreCase("AFS")) { // option to add burgers to the existing burger stock
                    System.out.print("Enter the number of burgers to add: ");
                    int burgersToAdd = scanner.nextInt();
                    scanner.nextLine();
                    remainingBurgerStock += burgersToAdd;
                    System.out.println(burgersToAdd + " burgers added to the stock. Remaining burger stock is: " + remainingBurgerStock);
                }

                if (option.equals("110") || option.equalsIgnoreCase("IFQ")) { // option to view the total cost of burgers in each queue
                    System.out.println("Total Cost of each Queue:");
                    System.out.println();

                    // Calculate the total cost of Queue 1
                    int queue1Total = 0;
                    for (Customer customer : customerQueue1.getCustomers()) {
                        queue1Total += customer.burgersRequired() * 650;
                    }
                    System.out.println("Queue 1 Total Cost: Rs." + queue1Total);

                    // Calculate the total cost of Queue 2
                    int queue2Total = 0;
                    for (Customer customer : customerQueue2.getCustomers()) {
                        queue2Total += customer.burgersRequired() * 650;
                    }
                    System.out.println("Queue 2 Total Cost: Rs." + queue2Total);

                    // Calculate the total cost of Queue 3
                    int queue3Total = 0;
                    for (Customer customer : customerQueue3.getCustomers()) {
                        queue3Total += customer.burgersRequired() * 650;
                    }
                    System.out.println("Queue 3 Total Cost: Rs." + queue3Total);
                }

                if (option.equals("112") || option.equalsIgnoreCase("GUI")) {
                    // Option to launch JavaFX Application (HelloApplication)
                    if (!javaFXApplicationLaunched) {
                        launchJavaFXApplication();
                        javaFXApplicationLaunched = true;
                    } else {
                        System.out.println("Can't call the GUI twice. Re-run the program if GUI is needed.");
                    }
                }

            } catch (InputMismatchException e) { // If an invalid input is entered, program asks to enter a new option
                System.out.println("Invalid input. Please enter a valid menu option.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
    }

    /**
     * Method used to run the GUI
     */
    private static void launchJavaFXApplication() {
        new Thread(() -> MainApplication.launch(MainApplication.class)).start();
    }

    /**
     * Store program data into a file.
     * Remaining burger stock and Customer queue details.
     * @param fileName The name of the file to store the program data.
     */
    private static void storeProgramData(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // Save Program data
            writer.write(Integer.toString(remainingBurgerStock));
            writer.newLine();
            storeQueueData(writer, customerQueue1);
            storeQueueData(writer, customerQueue2);
            storeQueueData(writer, customerQueue3);

        } catch (IOException e) {
            System.out.println("Error occurred while storing program data: " + e.getMessage());
        }
    }

    /**
     * A specific customer queue details be stored to a file.
     * @param writer The BufferedWriter to write data to the file.
     * @param queue The customer queue from which data should be stored.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void storeQueueData(BufferedWriter writer, FoodQueue queue) throws IOException {
        writer.write(Integer.toString(queue.getQueueSize()));
        writer.newLine();

        for (Customer customer : queue.getCustomers()) {
            writer.write(customer.firstName());
            writer.newLine();
            writer.write(customer.lastName());
            writer.newLine();
            writer.write(Integer.toString(customer.burgersRequired()));
            writer.newLine();
        }
    }

    /**
     * Load program data from a file
     * Remaining burger stock and Customer queue details
     * @param fileName The name of the file to load the program data.
     */
    private static void loadProgramData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            // Load program data
            remainingBurgerStock = Integer.parseInt(reader.readLine());
            customerQueue1 = loadQueueData(reader);
            customerQueue2 = loadQueueData(reader);
            customerQueue3 = loadQueueData(reader);
        } catch (IOException e) {
            System.out.println("Error occurred while loading program data: " + e.getMessage());
        }
    }

    /**
     * A specific customer queue details will be loaded from a file.
     * @param reader The BufferedReader to read data from the file.
     * @return FoodQueue object with customer data.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    private static FoodQueue loadQueueData(BufferedReader reader) throws IOException {
        int queueSize = Integer.parseInt(reader.readLine());
        FoodQueue queue = new FoodQueue();

        for (int i = 0; i < queueSize; i++) {
            String firstName = reader.readLine();
            String lastName = reader.readLine();
            int burgersRequired = Integer.parseInt(reader.readLine());
            queue.addCustomer(firstName, lastName, burgersRequired, remainingBurgerStock);
        }
        return queue;
    }
}
