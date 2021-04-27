package myStore;

/**
 * Interface class for product stock
 */
public interface ProductStockContainer {
    /**
     * get the product quantity
     * @param product Product, the product
     * @return Integer, the quantity of a product
     */
    int getProductQuantity(Product product);

    /**
     * add product to a stock
     * @param product Product, the product
     * @param amount Integer, the amount to be added
     */
    void addProductQuantity(Product product, int amount);

    /**
     * remove product from stock
     * @param product Product, the product
     * @param amount Integer, the amount to subtract
     */
    void removeProductQuantity(Product product, int amount);

    /**
     * get the number of products in stock
     * @return Integer, the number of products
     */
    int getNumOfProducts();

    /**
     * boolean method if the product exist in stock
     * @param product Product, the product
     * @return boolean, true iff the product exist, otherwise false
     */
    boolean checkIfProductExist(Product product); // Extra
}
