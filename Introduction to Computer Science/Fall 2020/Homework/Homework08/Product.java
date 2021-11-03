/**
 * ---------------------------------------------------------------------------
 * File name: Product.java
 * Project name: Homework08
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: 10/28/2020
 * ---------------------------------------------------------------------------
*/

/**
 * Product defines the name, description, and price
 * of a Product and can return/modify its attributes.
 * 
 * Date created: 10/28/2020
 * 
 * @author Michael Ng
 */
public class Product 
{
    //Creates 2 Strings and a double.
    private String name;
    private String description;
    private double price;

    /**
     * The constructor for the product class.
     * Creates a product based on parameter values.
     * 
     * Date created: 10/28/2020
     * 
     * @param name - String
     * @param description - String
     * @param price - double
     */
    public Product(String name, String description, double price)
    {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * returns the product's name.
     * 
     * Date created: 10/28/2020
     * 
     * @return name - String
     */
    public String getName() 
    {
        return name;
    }
    /**
     * returns the product's description.
     * 
     * Date created: 10/28/2020
     * 
     * @return description - String
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * returns the product's price.
     * 
     * Date created: 10/28/2020
     * 
     * @return price - double
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * changes the product's name.
     * 
     * Date created: 10/28/2020
     * 
     * @param name - String
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * changes the product's description.
     * 
     * Date created: 10/28/2020
     * 
     * @param description - String
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    /**
     * changes the product's price.
     * 
     * Date created: 10/28/2020
     * 
     * @param price - double
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * returns the Product's attributes.
     * 
     * Date created: 10/28/2020
     */
    public String toString()
    {
        String message = "";

        message += name;
        message += "\nProduct Description: " + description;
        message += "\nPrice: " + price;

        return message + "\n\n";
    }
}
