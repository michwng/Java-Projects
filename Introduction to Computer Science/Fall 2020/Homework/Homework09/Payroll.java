/**
 * ---------------------------------------------------------------------------
 * File name: Payroll.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/

/**
 * Simulates a Payroll by creating an employee
 * and calculating their wage based on user input
 * in PayrollDriver.java.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class Payroll
{
    //Fields for the Payroll Class.
    private String name;
    private int idNumber;
    private double hourlyRate;
    private double numHours;

    /**
     * Constructor for the Payroll Class.
     * Sets the employee's name and idNumber to the parameter's values.
     * 
     * @param name - String
     * @param idNumber - int
     */
    public Payroll(String name, int idNumber)
    {
        this.name = name;
        this.idNumber = idNumber;
    }

    /**
     * returns the employee's name.
     * 
     * @return name - String
     */
    public String getName()
    {
        return name;
    }
    /**
     * returns the employee's ID Number.
     * 
     * @return idNumber - int
     */
    public int getIdNumber()
    {
        return idNumber;
    }
    /**
     * returns the employee's hourly rate.
     * 
     * @return hourlyRate - double
     */
    public double getHourlyRate()
    {
        return hourlyRate;
    }
    /**
     * returns the employee's hours.
     * 
     * @return numHours - double
     */
    public double getNumHours()
    {
        return numHours;
    }
    /**
     * returns the employee's gross pay.
     * 
     * @return (numHours * hourlyRate) - double
     */
    public double getGrossPay()
    {
        return (numHours * hourlyRate);
    }

    /**
     * Sets the employee's name to the parameter's value.
     * 
     * @param name - String
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * Sets the employee's ID Number to the parameter's value.
     * 
     * @param idNumber - int
     */
    public void setIdNumber(int idNumber)
    {
        this.idNumber = idNumber;
    }
    /**
     * Sets the employee's hourly rate to the parameter's value.
     * 
     * @param hourlyRate - double
     */
    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }
    /**
     * Sets the employee's hours to the parameter's value.
     * 
     * @param numHours - double
     */
    public void setNumHours(double numHours)
    {
        this.numHours = numHours;
    }
}
