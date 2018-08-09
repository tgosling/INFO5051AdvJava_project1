import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class LibraryController
{
	private LibraryView libView;
	private LibraryModel libModel;
	private LibraryJTable libTable;

	
	LibraryController(LibraryView libView, LibraryModel libModel)
	{
		this.libView = libView;
		this.libModel = libModel;
		this.libView.addButtonListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRslt = null;
			
			try 
			{
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info5051_books?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST5EDT","root","password");
				
				myStmt = myConn.createStatement();
				
				
				//Library Content Buttons
				if(e.getSource().equals(libView.getBooksButton()))
				{					
					LibraryJTable view = new LibraryJTable(libModel.getAllBooks());
					System.out.println("test test");
				}
				else if(e.getSource().equals(libView.getBorwButton()))//Get All Borrowers
				{	
					LibraryJTable view = new LibraryJTable(libModel.getAllBorrowers());
				}
				else if(e.getSource().equals(libView.getLoanButton()))//Get All Loans
				{
					LibraryJTable view = new LibraryJTable(libModel.getAllLoans());
				}
				else if(e.getSource().equals(libView.getOvrDButton()))//Get Overdue books
				{	
					LibraryJTable view = new LibraryJTable(libModel.getAllOverDue());
				}
				else if(e.getSource().equals(libView.srchAuthButton()))//Search Author
				{	
					//Author Search Variables
					String authFName = libView.getSearchAuthFirst();
					String authLName = libView.getSearchAuthLast();
					
					LibraryJTable view = new LibraryJTable(libModel.searchAuthor(authFName, authLName));
				}
				else if(e.getSource().equals(libView.srchSubjButton()))//Search Subject
				{
					//Subject Search Variables
					String subj = libView.getSearchSubj();
					
					LibraryJTable view = new LibraryJTable(libModel.searchSubject(subj));
				}
				else if(e.getSource().equals(libView.addBrwrButton()))//Add Borrower
				{
					//Borrower Variables
					String brwFName = libView.getFirstName();
					String brwLName = libView.getLastName();
					String brwEmail = libView.getEmail();
					
					libModel.addNewBorrower(brwFName, brwLName, brwEmail);
				}
				else if(e.getSource().equals(libView.addBookButton()))//Add Book
				{
					//Book Variables
					String bkTitle = libView.getBookTitle();
					String bkISBN = libView.getBookISBN();
					String bkEdt = libView.getBookEdit();
					int iEdt = Integer.parseInt(bkEdt);
					String bkSubj = libView.getBookSubj();
					String bkAuth = libView.getAuthName();
					
					List<String> Authors = new ArrayList<String>();
					
					Authors.add(bkAuth);
					
					libModel.addNewBook(bkTitle, bkISBN, iEdt, bkSubj, Authors);
					//TO do Get from view
				}
				else if(e.getSource().equals(libTable.chkInButton()))
				{
					//get selected row
					int column = 0;
					int row = libTable.table.getSelectedRow();
					String value = libTable.table.getModel().getValueAt(row, column).toString();
					
					PreparedStatement query = myConn.prepareStatement("UPDATE book SET book.Available = 1 WHERE book.Title = ?");
					query.setString(1, value);
					
					LibraryJTable view = new LibraryJTable(libModel.getAllBooks());
					
					System.out.println("test test");
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
