/**
 * ---------------------------------------------------------------------------
 * File name: Book.java Project name: Files-Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Don Bailes, bailes@etsu.edu
 * Course-Section: CSCI1260
 * Creation Date: March 21, 2018
 * ---------------------------------------------------------------------------
 */

package books;

import java.text.NumberFormat;

/**
 * Model a Book<br>
 * 
 * <hr>
 * Date created: March 21, 2018<br>
 * Date last modified: March 21, 2018<br>
 * <hr>
 * 
 * @author Don Bailes
 */
public class Book
{
	private String	title;
	private String	author;
	private double	price;

	/**
	 * Default Constructor <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 */
	public Book ( )
	{
		this.title = "no title";
		this.author = "no author";
		this.price = 0.0;
	}

	/**
	 * Parameterized Constructor <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @param title
	 * @param author
	 * @param price
	 */
	public Book (String title, String author, double price)
	{
		this.title = title;
		this.author = author;
		this.price = price;
	}

	/**
	 * @return title
	 */
	public String getTitle ( )
	{
		return this.title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}

	/**
	 * @return author
	 */
	public String getAuthor ( )
	{
		return this.author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor (String author)
	{
		this.author = author;
	}

	/**
	 * @return price
	 */
	public double getPrice ( )
	{
		return this.price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice (double price)
	{
		this.price = price;
	}

	/**
	 * Format the book information for possible display <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @return the formatted string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		NumberFormat fmt = NumberFormat.getCurrencyInstance ( );
		String str = "Title:  " + this.title + "        \n";
		str += "Author: " + this.author + ", ";
		str += "Price:  " + fmt.format (this.price) + "        \n";
		return str;
	}

	/**
	 * Copy Constructor <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * Date last modified: March 21, 2018 <br>
	 * 
	 * <hr>
	 */
	public Book (Book b)
	{
		this.title = b.title;
		this.author = b.author;
		this.price = b.price;
	}
}
