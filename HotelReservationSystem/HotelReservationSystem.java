package HotelReservationSystem;

import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu.greetingMenu();

//        main loop of the program
        boolean mainLoop = true;
        while (mainLoop) {
            Menu.loginAndRegisterMenu();
            String loginResponse = scanner.nextLine().trim();

            switch (loginResponse) {
                case "1":
//                    prompt for registration info
                    System.out.println("Please specify a name and a password for your account.");
                    System.out.println("Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.println("Password: ");
                    String password = scanner.nextLine().trim();

//                    check if username is unique and validate password

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
    public boolean validatePassword(String password) {
        return password.matches("^[a-zA-Z0-9]{6}$");
    }
}

