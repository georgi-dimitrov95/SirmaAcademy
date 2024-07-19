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
//
            switch (loginResponse) {
                case "1":
//                    register a customer

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
}
