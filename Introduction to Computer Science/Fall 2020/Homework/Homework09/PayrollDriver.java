/**
 * ---------------------------------------------------------------------------
 * File name: PayrollDriver.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

/**
 * Creates a Payroll object
 * and uses the methods in the Payroll class.
 * Prints the object's gross pay after initializing all attributes.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class PayrollDriver
{
    /**
     * Main method to run the program.
     * Displays output based on user input values.
     * 
     * Date created: November 10, 2020
     * @param args
     */
    public static void main(String[] args)
    {
        DecimalFormat format = new DecimalFormat("$#,##0.##");
        String name = JOptionPane.showInputDialog(null, "What is the name of the employee?", "Employee Name", JOptionPane.QUESTION_MESSAGE);

        //Continually asks the user to input the employee's ID Number while the input is invalid.
        int idNumber = 0;
        do
        {
            idNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "What is " + name + "'s ID Number?", "ID Number", JOptionPane.QUESTION_MESSAGE));

            if(String.valueOf(idNumber).length() != 8)
            {
                JOptionPane.showMessageDialog(null, "Sorry, this employee's ID Number is not valid. \nID Numbers must be 8 numbers long.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }while(String.valueOf(idNumber).length() != 8);
        Payroll employee = new Payroll(name, idNumber);

        //Continually asks the user to input the employee's Hourly Rate while the input is invalid.
        double hourlyRate = 0;
        do
        {
            hourlyRate = Double.parseDouble(JOptionPane.showInputDialog(null, "What is " + name + "'s hourly rate?", "Hourly Rate", JOptionPane.QUESTION_MESSAGE));
            if(hourlyRate < 0)
            {
                JOptionPane.showMessageDialog(null, "Sorry, this employee's hourly rate is not valid. \nHourly Rates must be Positive.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }while(hourlyRate < 0);
        
        //Continually asks the user to input the employee's Hours while the input is invalid.
        double numHours = 0;
        do
        {
            numHours = Double.parseDouble(JOptionPane.showInputDialog(null, "How many hours did " + name + " work?", "Hours Worked", JOptionPane.QUESTION_MESSAGE));
            if(numHours < 0)
            {
                JOptionPane.showMessageDialog(null, "Sorry, this employee's hours are not valid. \nThe Employee'sHours must be Positive or 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }while(numHours < 0);
        
        employee.setHourlyRate(hourlyRate);
        employee.setNumHours(numHours);

        JOptionPane.showMessageDialog(null, name + "'s Gross Pay is: " + format.format(employee.getGrossPay()), name + "'s Gross Pay", JOptionPane.INFORMATION_MESSAGE);;
    }
}
