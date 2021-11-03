/**
 * --------------------------------------------------------------------------
 * File name: Driver.java
 * Project name: Homework05
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 9/24/2020
 * --------------------------------------------------------------------------
 */
import java.util.ArrayList;
public class Driver 
{
    public static void main(String[] args)
    {
        //Creates one ArrayList<Movie> and five Movie instances.
        ArrayList<Movie> movieCollection = new ArrayList<Movie>();
        Movie movieOne = new Movie("Eddie's Car Adventure", 1976, 7.7);
        Movie movieTwo = new Movie("The Legend of Michael Rosin", 2009, 8.7);
        Movie movieThree = new Movie("Jojo's Strange Adventure Ep1", 2012, 9.8);
        Movie movieFour = new Movie("The Laughing Wolf", 2020, 10.0);
        Movie movieFive = new Movie("The Surprised Yellow Mouse", 2020, 8.9);
        
        //Adds all movie instances to movieCollection and prints all instances.
        movieCollection.add(movieOne);
        movieCollection.add(movieTwo);
        movieCollection.add(movieThree);
        movieCollection.add(movieFour);
        movieCollection.add(movieFive);
        for(Movie movieStats: movieCollection)
        {
            movieStats.printMovie();
        }
    }
}