package Task2_and_Task3;

import java.util.ArrayList;

public class FoodQueue {
    private final ArrayList<Customer> queue;

    public FoodQueue() {
        this.queue = new ArrayList<>();
    }

    /**
     * Adds a customer to the queue if the burger stock count is greater than or equal to the number of burgers ordered by the customer.
     * Deducts the number of burgers required by the customer from the remaining burger stock.
     * Prints a success message and updates the remaining stock value.
     * Asks the user to enter a new option if the burger count is not sufficient to serve the customer.
     * Gives a warning message if the burger count is less than 10.
     * @param firstName first name of the customer.
     * @param lastName last name of the customer.
     * @param burgersRequired number of burgers required by the customer.
     */
    public boolean addCustomer(String firstName, String lastName, int burgersRequired, int remainingBurgerStock) {
        boolean customerAdded = false;
        if (burgersRequired <= remainingBurgerStock) {
            queue.add(new Customer(firstName, lastName, burgersRequired));
            customerAdded = true;

        } else {
            System.out.println("Remaining stock is not sufficient to serve the customer.");
            System.out.println("Please add more burgers to the stock.");
        }
        return customerAdded;
    }

    /**
     * Removes the first customer from the specific queue given by the user.
     * @return return null if the waiting queue is empty
     */
    public Customer removeFirstCustomer() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        return null;
    }

    /**
     * Removes a customer from a specific location in the queue and display that customers details.
     * Adds the burgers ordered by that customer back to remaining burger stock
     * @param position The specific location of the customer to be removed.
     */
    public Customer removeSpecificCustomer(int position) {
        if (position >= 0 && position < queue.size()) {
            Customer removedCustomer = queue.remove(position);

            System.out.println("Customer removed: " + removedCustomer.firstName() + " " + removedCustomer.lastName());
            return removedCustomer;
        } else {
            System.out.println("No customer was found at that specific location.");
        }
        return null;
    }

    public String getOccupancy(int position){
        try {
            Customer queuePos = queue.get(position);
            return "O";
        }catch (IndexOutOfBoundsException e){
            return "X";
        }
    }

    public int getQueueSize() {
        return queue.size();
    }
    public ArrayList<Customer> getCustomers() {
        return queue;
    }
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }


}
