/**
 * ---------------------------------------------------------------------------
 * File name: ShoppingDriver.java
 * Project name: Homework08
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: 10/28/2020
 * ---------------------------------------------------------------------------
*/
import javax.swing.JOptionPane;

/**
 * Simulates a shopping menu and utilizes
 * objects from the Product and ShoppingCart
 * classes.
 * 
 * Date created: 10/28/2020
 * 
 * @author Michael Ng
 */
public class ShoppingDriver
{
    //Creates 5 product classes and a shopping cart class.
    static Product product1 = new Product("Bag of Memes", "A bag of 200 random memes ranging from 2010-2020.", 9.99);
    static Product product2 = new Product("Stonks Trinket", "A small rectangular trinket with the 'stonks' meme etched into it.", 0.99);
    static Product product3 = new Product("Plush Baby Yoda", "A cute plush baby yoda.", 12.99);
    static Product product4 = new Product("Plush Godzilla", "A plush toy of Godzilla saying 'that wasn't very cash money of you.", 19.99);
    static Product product5 = new Product("Plush Worried Dog", "A plush toy of a worried or stressed dog.", 10.99);
    static ShoppingCart shoppingCart = new ShoppingCart();

    /**
     * Main method to run the program.
     * Accepts button input from the user
     * and naviagates based on user input.
     * 
     * Date created: 10/28/2020
     * @param args
     */
    public static void main(String[] args)
    {
        //choice is defined outside the do-while loop since the while loop does not recognize a choice variable inside the loop.
        int choice;
        do
        {
            //String[] choices are the buttons shown at the main screen.
            String[] choices = {"Products", "Shopping Cart", "Checkout", "Exit"};
            choice = JOptionPane.showOptionDialog(null, "Welcome to Mike's Meme Mall! \nFeel free to browse our collection of meme merchandise. \n\nWe offer a variety of meme products from 2018-2020. \nDid a product catch your eye? Don't hesistate to buy it!\n\n", "Mike's Meme Mall", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
            menu(choice);
        }while(choice != -1 && choice != 3); //While the user has not pressed the 'X' in the upper-right hand corner or 'Exit' in the Main screen.
        shoppingCart.removeProducts(); //Removes all products in shopping cart before leaving. Stops thieving.
        System.exit(0);
    }
    /**
     * menu displays options based
     * on the user input. No typing is
     * necessary.
     * 
     * Date created: 10/28/2020
     * @param product - int
     */
    public static void menu(int product)
    {
        switch(product)
        {
            //When the user selects 'Products'
            case 0:
                String[] productChoices = {"Bag of Memes", "Stonks Trinket", "Plush Baby Yoda", "Plush Godzilla", "Plush Worried Dog", "Exit"};
                int productNum = JOptionPane.showOptionDialog(null, "1. " + product1.toString() + "2. " + product2.toString() + "3. " + product3.toString() + "4. " + product4.toString() + "5. " + product5.toString() + "Do any of these products interest you?", "Products", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, productChoices, productChoices[0]);
                switch(productNum)
                {
                    //User buys Bag of Memes
                    case 0:
                        shoppingCart.addProduct(product1);
                        JOptionPane.showMessageDialog(null, product1.getName() + " has been successfully added to the shopping cart.", "Success", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User buys Stonks Trinket
                    case 1:
                        shoppingCart.addProduct(product2);
                        JOptionPane.showMessageDialog(null, product2.getName() + " has been successfully added to the shopping cart.", "Success", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User buys Plush Baby Yoda
                    case 2:
                        shoppingCart.addProduct(product3);
                        JOptionPane.showMessageDialog(null, product3.getName() + " has been successfully added to the shopping cart.", "Success", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User buys Plush Godzilla
                    case 3:
                        shoppingCart.addProduct(product4);
                        JOptionPane.showMessageDialog(null, product4.getName() + " has been successfully added to the shopping cart.", "Success", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User buys Plush Worried Dog
                    case 4:
                        shoppingCart.addProduct(product5);
                        JOptionPane.showMessageDialog(null, product5.getName() + " has been successfully added to the shopping cart.", "Success", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User Exits
                    case 5:
                        JOptionPane.showMessageDialog(null, "Returning to the Main Screen.", "Returning...", JOptionPane.DEFAULT_OPTION);
                        break;
                    //User presses the 'X' in the upper-right hand corner
                    case -1:
                        break;
                    //Implemented just in case.
                    default:
                        JOptionPane.showMessageDialog(null, "Sorry, " + productNum + " is not a product number. Returning to Main Screen.", "Returning...", JOptionPane.DEFAULT_OPTION);
                        break;
                }
                break;
            //When the user selects 'Shopping Cart'
            case 1:
                if(shoppingCart.getShoppingCartSize() >= 1)
                {
                    JOptionPane.showMessageDialog(null, "Here is your shopping cart: \n\n" + shoppingCart.toString(), "Checkout", JOptionPane.DEFAULT_OPTION);
                }
                else //if the user does not have anything in their shopping cart,
                {
                    JOptionPane.showMessageDialog(null, "There is nothing in your shopping cart!", "No Items in Shopping Cart.", JOptionPane.DEFAULT_OPTION);
                }
                
                break;
            //When the user selects Checkout.
            case 2:
                shoppingCart.checkOut();
                break;
            //When the user selects Exit.
            case 3:
                JOptionPane.showMessageDialog(null, "Exiting Mike's Meme Mall. Thank you for shopping with us!", "Checkout", JOptionPane.DEFAULT_OPTION);
                break;
            //When the user presses the 'X' in the upper-right hand corner.
            case -1:
                JOptionPane.showMessageDialog(null, "Exiting Mike's Meme Mall. Thank you for shopping with us!", "Checkout", JOptionPane.DEFAULT_OPTION);
                break;
            //Not necessary, but implemented just in case.
            default:
                JOptionPane.showMessageDialog(null, "Sorry, " + product + " is not a valid input.", "Error - Invalid Input", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}