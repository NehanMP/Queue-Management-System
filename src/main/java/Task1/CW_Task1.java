package Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public class CW_Task1 {
    private static final int Queue1_Size = 2; // Assigning number of elements in the customer queue arrays
    private static final int Queue2_Size = 3;
    private static final int Queue3_Size = 5;

    private static final int initialStock = 50; // Initial stock value of burgers
    private static int remainingBurgers = initialStock;

    private static  String[] customerName1 = new String[Queue1_Size]; // Initializing customer names arrays
    private static  String[] customerName2 = new String[Queue2_Size];
    private static  String[] customerName3 = new String[Queue3_Size];

    private static  String[] Queue1 = new String[Queue1_Size]; // Initializing cashier queue arrays
    private static  String[] Queue2 = new String[Queue2_Size];
    private static  String[] Queue3 = new String[Queue3_Size];

    public static void main(String[] args) {
        Arrays.fill(Queue1, "X"); // Assigning "X" to the arrays based on the number of elements
        Arrays.fill(Queue2, "X");
        Arrays.fill(Queue3, "X");

        Arrays.fill(customerName1, ""); // Initialize customerName arrays with empty strings
        Arrays.fill(customerName2, "");
        Arrays.fill(customerName3, "");

        Scanner scanner = new Scanner(System.in);

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
        System.out.println("109 or AFS: Add burgers to Stock.  ");
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
                    printCashierQueues();
                    System.out.println("X-Not Occupied | O-Occupied ");
                }

                if (option.equals("101") || option.equalsIgnoreCase("VEQ")) { // option to view all empty queues
                    System.out.println("****************************");
                    System.out.println("*      Empty Cashiers      *");
                    System.out.println("****************************");

                    if (isQueueEmpty(Queue1)) {
                        System.out.print("Queue 1    ");
                    }
                    if (isQueueEmpty(Queue2)) {
                        System.out.print("Queue 2");
                    }
                    if (isQueueEmpty(Queue3)) {
                        System.out.print("    Queue 3");
                    }
                    System.out.println();
                }

                if (option.equals("102") || option.equalsIgnoreCase("ACQ")) { // option to dd customers to the queue
                    System.out.print("Enter the queue number to which the customer should be added (1, 2, or 3): ");
                    int queueNumber = scanner.nextInt();
                    scanner.nextLine();

                    switch (queueNumber) {
                        case 1 -> {
                            if (removeBurgersFromStock()) {
                                System.out.print("Enter the customer's name: ");
                                String customerName = scanner.nextLine();
                                addCustomerToQueue(Queue1, customerName);
                            } else {
                                System.out.println("Insufficient burgers in stock. Cannot add customer to the queue.");
                            }
                        }
                        case 2 -> {
                            if (removeBurgersFromStock()) {
                                System.out.print("Enter the customer's name: ");
                                String customerName = scanner.nextLine();
                                addCustomerToQueue(Queue2, customerName);
                            } else {
                                System.out.println("Insufficient burgers in stock. Cannot add customer to the queue.");
                            }
                        }
                        case 3 -> {
                            if (removeBurgersFromStock()) {
                                System.out.print("Enter the customer's name: ");
                                String customerName = scanner.nextLine();
                                addCustomerToQueue(Queue3, customerName);
                            } else {
                                System.out.println("Insufficient burgers in stock. Cannot add customer to the queue.");
                            }
                        }
                    }
                }

                if (option.equals("103") || option.equalsIgnoreCase("RCQ")) { // option to remove a customer from a specific location
                    System.out.print("Enter the queue number from which the customer should be removed (1, 2, or 3): ");
                    int queueNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the row number from nearest to the cashier of the customer that needs to be removed: ");
                    int rowNumber = scanner.nextInt();
                    scanner.nextLine();

                    switch (queueNumber) {
                        case 1 -> {
                            if (rowNumber >= 1 && rowNumber <= 2) {
                                queueRow(Queue1, rowNumber - 1);
                            } else {
                                System.out.println("Invalid row number. Row number should be between 1 and 2 for Queue 1.");
                            }
                        }
                        case 2 -> {
                            if (rowNumber >= 1 && rowNumber <= 3) {
                                queueRow(Queue2, rowNumber - 1);
                            } else {
                                System.out.println("Invalid row number. Row number should be between 1 and 3 for Queue 2.");
                            }
                        }
                        case 3 -> {
                            if (rowNumber >= 1 && rowNumber <= 5) {
                                queueRow(Queue3, rowNumber - 1);
                            } else {
                                System.out.println("Invalid row number. Row number should be between 1 and 5 for Queue 3.");
                            }
                        }
                        default -> System.out.println("Invalid queue number.");
                    }
                }

                if (option.equals("104") || option.equalsIgnoreCase("PCQ")) { // option to remove a served customer
                    System.out.print("Enter the queue number from which the customer should be removed (1, 2, or 3): ");
                    int queueNumber = scanner.nextInt();
                    scanner.nextLine();

                    switch (queueNumber) {
                        case 1 -> removeCustomerFromQueue(Queue1);
                        case 2 -> removeCustomerFromQueue(Queue2);
                        case 3 -> removeCustomerFromQueue(Queue3);
                        default -> System.out.println("Invalid queue number.");
                    }
                }

                if (option.equals("105") || option.equalsIgnoreCase("VCS")) { // option to view customer is queue sorted in alphabetical order
                    System.out.println("*************************");
                    System.out.println("*  Customers Sorted in  *");
                    System.out.println("*  Alphabetical  Order  *");
                    System.out.println("*************************");

                    displayCustomerQueue(bubbleSort(customerName1), 1);
                    displayCustomerQueue(bubbleSort(customerName2), 2);
                    displayCustomerQueue(bubbleSort(customerName3), 3);
                }

                if (option.equals("106") || option.equalsIgnoreCase("SPD")) {
                    System.out.print("Enter the filename to store the program data: ");
                    String fileName = scanner.nextLine();
                    storeProgramDataToFile(fileName);
                }

                if (option.equals("107") || option.equalsIgnoreCase("LPD")) {
                    System.out.print("Enter the filename to load the program data from: ");
                    String fileName = scanner.nextLine();
                    loadProgramDataFromFile(fileName);
                }

                if (option.equals("108") || option.equalsIgnoreCase("STK")) { // option to view remaining burgers in stock
                    System.out.println("Remaining burgers in stock: " + remainingBurgers);
                }

                if (option.equals("109") || option.equalsIgnoreCase("AFS")) { // option to add burgers to the existing stock
                    System.out.print("Enter the number of burgers to add to the stock: ");
                    int addBurgers = scanner.nextInt();
                    scanner.nextLine();
                    addBurgersToStock(addBurgers);
                }
            } catch (InputMismatchException e) { // If an invalid input is entered program asks to enter a new option
                System.out.println("Invalid input. Please enter a new menu option.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
    }

    /**
     * Deducts 5 burgers from the stock when a customer is added
     * Give a warning message if burgers in stock is less than 10
     * Returns true if burgers can be deducted, false if can't
     */
    private static boolean removeBurgersFromStock() {
        if (remainingBurgers >= 5) {
            remainingBurgers -= 5;
            if (remainingBurgers <= 10) {
                System.out.println("Warning! Remaining burgers in stock is: " + remainingBurgers);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds customer to a user-given queue
     * If a queue is full it shows a message saying can't add more
     * The queues are represented as an array of strings
     */
    private static void addCustomerToQueue(String[] queue, String customerName) {
        boolean added = false;

        for (int i = 0; i < queue.length; i++) {
            if (queue[i].equals("X")) {
                queue[i] = "O";
                added = true;
                break;
            }
        }

        if (added) {
            System.out.println("Customer successfully added to the queue.");

            // Add the customer's name to the respective customerName array
            if (queue == Queue1) {
                addCustomerName(customerName1, customerName);
            } else if (queue == Queue2) {
                addCustomerName(customerName2, customerName);
            } else if (queue == Queue3) {
                addCustomerName(customerName3, customerName);
            }
        } else {
            System.out.println("Sorry, the queue is full. Can't add more customers.");
        }
    }

    /**
     * Prints the cashier queues
     * Displays the customers in each queue, occupied and non-occupied slots
     */
    private static void printCashierQueues() {
        for (int x = 0; x < Queue3_Size; x++) {
            if (x < Queue1_Size) {
                System.out.printf("%7s", Queue1[x]);
            } else {
                System.out.printf("%7s", "");
            }
            if (x < Queue2_Size) {
                System.out.printf("%7s", Queue2[x]);
            } else {
                System.out.printf("%7s", "");
            }
            System.out.printf("%7s %n", Queue3[x]);
        }
        System.out.println();
    }

    /**
     * Removes a customer from a specific location
     * The function searches for the first occupied slot in the queue and marks it with an "X"
     * After removing the customer it swaps the customers behind forward
     * A success message is given if successfully removed
     * When queue is empty it will display a message saying no customers to remove
     */
    private static void removeCustomerFromQueue(String[] queue) {
        if (queue[0].equals("X")) {
            System.out.println("The queue is empty. No customers to remove.");
            return;
        }
        // Shift the customers forward when a customer is removed from the front
        for (int i = 1; i < queue.length; i++) {
            if (!queue[i].equals("X")) {
                queue[i - 1] = queue[i];
                queue[i] = "X";
            }
        }
        queue[queue.length - 1] = "X";
        System.out.println("Customer successfully removed from the queue.");
    }

    /**
     * Adds burgers to the existing burger stock
     * Display the no. of burgers added and the new stock value
     */
    private static void addBurgersToStock(int amount) {
        remainingBurgers += amount;
        System.out.println(amount + " burgers added to the stock. Remaining burgers: " + remainingBurgers);
    }

    /**
     * Removes a customer from a specific location in a queue
     * @param queue queue which the customer is removed
     * @param rowNumber position of the queue which the customer is removed
     */
    private static void queueRow(String[] queue, int rowNumber) {
        if (rowNumber >= 0 && rowNumber < queue.length) {
            if (queue[rowNumber].equals("O")) {
                queue[rowNumber] = "X";
                System.out.println("Customer successfully removed from the queue.");

                // Shift the customers forward when a customer is removed from the front
                for (int i = rowNumber + 1; i < queue.length; i++) {
                    if (!queue[i].equals("X")) {
                        queue[i - 1] = queue[i];
                        queue[i] = "X";
                    }
                }
            } else {
                System.out.println("The entered row is empty. No customer to be removed.");
            }
        } else {
            System.out.println("Invalid row number.");
        }
    }

    /**
     * Checks the queues which are empty
     * @param queue Queues to be checked
     * @return true if queue is empty false if even one slot of the queue is occupied
     */
    private static boolean isQueueEmpty(String[] queue) {
        for (String element : queue) {
            if (!element.equals("X")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds the name of a customer to the customerNameArray
     * @param customerNameArray The customer names array
     * @param customerName name of the customer
     */
    private static void addCustomerName(String[] customerNameArray, String customerName) {
        for (int i = 0; i < customerNameArray.length; i++) {
            if (customerNameArray[i].isEmpty()) {
                customerNameArray[i] = customerName;
                break;
            }
        }
    }

    /**
     * Display the names of the customers with their respective queue
     * @param customerNameArray Customer names array
     * @param queueNumber queue number to be printed when displayed
     */
    private static void displayCustomerQueue(String[] customerNameArray, int queueNumber) {
        System.out.print("Customer Names of Queue " + queueNumber + ": ");
        StringJoiner joiner = new StringJoiner(", ");
        for (String customerName : customerNameArray) {
            if (!customerName.isEmpty()) {
                joiner.add(customerName);
            }
        }
        System.out.println(joiner);
    }

    /**
     * Sorts the array using the bubble sort method
     * @param array The string array to be sorted
     * @return Sorted array of string in ascending order
     */
    private static String[] bubbleSort(String[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareStrings(array[j], array[j + 1]) > 0) {
                    // Swap array[j] and array[j + 1]
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * Compares two strings character by character
     * @param a first string
     * @param b second string
     * @return integer value of the comparison result
     */
    private static int compareStrings(String a, String b) {
        int minLength = Math.min(a.length(), b.length());

        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.charAt(i) - b.charAt(i);
            }
        }
        return a.length() - b.length();
    }
    private static void storeProgramDataToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(remainingBurgers + "\n");
            writer.write(String.join(",", Queue1) + "\n");
            writer.write(String.join(",", Queue2) + "\n");
            writer.write(String.join(",", Queue3) + "\n");
            writer.write(String.join(",", customerName1) + "\n");
            writer.write(String.join(",", customerName2) + "\n");
            writer.write(String.join(",", customerName3));
            System.out.println("Program data stored successfully.");
        } catch (IOException e) {
            System.out.println("Failed to store program data.");
        }
    }

    private static void loadProgramDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            remainingBurgers = Integer.parseInt(reader.readLine());
            Queue1 = reader.readLine().split(",");
            Queue2 = reader.readLine().split(",");
            Queue3 = reader.readLine().split(",");
            customerName1 = reader.readLine().split(",");
            customerName2 = reader.readLine().split(",");
            customerName3 = reader.readLine().split(",");
            System.out.println("Program data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load program data.");
        }
    }
}

























