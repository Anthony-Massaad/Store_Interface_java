package myStore;
import java.util.*;

/**
 * StoreViewText class
 */
public class StoreViewText {
    private final int cartID;
    private final StoreManager manager;
    public StoreViewText(StoreManager manager, int cartID){
        this.manager = manager;
        this.cartID = cartID;
    }

    /**
     * Get the cartID
     * @return Integer, the CartID
     */
    public int getCartID() {
        return this.cartID;
    }

    /**
     * Active iff command 'browse' is used. Displays the inventory to the user
     */
    public void DisplayGUI(){
        System.out.println("|--------------------THE COURSE STORE--------------------|");
        System.out.println("\\-------------------------BROWSE-------------------------/");
        System.out.println("Stock | Product Name | Unit Price");
        for (Product keys : this.manager.getInventoryProducts()){
            System.out.println(this.manager.checkStock(keys) + " | " + keys.getName() + " | $" + keys.getPrice());
        }
    }

    /**
     * Active iff command 'incart' is used. Displays contents in cart of specified cartID for the user
     */
    public void GetContentsOfCart(){
        System.out.print("|-------------------------The USER'S CART-------------------------|\n");
        System.out.println("\\-------------------------CONTENTS-------------------------/");
        System.out.println("How many you added | Product Name | Unit Price");
        for (Product product : this.manager.getCartProducts(this.cartID)){
            System.out.println(this.manager.checkStockForCartProduct(product, this.cartID) + " | " + product.getName() + " | $" + product.getPrice());
        }
    }

    /**
     * active iff command 'quit' is used. Will return all contents in cart back to inventory
     * @return boolean, true iff user commands 'quit'
     */
    public boolean returnCartStockToInventory(){
        for (Product keys : this.manager.getCartProducts(this.cartID)) {
            this.manager.addToStock(keys, this.manager.checkStockForCartProduct(keys, this.cartID));
        }
        return true;
    }

    /**
     * active iff user inputs 'checkout'. Will proceed to transaction through the manager
     * @return boolean, true iff user commands 'checkout'
     */
    public boolean checkout(){
        System.out.printf("Total price is: $%.2f\n", this.manager.processInteraction(this.cartID));
        return true;
    }

    /**
     * active iff user inputs 'removefromcart'
     * Will first display contents in cart, then ask for an option
     * if option is valid, will then subtract from cart to a specified Integer inputted by the user
     * otherwise will continue through the GUI doing no subtraction
     */
    public void RemoveFromCartGUI(){
        int itemCounter = 0;
        int counter = 0;
        int option;
        int amount;
        Scanner sc = new Scanner(System.in);
        System.out.println("|-----------------------------USER'S CART-----------------------------|");
        System.out.println("\\-------------------------SUBTRACT FROM CART-------------------------/");
        System.out.println("How many you have | Product Name | Unit Price | Option");
        for (Product product : this.manager.getCartProducts(this.cartID)){
            System.out.println(this.manager.checkStockForCartProduct(product, this.cartID) + " | " + product.getName() + " | $" + product.getPrice() + " | (" + itemCounter + ")");
            itemCounter++;
        }
        while (true){
            try{
                System.out.print("Option (or invalid number to cancel) >>> ");
                option = sc.nextInt();
                break;
            }
            catch (InputMismatchException e){
                System.out.println("**Invalid input, please put a number**");
                sc.next();
            }
        }
        for (Product keys : this.manager.getCartProducts(this.cartID)){
            if (option == counter){
                while (true){
                    try{
                        System.out.print("How many would you like to remove of " + keys.getName() + " out of " + this.manager.checkStockForCartProduct(keys, this.cartID) + " (negative to cancel) >>> ");
                        amount = sc.nextInt();
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("**Invalid input, please put a number**");
                        sc.next();
                    }
                }
                if (amount < 0){
                    System.out.println("Inputted negative amount, Exiting operation");
                    return;
                }
                if (this.manager.checkStockForCartProduct(keys, this.cartID) - amount > 0){
                    this.manager.removeFromCart(keys,amount, this.cartID);
                    this.manager.addToStock(keys, amount);
                    System.out.println("Subtracted from cart");
                }
                else{
                    this.manager.addToStock(keys, this.manager.checkStockForCartProduct(keys, this.cartID));
                    this.manager.removeFromCart(keys,this.manager.checkStockForCartProduct(keys, this.cartID), this.cartID);
                    System.out.println("Removed from cart");
                }
            }
            counter++;
        }
    }

    /**
     * active iff user inputs 'addtocart'
     * Will first display contents in cart, then ask for an option
     * if option is valid, will then add to cart with a specified Integer inputted by the user (needs to be a valid amount)
     * otherwise will continue through the GUI doing no addition
     */
    public void AddToCartGUI(){
        int itemCounter = 0;
        int counter = 0;
        int option;
        int amount;
        Scanner sc = new Scanner(System.in);
        System.out.println("|--------------------THE USER'S CART--------------------|");
        System.out.println("\\-------------------------ADD-------------------------/");
        System.out.println("Stock | Product Name | Unit Price | Option");
        for (Product keys : this.manager.getInventoryProducts()){
            System.out.println(this.manager.checkStock(keys) + " | " + keys.getName() + " | $" + keys.getPrice() + " | (" + itemCounter + ")");
            itemCounter++;
        }
        while (true){
            try{
                System.out.print("Option (or invalid number to cancel) >>> ");
                option = sc.nextInt();
                break;
            }
            catch (InputMismatchException e){
                System.out.println("**Invalid input, please put a number**");
                sc.next();
            }
        }
        for (Product keys : this.manager.getInventoryProducts()){
            if (option == counter){
                while (true){
                    try{
                        System.out.print("How many would you like of " + keys.getName() + " out of " + this.manager.checkStock(keys) + " (negative to exit) >>> ");
                        amount = sc.nextInt();
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("**Invalid input, please put a number**");
                        sc.next();
                    }
                }
                if (amount < 0){
                    System.out.println("Negative amount inputted, Exiting operation");
                    return;
                }
                if (this.manager.checkStock(keys) - amount >= 0){
                    this.manager.subFromStock(keys,amount);
                    this.manager.addToCart(keys,amount,this.cartID);
                    System.out.println("Added to Cart");
                }
                else {
                    System.out.println("Not a valid amount in inventory");
                }
            }
            counter ++;
        }
    }

    /**
     * Main method that controls the GUI of the program
     * @param args default args
     */
    public static void main(String[] args) {
        StoreManager sm = new StoreManager("Linda", "Pulles");
        StoreViewText sv1 = new StoreViewText(sm, sm.assignNewCartID());
        StoreViewText sv2 = new StoreViewText(sm, sm.assignNewCartID());
        StoreViewText sv3 = new StoreViewText(sm, sm.assignNewCartID());
        StoreViewText[] users = {sv1, sv2, sv3};
        int activeSV = users.length;
        String chooseAnother;
        Scanner sc = new Scanner(System.in);
        int choice;
        while (activeSV > 0){
            while (true){
                try{
                    System.out.printf("CHOOSE YOUR STOREVIEW (Range [%d,%d]) >>> ", 0, users.length - 1);
                    choice = sc.nextInt();
                    break;
                }
                catch (InputMismatchException e){
                    System.out.println("**Invalid input, please put an Integer**");
                    sc.next();
                }
            }
            if (choice < users.length && choice >= 0){
                if (users[choice] != null){
                    System.out.println("cart >>> " + users[choice].getCartID());
                    System.out.println("Enter a command ('Help' for command list)...");
                    chooseAnother = sc.next();
                    while (!chooseAnother.equalsIgnoreCase("y")){
                        if (chooseAnother.equalsIgnoreCase("browse")){
                            users[choice].DisplayGUI();
                        }
                        else if (chooseAnother.equalsIgnoreCase("addtocart")){
                            users[choice].AddToCartGUI();
                        }
                        else if (chooseAnother.equalsIgnoreCase("removefromcart")){
                            users[choice].RemoveFromCartGUI();
                        }
                        else if (chooseAnother.equalsIgnoreCase("help")){
                            System.out.println("Command | Description");
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("Browse | Browse what's in inventory");
                            System.out.println("addtocart | add item from inventory to cart");
                            System.out.println("removefromcart | Remove item from cart back to inventory");
                            System.out.println("checkout | proceed to checkout and calculate total");
                            System.out.println("incart | view contents in cart");
                            System.out.println("y | choose to view another store");
                            System.out.println("quit | Terminates the user's cart, and places items back to inventory");
                        }
                        else if (chooseAnother.equalsIgnoreCase("checkout") && users[choice].checkout()){
                            System.out.println("Transaction successful");
                            System.out.println("Now Terminating  Cart >>> " + users[choice].getCartID() + "\n");
                            users[choice] = null;
                            activeSV --;
                            break;
                        }
                        else if (chooseAnother.equalsIgnoreCase("incart")){
                            users[choice].GetContentsOfCart();
                        }
                        else if (chooseAnother.equalsIgnoreCase("quit") && users[choice].returnCartStockToInventory()){
                            System.out.println("Terminated Cart >>> " + users[choice].getCartID() + "\n");
                            users[choice] = null;
                            activeSV --;
                            break;
                        }
                        else{
                            System.out.println("MAIN > ERROR > BAD CHOICE\nNot a Valid Command");
                        }
                        System.out.println("\nGO TO ANOTHER STOREVIEW? (y) >>> ");
                        System.out.println("cart >>> " + users[choice].getCartID());
                        System.out.println("Enter a command ('Help' for command list)...");
                        chooseAnother = sc.next();
                    }
                }
                else{
                    System.out.println("MAIN > ERROR > BAD CHOICE\nThat StoreView was deactivated, please Select Another\n");
                }
            }
            else{
                System.out.printf("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]%n", 0, users.length - 1);
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}
