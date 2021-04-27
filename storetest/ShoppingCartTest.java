package storetest;
import store.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * ShoppingCartTest Class that tests ShoppingCart in store package
 * All tests are done linearly for less repeated code, meaning every test works with the previous
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartTest {
    private static ShoppingCart shoppingCart;
    private static int tests;
    private static Product tempProduct;

    /**
     * Initializes all variables before starting the Tests
     * This occurs before any other method in this class
     */
    @BeforeAll
    public static void init(){
        shoppingCart = new ShoppingCart();
        tests = 1;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~ShoppingCart Class Testing~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Test 1:
     * Checks  the method of adding items to the cart created
     * Will also test the method "checkExists" which returns true iff the item is in the cart, otherwise false
     * Ensures the size is correct using the CheckSize method
     * prints the contents of the current cart
     */
    @Test
    @Order(1)
    public void testAddToCart(){
        System.out.println("Running Test >>> " + tests + " -> Adding to cart");

        tempProduct = new Product("Item 1", "1", 2.99);
        System.out.println("Adding 10 of " + tempProduct.getName() + " to cart");
        shoppingCart.addToCart(tempProduct, 10);
        assertTrue(shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if " + tempProduct.getName() + " is in cart >>> Expected: true, Actual: " + shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));
        assertEquals(10, shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if stock of " + tempProduct.getName() + " is correct >>> Expected 10, Actual: " + shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));

        tempProduct = new Product("Item 2", "2", 2.99);
        System.out.println("Adding 15 of " + tempProduct.getName() + " to cart");
        shoppingCart.addToCart(tempProduct, 15);
        assertTrue(shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if " + tempProduct.getName() + " is in cart >>> Expected: true, Actual: " + shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));
        assertEquals(15, shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if stock of " + tempProduct.getName() + " is correct >>> Expected 15, Actual: " + shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));

        tempProduct = new Product("Item 1", "1", 2.99);
        System.out.println("Adding 10 more of " + tempProduct.getName() + " to cart");
        shoppingCart.addToCart(tempProduct, 10);
        assertEquals(20, shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if stock of " + tempProduct.getName() + " is correct >>> Expected 20, Actual: " + shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));

        assertEquals(2,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 2, Actual: " + shoppingCart.getCartSize());

        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
    }

    /**
     * Test 2:
     * Checks the method of subtracting items from the cart
     * Will also test the method "checkExists" which returns true iff the item is in the cart, otherwise false
     * Ensures the size is correct using the CheckSize method
     * prints the contents of the current cart
     */
    @Test
    @Order(2)
    public void testSubFromCart(){
        System.out.println("Running Test >>> " + tests + " -> Subtracting from cart");
        tempProduct = new Product("Item 1", "1", 2.99);
        System.out.println("Removing 5 of " + tempProduct.getName() + " from cart");
        shoppingCart.removeFromCart(tempProduct, 5);
        assertEquals(15, shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking new stock of " + tempProduct.getName() + " in cart >>> Expected: 15, Actual: " + shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));

        tempProduct = new Product("Item 2", "2", 2.99);
        System.out.println("Removing 10 of " + tempProduct.getName() + " from cart");
        shoppingCart.removeFromCart(tempProduct, 10);
        assertEquals(5, shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking new stock of " + tempProduct.getName() + " in cart >>> Expected: 5, Actual: " + shoppingCart.getStock(Integer.parseInt(tempProduct.getId())));

        tempProduct = new Product("Item 1", "1", 2.99);
        System.out.println("Removing all of " + tempProduct.getName() + " from cart, removing the item itself");
        shoppingCart.removeFromCart(tempProduct, 20);
        assertFalse(shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));
        System.out.println("Checking if " + tempProduct.getName() + " is in cart >>> Expected: false, Actual: " + shoppingCart.checkExists(Integer.parseInt(tempProduct.getId())));

        assertEquals(1,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 1, Actual: " + shoppingCart.getCartSize());

        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
    }

    /**
     * Test 3:
     * Adds a null product to the cart ensuring it doesn't cause an error or add it to the cart
     * will also ensure the size is correct for the cart
     * prints the contents of cart
     * passes if no errors occur
     */
    @Test
    @Order(3)
    public void testAddingNullToCart(){
        System.out.println("Running Test >>> " + tests + " -> Adding null products to cart");
        shoppingCart.addToCart(null, 0);
        shoppingCart.addToCart(null, 50);
        shoppingCart.addToCart(null, 100);
        shoppingCart.addToCart(null, 150);
        assertEquals(1,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 1, Actual: " + shoppingCart.getCartSize());
        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
        System.out.println("Case adding null products passed");
    }

    /**
     * Test4:
     * Adding a negative quantity to the cart. Ensuring it indeed does not add a negative quantity
     * Checks the size of the cart to ensure no negative quantity products were added
     * prints the contents of cart
     * Test passes if no negative quantities are in cart
     */
    @Test
    @Order(4)
    public void testNegativeAddingToCart(){
        System.out.println("Running Test >>> " + tests + " -> Adding negative quantities of products to cart");
        shoppingCart.addToCart(new Product("Item 2", "2", 2.99), -22);
        shoppingCart.addToCart(new Product("Item 2", "2", 2.99), -50);
        shoppingCart.addToCart(new Product("Item 2", "2", 2.99), -100);
        shoppingCart.addToCart(new Product("Item 2", "2", 2.99), -150);
        assertEquals(1,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 1, Actual: " + shoppingCart.getCartSize());
        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
        System.out.println("Case adding negative quantity of products passed");
    }

    /**
     * Test 5:
     * Tries to subtract a null product from the cart
     * checks the size of the cart ensuring subtracting null did not go through
     * prints contents of cart
     * Test passes if null products does not crash the code
     */
    @Test
    @Order(5)
    public void testSubtractingNullFromCart(){
        System.out.println("Running Test >>> " + tests + " -> removing null products from cart");
        shoppingCart.removeFromCart(null, 0);
        shoppingCart.removeFromCart(null, 50);
        shoppingCart.removeFromCart(null, 100);
        shoppingCart.removeFromCart(null, 150);
        assertEquals(1,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 1, Actual: " + shoppingCart.getCartSize());
        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
        System.out.println("Case subtracting null products passed");
    }

    /**
     * Test 6:
     * Tries to subtract negative quantity from cart
     * checks the size of the cart ensuring subtracting negative quantity did not go through
     * prints contents of cart
     * Test passes if contents in cart did not subtract by negative quantity
     */
    @Test
    @Order(6)
    public void testNegativeSubtractingFromCart(){
        System.out.println("Running Test >>> " + tests + " -> Subtracting negative quantities of products to cart");
        shoppingCart.removeFromCart(new Product("Item 2", "2", 2.99), -22);
        shoppingCart.removeFromCart(new Product("Item 2", "2", 2.99), -50);
        shoppingCart.removeFromCart(new Product("Item 2", "2", 2.99), -100);
        shoppingCart.removeFromCart(new Product("Item 2", "2", 2.99), -150);
        assertEquals(1,shoppingCart.getCartSize());
        System.out.println("Expected cart size: 1, Actual: " + shoppingCart.getCartSize());
        System.out.println("**Contents of Cart**");
        for (Product p : shoppingCart.getShoppingCart().keySet()){
            System.out.println("Product: " + p.getName() + ", Quantity: " + shoppingCart.getShoppingCart().get(p));
        }
        System.out.println("Case subtracting negative quantity of products passed");
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
        System.out.println("ShoppingCart class successfully passed all " + (tests - 1)  + " test cases");
    }

}
