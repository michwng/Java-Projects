/**
 * ---------------------------------------------------------------------------
 * File name: Circle.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/
import java.text.DecimalFormat;
/**
 * Simulates a Payroll by creating an employee
 * and calculating their wage based on user input
 * in PayrollDriver.java.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class Circle
{
    //Fields for the Circle Class
    private double radius;
    private final double PI = 3.14159;
    DecimalFormat format = new DecimalFormat("#,##0.#");

    /**
     * The constructor for the Circle Class.
     * Sets radius equal to the parameter's value.
     * 
     * @param radius
     */
    public Circle(double radius)
    {
        this.radius = radius;
    }

    /**
     * returns the circle's radius.
     * 
     * @return radius - double
     */
    public double getRadius()
    {
        return radius;
    }
    /**
     * returns the circle's area.
     * 
     * @return (PI * radius * radius) - double
     */
    public double getArea()
    {
        return (PI * radius * radius);
    }
    /**
     * returns the circle's diameter.
     * 
     * @return (radius * 2) - double
     */
    public double getDiameter()
    {
        return (radius * 2);
    }
    /**
     * returns the circle's circumference.
     * 
     * @return (2 * PI * radius) - double
     */
    public double getCircumference()
    {
        return (2 * PI * radius);
    }
    /**
     * sets the radius of the circle to the parameter's value.
     * 
     * @param radius - double
     */
    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public String toString()
    {
        String message = "";
        message += "Circle Radius: " + format.format(radius);
        message += "\nCircle Area: " + format.format(getArea());
        message += "\nCircle Diameter: " + format.format(getDiameter());
        message += "\nCircle Circumference: " + format.format(getCircumference());

        return message;
    }
}