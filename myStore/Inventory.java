package myStore;
import java.util.*;

/**
 * Inventory Class Which is managed by the StoreManager Class
 */
public class Inventory implements ProductStockContainer {
    private HashMap<Product, Integer> storedProduct; // Inventory using hashmaps
    private int defaultQuantityForAllProducts;

    /**
     * Default constructor for Inventory
     */
    public Inventory(){
        this(10);
    }

    /**
     * Constructor for Inventory
     * @param defaultQuantityForAllProducts Integer, default quantity of any new products added to inventory
     */
    public Inventory(int defaultQuantityForAllProducts){
        this.storedProduct = new HashMap<Product, Integer>();
        this.defaultQuantityForAllProducts = defaultQuantityForAllProducts;
    }

    /**
     * getter to get the inventory hashmap
     * @return HashMap<Product, Integer>, hashmap of products and quantities in inventory
     */
    public HashMap<Product, Integer> getStoredProduct() {
        return this.storedProduct;
    }

    /**
     * get the size of the inventory stock
     * @return Integer, the size of the inventory
     */
    @Override
    public int getNumOfProducts(){
        return this.storedProduct.size();
    }


    /**
     * get the keys of the storeProduct hashmap, which are the products of the inventory
     * @return ArrayList<Product>, list of products stored in inventory
     */
    public ArrayList<Product> getStoredProductKeys() {
        return new ArrayList<Product>(this.storedProduct.keySet());
    }

    /**
     * set the default Quantity of new products to a new quantity
     * @param defaultQuantityForAllProducts int, the new default quantity for new products
     */
    public void setDefaultQuantityForAllProducts(int defaultQuantityForAllProducts) {
        this.defaultQuantityForAllProducts = defaultQuantityForAllProducts;
    }

    /**
     * get the default quantity for products
     * @return Integer, the default quantity
     */
    public int getDefaultQuantityForAllProducts() {
        return this.defaultQuantityForAllProducts;
    }

    /**
     * add new products with a set quantity (can be the default quantity) to inventory
     * @param product Product, the new product
     * @param quantity Integer, quantity of the specified product
     */
    public void addProductToInven(Product product, int quantity){
        if (product == null) return;
        this.storedProduct.put(product,quantity);
    }

    /**
     * get the amount of stock for a specified product
     * @param p Product, the  product
     * @return Integer, the Stock of a product given the id, otherwise -1 if the id is non existent in inventory
     */
    @Override
    public int getProductQuantity(Product p){
        for (Product keys : this.storedProduct.keySet()){
            if (keys.equals(p)){
                return this.storedProduct.get(keys);
            }
        }
        return -1;
    }

    /**
     * Boolean check if a given product id is in inventory
     * @param product Product, the product
     * @return boolean, true if the product id exist in inventory, otherwise false
     */
    @Override
    public boolean checkIfProductExist(Product product){
        for (Product keys : this.storedProduct.keySet()) {
            if (keys.equals(product)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add stock of a specified product with a given amount.
     * If the product does not exist in inventory, add the new product with quantity to inventory
     * @param product Product, the Product
     * @param quantityAdded Integer, quantity want added
     */
    @Override
    public void addProductQuantity(Product product, int quantityAdded){
        if (product == null || quantityAdded < 0) return;
        if (this.checkIfProductExist(product)){
            for (Product item : this.storedProduct.keySet()){
                if(product.equals(item)){
                    int previousQuantity = this.storedProduct.get(item);
                    int newQuantity = previousQuantity + quantityAdded;
                    this.storedProduct.replace(item, previousQuantity, newQuantity);
                }
            }
        }
        else{
            addProductToInven(product, quantityAdded);
        }
    }

    /**
     * Subtract stock of a specified product with a given amount in envtory,
     * Ensures that it's not overly subtracted
     * @param product Product, the Product
     * @param quantitySubtracted Integer, quantity want subtracted
     */
    @Override
    public void removeProductQuantity(Product product, int quantitySubtracted){
        if (quantitySubtracted < 0) return;
        for (Product keys : this.storedProduct.keySet()){
            if (keys.equals(product)){
                int previousQuantity = this.storedProduct.get(keys);
                if (previousQuantity - quantitySubtracted >= 0){
                    this.storedProduct.replace(keys,previousQuantity,previousQuantity-quantitySubtracted);
                }
                else{
                    System.out.println("Not subtractable");
                }
                return;
            }
        }
        System.out.println("N/A");
    }

    /**
     * get the price of a specified product
     * @param id Integer, Product ID as an Integer
     * @return double, return product price iff product ID is in invetory, otherwise -1
     */
    public double getProductPrice(int id){
        for (Product keys : this.storedProduct.keySet()){
            if (Integer.parseInt(keys.getId()) == id){
                return keys.getPrice();
            }
        }
        return - 1;
    }

    /**
     * get the product of a specific product id
     * @param id String, the productID
     * @return Product, the product of the specified id
     */
    public Product getProduct(String id){
        for (Product keys : this.storedProduct.keySet()){
            if (keys.getId().equals(id)){
                return keys;
            }
        }
        return null;
    }

    /**
     * Upon request, get the information of a product given the ID
     * @param id String, A Product ID
     * @return String, the name, id and price of a product given the id iff id is in invetory
     *         Otherwise, not in inventory
     */
    public String getInfoOfAProduct(String id){
        for (Product keys : this.storedProduct.keySet()){
            if (keys.getId().equals(id)){
                return keys.getName() + ", P#: " + keys.getId() + ", Price: $" + keys.getPrice();
            }
        }
        return "Not in inventory";
    }
}
