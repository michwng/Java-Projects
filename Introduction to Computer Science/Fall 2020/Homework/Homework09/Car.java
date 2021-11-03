/**
 * ---------------------------------------------------------------------------
 * File name: Car.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/

/**
 * Simulates a Car by having a unique
 * Year, Make, and Speed. Can Accelerate
 * and Brake.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class Car
{
    //Fields for the Car Class
    private int yearModel;
    private String make;
    private int speed;

    /**
     * The Constructor for the Car class.
     * Sets fields equala to the value of the parameters.
     * speed is initialized to 0.
     * 
     * @param yearModel - int
     * @param make - String
     */
    public Car(int yearModel, String make)
    {
        this.yearModel = yearModel;
        this.make = make;
        speed = 0;
    }

    /**
     * returns the car's yearModel.
     * 
     * @return yearModel - int
     */
    public int getYearModel()
    {
        return yearModel;
    }
    /**
     * returns the car's make.
     * 
     * @return make - String
     */
    public String getMake()
    {
        return make;
    }
    /**
     * returns the car's speed.
     * 
     * @return speed - int
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Increases the Car's speed by 5.
     */
    public void accelerate()
    {
        speed += 5;
    }
    /**
     * Decreases the Car's speed by 5.
     */
    public void brake()
    {
        speed -= 5;
    }
}