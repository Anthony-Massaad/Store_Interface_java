package myStore;

/**
 * Product class
 */
public class Product {
    private final String name;
    private final String id;
    private final double price;

    /**
     * Constructor for Product
     * @param name String, name of the product
     * @param id String, The id of the product
     * @param price double, The price of the product
     */
    public Product(String name, String id, double price){
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Overrides java oject equals() method
     * @param obj Object, the object to check
     * @return boolean, true iff the comparison and the object is identical, otherwise false
     */
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null || obj.getClass()!= this.getClass()){
            return false;
        }
        Product product = (Product) obj;
        return this.id.equals(product.id) && this.name.equals(product.name) && this.price == product.price;
    }

    /**
     * Get the name of a specified product
     * @return String, name of the product
     */
    public String getName(){
        return name;
    }

    /**
     * Get the Id of a specified product
     * @return String, id of the product
     */
    public String getId(){
        return id;
    }

    /**
     * get the price of a specified product
     * @return double, price of the product
     */
    public double getPrice(){
        return price;
    }

}

