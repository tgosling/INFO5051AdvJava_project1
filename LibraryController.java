import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class LibraryController
{
	private LibraryView libView;
	
	LibraryController(LibraryView libView)
	{
		this.libView = libView;
		this.libView.addButtonListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRslt = null;
			
			//Borrower Variables
			String firstName = libView.getFirstName();
			String lastName = libView.getLastName();
			String brwrEmail = libView.getEmail();
			
			//Book Variables
			String bkTitle = libView.getBookTitle();
			String bkISBN = libView.getBookISBN();
			String bkEdt = libView.getBookEdit();
			String bkSubj = libView.getBookSubj();
			
			//Author Search Variables
			String authFName = libView.getSearchAuthFirst();
			String authLName = libView.getSearchAuthLast();
			
			//Subject Search Variables
			String subjSrch = libView.getSearchSubj();
			
			try 
			{
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info5051_books?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST5EDT","root","password");
				
				myStmt = myConn.createStatement();
				
				
				//Library Content Buttons
				if(e.getSource().equals(libView.getBooksButton()))
				{
					String bookSlct = "SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author"
									+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
									+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID";
					
					myRslt = myStmt.executeQuery(bookSlct);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.getBorwButton()))//Get All Borrowers
				{
					String borwSlct = "SELECT CONCAT(First_Name, ' ', Last_Name) AS Borrower FROM Borrower";
					
					myRslt = myStmt.executeQuery(borwSlct);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.getLoanButton()))//Get All Loans
				{
					//TO-DO
					String loanSrch = "SELECT Book.Title, CONCAT(Borrower.First_Name, ' ', Borrower.Last_Name) AS  Borrower"
									+ " FROM book INNER JOIN Book_loan ON book.BookID = Book_loan.Borrower_Borrower_ID "
									+ " INNER JOIN Borrower ON Book_loan.Borrower_Borrower_ID = Borrower.Borrower_ID"
									+ " WHERE book.Available = 0";
					
					myRslt = myStmt.executeQuery(loanSrch);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.getOvrDButton()))//Get Overdue books
				{
					//TO-DO
					String overDueSrch = "SELECT Book.Title, Book_loan.Date_due, CONCAT(Borrower.First_Name, ' ', Borrower.Last_Name) AS  Borrower "
									   + " FROM book INNER JOIN book_loan ON book.BookID = Book_loan.Book_BookID "
									   + " INNER JOIN Borrower ON Book_loan.Borrower_Borrower_ID = Borrower.Borrower_ID "
									   + " WHERE Book_loan.date_due < curdate() AND Book_loan.Date_Returned IS NULL";
					
					myRslt = myStmt.executeQuery(overDueSrch);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.srchAuthButton()))//Search Author
				{
					String srchAuth = "SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author"
							+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
							+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID"
							+ " WHERE author.First_Name = '"+authFName+"' AND author.Last_Name = '"+authLName+"'";
					
					myRslt = myStmt.executeQuery(srchAuth);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.srchSubjButton()))//Search Subject
				{
					String srchAuth = "SELECT book.Title, CONCAT(author.First_Name, ' ', author.Last_Name) AS Author"
							+ " FROM book INNER JOIN book_author ON book.BookID = Book_author.Book_BookID"
							+ " INNER JOIN author ON Book_author.Author_AuthorID = author.AuthorID"
							+ " WHERE book.Subject = '"+subjSrch+"'";
					
					myRslt = myStmt.executeQuery(srchAuth);
					
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
					
					LibraryJTable view = new LibraryJTable(model);
				}
				else if(e.getSource().equals(libView.addBrwrButton()))//Add Borrower
				{
					String createBrwr = "INSERT INTO Borrower (Last_Name, First_Name, Borrower_email) "
										+ "VALUES('"+lastName+"', '"+firstName+"', '"+brwrEmail+"')";
					
					myStmt.executeUpdate(createBrwr);
				}
				else if(e.getSource().equals(libView.addBookButton()))//Add Book
				{
					//TO DO: Add Author 
					String createBook = "INSERT INTO Book (Title, ISBN, Edition_Number, Subject) "
										+ "VALUES('"+bkTitle+"', '"+bkISBN+"', '"+bkEdt+"', '"+bkSubj+"')";
					
					myStmt.executeUpdate(createBook);
				}
				
			}
			catch(SQLException ex)
			{
				System.out.println("SQLException caught, message is: " + ex.getMessage());
				ex.printStackTrace();
			}
			catch(Exception ex)
			{
				System.out.println("Some Exception caught, message is: " + ex.getMessage());
			}
			finally 
			{
					try
						{
						if(myRslt != null)
						{
							myRslt.close();
						}
						
						if(myStmt != null)
						{
							myStmt.close();
						}
						
						if(myConn != null)
						{
							myConn.close();
						}
						}//end try
			catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	}
}
	
}
