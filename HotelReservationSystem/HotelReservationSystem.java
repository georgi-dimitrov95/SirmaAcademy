package HotelReservationSystem;

import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();
        Menu.greetingMenu();

//        main loop of the program
        boolean mainLoop = true;
        while (mainLoop) {
            Menu.loginAndRegisterMenu();
            String loginResponse = scanner.nextLine().trim();

            switch (loginResponse) {
                case "1":
                    while (true) {
                        System.out.println("Please specify a name and a password for your account.");
                        System.out.println("Name: ");
                        String name = scanner.nextLine().trim();

//                        check if username is not already taken
                        if (reservationSystem.userNameExists(name)) {
                            System.out.println("The username already exists. Please try another one.");
                            continue;
                        }

                        System.out.println("Password: ");
                        String password = scanner.nextLine().trim();

//                        validate the user password
                        if (!validatePassword(password)) {
                            System.out.println("The password must be at least 6 characters in length and contain at least one uppercase letter as well as at least one digit.");
                            continue;
                        }

//                        if we're here then the registration process is successful, so we create the user and add it to the reservation system
                        User newUser = new User(name, password);
                        reservationSystem.addUser(newUser);
                        System.out.println("You have successfully registered in our system! Now you can log in your account.");
                        break;
                    }
                    break;
                case "2":
//                    login customer

                    break;
                case "3":
//                    login admin
                    break;
                default:
                    mainLoop = false;
                    break;
            }
        }
    }

    //    validate user password
    public static boolean validatePassword(String password) {
        return password.matches("^[a-zA-Z0-9]{6}$");
    }
}

