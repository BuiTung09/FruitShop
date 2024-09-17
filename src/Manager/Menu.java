
package Manager;

import java.util.Scanner;

public class Menu {
    private final Control control = new Control();
    private final Scanner scanner = new Scanner(System.in);

    // Hiển thị menu chính
    public void mainMenu() throws Exception {
        while (true) {
            System.out.println("FRUIT SHOP SYSTEM");
            System.out.println("1. Create Fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            System.out.print("Please choose: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    control.createFruit();
                    break;
                case 2:
                    control.viewOrders();
                    break;
                case 3:
                    control.shopping();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

