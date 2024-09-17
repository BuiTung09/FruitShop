package Entity;

public class Fruit {
    private String fruitID, fruitName, Origin;
    private int quantity;
    private double price;
    
    public Fruit(){   
    }
    public Fruit(String fruitID, String fruitName, String Origin, int quantity, double price) {
        this.fruitID = fruitID;
        this.fruitName = fruitName;
        this.Origin = Origin;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFruitID() {
        return fruitID;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String getOrigin() {
        return Origin;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setFruitID(String fruitID) {
        this.fruitID = fruitID;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public void setQuantity(int quantity) throws Exception {
        if(quantity > 0){
            this.quantity = quantity;
        }
        else {
            throw new Exception("Quantity must > 0");
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getAmount(){
        return price*quantity;
    }
    
}
