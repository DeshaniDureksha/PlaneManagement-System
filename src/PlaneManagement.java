import java.util.Scanner;
import java.io.File;

public class PlaneManagement {

    public static void main(String[]args){

        Scanner userInput= new Scanner(System.in); // Create a Scanner object to read input

        // Initialize plane seats with a 2D array
        int[][] planeSeats = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //row A
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //row B
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //row C
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//row D
        };
        // Initialize the 'tickets' array to represent tickets on the plane
        Ticket[][] tickets={
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };

        boolean terminate = false;// Initialize a boolean variable 'terminate' to control the termination of the application loop.

        // Start a loop that continues until 'terminate' becomes true.
        while (!terminate){
            System.out.println("\nWelcome to the Plane Management application");
            System.out.println(
                    "****************************************************\n"+
                            "*                   MENU OPTIONS                   *\n"+
                            "****************************************************\n"+
                            "\t  1) Buy a seat\n"+
                            "\t  2) Cancel a seat\n"+
                            "\t  3) Find first available seat\n"+
                            "\t  4) Show seating plan\n"+
                            "\t  5) Print tickets information and total sales\n"+
                            "\t  6) Search ticket\n"+
                            "\t  0) Quit\n"+
                            "****************************************************\n");
            int option; // This variable will store the user's input representing the menu option they choose

            // Start a try block to handle potential input errors.
            try {
                System.out.print("Please select an option:    ");
                // Read user's choice
                option = userInput.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 6");
                userInput.nextLine();
                continue;
            }

            // Perform action based on user's choice
            switch (option) {
                case 1:
                    buy_seat(planeSeats, userInput,tickets);
                    break;

                case 2:
                    cancel_seat(planeSeats, userInput,tickets);
                    break;

                case 3:
                    find_first_available(planeSeats);
                    break;

                case 4:
                    show_seating_plan(planeSeats);
                    break;

                case 5:
                    print_tickets_info(tickets);
                    break;

                case 6:
                    search_ticket(tickets,userInput);
                    break;
                case 0:
                    terminate = true; // Set 'terminate' to true to exit the while loop and terminate the application.
                    break;
                default: // If the user enters an invalid option
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
    }
    // Method to buy a seat
    private static void buy_seat(int[][] planeSeats,Scanner userInput,Ticket[][] tickets) {
        System.out.println("Buy seat");

        // Prompt user to enter row letter
        System.out.print("Enter the row letter (A - D) :   ");
        char rowLetter;
        while (true) {
            String input = userInput.next().toUpperCase(); // Read user input and convert it to uppercase

            // Check if the input is not a single character or is not between 'A' and 'D'
            if (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) > 'D') {
                System.out.print("Invalid row letter. Please enter a letter between A and D:  ");
            } else {
                rowLetter = Character.toUpperCase(input.charAt(0));
                break;
            }
        }
        // Prompt user to enter seat number
        System.out.print("Enter seat number: ");
        int seatNumber;
        while (true) {

            // Check if the input is an integer
            if (userInput.hasNextInt()) {
                seatNumber = userInput.nextInt();
                if (seatNumber < 1 || seatNumber > planeSeats[rowLetter - 'A'].length) {
                    System.out.print("Invalid seat number. Please enter a valid seat number: ");
                } else {
                    break;
                }
            } else {
                System.out.print("Invalid seat number. Please enter a valid seat number:  ");
                userInput.next(); // Consume invalid input
            }
        }

        // Calculate the index of the row in the planeSeats array based on the ASCII value of the rowLetter
        int rowIndex = rowLetter - 'A';
        userInput.nextLine();

        // Check if seat is available
        if (planeSeats[rowIndex][seatNumber - 1] == 0) {

            planeSeats[rowIndex][seatNumber - 1] =1; // Mark the seat as booked

            // Prompt the user to enter their name, surname, and email
            System.out.print("Enter your name:  ");
            String name= userInput.nextLine();
            System.out.print("Enter your surname:  ");
            String surname = userInput.nextLine();
            System.out.print("Enter your email: ");
            String email =userInput.nextLine();

            Person newPerson=new Person(name,surname,email); // Create a new Person object with the entered information

            // Determine the price based on the seat number
            int price;
            if (seatNumber <=5){
                price=200;
            } else if (seatNumber <=9) {
                price=150;
            }else {
                price=180;
            }

            // Create a new Ticket object with the seat information and the person who booked it
            Ticket newTicket =new Ticket(rowLetter,seatNumber,price,newPerson);

            tickets[rowIndex][seatNumber-1]=newTicket; // Store the new ticket in the tickets array

            newTicket.save(); // Call the 'save()' method of the 'newTicket' object to save the ticket information to a textFile

            System.out.println("Seat successfully booked");
        } else {
            System.out.println("The seat is already booked ");
        }
    }
    // Method to cancel a seat
    private static void cancel_seat(int[][] planeSeats,Scanner userInput,Ticket[][]tickets){
        System.out.println("Cancel seat");

        // Prompt user to enter row letter
        System.out.print("Enter the row letter (A -D):  ");
        char rowLetter ;

        while (true) { // Start a loop to ensure valid input for the row letter
            String input = userInput.next().toUpperCase();
            if (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) > 'D') {
                System.out.print("Invalid row letter. Please enter a letter between A and D:  ");
            } else {
                rowLetter = Character.toUpperCase(input.charAt(0));
                break;
            }
        }

        // Prompt user to enter seat number
        System.out.print("Enter seat number: ");
        int seatNumber ;

        while (true) {
            if (userInput.hasNextInt()) { // Check if the input is an integer
                seatNumber = userInput.nextInt();
                if (seatNumber < 1 || seatNumber > planeSeats[rowLetter - 'A'].length) {
                    System.out.print("Invalid seat number. Please enter a valid seat number: ");
                } else {
                    break;
                }
            } else {
                System.out.print("Invalid seat number. Please enter a valid seat number: ");
                userInput.next(); // Consume invalid input
            }
        }
        // Calculate the index of the row in the planeSeats array based on the ASCII value of the rowLetter
        int rowIndex   = rowLetter - 'A';

        // Check if seat is already booked
        if (planeSeats[rowIndex][seatNumber-1] ==1){
            planeSeats[rowIndex][seatNumber-1]=0; //If the seat is booked, cancel the booking
            System.out.println("Seat  successfully canceled");

            tickets[rowIndex][seatNumber-1]=null;  //Remove the corresponding ticket from the tickets array

            String path = rowLetter + "" + seatNumber + ".txt"; // Constructs the path for the file to be deleted.

            File delfile = new File(path); // Creates a File object representing the file specified by the path
            delfile.delete();// Deletes the file represented by the File object.

        }else {
            System.out.println("The seat is not booked");
        }
    }

    // Method to find the first available seat
    private static void find_first_available(int[][]planeSeats){
        // Iterate through the plane seats
        for (int rowIndex=0; rowIndex < planeSeats.length; rowIndex ++){
            for (int seatNumber = 0 ; seatNumber < planeSeats[rowIndex].length; seatNumber++){

                // If seat is available, print its location and return
                if (planeSeats[rowIndex][seatNumber] == 0) {
                    char rowLetter = (char) ('A' + rowIndex);

                    System.out.println("First available seat:  "+ rowLetter + (seatNumber +1));
                    return;
                }
            }
        }
        // If all seats are booked
        System.out.println("All seats are booked.");
    }

    // Method to show seating plan
    private static void show_seating_plan(int[][] planeSeats){
        System.out.println("Show seating plan ( 0: Available , X : Unavailable)");

        // Iterate through the plane seats
        for(int rowIndex=0;rowIndex< planeSeats.length;rowIndex++){ // Iterates through the rows of the plane seats array.
            for (int seatNumber=0;seatNumber<planeSeats[rowIndex].length;seatNumber++){  // Within each row, iterates through the seat numbers.

                if (planeSeats[rowIndex][seatNumber]==0){
                    System.out.print("0 "); // Available seat
                }else {
                    System.out.print("X "); // Booked seat
                }
            }
            System.out.println(); // Move to next row
        }
    }
    private static void print_tickets_info(Ticket[][]tickets){ //method to print ticket information

        double totalPrice=0;
        for(int rowIndex=0;rowIndex< tickets.length;rowIndex++){ // Iterates through the rows of the tickets array.
            for (int seatNumber=0;seatNumber<tickets[rowIndex].length;seatNumber++){ // Within each row, iterates through the seat numbers.

                if (tickets[rowIndex][seatNumber]!= null){ // Checks if there is a ticket object at the current position in the array.
                    tickets[rowIndex][seatNumber].TicketInformation();  // If a ticket exists at the current position, calls the TicketInformation() method to print its information.
                    totalPrice= totalPrice+tickets[rowIndex][seatNumber].getPrice(); // Adds the price of the ticket to the total price.
                }
            }
        }
        System.out.println("\nThe total price is: $" + totalPrice); // After processing all tickets, prints the total price.
    }
    private static void search_ticket(Ticket[][] tickets, Scanner userInput){ //method to search tickets
        System.out.print("Enter the row letter  :   ");
        char row = userInput.next().toUpperCase().charAt(0);
        System.out.print("Enter seat number: ");
        int seat = userInput.nextInt();

        int rowIndex = row - 'A';

        if (tickets[rowIndex][seat-1] != null) { // Checks if a ticket exists at the specified row and seat in the tickets array.
            tickets[rowIndex][seat-1].TicketInformation(); // If a ticket exists at the specified position, calls the TicketInformation() method to print its information.
        } else {
            System.out.println("Seat is not booked");
        }
    }
}