package myStore;

import java.util.*;
/*
 * Due to the manager managing the Inventory as well as ShoppingCart class,
 * All methods in each class are managed through StoreManager, hence why store manager
 * Has lots of methods for both inventory and carts
 */

/**
 * The StoreManager Class
 */
public class StoreManager{
    private String firstName;
    private String lastName;
    private Inventory inven;
    private HashMap<Integer, ShoppingCart> carts;
    private int cartID;

    /**
     * Constructor for store Manager
     * @param firstName String, Manager's first name
     * @param lastName String, Manager's Last name
     */
    public StoreManager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inven = new Inventory();
        this.carts = new HashMap<Integer, ShoppingCart>();
        //Initializing the inventory
        addToStock(new Product("SYSC2004 TextBook", "1", 400.0), 25);
        addToStock(new Product("Pack Of 4 Pens", "2", 5.99), 32);
        addToStock(new Product("200 Blank Paper", "3", 1.99), 55);
        addToStock(new Product("Java", "4", 1000), 1);
    }

    /**
     * boolean method true iff product is in inventory, otherwise false
     * @param product Product, the product
     * @return boolean, true if product is in inveotry, otherwise false
     */
    public boolean checkInventoryProductExits(Product product){
        return this.inven.checkIfProductExist(product);
    }

    /**
     * getter for the carts hashmap that contains cart id and the respective shopping card
     * @return HashMap<Integer, ShoppingCart>, cart id and the respective shoppingcart object
     */
    public HashMap<Integer, ShoppingCart> getCarts() {
        return this.carts;
    }

    /**
     * Get the Manager's first name
     * @return String, Manager's first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Get the Manager's Last name
     * @return String, Manager's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the inventory Products from the StoreProduct hashmap
     * @return ArrayList<Product>, List of products
     */
    public ArrayList<Product> getInventoryProducts(){
        return this.inven.getStoredProductKeys();
    }

    /**
     * Get the Cart Products from the Shopping cart hashmap of a specified CartID
     * @param cartID Integer, the cart ID
     * @return ArrayList<Product>, List of products
     */
    public ArrayList<Product> getCartProducts(int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                return this.carts.get(keys).getCartsProductKeys();
            }
        }
        return null;
    }

    /**
     * Get the quantities formatted in an arraylist respective to the products arraylist
     * @param cartID Integer, the CartID
     * @return ArrayList<Integer>, list of quantities respective to the products arraylist
     */
    public ArrayList<Integer> getCartQuantities(int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                return this.carts.get(keys).getCartsValueQuantity();
            }
        }
        return null;
    }

    /**
     * get a specific product by id
     * @param id String, the ProductID
     * @return Product, the product respective to the id
     */
    public Product getInventoryProductByID(String id){
        return this.inven.getProduct(id);
    }

    /**
     * get the cart contents of a specific cartID
     * @param cartID Integer, the CartID
     * @return String, the string format of the cart contents
     */
    public String getInfoOfCartProducts(int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                return this.carts.get(keys).printProductAndQuantity();
            }
        }
        return null;
    }

    /**
     * boolean method to check if a specific product id exist in a cart relative to the cartID
     * @param product Product, the product
     * @param cartID Integer, the cartID
     * @return boolean, true iff the product id is in the cart, otherwise false
     */
    public boolean checkShoppingCartProductExist(Product product, int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                return this.carts.get(keys).checkIfProductExist(product);
            }
        }
        return false;
    }

    /**
     * Assigns a new cart id to a new shoppingCart class through hashmaps
     * @return Integer, the Cart ID
     */
    public int assignNewCartID(){
        if (carts.size() == 0){
            this.cartID = 0;
            this.carts.put(this.cartID, new ShoppingCart());
            return this.cartID;
        }
        this.cartID+= 1;
        this.carts.put(this.cartID, new ShoppingCart());
        return this.cartID;
    }

    /**
     * Adds a Product with a specified quantity to the specified cart through CartId
     * Calls the addProductQuantity method in shoppingCart class
     * @param product Product, the product content to add to cart
     * @param quantityAdded Integer, quantity of specified product to be added
     * @param cartID Integer, The cartID
     */
    public void addToCart(Product product, int quantityAdded, int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                this.carts.get(keys).addProductQuantity(product, quantityAdded);
            }
        }
    }

    /**
     * Remove Product with a specified quantity to the specified cart through CartId
     * Calls the removeProductQuantity method in ShoppingCart class
     * @param product product, the product
     * @param quantityRemoved Integer, quantity of specified product to be subtracted
     * @param cartID Integer, The cartID
     */
    public void removeFromCart(Product product, int quantityRemoved, int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                this.carts.get(keys).removeProductQuantity(product,quantityRemoved);
            }
        }
    }

    /**
     * Add a new product to inventory and set the quantity to default quantity
     * Calls addProductToInven method from Inventory class
     * @param p1 Product, new Product to be added
     */
    public void addToInven(Product p1){
        this.inven.addProductToInven(p1, this.inven.getDefaultQuantityForAllProducts());
    }

    /**
     * Add stock to a product given a specified amount
     * Calls the addProductQuantity method from Inventory Class
     * @param product Product, the prdouct
     * @param amount Integer, the amount to be added
     */
    public void addToStock(Product product, int amount){
        this.inven.addProductQuantity(product, amount);
    }

    /**
     * Get the size of the inventory
     * @return Integer, the size of the inventory
     */
    public int getInventorySize(){
        return this.inven.getNumOfProducts();
    }

    /**
     * Substract from inventory stock of a specified Product ID
     * Calls the removeProductQuantity method from inventory
     * @param p Product, the Product
     * @param amount Integer, quantity to be subtracted from stock
     */
    public void subFromStock(Product p, int amount){
        this.inven.removeProductQuantity(p, amount);
    }

    /**
     * Check the stock of a specified product ID
     * Calls the getProductQuantity method from inventory
     * @param p Product, the Product
     * @return Integer, the stock of the specified Product ID through the getProductQuantity method in inventory
     */
    public int checkStock(Product p){
        return this.inven.getProductQuantity(p);
    }

    /**
     * Check the stock of a specified product ID content a specified cart through CartID
     * Calls the getProductQuantity method in ShoppingCart Class
     * @param p Product, the product
     * @param cartID Integer, the cartID
     * @return Integer, the stock amount of the product if the specified cartID exists in the carts hashmap
     *          otherwise, return -1
     */
    public int checkStockForCartProduct(Product p, int cartID){
        for (int keys : this.carts.keySet()){
            if (keys == cartID){
                return this.carts.get(keys).getProductQuantity(p);
            }
        }
        return -1;
    }

    /**
     * gets the information of a specified product ID (i.e Name, id and the price)
     * Calls the getInfoOfProduct method in Inventory class
     * @param id String, Product ID is a string
     */
    public String getInfoForProduct(String id){
        return this.inven.getInfoOfAProduct(id);
    }

    /**
     * Clear the cart
     * @param cartID Integer, the cartID
     */
    public void clear(int cartID) {
        for (int cartIDs : this.carts.keySet()) {
            if (cartIDs == cartID) {
                for (int i = 0; i < this.carts.get(cartID).getNumOfProducts(); i++) {
                    this.addToStock(this.carts.get(cartIDs).getCartsProductKeys().get(i), this.carts.get(cartIDs).getCartsValueQuantity().get(i));
                }
                this.carts.get(cartID).clear();
                break;
            }
        }
    }

    /**
     * clears the cart respective to the cartID
     * @param cartID Integer, the cartID
     */
    public void transactionClear(int cartID){
        for (int cartIDs : this.carts.keySet()) {
            if (cartIDs == cartID){
                this.carts.get(cartIDs).clear();
                return;
            }
        }
    }

    /**
     * Transaction between the Cart and the manager. Given cartID, access contents of the cart then proceeds transaction
     * Calls getNumOfProducts, getCartsProductKeys, and getCartsValueQuantity methods from ShoppingCart Class
     * @param cartID Integer, cart ID
     * @return double, the total amount for the contents in the cart (quantity * price)
     */
    public double processInteraction(int cartID){
        double total = 0;
        System.out.println("**Contents In Cart**");
        for (int cartIDs : this.carts.keySet()){
            if (cartIDs == cartID){
                for (int i = 0; i<this.carts.get(cartID).getNumOfProducts(); i++){
                    System.out.printf("%d of item %s for %.2f each\n",  this.carts.get(cartIDs).getCartsValueQuantity().get(i), this.carts.get(cartIDs).getCartsProductKeys().get(i).getName(),this.carts.get(cartIDs).getCartsProductKeys().get(i).getPrice());
                    total += this.carts.get(cartIDs).getCartsProductKeys().get(i).getPrice() * this.carts.get(cartIDs).getCartsValueQuantity().get(i);
                }
            }
        }
        return total;
    }
}
