/**
 * ---------------------------------------------------------------------------
 * File name: ShoppingCart.java
 * Project name: Homework08
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: 10/28/2020
 * ---------------------------------------------------------------------------
*/
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

/**
 * ShoppingCart keeps track of products
 * that the user has put in their cart.
 * This also calculates the user's 
 * subtotal, sales tax, and total price.
 * 
 * Date created: 10/28/2020
 * 
 * @author Michael Ng
 */
public class ShoppingCart 
{
    //Creates the productList arrayList and 3 doubles that will be used in checkout.
    private ArrayList<Product> productList;
    private double subtotal = 0.0;
    private double salesTax = 0.0;
    private double totalPrice = 0.0;
    //Formats each double to 2 decimal places.
    DecimalFormat twoPlaces = new DecimalFormat("0.00");
    
    /**
     * The constructor for ShoppingCart. 
     * Instantiates productList.
     * 
     * Date created: 10/28/2020
     */
    public ShoppingCart()
    {
        productList = new ArrayList<>();
    }
    /**
     * adds a product to the arrayList.
     * 
     * Date created: 10/28/2020
     * 
     * @param product - Product
     */
    public void addProduct(Product product)
    {
        productList.add(product);
    }
    /**
     * allows the user to checkout their items.
     * 
     * Date created: 10/28/2020
     */
    public void checkOut()
    {
        if(productList.size() >= 1)
        {
            for(Product product: productList)
            {
                subtotal += product.getPrice();
            }
            salesTax = getStateSalesTax() * subtotal;
            if(salesTax == 0.0) //Or, when getStateSalesTax() returns 0.
            {
                subtotal = 0.0;
                salesTax = 0.0;
                return;
            }
            totalPrice = subtotal + salesTax;
            //options that shows up in the options box.
            String[] options = {"Yes", "No"};
            int num = JOptionPane.showOptionDialog(null, "Are you sure you want to check out? \nSubtotal: $" + twoPlaces.format(subtotal) + "\nSales Tax: $" + twoPlaces.format(salesTax) + "\nTotal Price: $" + twoPlaces.format(totalPrice), "Checkout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(num == -1) //if the user selects the 'X' in the upper-right hand corner
            {
                //this reset of values is necessary so future calls of checkOut() do not continually add to the original value.
                subtotal = 0.0;
                salesTax = 0.0;
                totalPrice = 0.0;
                return;
            }
            if(num == 0) //if the user selects 'yes'
            {
                JOptionPane.showConfirmDialog(null, "Successfully Checked out. Thank you for shopping at Mike's Meme Mall!\n -------------------------\nReceipt\n --------------------\n Items Purchased: " + productList.size() + "\nSubtotal: $" + twoPlaces.format(subtotal) + "\nSales Tax: $" + twoPlaces.format(salesTax) + "\nTotal Price: $" + twoPlaces.format(totalPrice) + "\n-------------------- \n-------------------------", "Successfully Checked Out", JOptionPane.DEFAULT_OPTION);
                removeProducts();
                subtotal = 0;
                salesTax = 0;
                totalPrice = 0;
            }
            if(num == 1) //if the user selects 'no'
            {
                subtotal = 0.0;
                salesTax = 0.0;
                totalPrice = 0.0;
                JOptionPane.showMessageDialog(null, "Returning to the Main Screen.", "Returning...", JOptionPane.DEFAULT_OPTION);
            }
        }
        else //if productList's size is less than one (equal to 0),
        {
            JOptionPane.showMessageDialog(null, "There is nothing in your shopping cart!", "No Items in Shopping Cart.", JOptionPane.DEFAULT_OPTION);
        }
        
    }

    /**
     * asks the user if they live in Tennessee or Virginia
     * in order to calculate sales tax.
     * No text input is necessary.
     * 
     * Returns 0 when the operation is cancelled.
     * 
     * Date created: 10/28/2020
     * 
     * @return salesTax (or 0, if cancelled) - double
     */
    private double getStateSalesTax()
    {
        //options for the user to choose.
        String[] states = {"Tennessee", "Virginia"};
        int state = JOptionPane.showOptionDialog(null, "Which state do you live in?", "State", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, states, states[0]);
        double salesTax = 1.00;
        if(state == -1) //when the user presses the 'X' in the upper-right hand corner.
        {
            return 0.0;
        }
        if(state == 0) //when the user selects 'Tennessee'
        {
            salesTax = .095;
        }
        if(state == 1)
        {
            salesTax = .082;
        }
        return salesTax; //when the user selects 'Virginia'
    }

    /**
     * returns the shopping cart size.
     * 
     * Date created: 10/28/2020
     * 
     * @return productList.size() - int
     */
    public int getShoppingCartSize()
    {
        return productList.size();
    }

    /**
     * Returns a list of products. 
     * Used in view shopping cart.
     * 
     * Date created: 10/28/2020
     */
    public String toString()
    {
        String message = "";
        for(Product product: productList)
        {
            message += product.toString();
        }

        return message;
    }

    /**
     * removes all products in the shopping cart.
     * 
     * Date created: 10/28/2020
     */
    public void removeProducts()
    {
        productList = new ArrayList<>();
    }
}
