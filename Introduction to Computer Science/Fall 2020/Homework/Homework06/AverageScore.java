/**
 * --------------------------------------------------------------------------
 * File name: AverageScore.java
 * Project name: Homework06
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 9/30/2020
 * --------------------------------------------------------------------------
 */
import java.util.Scanner;
public class AverageScore
{
    public static void main(String[] args) 
    {
        //Declared variable names. 
        double score1;
        double score2;
        double score3;
        double average;
        
        Scanner scan = new Scanner(System.in);
        //Asks the user to input the first, second, and third score.
        //Initializes score1, score2, score3 to a double using scan.nextDouble();
        System.out.print("Please enter the first score: ");
        score1 = scan.nextDouble();

        System.out.print("Please enter the second score: ");
        score2 = scan.nextDouble();

        System.out.print("Please enter the third score: ");
        score3 = scan.nextDouble();

        average = (score1 + score2 + score3)/3;
        System.out.println("Average: " + average);

        //If the calculated average is above 95.0%, recieve a compliment.
        if(average > 95.0)
        {
            System.out.println("That's a great score!");
        }

        //Closes the scanner scan. 
        scan.close();
    }
}