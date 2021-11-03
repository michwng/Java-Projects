/**
 * ---------------------------------------------------------------------------
 * File name: LibraryDriver.java Project name: Files-Books
 * ---------------------------------------------------------------------------
 * Creator's name and email: Don Bailes, bailes@etsu.edu
 * Course-Section: CSCI1260
 * Creation Date: March 21, 2018
 * ---------------------------------------------------------------------------
 */

package books;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Demo the Book and Library classes<br>
 * 
 * <hr>
 * Date created: March 21, 2018<br>
 * <hr>
 * 
 * @author Don Bailes
 */
public class LibraryDriver
{
	private static Library	lib			= new Library ( );
	private static String	fileName	= "";								   // Save filename for later use

	/**
	 * Main method - fill library from input file; add books; save library<br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 * 
	 * @param args
	 *            - not used here
	 */
	public static void main (String [ ] args)
	{
		UIManager.put ("OptionPane.messageFont", new FontUIResource (new Font ("Arial", Font.PLAIN,
						12)));
		JOptionPane.showMessageDialog (null, "Welcome to the Library Manager.", "Library Manager",
			JOptionPane.INFORMATION_MESSAGE);
		inputFile ( );
		displayLibrary ( );
		//Commented out: part of testing.
		//saveFile();
		addBooks ( );

		JOptionPane.showMessageDialog (null, "Thank you for using the Library Manager.",
			"Goodbye Now", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Save the file back to its original position <br>
	 * 
	 * The saveFile method should save the Library into the same file used to input the library, 
	 * thus replacing that file with the newly savedfile.  
	 * The format of the records in the file should be exactly as it was on input.  
	 * Be sure to handle all potential exceptions.  
	 * Close the output file when you have written the last Book to it.
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 */
	private static void saveFile ( )
	{
		PrintWriter pw = null;
		
		try 
		{
			pw = new PrintWriter(fileName);
			for(int i = 0; i < lib.getNumBooks(); i++) 
			{
				Book book =  lib.getBook (i);
				pw.println(book.getTitle() + "," + book.getAuthor ( ) + "," + book.getPrice ( ));
			}
		}
		catch(Exception e) 
		{
			System.out.println("The library has not been completely saved: " + e.getMessage());
		}
		finally 
		{
			if(pw != null) 
			{
				pw.close();
			}
		}
		
	}

	/**
	 * Allow the user to try to add any number of books to the library; this
	 * will result in an exception if one tries to add a book beyond the library
	 * size <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 * returns - number of books added
	 */
	private static void addBooks ( )
	{
		int booksAdded = 0;
		String title = "";
		String author = "";
		double price;
		boolean more = true;
		
		do
		{
			int choice = JOptionPane.showConfirmDialog (null, "Would you like to add another book? \nYou have currently added " + booksAdded + " books to the library.", "Add Book", JOptionPane.YES_NO_OPTION);
			if(choice != 0) 
			{
				more = false;
			}
			else 
			{
				try 
				{
					title = (String)JOptionPane.showInputDialog (null, "What is the title of the book?", "Book Title", JOptionPane.OK_CANCEL_OPTION, null, null, title);
					author = (String) JOptionPane.showInputDialog (null, "Who is the author of " + title + "?", "Author", JOptionPane.OK_CANCEL_OPTION, null, null, author);
					price = Double.parseDouble(JOptionPane.showInputDialog (null, "What is the book's price?", "Book Price", JOptionPane.OK_CANCEL_OPTION));
					
					lib.addBook (new Book(title, author, price));
					booksAdded++;
				}
				catch(NumberFormatException nfe) 
				{
					JOptionPane.showMessageDialog (null, "Sorry, but the number you inputted for price is not a valid number.\nPlease try again.\n" + nfe.getMessage ( ));
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog (null, "Sorry, this book is either already in the library, or \nhas a null value.\n" + e.getMessage ( ));
				}
			}
			
		}while(more);
		// code for adding books to library goes here - including any exception handling

		if (booksAdded > 0)
		{
			JOptionPane.showMessageDialog (null, booksAdded + " books added", "Books Added",
				JOptionPane.INFORMATION_MESSAGE);

			displayLibrary ( );
			saveFile ( );
		}
	}

	/**
	 * Get the filename from the user and try to open it (JFileChooser); read contents and
	 * build library; handle any exceptions that occur. Save the filename and path for
	 * later use by the saveFile method<br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 */

	private static void inputFile ( )
	{
		Scanner scan = new Scanner(System.in);
		JFileChooser fileChooser = new JFileChooser("src\\TextFiles");
		int fileChoice = fileChooser.showOpenDialog (null);
		if(fileChoice == JFileChooser.APPROVE_OPTION) 
		{
			File file = fileChooser.getSelectedFile ( );
			fileName = fileChooser.getSelectedFile ( ).getPath ( );
			try 
			{
				scan.close ( );
				scan = new Scanner(file);
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Error: " + e.getMessage ( ));
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "No file was selected - cannot continue program.", "Cannot Continue", JOptionPane.ERROR_MESSAGE);
			System.exit (0);
		}
		
		while(scan.hasNext()) 
		{
			String str = scan.nextLine ( );
			String[] strings = str.split (",");
			
			for(int i = 0; i < strings.length - 1; i+=3) 
			{
				try
				{
					lib.addBook (new Book(strings[i], strings[i+1], Double.parseDouble(strings[i+2])));
				}
				catch (NumberFormatException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		scan.close ( );
		System.out.println ( );

	}

	/**
	 * Display all books in the library <br>
	 * 
	 * <hr>
	 * Date created: March 21, 2018 <br>
	 * 
	 * <hr>
	 */
	private static void displayLibrary ( )
	{
		JOptionPane.showMessageDialog (null, "There are " + lib.getNumBooks ( ) + " books in the library.",
			"Library Size", JOptionPane.INFORMATION_MESSAGE);
		System.out.println ("\n\n\nLibrary Contents\n----------------\n");
		System.out.println (lib);
		System.out.println ("\n\n");
	}
}
