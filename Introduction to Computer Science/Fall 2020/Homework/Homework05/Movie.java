/**
 * --------------------------------------------------------------------------
 * File name: Movie.java
 * Project name: Homework05
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 9/24/2020
 * --------------------------------------------------------------------------
 */
public class Movie
{

    String title;
    int year;
    double rating;

    //The constructor for the Movie class, defines title, year, and rating fields.
    public Movie(String movieTitle, int yearReleased, double recievedRating)
    {
        title = movieTitle;
        year = yearReleased;
        rating = recievedRating;
    }

    public void printMovie()
    {
        System.out.println("Title: " + title);
        System.out.println("Year:" + year);
        System.out.println("Rating: " + rating);
    }
}