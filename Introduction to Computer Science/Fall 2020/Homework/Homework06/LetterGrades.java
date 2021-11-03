/**
 * --------------------------------------------------------------------------
 * File name: LetterGrades.java
 * Project name: Homework06
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 9/30/2020
 * --------------------------------------------------------------------------
 */
import java.util.Scanner;
public class LetterGrades 
{
    public static void main(String[] args) 
    {
        //Declares an int and initalilzes a Scanner.
        int testScore;
        Scanner scan = new Scanner(System.in);

        System.out.print("Please input a number score for a letter grade: ");
        testScore = scan.nextInt();

        /**
         * If the score is below 60, outputs an F.
         * If the score is below 70, outputs an D.
         * If the score is below 80, outputs an C.
         * If the score is below 90, outputs an B.
         * Else, outputs an A.
         */
        if(testScore < 60)
        {
            System.out.println("F");
        }
        else if(testScore < 70)
        {
            System.out.println("D");
        }
        else if(testScore < 80)
        {
            System.out.println("C");
        }
        else if(testScore < 90)
        {
            System.out.println("B");
        }
        else
        {
            System.out.println("A");
        }
        
        //Closes the scanner scan.
        scan.close();
    }
}
