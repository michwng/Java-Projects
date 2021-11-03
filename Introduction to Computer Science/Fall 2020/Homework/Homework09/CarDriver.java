/**
 * ---------------------------------------------------------------------------
 * File name: CarDriver.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/

import javax.swing.JOptionPane;
/**
 * Driver for the Car Class.
 * Creates a Car object and 
 * utilizes its methods.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class CarDriver
{
    static Car car = new Car(2020, "Tesla");

    /**
     * Main method to run the program.
     * Displays output after accelerating 5 times and
     * after braking 5 times.
     * 
     * Date created: November 10, 2020
     * @param args
     */
    public static void main(String[] args)
    {
        accelerate25MPH();
        brake25MPH();
    }
    /**
     * Calls the Car's accelerate method 5 times. 
     * Displays the current speed after completion.
     */
    public static void accelerate25MPH()
    {
        for(int i = 0; i < 5; i++)
        {
            car.accelerate();
        }
        JOptionPane.showMessageDialog(null, "The Car Accelerated 5 Times! The Car's Current Speed is: " + car.getSpeed(), "Accelerated 5 Times", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Calls the Car's brake method 5 times. 
     * Displays the current speed after completion.
     */
    public static void brake25MPH()
    {
        for(int i = 0; i < 5; i++)
        {
            car.brake();
        }
        JOptionPane.showMessageDialog(null, "The Car Braked 5 Times! The Car's Current Speed is: " + car.getSpeed(), "Braked 5 Times", JOptionPane.INFORMATION_MESSAGE);
    }
}