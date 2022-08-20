package utp.edu.mvp_firestore_java.model;

public class Product extends Item {
    String description;
    String price;

    public Product() {
    }

    public Product(String description, String price, String id_product, String title_product) {
        id = id_product;
        title = title_product;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
