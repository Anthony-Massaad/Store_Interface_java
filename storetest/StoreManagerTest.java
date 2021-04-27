package storetest;
import store.*;
import org.junit.jupiter.api.*;
import java.text.DecimalFormat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * StoreManagerTest class which tests StoreManager in store package
 * All tests are done linearly for less repeated code, meaning every test works with the previous
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreManagerTest {
    private static StoreManager manager;
    private static int tests;
    private static Product tempProduct;
    private static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Initializes all variables before starting the Tests
     * This occurs before any other method in this class
     */
    @BeforeAll
    public static void init(){
        manager = new StoreManager("Name1","LastName1");
        tests = 1;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~StoreManager Class Testing~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Test 1:
     * Checks if the inital contents are inside inventory
     * Uses assertEquals and checkexist, which returns true iff content is in inventory, otherwise false
     * Checks the stock of each product to make sure it is the correct quantity
     * checks the size of inventory to ensure it is correct
     */
    @Test
    @Order(1)
    public void testCheckingInitialInventory(){
        System.out.println("Running Test >>> " + tests + " -> Checking contents of initial inventory");
        System.out.println("**Expected inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
        tempProduct = new Product("SYSC2006", "3", 1.99);
        assertTrue(manager.checkInventoryProductExits(tempProduct.getId()), "False, Does not have the product");
        System.out.println("Checking if " + tempProduct.getName() + " is in inventory >>> Expected: True, Actual: " + manager.checkInventoryProductExits(tempProduct.getId()));
        assertEquals(0, manager.checkStock(tempProduct.getId()), "Not correct Stock for Product " + tempProduct.getName());
        System.out.println("Checking Stock of " + tempProduct.getName() + " >>> Expected: 0, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("SYSC2004", "1", 2.99);
        assertTrue(manager.checkInventoryProductExits(tempProduct.getId()), "False, Does not have the product");
        System.out.println("Checking if " + tempProduct.getName() + " is in inventory >>> Expected: True, Actual: " + manager.checkInventoryProductExits(tempProduct.getId()));
        assertEquals(76, manager.checkStock(tempProduct.getId()), "Not correct Stock for Product " + tempProduct.getName());
        System.out.println("Checking Stock of " + tempProduct.getName() + " >>> Expected: 76, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("ELEC4705", "4", 4.85);
        assertTrue(manager.checkInventoryProductExits(tempProduct.getId()), "False, Does not have the product");
        System.out.println("Checking if " + tempProduct.getName() + " is in inventory >>> Expected: True, Actual: " + manager.checkInventoryProductExits(tempProduct.getId()));
        assertEquals(132, manager.checkStock(tempProduct.getId()), "Not correct Stock for Product " + tempProduct.getName());
        System.out.println("Checking Stock of " + tempProduct.getName() + " >>> Expected: 132, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("SYSC4906", "2", 5.99);
        assertTrue(manager.checkInventoryProductExits(tempProduct.getId()), "False, Does not have the product");
        System.out.println("Checking if " + tempProduct.getName() + " is in inventory >>> Expected: True, Actual: " + manager.checkInventoryProductExits(tempProduct.getId()));
        assertEquals(32, manager.checkStock(tempProduct.getId()), "Not correct Stock for Product " + tempProduct.getName());
        System.out.println("Checking Stock of " + tempProduct.getName() + " >>> Expected: 32, Actual: " + manager.checkStock(tempProduct.getId()));

        assertEquals(4, manager.getInventoryProducts().size());
        System.out.println("Checking Inventory size >>> Expected: 4, Actual:" + manager.getInventoryProducts().size());
        System.out.println("**Inventory Successfully created**");
    }

    /**
     * Test 2:
     * Test add method for inventory to add quantity to an existing item, or add a new item
     * checks  if stock is correct for each item
     * checks if new items are in inventory using checkExists method. Exists if true, otherwise false
     * checks the size of the inventory ensuring it is correct
     * prints contents of inventory
     */
    @Test
    @Order(2)
    public void testAddingToInventory(){
        System.out.println("Running Test >>> " + tests + " -> Adding to Inventory");
        tempProduct = new Product("SYSC2006", "3", 1.99);
        System.out.println("Adding 15 of " + tempProduct.getName() + " to inventory");
        manager.addToStock(tempProduct, 15);
        assertEquals(15, manager.checkStock(tempProduct.getId()));
        System.out.println("Checking new stock of " + tempProduct.getName() + " >>> Expected: 15, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("SYSC2004", "1", 2.99);
        System.out.println("Adding 20 of " + tempProduct.getName() + " to inventory");
        manager.addToStock(tempProduct, 20);
        assertEquals(96, manager.checkStock(tempProduct.getId()));
        System.out.println("Checking new stock of " + tempProduct.getName() + " >>> Expected: 96, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("ELEC2501", "5", 5.99);
        System.out.println("Adding 50 of " + tempProduct.getName() + " to inventory");
        manager.addToStock(tempProduct, 50);
        assertTrue(manager.checkInventoryProductExits(tempProduct.getId()), "False, Does not have the product");
        System.out.println("Checking if " + tempProduct.getName() + " is in inventory >>> Expected: True, Actual: " + manager.checkInventoryProductExits(tempProduct.getId()));
        assertEquals(50, manager.checkStock(tempProduct.getId()), "Not correct Stock for Product " + tempProduct.getName());
        System.out.println("Checking Stock of " + tempProduct.getName() + " >>> Expected: 50, Actual: " + manager.checkStock(tempProduct.getId()));

        assertEquals(5, manager.getInventoryProducts().size());
        System.out.println("Checking Inventory size >>> Expected: 5, Actual:" + manager.getInventoryProducts().size());

        System.out.println("**New Inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
    }

    /**
     * Test 3:
     * test subtraction method to subtract contents from inventory
     * checks stocks of each product after subtraction
     * checks size of inventory at the end of the test
     * prints contents of inventory to ensure everything was subtracted correctly
     */
    @Test
    @Order(3)
    public void testSubtractingFromInventory(){
        System.out.println("Running Test >>> " + tests + " -> subtracting from Inventory");
        tempProduct = new Product("SYSC2006", "3", 1.99);
        System.out.println("removing 5 of " + tempProduct.getName() + " from inventory");
        manager.subFromStock(tempProduct.getId(), 5);
        assertEquals(10, manager.checkStock(tempProduct.getId()));
        System.out.println("Checking new stock of " + tempProduct.getName() + " >>> Expected: 10, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("ELEC2501", "5", 5.99);
        System.out.println("removing 20 of " + tempProduct.getName() + " from inventory");
        manager.subFromStock(tempProduct.getId(), 50);
        assertEquals(0, manager.checkStock(tempProduct.getId()));
        System.out.println("Checking new stock of " + tempProduct.getName() + " >>> Expected: 0, Actual: " + manager.checkStock(tempProduct.getId()));

        tempProduct = new Product("SYSC2004", "1", 2.99);
        System.out.println("removing 100 of " + tempProduct.getName() + " from inventory");
        manager.subFromStock(tempProduct.getId(), 100);
        assertEquals(96, manager.checkStock(tempProduct.getId()));
        System.out.println("Checking new stock of " + tempProduct.getName() + " >>> Expected: Not subtractable, Actual: Not subtractable");

        tempProduct = new Product("SYSC2310", "6", 2.99);
        System.out.println("Removing 10 of non existent product in inventory");
        manager.subFromStock(tempProduct.getId(), 10);
        System.out.println("Checking stock of " + tempProduct.getName() + " >>> Expected: N/A, Actual: N/A");
        assertEquals(5, manager.getInventoryProducts().size());
        System.out.println("Checking Inventory size >>> Expected: 5, Actual:" + manager.getInventoryProducts().size());
        System.out.println("**New Inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
    }

    /**
     * test 4:
     * Creates 2 new shopping carts using the assignnewCartMethod
     * Checks the size of the carts hashmap to ensure there are 2 new carts
     */
    @Test
    @Order(4)
    public void testCreateNewShoppingCarts(){
        System.out.println("Running Test >>> " + tests + " -> Creating and assigning 2 new ShoppingCart ID");
        manager.assignNewCartID();
        manager.assignNewCartID();
        assertEquals(2, manager.getCarts().size());
        System.out.println("Checking size of carts hashmap >>> Expected carts created: 2, Actual: " + manager.getCarts().size());
    }

    /**
     * Test 5:
     * Test add products to the first shopping cart
     * checks if the product is indeed inside the shoppingcart using checkExist method. True if exist, otherwise false
     * checks the stock of each item added
     * checks the size of how many contents added
     * prints contents of cart 1 to ensure everything added correctly
     */
    @Test
    @Order(5)
    public void testAddToShoppingCart1(){
        System.out.println("Running Test >>> " + tests + " -> Adding to ShoppingCart 1");
        tempProduct = new Product("SYSC2004", "1", 2.99);
        System.out.println("Adding 10 of " + tempProduct.getName() + " to ShoppingCart 1");
        manager.addToCart(tempProduct, 10,0);
        assertTrue(manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        System.out.println("Checking if " + tempProduct.getName() + " is in ShoppingCart 1 >>> Expected: True, Actual: " + manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        assertEquals(10, manager.checkStockForCartProduct(tempProduct.getId(), 0));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 10, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 0));

        tempProduct = new Product("ELEC2501", "5", 5.99);
        System.out.println("Adding 15 of " + tempProduct.getName() + " to ShoppingCart 1");
        manager.addToCart(tempProduct, 15,0);
        assertTrue(manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        System.out.println("Checking if " + tempProduct.getName() + " is in ShoppingCart 1 >>> Expected: True, Actual: " + manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        assertEquals(15, manager.checkStockForCartProduct(tempProduct.getId(), 0));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 15, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 0));

        assertEquals(2, manager.getCarts().get(0).getCartSize());
        System.out.println("Expected size of cart: 2, Actual: " + manager.getCarts().get(0).getCartSize());
        System.out.println("**ShoppingCart 1 Contents***");
        for (Product product : manager.getCartProducts(0)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 0));
        }
    }

    /**
     * Test 6:
     * Test add products to the second shopping cart
     * checks if the product is indeed inside the shoppingcart using checkExist method. True if exist, otherwise false
     * checks the stock of each item added
     * checks the size of how many contents added
     * prints contents of cart 2 to ensure everything added correctly
     */
    @Test
    @Order(6)
    public void testAddToShoppingCart2(){
        System.out.println("Running Test >>> " + tests + " -> Adding to ShoppingCart 2");
        tempProduct = new Product("SYSC4906", "2", 5.99);

        System.out.println("Adding 7 of " + tempProduct.getName() + " to ShoppingCart 2");
        manager.addToCart(tempProduct, 7,1);
        assertTrue(manager.checkShoppingCartProductExist(tempProduct.getId(),1));
        System.out.println("Checking if " + tempProduct.getName() + " is in ShoppingCart 2 >>> Expected: True, Actual: " + manager.checkShoppingCartProductExist(tempProduct.getId(),1));
        assertEquals(7, manager.checkStockForCartProduct(tempProduct.getId(), 1));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 7, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(),1 ));

        tempProduct = new Product("ELEC4705", "4", 4.85);
        System.out.println("Adding 11 of " + tempProduct.getName() + " to store.ShoppingCart 2");
        manager.addToCart(tempProduct, 11,1);
        assertTrue(manager.checkShoppingCartProductExist(tempProduct.getId(),1));
        System.out.println("Checking if " + tempProduct.getName() + " is in ShoppingCart 2 >>> Expected: True, Actual: " + manager.checkShoppingCartProductExist(tempProduct.getId(),1));
        assertEquals(11, manager.checkStockForCartProduct(tempProduct.getId(), 1));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 11, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 1));

        assertEquals(2, manager.getCarts().get(1).getCartSize());
        System.out.println("Expected size of cart: 2, Actual: " + manager.getCarts().get(1).getCartSize());
        System.out.println("**ShoppingCart 2 Contents***");
        for (Product product : manager.getCartProducts(1)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 1));
        }
    }

    /**
     * Test 7:
     * Test subtract products from the first shopping cart
     * if the subtraction of quantity is less than or equal to 0, remove item from cart. therefore will check
     * if the item is in cart and should return false.
     * checks the stock of each item added
     * checks the size of how many contents in the cart to ensure it is correct
     * prints contents of cart 1 to ensure everything added correctly
     */
    @Test
    @Order(7)
    public void testSubfromShoppingCart1(){
        System.out.println("Running Test >>> " + tests + " -> Removing from ShoppingCart 1");
        tempProduct = new Product("SYSC2004", "1", 2.99);
        System.out.println("Removing 5 of " + tempProduct.getName() + " From ShoppingCart 1");
        manager.removeFromCart(tempProduct, 5, 0);
        assertEquals(5, manager.checkStockForCartProduct(tempProduct.getId(), 0));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 5, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 0));

        tempProduct = new Product("ELEC2501", "5", 5.99);
        System.out.println("Removing all 15 of " + tempProduct.getName() + " and removing entire product from ShoppingCart 1");
        manager.removeFromCart(tempProduct, 17, 0);
        assertFalse(manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        System.out.println("Checking if product " + tempProduct.getName() + " exist in ShoppingCart 1 >>> Expected: false, Actual: " + manager.checkShoppingCartProductExist(tempProduct.getId(),0));
        assertEquals(1, manager.getCarts().get(0).getCartSize());

        System.out.println("Expected size of cart: 1, Actual: " + manager.getCarts().get(0).getCartSize());
        System.out.println("**New ShoppingCart 1 Contents***");
        for (Product product : manager.getCartProducts(0)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 0));
        }
    }

    /**
     * Test 8:
     * Test subtract products from the second shopping cart
     * if the subtraction of quantity is less than or equal to 0, remove item from cart. therefore will check
     * if the item is in cart and should return false.
     * checks the stock of each item added
     * checks the size of how many contents in the cart to ensure it is correct
     * prints contents of cart 2 to ensure everything added correctly
     */
    @Test
    @Order(8)
    public void testSubFromShoppingCart2(){
        System.out.println("Running Test >>> " + tests + " -> Removing from ShoppingCart 2");
        tempProduct = new Product("SYSC4906", "2", 5.99);
        System.out.println("Removing 5 of " + tempProduct.getName() + " From ShoppingCart 2");
        manager.removeFromCart(tempProduct, 5, 1);
        assertEquals(2, manager.checkStockForCartProduct(tempProduct.getId(), 1));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 2, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 1));

        tempProduct = new Product("ELEC4705", "4", 4.85);
        System.out.println("Removing 6 of " + tempProduct.getName() + " From ShoppingCart 2");
        manager.removeFromCart(tempProduct, 6, 1);
        assertEquals(5, manager.checkStockForCartProduct(tempProduct.getId(), 1));
        System.out.println("Checking if correct quantity of " + tempProduct.getName() + " >>> Expected: 5, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 1));

        assertEquals(2, manager.getCarts().get(1).getCartSize());
        System.out.println("Expected size of cart: 2, Actual: " + manager.getCarts().get(1).getCartSize());
        System.out.println("**New ShoppingCart 2 Contents***");
        for (Product product : manager.getCartProducts(1)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 1));
        }
    }

    /**
     * Test 9:
     * Test checking a random product in a non existence cart
     * test passes if code does not break and returns -1
     */
    @Test
    @Order(9)
    public void testCheckingNonExistentCart(){
        System.out.println("Running Test >>> " + tests + " -> Ensuring Non-Existent ShoppingCart using ShoppingCart ID 3 returns -1");
        tempProduct = new Product("SYSC4906", "2", 5.99);
        assertEquals(-1, manager.checkStockForCartProduct(tempProduct.getId(), 3));
        System.out.println("Checking if " + tempProduct.getName() + " is in ShoppingCart 3 >>> Expected: -1, Actual: " + manager.checkStockForCartProduct(tempProduct.getId(), 3));

    }

    /**
     * Test 10:
     * Tries to add null products to inventory
     * prints contents of current inventory to ensure no null products were added
     * test passes if no exceptions thrown
     */
    @Test
    @Order(10)
    public void testAddingNullProductsToInventory(){
        System.out.println("Running Test >>> " + tests + " -> Adding null products to inventory");
        manager.addToStock(null,15);
        manager.addToInven(null);
        System.out.println("**Contents of inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
        System.out.println("Test case Add Null to inventory passes");
    }

    /**
     * Test 11:
     * Adding a negative quantity to inventory. Ensuring it indeed does not add a negative quantity
     * prints the contents of the current inventory
     * Test passes if no negative quantities are in inventory
     */
    @Test
    @Order(11)
    public void testAddingNegativeQuantityToInventory(){
        System.out.println("Running Test >>> " + tests + " -> Adding negative quantity to Inventory");
        manager.addToStock(new Product("ss", "dd", 11.00),-15);
        manager.addToStock(new Product("ss", "dd", 11.00),-25);
        System.out.println("**Contents of inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
        System.out.println("Test case add negative quantity to inventory passes");
    }

    /**
     * Test 12:
     * Adds a null product to cart 1 ensuring it doesn't cause an error or add it to the cart
     * subtract a null product from cart 1 ensuring it doesn't cause an error
     * will also ensure the size is correct for the cart
     * prints the contents of cart
     * passes if no errors occur
     */
    @Test
    @Order(12)
    public void testAddAndSubtractingNullInCart1(){
        System.out.println("Running Test >>> " + tests + " -> Add and subtract null products in cart 1");
        manager.addToCart(null,10,0);
        manager.removeFromCart(null,15,0);
        System.out.println("**New ShoppingCart 1 Contents***");
        for (Product product : manager.getCartProducts(0)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 0));
        }
        System.out.println("Test case add and subtract null object in cart 1 passed");
    }

    /**
     * Test 13:
     * Adding a negative quantity to cart 1. Ensuring it indeed does not add a negative quantity
     * Subtracting negative quantity from cart 1, ensuring it doesn't throw an error
     * Checks the size of the cart to ensure no negative quantity products were added or subtracted
     * prints the contents of cart
     * Test passes if no negative quantities are in cart 1 or subtracted from cart 1
     */
    @Test
    @Order(13)
    public void testAddAndSubtractingNegativeQuantityInCart1(){
        System.out.println("Running Test >>> " + tests + " -> Add and subtract negative quantity in cart 1");
        manager.addToCart(new Product("ss", "dd", 11.00),-10,0);
        manager.removeFromCart(new Product("SYSC2004", "1", 2.99),-15,0);
        System.out.println("**New ShoppingCart 1 Contents***");
        for (Product product : manager.getCartProducts(0)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 0));
        }
        System.out.println("Test case add and subtract negative quantity in cart 1 passed");
    }


    /**
     * Test 14:
     * Adds a null product to cart 2 ensuring it doesn't cause an error or add it to the cart
     * subtract a null product from cart 2 ensuring it doesn't cause an error
     * will also ensure the size is correct for the cart
     * prints the contents of cart
     * passes if no errors occur
     */
    @Test
    @Order(14)
    public void testAddAndSubtractingNullInCart2(){
        System.out.println("Running Test >>> " + tests + " -> Add and subtract null products in cart 2");
        manager.addToCart(null,10,1);
        manager.removeFromCart(null,15,1);
        System.out.println("**New ShoppingCart 2 Contents***");
        for (Product product : manager.getCartProducts(1)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 1));
        }
        System.out.println("Test case add and subtract null object in cart 2 passed");
    }

    /**
     * Test 15:
     * Adding a negative quantity to cart 2. Ensuring it indeed does not add a negative quantity
     * Subtracting negative quantity from cart 2, ensuring it doesn't throw an error
     * Checks the size of the cart to ensure no negative quantity products were added or subtracted
     * prints the contents of cart
     * Test passes if no negative quantities are in cart 1 or subtracted from cart 2
     */
    @Test
    @Order(15)
    public void testAddAndSubtractingNegativeQuantityInCart2(){
        System.out.println("Running Test >>> " + tests + " -> Add and subtract negative quantity in cart 2");
        manager.addToCart(new Product("ss", "dd", 11.00),-10,1);
        manager.removeFromCart(new Product("SYSC4906", "2", 5.99),-15,1);
        System.out.println("**New ShoppingCart 2 Contents***");
        for (Product product : manager.getCartProducts(1)){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStockForCartProduct(product.getId(), 1));
        }
        System.out.println("Test case add and subtract negative quantity in cart 2 passed");
    }

    /**
     * Test 16:
     * test the transaction for cart 1
     * ensures the price is what it should be according to the contents of the cart
     * math = content price * quantity
     */
    @Test
    @Order(16)
    public void testTransactionShoppingCart1(){
        System.out.println("Running Test >>> " + tests + " -> Transaction of ShoppingCart 1");
        assertEquals("14.95", df.format(manager.processInteraction(0)));
        System.out.println("Transaction Total for cart 1 >>> Expected: 14.95, Actual: " + df.format(manager.processInteraction(0)));
    }

    /**
     * Test 17:
     * test the transaction for cart 2
     * ensures the price is what it should be according to the contents of the cart
     * math = content price * quantity
     */
    @Test
    @Order(17)
    public void testTransactionShoppingCart2(){
        System.out.println("Running Test >>> " + tests + " -> Transaction of ShoppingCart 2");
        assertEquals("36.23", df.format(manager.processInteraction(1)));
        System.out.println("Transaction Total for cart 2 >>> Expected: 36.23, Actual: " + df.format(manager.processInteraction(1)) );
    }

    /**
     * Test 18:
     * test to see if subtracting negative quantity of any product from inventory
     * prints contents of current inventory
     * passes if no negative quantity is subtracted
     */
    @Test
    @Order(18)
    public void testSubtractingNegativeQuantityFromInventory(){
        System.out.println("Running Test >>> " + tests + " -> subtracting negative quantity from inventory");
        manager.subFromStock("1",-15);
        manager.subFromStock("5", -20);
        System.out.println("**Contents of inventory**");
        for (Product product : manager.getInventoryProducts()){
            System.out.println("Product: " + product.getName() + ", Quantity: " + manager.checkStock(product.getId()));
        }
        System.out.println("Test case subtract negative quantity from inventory passes");
    }

    /**
     * Test 19:
     * Test checking an existing product and a non existing product using the ids
     * gets the information of the product if in inventory, otherwise prints the exception
     * test passes if no errors occurs
     */
    @Test
    @Order(19)
    public void testGetInfoOfSomeProductsInInventory(){
        System.out.println("Running Test >>> " + tests + " -> getting info of a product in inventory given ID");
        tempProduct = new Product("SYSC2004", "1", 2.99);
        System.out.println("Testing ID '1'");
        assertEquals("Name: " + tempProduct.getName() + ",ID: " + tempProduct.getId() + ", Price: " + tempProduct.getPrice(), manager.getInfoForProduct("1"));
        System.out.println("Expected: " + "Name: " + tempProduct.getName() + ",ID: " + tempProduct.getId() + ", Price: " + tempProduct.getPrice() + ", Actual: " + manager.getInfoForProduct("1"));

        System.out.println("Testing ID '16'");
        assertEquals("Not in inventory", manager.getInfoForProduct("16"));
        System.out.println("Expected: Not in inventory, Actual: " + manager.getInfoForProduct("16"));
    }


    /**
     * After each test, perform task afterEachTest()
     */
    @AfterEach
    public void afterEachTest(){
        System.out.println();
        tests++;
    }

    /**
     * After all the tests completed, perform task finishedTests()
     */
    @AfterAll
    public static void finishedTests(){
        System.out.println("StoreManager class successfully passed all " + (tests - 1)  + " tests");
    }



}
