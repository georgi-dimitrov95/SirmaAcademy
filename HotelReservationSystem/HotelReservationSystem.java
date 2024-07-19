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
//                        prompt for registration info
                        System.out.println("Please specify a name and a password for your account.");
                        System.out.println("Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.println("Password: ");
                        String password = scanner.nextLine().trim();

//                        check if username is unique
                        if (reservationSystem.userNameExists(name)) {
                            System.out.println("The username already exists. Please try another one.");
                            continue;
                        }

//                        validates the user password
                        if (!validatePassword(password)) {
                            continue;
                        }

                        User newUser = new User();
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

