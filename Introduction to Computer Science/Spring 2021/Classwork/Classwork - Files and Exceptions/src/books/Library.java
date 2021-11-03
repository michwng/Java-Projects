/**
 * ---------------------------------------------------------------------------
 * File name: Library.java Project name: Files-Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Don Bailes, bailes@etsu.edu
 * Course-Section: CSCI1260
 * Creation Date: March 21, 2018
 * ---------------------------------------------------------------------------
 */

package books;

import java.util.ArrayList;

/**
 * Implement Library as an array of Book objects<br>
 * 
 * <hr>
 * Date created: March 21, 2018<br>
 * Date last modified: March 21, 2018<br>
 * <hr>
 * 
 * @author Don Bailes
 */

public class Library
{
	private ArrayList <Book>	books;
	private boolean				hasChange;

	/**
	 * Default Constructor - empty library <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 */
	public Library ( )
	{
		this.books = new ArrayList <> ( );
	}

	/**
	 * @return the number of Books in books
	 */
	public int getNumBooks ( )
	{
		return books.size ( );
	}

	/**
	 * If n is valid, return nth Book in the library; else throw exception <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @param n the position of the book to be returned
	 * @return the selected book
	 */
	public Book getBook (int n) throws Exception
	{
		if (n >= 0 && n < getNumBooks ( ))
			return books.get (n);
		else
			throw new Exception ("There is no book number " + n + " in the library.");
	}

	/**
	 * Add a book to the library if it is not already; throw exception if it is
	 * already <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @param b - Book to Add
	 * @throws Exception if present
	 */
	public void addBook (Book b) throws Exception
	{
		if (books.contains (b))
			throw new Exception ("Book (" + b.getTitle ( ) + ") is already in the library");
		else
			books.add (b);
	}

	/**
	 * Format the books in the library for display <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @return the formatted string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		String str = "";
		for (int n = 0; n < getNumBooks ( ); n++ )
		{
			str += "Book " + (n + 1) + ".\n" + books.get (n) + "\n";
		}
		return str;
	}

	/**
	 * Reset hasChange attribute to false <br>
	 *
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 *
	 * <hr>
	 */
	public void resetChangeFlag ( )
	{
		hasChange = false;
	}

	/**
	 * Delete the selected book from the Library <br>
	 *
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 *
	 * <hr>
	 * 
	 * @param index - position of Book to be deleted
	 * @throws Exception if no such Book
	 */
	public void delete (int index) throws Exception
	{
		if ( (index >= 0) && (index < getNumBooks ( )))
		{
			books.remove (index);
			hasChange = true;
		}
		else
			throw new Exception ("The index " + index + " is invalid in the Library's delete method.");
	}

	/**
	 * Getter for hasChange flag <br>
	 *
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 *
	 * <hr>
	 * 
	 * @return true if there have been changes since
	 *         the last save and false otherwise
	 */
	public boolean hasChanges ( )
	{
		return hasChange;
	}
}
