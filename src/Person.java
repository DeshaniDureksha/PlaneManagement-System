// Defines a class representing a person with name, surname, and email attributes.
public class Person {
    private String name;
    private String surname;
    private String email;

    //Constructor
    // Initializes a Person object with the provided name, surname, and email.
    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
    public String getName(){ // Getter method to retrieve the name of the person.
        return name;         // Returns the name of the person.
    }

    public void setName(String name){  // Setter method to set the name of the person.
        this.name=name;                // Sets the name of the person.
    }

    public String getSurname(){
        return surname;
    } // Getter method to retrieve the surname of the person.

    public void setSurname(String surname){
        this.surname=surname;
    } // Setter method to set the surname of the person.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print information
    public void personInformation() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}
