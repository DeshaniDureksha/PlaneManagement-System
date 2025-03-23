import java.io.FileWriter;
import java.io.IOException;


public class Ticket {
    private char row;
    private int seat;
    private int price;
    private Person person;

    // Constructor
    public Ticket(char row,int seat,int price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }
    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print information of a Ticket
    // Prints details of the ticket including row, seat, price, and information about the person who booked the ticket.
    public void TicketInformation() {
        System.out.println("\nRow: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);
        person.personInformation();
    }

    // Method to save ticket information to a file
    public void save(){
        try{

            // Constructs the filename based on the row and seat number of the ticket.
            String path= String.valueOf(getRow()) + String.valueOf(getSeat()) + ".txt";

            FileWriter newfile=new FileWriter(path); // Creates a new FileWriter object to write to the file.
            newfile.write("Ticket Information\n");
            newfile.write("Row: "+getRow()+"\tSeat: " +getSeat());
            newfile.write("\nPrice: " + getPrice());
            newfile.write("\nPerson Information");
            newfile.write("\nName: " + person.getName());
            newfile.write("\nSurname: " + person.getSurname());
            newfile.write("\nEmail: " + person.getEmail());

            newfile.close(); // Closes the FileWriter object


        }catch (IOException e){
            System.out.println("Error occurred");
        }
    }
}
