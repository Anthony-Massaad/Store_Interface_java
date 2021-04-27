package myStore;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shopping cart Class
 */
public class ShoppingCart implements ProductStockContainer{
    private HashMap<Product, Integer> shoppingCart;

    /**
     * default constructor
     */
    public ShoppingCart(){
        this.shoppingCart = new HashMap<Product, Integer>();
    }

    /**
     * getter method to get the cart hasmap
     * @return HashMap<Product, Integer>, the contents of inventory in product, quantity form
     */
    public HashMap<Product, Integer> getShoppingCart() {
        return this.shoppingCart;
    }

    /**
     * Clear the cart contents
     */
    public void clear(){
        this.shoppingCart.clear();
    }

    /**
     * Boolean check if a given product id is in the ShoppingCart
     * @param product Product, the product
     * @return boolean, true if the product id exist in the cart, otherwise false
     */
    @Override
    public boolean checkIfProductExist(Product product){
        for (Product keys : this.shoppingCart.keySet()) {
            if (keys.equals(product)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds to product with a specified quantity
     * If product does not exist in cart, will add the contents of product and quantity to cart
     * @param product Product, The product
     * @param quantityAdded Integer, the quantity to be added
     */
    @Override
    public void addProductQuantity(Product product, int quantityAdded){
        if (product == null || quantityAdded < 0) return;

        if (this.checkIfProductExist(product)){
            for (Product item : this.shoppingCart.keySet()){
                if(product.equals(item)){
                    int previousQuantity = this.shoppingCart.get(item);
                    this.shoppingCart.replace(item, previousQuantity, previousQuantity + quantityAdded);
                    return;
                }
            }
        }
        else{
            this.shoppingCart.put(product,quantityAdded);
        }
    }

    /**
     * Get the size of the cart (i.e how many elements in shoppingCart hashmap)
     * @return Integer, the size of the cart
     */
    @Override
    public int getNumOfProducts(){
        return this.shoppingCart.size();
    }

    /**
     * Gets the product contents (i.e keys of shoppingCart hashmap) in the cart and adds them to an arraylist
     * @return ArrayList<Product>, list of products
     */
    public ArrayList<Product> getCartsProductKeys() {
        return new ArrayList<Product>(this.shoppingCart.keySet());
    }

    /**
     * Gets the quantity contents (i.e value of shoppingCart hashmap) in the cart and adds them to an arraylist
     * @return ArrayList<Integer>, list of quantities in order of product
     */
    public ArrayList<Integer> getCartsValueQuantity() {
        return new ArrayList<Integer>(this.shoppingCart.values());
    }

    /**
     * get the quantity of a specified product ID in the cart
     * @param product Product, the product
     * @return Integer, Quantity of the product iff the product exist in cart, otherwise return -1
     */
    @Override
    public int getProductQuantity(Product product){
        for (Product keys : this.shoppingCart.keySet()){
            if (keys.equals(product)){
                return this.shoppingCart.get(keys);
            }
        }
        return -1;
    }

    /**
     * print the contents of the cart by product and quantity using stringbuilder
     * @return String, the items and quantity in the cart
     */
    public String printProductAndQuantity(){
        StringBuilder sb = new StringBuilder();
        for (Product product : this.shoppingCart.keySet()){
            sb.append("<html>");
            sb.append("Product: ").append(product.getName()).append(", Quantity: ").append(this.shoppingCart.get(product)).append("<br/>");
            sb.append("$").append(product.getPrice()).append(" Each");
            sb.append("<br/>").append("<html>");
        }
        return sb.toString();
    }

    /**
     * subtract a product with a specified quantity
     * if the product subtraction is greater than 0, regular subtraction and add to inventory the subtracted amount
     * otherwise, return the total amount in cart of specified product to inventory, and remove product content
     * From cart
     * @param product Product, the Product
     * @param quantitySubtracted Integer, amount to be subtracted from product
     */
    @Override
    public void removeProductQuantity(Product product, int quantitySubtracted) {
        if (product == null || quantitySubtracted < 0) return;
        if (this.checkIfProductExist(product)){
            for (Product keys : this.shoppingCart.keySet()) {
                if (keys.equals(product)) {
                    int previousQuantity = this.shoppingCart.get(keys);
                    if (previousQuantity - quantitySubtracted > 0) {
                        this.shoppingCart.replace(keys, previousQuantity, (previousQuantity - quantitySubtracted));
                    }
                    else{
                        this.shoppingCart.remove(keys);
                    }
                    return;
                }
            }
        }
    }
}
