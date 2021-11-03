
/**
 * This code simulates a heater.
 *
 * @author Michael Ng
 * @version 9/10/2020
 */
public class Heater
{
    // instance variables - replace the example below with your own
    private double temperature;
    private double min;
    private double max;
    private double increment;

    /**
     * Constructor for objects of class Heater
     */
    public Heater()
    {
        temperature = 15.0;
        increment = 5.0;
    }

    /**
     * Increases the temperature
     */
    public void warmer()
    {
        if((temperature + increment) < max)
        {
            temperature = temperature + increment;
        }
    }
    /**
     * Decreases the temperature
     */
    public void cooler()
    {
        if((temperature - increment) > min)
        {
            temperature = temperature - increment;
        }
    }
    /**
     * Returns the temperature
     */
    public double getTemp()
    {
        return temperature;
    }
    public void setIncrement(double set)
    {
        if(set > 0)
        {
            increment = set;
        }
        else
        {
            System.out.println("Please input a positive number");
        }
    }
}
