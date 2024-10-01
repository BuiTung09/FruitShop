package Manager;

import Entity.Fruit;
import Entity.Order;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Control {
    private final ArrayList<Fruit> listFruit = new ArrayList<>();// có thể là new ArrayList<Fruit> nếu netbeans 8.1
    private final Hashtable<String, ArrayList<Order>> orderTable = new Hashtable<>(); // same
    private final Scanner scanner = new Scanner(System.in);

    // Tạo mới Fruit
    public void createFruit() {
        while (true) {
            System.out.print("Enter fruit ID: ");
            String id = scanner.next();
            System.out.print("Enter fruit name: ");
            String name = scanner.next();
            System.out.print("Enter fruit origin: ");
            String origin = scanner.next();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            Fruit newFruit = new Fruit(id, name, origin, quantity, price);
            listFruit.add(newFruit);

            System.out.print("Do you want to continue (Y/N)? ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("N")) {
                displayFruits();
                break;
            }
        }
    }

    // Hiển thị danh sách đơn hàng
    public void viewOrders() {
        if (orderTable.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (String customer : orderTable.keySet()) {
                System.out.println("Customer: " + customer);
                ArrayList<Order> orders = orderTable.get(customer);
                double totalAmount = 0;
                for (Order order : orders) {
                    for (Fruit fruit : order.getListFruit()) {
                        System.out.printf("%s | %d | %.2f$ | %.2f$\n", fruit.getFruitName(), fruit.getQuantity(), fruit.getPrice(), fruit.getAmount());
                        totalAmount += fruit.getAmount();
                    }
                    System.out.printf("Total: %.2f$\n", totalAmount);
                }
                System.out.println();
            }
        }
    }

    // Cho khách hàng mua trái cây
    public void shopping() throws Exception {
        if (listFruit.isEmpty()) {
            System.out.println("No fruits available.");
            return;
        }

        // Tạo một đơn hàng mới
        System.out.println("Enter your name: ");
        String customerName = scanner.next();
        Order newOrder = new Order(customerName);  // Khởi tạo Order chỉ với tên khách hàng
        
        while (true) {
            displayFruits();
            System.out.print("Please select an item: ");
            int item = scanner.nextInt();
            if (item < 1 || item > listFruit.size()) {
                System.out.println("Invalid item, please try again.");
                continue;
            }

            Fruit selectedFruit = listFruit.get(item - 1);
            System.out.println("You selected: " + selectedFruit.getFruitName());
            System.out.print("Please input quantity: ");
            int quantity = scanner.nextInt();
            if (quantity > selectedFruit.getQuantity()) {
                System.out.println("Not enough stock. Please try again.");
                continue;
            }

            // Tạo một đối tượng trái cây với số lượng mới và thêm vào đơn hàng
            Fruit orderedFruit = new Fruit(selectedFruit.getFruitID(), selectedFruit.getFruitName(), selectedFruit.getOrigin(), quantity, selectedFruit.getPrice());
            newOrder.addFruit(orderedFruit);  // Thêm trái cây vào đơn hàng

            // Giảm số lượng trái cây trong kho
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);

            System.out.print("Do you want to order now (Y/N)? ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y")) {
                // Thêm đơn hàng vào bảng đơn hàng
                orderTable.computeIfAbsent(customerName, k -> new ArrayList<>()).add(newOrder);

                // Hiển thị đơn hàng
                System.out.println("Your order:");
                double total = 0;
                for (Fruit fruit : newOrder.getListFruit()) {
                    System.out.printf("%s | %d | %.2f$ | %.2f$\n", fruit.getFruitName(), fruit.getQuantity(), fruit.getPrice(), fruit.getAmount());
                    total += fruit.getAmount();
                }
                System.out.printf("Total: %.2f$\n", total);
                break;
            }
        }
    }

    // Hiển thị danh sách trái cây
    public void displayFruits() {
        System.out.println("List of Fruit:");
        for (int i = 0; i < listFruit.size(); i++) {
            Fruit fruit = listFruit.get(i);
            System.out.printf("%d. %s | %s | %.2f$\n", i + 1, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
        }
    }
}
