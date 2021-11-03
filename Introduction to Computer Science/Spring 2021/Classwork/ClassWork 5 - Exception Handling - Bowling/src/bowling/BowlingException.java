/**
 * ---------------------------------------------------------------------------
 * File name: BowlingException.java<br/>
 * Project name: Classwork - Exception Handling<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: Don Bailes, bailes@etsu.edu<br/>
 * Course: CSCI 1260<br/>
 * Creation Date: Oct 11, 2017<br/>
 * ---------------------------------------------------------------------------
 */

package bowling;

/**
 * Specialized exception class for Bowling Averages<br>
 *
 * <hr>
 * Date created: Oct 11, 2017<br>
 * <hr>
 * 
 * @author Don Bailes
 */
public class BowlingException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 */
	public BowlingException ( )
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @param arg0
	 */
	public BowlingException (String arg0)
	{
		super (arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @param arg0
	 */
	public BowlingException (Throwable arg0)
	{
		super (arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 * *
	 * <hr>
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public BowlingException (String arg0, Throwable arg1)
	{
		super (arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 * Date last modified: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @param arg0
	 *            message
	 * @param arg1
	 *            exception type
	 * @param arg2
	 *            3rd and
	 * @param arg3
	 *            4th args to Exception constructor
	 */
	public BowlingException (String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super (arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Specialized exception class for BowlingAverage <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @return formatted string
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage ( )
	{
		return "Bowling Average Exception: " + super.getMessage ( );
	}
}
