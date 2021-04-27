package myStore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.Font.PLAIN;

/**
 * StoreView GUI class
 */
public class StoreView{
    private int currentCardID;
    private boolean cart1Enabled, cart2Enabled, cart3Enabled;
    private final StoreManager manager;
    private  final JFrame frame, areYouSure, checkoutFrame, checkoutExitFrame;
    private final JTextArea bigReceipt, totalprintout;
    private  final JPanel panel, checkoutPanel, exitPanel;
    private  final Image product1_ig, product2_ig, product3_ig, product4_ig;
    private  final JLabel product1_lb, product2_lb, product3_lb, product4_lb, cart_lb, storeTitle_lb, cartTitle_lb;
    private  final JButton addProduct1_btn, addProduct2_btn, addProduct3_btn, addProduct4_btn;
    private  final JButton cart1_btn, cart2_btn, cart3_btn;
    private  final JButton subProduct1_btn, subProduct2_btn, subProduct3_btn, subProduct4_btn;
    private  final JLabel product1Stock, product2Stock, product3Stock, product4Stock;
    private final JLabel leaveMessage;
    private final JButton checkout_btn, exit_btn, clear_btn;
    private String cart1Name, cart2Name, cart3Name;
    private final Product invenP1, invenP2, invenP3, invenP4;
    public StoreView (StoreManager manager){
        this.cart1Name = "Store Cart 1";
        this.cart2Name = "Store Cart 2";
        this.cart3Name = "Store Cart 3";
        this.checkoutExitFrame = new JFrame("Wish to continue?");
        this.exitPanel = new JPanel(null);
        this.leaveMessage = new JLabel();
        this.areYouSure = new JFrame("");
        this.checkoutPanel = new JPanel(null);
        this.bigReceipt = new JTextArea("", 36,5);
        this.totalprintout = new JTextArea("",36,5);
        this.checkoutFrame = new JFrame("Transaction");
        this.cart1Enabled = true;
        this.cart2Enabled = true;
        this.cart3Enabled = true;
        this.manager = manager;
        this.manager.assignNewCartID();
        this.manager.assignNewCartID();
        this.manager.assignNewCartID();
        this.invenP1 = this.manager.getInventoryProductByID("1");
        this.invenP2 = this.manager.getInventoryProductByID("2");
        this.invenP3 = this.manager.getInventoryProductByID("3");
        this.invenP4 = this.manager.getInventoryProductByID("4");
        this.currentCardID = 0;
        this.frame = new JFrame("StoreView");
        this.panel = new JPanel();
        this.panel.setLayout(null);
        //TOP of GUI
        this.storeTitle_lb = new JLabel("**MILESTONE 4**", SwingConstants.CENTER);
        this.cart1_btn = new JButton(this.cart1Name + " (Active)");
        this.cart2_btn = new JButton(this.cart2Name);
        this.cart3_btn = new JButton(this.cart3Name);
        //PRODUCT 1
        this.product1_lb = new JLabel(this.manager.getInfoForProduct("1"), SwingConstants.CENTER);
        this.product1_ig = new ImageIcon(this.getClass().getResource("OOPTextbook.PNG")).getImage();
        this.addProduct1_btn = new JButton("+");
        this.subProduct1_btn = new JButton("-");
        this.subProduct1_btn.setEnabled(false);
        this.product1Stock = new JLabel("Quantity available: " + this.manager.checkStock(this.invenP1), SwingConstants.CENTER);
        //PRODUCT 2
        this.product2_lb = new JLabel(this.manager.getInfoForProduct("2"), SwingConstants.CENTER);
        this.product2_ig = new ImageIcon(this.getClass().getResource("4Pens.PNG")).getImage();
        this.addProduct2_btn = new JButton("+");
        this.subProduct2_btn = new JButton("-");
        this.subProduct2_btn.setEnabled(false);
        this.product2Stock = new JLabel("Quantity available: " + this.manager.checkStock(this.invenP2), SwingConstants.CENTER);
        //PRODUCT 3
        this.product3_lb = new JLabel(this.manager.getInfoForProduct("3"), SwingConstants.CENTER);
        this.product3_ig = new ImageIcon(this.getClass().getResource("Paper.PNG")).getImage();
        this.addProduct3_btn = new JButton("+");
        this.subProduct3_btn = new JButton("-");
        this.subProduct3_btn.setEnabled(false);
        this.product3Stock = new JLabel("Quantity available: " + this.manager.checkStock(this.invenP3), SwingConstants.CENTER);
        //PRODUCT 4
        this.product4_lb = new JLabel(this.manager.getInfoForProduct("4"), SwingConstants.CENTER);
        this.product4_ig = new ImageIcon(this.getClass().getResource("Java.PNG")).getImage();
        this.addProduct4_btn = new JButton("+");
        this.subProduct4_btn = new JButton("-");
        this.subProduct4_btn.setEnabled(false);
        this.product4Stock = new JLabel("Quantity available: " + this.manager.checkStock(this.invenP4), SwingConstants.CENTER);
        //RIGHT of GUI
        this.cartTitle_lb = new JLabel("Store Cart >>> " + (this.currentCardID + 1), SwingConstants.CENTER);
        this.cart_lb = new JLabel(this.manager.getInfoOfCartProducts(this.currentCardID));
        //Bottom Right GUI
        this.checkout_btn = new JButton("Checkout");
        this.checkout_btn.setEnabled(false);
        this.exit_btn = new JButton("Exit");
        this.clear_btn = new JButton("Clear Cart");
        this.frame.add(this.panel);
        this.frame.setSize(900,838);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(dim.width/2-this.frame.getSize().width/2, dim.height/2-this.frame.getSize().height/2);
        this.frame.setResizable(false);

    }

    /**
     * Method that either enables or disables the checkout button depending if there is
     * contents in the cart or not
     */
    private void checkCheckoutButton(){
        this.checkout_btn.setEnabled(this.manager.getCartProducts(currentCardID).size() != 0);
    }

    /**
     * Checking if all the carts are disabled. if so, display message and terminate the program
     */
    private void checkingAllDisabled(){
        if (!this.cart1Enabled && !this.cart2Enabled && !this.cart3Enabled){
            JFrame terminating = new JFrame("Terminate");
            JPanel terminatingPanel = new JPanel(null);
            JLabel terminatingLabel = new JLabel("All carts disabled, Terminating program");
            JButton ok_btn = new JButton("Ok");
            ok_btn.setBounds(110,60,64,32);
            ok_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            terminatingLabel.setBounds(30,0,300,32);
            terminating.add(ok_btn);
            terminatingPanel.add(terminatingLabel);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            terminating.setSize(300,150);
            terminating.setLocation(dim.width/2-terminating.getSize().width/2, dim.height/2-terminating.getSize().height/2);
            terminating.setResizable(false);
            terminating.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            terminating.add(terminatingPanel);
            frame.setVisible(false);
            checkoutExitFrame.setVisible(false);
            areYouSure.setVisible(false);
            checkoutFrame.setVisible(false);
            terminating.setVisible(true);
        }
        else{
            areYouSure.setVisible(false);
            checkoutFrame.setVisible(false);
            checkoutExitFrame.setVisible(false);
            frame.setVisible(true);
        }
    }

    /**
     * When disabling the carts, check which carts are left active and which aren't active,
     * diactivates current cart, and proceeds to the next cart that is active
     */
    private void disableCart(){
        if (this.currentCardID == 0){
            this.cart1Enabled = false;
            this.cart1Name = "Disabled";
            if (this.cart2Enabled){
                this.currentCardID = 1;
                this.cart2_btn.setText(this.cart2Name + " (Active)");
                this.cart2_btn.setEnabled(false);
            }
            else if (this.cart3Enabled){
                this.currentCardID = 2;
                this.cart3_btn.setText(this.cart3Name + " (Active)");
                this.cart3_btn.setEnabled(false);
            }
            this.cart1_btn.setText(this.cart1Name);
        }
        else if (this.currentCardID == 1){
            this.cart2Enabled = false;
            this.cart2Name = "Disabled";
            if (this.cart1Enabled){
                this.currentCardID = 0;
                this.cart1_btn.setText(this.cart1Name + " (Active)");
                this.cart1_btn.setEnabled(false);
            }
            else if (this.cart3Enabled){
                this.currentCardID = 2;
                this.cart3_btn.setText(this.cart3Name + " (Active)");
                this.cart3_btn.setEnabled(false);
            }
            this.cart2_btn.setText(this.cart2Name);
        }
        else if (this.currentCardID == 2){
            this.cart3Enabled = false;
            this.cart3Name = "Disabled";
            if (this.cart1Enabled){
                this.currentCardID = 0;
                this.cart1_btn.setText(this.cart1Name + " (Active)");
                this.cart1_btn.setEnabled(false);
            }
            else if (this.cart2Enabled){
                this.currentCardID = 1;
                this.cart2_btn.setText(this.cart2Name + " (Active)");
                this.cart2_btn.setEnabled(false);
            }
            this.cart3_btn.setText(this.cart3Name);
        }
        checkingAllDisabled();
    }

    /**
     * GUI display for the checkout, clear cart and exit button
     * checkout - when user is ready, proceeds to checkout and print receipt using a different frame.
     *            After, asks whether the user wants to continue shopping or terminate
     * clear cart - clear the current cart contents back to inventory
     * exit - terminates the current cart if the user wishes to terminate
     */
    private void bottomRightGUI(){
        this.checkout_btn.setBounds(690, 500, 100,32);
        this.checkout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutFrame.setResizable(false);
                checkoutFrame.setSize(316,370);
                bigReceipt.setEditable(false);
                totalprintout.setEditable(false);
                bigReceipt.setBounds(0,0,300,180);
                totalprintout.setBounds(0,200,300,300);
                StringBuilder s =
                        new StringBuilder(String.format("%50s", "Milestone 4\n") + String.format("%50s", "Carleton University\n") +
                                String.format("%50s", "SYSC2004 Transactions >>>\n") + String.format("%50s", "Cart >>> " + (currentCardID + 1) + "\n")
                        + "       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                double subtotal = 0;
                for (int i = 0; i<manager.getCartProducts(currentCardID).size(); i++){
                    s.append(manager.getCartQuantities(currentCardID).get(i)).append(" of product ").append(manager.getCartProducts(currentCardID).get(i).getName())
                    .append(" $").append(manager.getCartProducts(currentCardID).get(i).getPrice()).append(" Each\n");
                    subtotal += manager.getCartProducts(currentCardID).get(i).getPrice() * manager.getCartQuantities(currentCardID).get(i);
                }
                double HST = subtotal * 0.13;
                double total = subtotal + HST;
                bigReceipt.setText(s.toString());
                String totalReceipt = "Subtotal: $" + String.format("%.2f", subtotal) + "\n" +
                        "HST 13.00%: $" + String.format("%.2f", HST) + "\n" +
                        "Total: " + String.format("%.2f", total) + "\n" +
                        "Paid With Credit Card\n" + "Transaction Successful!\n" + "Thank You!";
                totalprintout.setText(totalReceipt);
                bigReceipt.setFont(new Font("Serif", Font.BOLD, 12));
                totalprintout.setFont(new Font("Serif", Font.BOLD, 12));
                JButton ok = new JButton("Ok");
                ok.setBounds(117,300,64,32);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manager.transactionClear(currentCardID);
                        refreshNewCartID();
                        checkCheckoutButton();
                        checkoutFrame.setVisible(false);
                        JLabel message = new JLabel("Yes to terminate your cart");
                        JLabel message2 = new JLabel("No to continue shopping");
                        JButton yes_btn = new JButton("Yes");
                        JButton no_btn = new JButton("No");
                        JPanel checkoutExit = new JPanel(null);
                        message.setBounds(65,0,300,32);
                        message2.setBounds(65,20,300,32);
                        yes_btn.setBounds(45,65,64,32);
                        no_btn.setBounds(165,65,64,32);
                        checkoutExit.add(message);
                        checkoutExit.add(message2);
                        checkoutExit.add(yes_btn);
                        yes_btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                manager.transactionClear(currentCardID);
                                disableCart();
                                refreshNewCartID();
                            }
                        });
                        checkoutExit.add(no_btn);
                        no_btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                manager.transactionClear(currentCardID);
                                checkoutExitFrame.setVisible(false);
                                frame.setVisible(true);
                            }
                        });
                        checkoutExitFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        checkoutExitFrame.setSize(300,150);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        checkoutExitFrame.setLocation(dim.width/2-checkoutExitFrame.getSize().width/2, dim.height/2-checkoutExitFrame.getSize().height/2);
                        checkoutExitFrame.setResizable(false);
                        checkoutExitFrame.add(checkoutExit);
                        checkoutExitFrame.setVisible(true);
                    }
                });
                checkoutPanel.add(ok);
                checkoutPanel.add(bigReceipt);
                checkoutPanel.add(totalprintout);
                checkoutFrame.add(checkoutPanel);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                checkoutFrame.setLocation(dim.width/2-checkoutFrame.getSize().width/2, dim.height/2-checkoutFrame.getSize().height/2);
                checkoutFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                checkoutFrame.setVisible(true);
                frame.setVisible(false);
            }
        });
        this.clear_btn.setBounds(690, 600, 100,32);
        this.clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.clear(currentCardID);
                refreshButtonsAndStock();
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                checkCheckoutButton();
            }
        });
        this.exit_btn.setBounds(690,700,100,32);
        this.exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaveMessage.setText("Are you Sure you want to terminate the cart " + (currentCardID + 1) + "?");
                JButton yes_btn = new JButton("Yes");
                JButton no_btn = new JButton("No");
                leaveMessage.setBounds(12,10,300,32);
                yes_btn.setBounds(45,45,64,32);
                no_btn.setBounds(165,45,64,32);
                exitPanel.add(leaveMessage);
                exitPanel.add(yes_btn);
                yes_btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manager.clear(currentCardID);
                        refreshButtonsAndStock();
                        disableCart();
                        refreshNewCartID();
                    }
                });
                exitPanel.add(no_btn);
                no_btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        areYouSure.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                areYouSure.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                areYouSure.setSize(300,150);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                areYouSure.setLocation(dim.width/2-areYouSure.getSize().width/2, dim.height/2-areYouSure.getSize().height/2);
                areYouSure.setResizable(false);
                areYouSure.add(exitPanel);
                frame.setVisible(false);
                areYouSure.setVisible(true);
            }
        });
        this.panel.add(this.clear_btn);
        this.panel.add(this.checkout_btn);
        this.panel.add(this.exit_btn);
    }

    /**
     * Private method to refresh the button
     */
    private void refreshButtonsAndStock(){
        addProduct1_btn.setEnabled(manager.checkStock(this.invenP1) != 0);
        addProduct2_btn.setEnabled(manager.checkStock(this.invenP2) != 0);
        addProduct3_btn.setEnabled(manager.checkStock(this.invenP3) != 0);
        addProduct4_btn.setEnabled(manager.checkStock(this.invenP4) != 0);
        subProduct1_btn.setEnabled(manager.checkShoppingCartProductExist(this.invenP1, currentCardID));
        subProduct2_btn.setEnabled(manager.checkShoppingCartProductExist(this.invenP2, currentCardID));
        subProduct3_btn.setEnabled(manager.checkShoppingCartProductExist(this.invenP3, currentCardID));
        subProduct4_btn.setEnabled(manager.checkShoppingCartProductExist(this.invenP4, currentCardID));
        product1Stock.setText("Quantity available: " + manager.checkStock(this.invenP1));
        product2Stock.setText("Quantity available: " + manager.checkStock(this.invenP2));
        product3Stock.setText("Quantity available: " + manager.checkStock(this.invenP3));
        product4Stock.setText("Quantity available: " + manager.checkStock(this.invenP4));
    }

    /**
     * Sets up the cart buttons at the top of the GUI along with their respecitve ActionListeners
     */
    private void cartButtonsAndTitle(){
        //title
        this.storeTitle_lb.setFont(new Font("Serif", PLAIN, 50));
        this.storeTitle_lb.setBounds(0,32,900,40);
        //CartButtons
        this.cart1_btn.setEnabled(false);
        this.cart1_btn.setBounds(0,0,300,32);
        this.cart2_btn.setBounds(300,0,300,32);
        this.cart3_btn.setBounds(600,0,300,32);
        this.cart1_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart1_btn.setEnabled(false);
                cart2_btn.setEnabled(cart2Enabled);
                cart3_btn.setEnabled(cart3Enabled);
                currentCardID = 0;
                cart1_btn.setText(cart1Name + " (Active)");
                cart2_btn.setText(cart2Name);
                cart3_btn.setText(cart3Name);
                refreshNewCartID();
                checkCheckoutButton();
            }
        });
        this.cart2_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart1_btn.setEnabled(cart1Enabled);
                cart2_btn.setEnabled(false);
                cart3_btn.setEnabled(cart3Enabled);
                currentCardID = 1;
                cart1_btn.setText(cart1Name);
                cart2_btn.setText(cart2Name + " (Active)");
                cart3_btn.setText(cart3Name);
                refreshNewCartID();
                checkCheckoutButton();
            }
        });
        this.cart3_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart1_btn.setEnabled(cart1Enabled);
                cart2_btn.setEnabled(cart2Enabled);
                cart3_btn.setEnabled(false);
                currentCardID = 2;
                cart1_btn.setText(cart1Name);
                cart2_btn.setText(cart2Name);
                cart3_btn.setText(cart3Name + " (Active)");
                refreshNewCartID();
                checkCheckoutButton();
            }
        });
        this.panel.add(this.storeTitle_lb);
        this.panel.add(this.cart1_btn);
        this.panel.add(this.cart2_btn);
        this.panel.add(this.cart3_btn);
    }

    /**
     * When switching cart IDs, this method will refresh the text content for the respective cart id
     * along with the the buttons for adding and subtracting contents
     */
    private void refreshNewCartID(){
        this.cart_lb.setText(this.manager.getInfoOfCartProducts(this.currentCardID));
        this.cart_lb.setVerticalAlignment(JLabel.TOP);
        this.cartTitle_lb.setText("Store Cart >>> " + (this.currentCardID + 1));
        this.subProduct1_btn.setEnabled(this.manager.checkShoppingCartProductExist(invenP1, this.currentCardID));
        this.subProduct2_btn.setEnabled(this.manager.checkShoppingCartProductExist(invenP2, this.currentCardID));
        this.subProduct3_btn.setEnabled(this.manager.checkShoppingCartProductExist(invenP3, this.currentCardID));
        this.subProduct4_btn.setEnabled(this.manager.checkShoppingCartProductExist(invenP4, this.currentCardID));
    }

    /**
     * Displays the first inventory product to its respective locations along with its buttons and quantity
     */
    private void inventoryProduct1(){
        JLabel imageProduct1;
        //Product 1
        this.product1_lb.setBounds(0,72,300,32);
        this.product1_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        ImageIcon image = new ImageIcon(this.product1_ig);
        imageProduct1 = new JLabel(image);
        imageProduct1.setBounds(0,104,300,300);
        imageProduct1.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.addProduct1_btn.setBounds(0,404,64,32);
        this.subProduct1_btn.setBounds(64,404,64,32);
        this.product1Stock.setBounds(128,404,172,32);
        this.addProduct1_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.addToCart(manager.getInventoryProductByID("1"), 1,currentCardID);
                manager.subFromStock(invenP1,1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product1Stock.setText("Quantity available: " + manager.checkStock(invenP1));
                subProduct1_btn.setEnabled(true);
                if (manager.checkStock(invenP1) == 0){
                    addProduct1_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.subProduct1_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.removeFromCart(manager.getInventoryProductByID("1"),1,currentCardID);
                manager.addToStock(manager.getInventoryProductByID("1"),1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product1Stock.setText("Quantity available: " + manager.checkStock(invenP1));
                addProduct1_btn.setEnabled(true);
                if (!manager.checkShoppingCartProductExist(invenP1, currentCardID)){
                    subProduct1_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.panel.add(this.addProduct1_btn);
        this.panel.add(this.subProduct1_btn);
        this.panel.add(this.product1_lb);
        this.panel.add(imageProduct1);
        this.panel.add(this.product1Stock);
    }

    /**
     * Displays the second inventory product to its respective locations along with its buttons and quantity
     */
    private void inventoryProduct2(){
        JLabel imageProduct2;
        //Product 1
        this.product2_lb.setBounds(300,72,300,32);
        this.product2_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        ImageIcon image = new ImageIcon(this.product2_ig);
        imageProduct2 = new JLabel(image);
        imageProduct2.setBounds(300,104,300,300);
        imageProduct2.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.addProduct2_btn.setBounds(300,404,64,32);
        this.subProduct2_btn.setBounds(364,404,64,32);
        this.product2Stock.setBounds(428,404,172,32);
        this.addProduct2_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.addToCart(manager.getInventoryProductByID("2"), 1,currentCardID);
                manager.subFromStock(invenP2,1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product2Stock.setText("Quantity available: " + manager.checkStock(invenP2));
                subProduct2_btn.setEnabled(true);
                if (manager.checkStock(invenP2) == 0){
                    addProduct2_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.subProduct2_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.removeFromCart(manager.getInventoryProductByID("2"),1,currentCardID);
                manager.addToStock(manager.getInventoryProductByID("2"),1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product2Stock.setText("Quantity available: " + manager.checkStock(invenP2));
                addProduct2_btn.setEnabled(true);
                if (!manager.checkShoppingCartProductExist(invenP2, currentCardID)){
                    subProduct2_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.panel.add(this.addProduct2_btn);
        this.panel.add(this.subProduct2_btn);
        this.panel.add(this.product2_lb);
        this.panel.add(imageProduct2);
        this.panel.add(this.product2Stock);
    }

    /**
     * Displays the third inventory product to its respective locations along with its buttons and quantity
     */
    private void inventoryProduct3(){
        JLabel imageProduct3;
        //Product 1
        this.product3_lb.setBounds(0,436,300,32);
        this.product3_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        ImageIcon image = new ImageIcon(this.product3_ig);
        imageProduct3 = new JLabel(image);
        imageProduct3.setBounds(0,468,300,300);
        imageProduct3.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.addProduct3_btn.setBounds(0,768,64,32);
        this.subProduct3_btn.setBounds(64,768,64,32);
        this.product3Stock.setBounds(128,768,172,32);
        this.addProduct3_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.addToCart(manager.getInventoryProductByID("3"), 1,currentCardID);
                manager.subFromStock(invenP3,1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product3Stock.setText("Quantity available: " + manager.checkStock(invenP3));
                subProduct3_btn.setEnabled(true);
                if (manager.checkStock(invenP3) == 0){
                    addProduct1_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.subProduct3_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.removeFromCart(manager.getInventoryProductByID("3"),1,currentCardID);
                manager.addToStock(manager.getInventoryProductByID("3"),1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product3Stock.setText("Quantity available: " + manager.checkStock(invenP3));
                addProduct3_btn.setEnabled(true);
                if (!manager.checkShoppingCartProductExist(invenP3, currentCardID)){
                    subProduct3_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.panel.add(this.addProduct3_btn);
        this.panel.add(this.subProduct3_btn);
        this.panel.add(this.product3_lb);
        this.panel.add(imageProduct3);
        this.panel.add(this.product3Stock);
    }

    /**
     * Displays the fourth inventory product to its respective locations along with its buttons and quantity
     */
    private void inventoryProduct4(){
        JLabel imageProduct4;
        //Product 1
        this.product4_lb.setBounds(300,436,300,32);
        this.product4_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        ImageIcon image = new ImageIcon(this.product4_ig);
        imageProduct4 = new JLabel(image);
        imageProduct4.setBounds(300,468,300,300);
        imageProduct4.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.addProduct4_btn.setBounds(300,768,64,32);
        this.subProduct4_btn.setBounds(364,768,64,32);
        this.product4Stock.setBounds(428,768,172,32);
        this.addProduct4_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.addToCart(manager.getInventoryProductByID("4"), 1,currentCardID);
                manager.subFromStock(invenP4,1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product4Stock.setText("Quantity available: " + manager.checkStock(invenP4));
                subProduct4_btn.setEnabled(true);
                if (manager.checkStock(invenP4) == 0){
                    addProduct4_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.subProduct4_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.removeFromCart(manager.getInventoryProductByID("4"),1,currentCardID);
                manager.addToStock(manager.getInventoryProductByID("4"),1);
                cart_lb.setText(manager.getInfoOfCartProducts(currentCardID));
                cart_lb.setVerticalAlignment(JLabel.TOP);
                product4Stock.setText("Quantity available: " + manager.checkStock(invenP4));
                addProduct4_btn.setEnabled(true);
                if (!manager.checkShoppingCartProductExist(invenP4, currentCardID)){
                    subProduct4_btn.setEnabled(false);
                }
                checkCheckoutButton();
            }
        });
        this.panel.add(this.addProduct4_btn);
        this.panel.add(this.subProduct4_btn);
        this.panel.add(this.product4_lb);
        this.panel.add(imageProduct4);
        this.panel.add(this.product4Stock);
    }

    /**
     * sets up the JLabels for the current cart the user's on and its contents
     */
    private void cartProduct(){
        this.cartTitle_lb.setBounds(600,72,300,32);
        this.cartTitle_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.cart_lb.setVerticalAlignment(JLabel.TOP);
        this.cart_lb.setBounds(600,104,300,300);
        this.cart_lb.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // maybe delete later
        this.panel.add(this.cartTitle_lb);
        this.panel.add(this.cart_lb);
    }

    /**
     * Main GUI method that calls all previous methods to create milestone 4
     */
    public void displayGUI(){
        // Cart Buttons and the Title
        this.cartButtonsAndTitle();
        //Inventory products
        this.inventoryProduct1();
        this.inventoryProduct2();
        this.inventoryProduct3();
        this.inventoryProduct4();
        //Cart Printing
        this.cartProduct();
        //Users Transaction part
        this.bottomRightGUI();
        //Creating the frame
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want terminate the program")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    System.exit(0);
                }
            }
        });

        this.frame.setVisible(true);
    }

    /**
     * Initializes manager and storeview and calls the GUI
     * @param args String array, the default args for Main
     */
    public static void main(String[] args) {
        StoreManager sm = new StoreManager("Linda", "Pulles");
        StoreView sv = new StoreView(sm);
        sv.displayGUI();
    }
}
