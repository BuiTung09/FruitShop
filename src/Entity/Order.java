package Entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customer; // Tên khách hàng
    private List<Fruit> listFruit; // Danh sách các loại trái cây trong order

    // Constructor không đối số
    public Order(){
        listFruit = new ArrayList<>();
    }
    // Constructor có tham số
    public Order(String customer) {
        this.customer = customer;
        listFruit = new ArrayList<>();
    }


    // Getter cho danh sách trái cây
    public List<Fruit> getListFruit() {
        return listFruit;
    }

    // Getter cho tên khách hàng
    public String getCustomer() {
        return customer;
    }

    // Thêm một loại trái cây vào order
    public void addFruit(Fruit newFruit ) throws Exception {
        for (Fruit fruit : listFruit) {
            if(fruit.getFruitID().equals(newFruit.getFruitID())){
               fruit.setQuantity(newFruit.getQuantity()+ fruit.getQuantity());
               return;
            }
        }
    }

    // Tính tổng số tiền của tất cả các loại trái cây trong order
    public double getTotal() {
        double total = 0;
        for (Fruit fruit : listFruit) {
            total += fruit.getAmount(); // Sử dụng phương thức getAmount từ class Fruit
        }
        return total;
    }

    // In thông tin của order
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(customer).append("\n");
        sb.append("List of Fruits:\n");
        for (Fruit fruit : listFruit) {
            sb.append(fruit.getFruitName()).append(" (").append(fruit.getOrigin()).append(") - ")
              .append(fruit.getQuantity()).append(" pcs - ")
              .append(fruit.getPrice()).append("$ each - Amount: ")
              .append(fruit.getAmount()).append("$\n");
        }
        sb.append("Total: ").append(getTotal()).append("$\n");
        return sb.toString();
    }
}

