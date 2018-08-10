import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LibraryModel 
{
	
	String url = "jdbc:mysql://localhost:3306/info5051_books?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST5EDT";//Note changed database here to 'world'
	String user = "root";
	String password = "password";
	//Get All Books in the Library
	public DefaultTableModel getAllBooks()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author, book.Available "
									+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
									+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		
		
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Get All Loans in Library
	public DefaultTableModel getAllLoans()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT Book.Title, CONCAT(Borrower.First_Name, ' ', Borrower.Last_Name) AS  Borrower"
					+ " FROM book INNER JOIN Book_loan ON book.BookID = Book_loan.Borrower_Borrower_ID "
					+ " INNER JOIN Borrower ON Book_loan.Borrower_Borrower_ID = Borrower.Borrower_ID"
					+ " WHERE book.Available = 0");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Get All Over Due Books 
	public DefaultTableModel getAllOverDue()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT Book.Title, Book_loan.Date_due, CONCAT(Borrower.First_Name, ' ', Borrower.Last_Name) AS  Borrower "
					   + " FROM book INNER JOIN book_loan ON book.BookID = Book_loan.Book_BookID "
					   + " INNER JOIN Borrower ON Book_loan.Borrower_Borrower_ID = Borrower.Borrower_ID "
					   + " WHERE Book_loan.date_due < curdate() AND Book_loan.Date_Returned IS NULL");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Get All Borrowers
	public DefaultTableModel getAllBorrowers()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT CONCAT(First_Name, ' ', Last_Name) AS Borrower FROM Borrower");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Search Subject
	public DefaultTableModel searchSubject(String subj)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author"
					+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
					+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID"
					+ " WHERE book.Subject = '"+subj+"'");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Search Author
	public DefaultTableModel searchAuthor(String autFName, String autLName)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myRslt = myStmt.executeQuery("SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author"
					+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
					+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID"
					+ " WHERE author.First_Name = '"+autFName+"' AND author.Last_Name = '"+autLName+"'");
			
			return (DefaultTableModel)DbUtils.resultSetToTableModel(myRslt);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	//Add a Borrower to the Library
	public boolean addNewBorrower(String borFName, String borLName, String borEmail)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myStmt.executeUpdate("INSERT INTO Borrower (Last_Name, First_Name, Borrower_email) "
					  + "VALUES('"+borLName+"', '"+borFName+"', '"+borEmail+"')");
			
			myRslt = myStmt.executeQuery("SELECT * FROM Borrower WHERE First_Name ='"+borFName+"' AND Last_Name = '"+borLName+"'");
			
			if(myRslt.next())
			{
				return true;
			}//TO Do error handling
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	//Add a New Book to the library
	public boolean addNewBook(String bTitle, String bISBN, int bEdt, String bSubj, List<String> bAuthor)
	{
		Connection myConn = null;
		Statement myStmt = null;
		Statement myStmt4 = null;
		ResultSet myRslt = null;
		ResultSet aRslt = null;
		ResultSet abRslt = null;
		
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			myStmt.executeUpdate("INSERT INTO Book (Title, ISBN, Edition_Number, Subject, Available) "
					+ "VALUES('"+bTitle+"', '"+bISBN+"', '"+bEdt+"', '"+bSubj+"', 1)");
			
			myRslt = myStmt.executeQuery("SELECT * FROM book WHERE ISBN ='"+bISBN+"' AND Title = '"+bTitle+"'");
			
			if(myRslt.next())
			{
				int bookId = myRslt.getInt("bookID");
				for(int i = 0; i < bAuthor.size(); i++)
				{
					String[] name = bAuthor.get(i).trim().split("\\s*,\\s*");
		            String first = name[1];
		            String last = name[0];
		            
		            aRslt = myStmt.executeQuery("SELECT * FROM author WHERE First_Name = '"+first+"' AND Last_Name = '"+last+"'");
				
		            if(aRslt.next())
		            {
		            	myStmt.executeUpdate("INSERT INTO Book_Author VALUES("+bookId+", "+aRslt.getInt("AuthorID")+")");
		            }
		            else
		            {
		            	myStmt.executeUpdate("INSERT INTO Author (Last_Name, First_Name) "
		  					  + "VALUES('"+last+"', '"+first+"')");
		            	
		            	abRslt = myStmt.executeQuery("SELECT * FROM author WHERE First_Name = '"+first+"' AND Last_Name = '"+last+"'");
		            	
		            	if(abRslt.next())
		            	{
		            		myStmt4.executeUpdate("INSERT INTO Book_Author VALUES("+bookId+", "+aRslt.getInt("AuthorID")+")");
		            	}
		            }
				}
				return true;
			}//TO Do error handling
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException caught, message is: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
		return false;
	}//End addNewBook
	
}//End Library Model Class
