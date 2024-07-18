package HotelReservationSystem;

public class Menu {

//    greets the user upon starting the program
    public static void GreetingMenu() {
        String welcome = "Welcome to the Hotel Reservation System!";
        System.out.println("=".repeat(welcome.length()));
        System.out.println(welcome);
        System.out.println("=".repeat(welcome.length()));
    }

    public static void loginAndRegisterMenu() {
        System.out.println("Before interacting with the system you must either register an account and/or log in.");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Register as a Customer.");
        System.out.println("2. Log in as a Customer.");
        System.out.println("3. Log in as an Admin.");
    }

    public static void customerMainMenu() {
        System.out.println("Please choose one of the following options:");
        System.out.println("1. View available rooms.");
        System.out.println("2. Book a room.");
        System.out.println("3. Cancel booking.");
        System.out.println("4. View profile.");
    }
}
