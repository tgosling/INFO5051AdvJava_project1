import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LibraryView extends JFrame
{
	//Components
	JTextField fNameTxt, lNameTxt, emailTxt, bTitle, ISBN, edtTxt, subTxt, avlbTxt,
			   afNameTxt, alNameTxt, subjTxt;
	JButton  listBkBtn, listLnBtn, listBrwBtn, listOvDBtn, addBrwrBtn, addBookBtn, 
			 srchAthrBtn, srchSubjBtn;
	
	public LibraryView()
	{
		super("Professor Mohan's Personal Library");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		//Tabbed Pane
		JTabbedPane libraryPane = new JTabbedPane();
		
		this.add(libraryPane);
		//Panels
		JPanel userPanel = new JPanel();
		JPanel bookPanel = new JPanel();
		JPanel librPanel = new JPanel();
		JPanel lBtnPanel = new JPanel();
		JPanel addUPanel = new JPanel();
		JPanel usrBPanel = new JPanel();
		JPanel addBPanel = new JPanel();
		JPanel BkBtPanel = new JPanel();
		JPanel libSPanel = new JPanel();
		JPanel subSPanel = new JPanel();
		
		//Panel Labels
		JLabel userLbl = new JLabel();
		JLabel bookLbl = new JLabel();
		JLabel librLbl = new JLabel();
		
		//Set Panel Labels
		userLbl.setText("Borrower Maintenance Panel");
		bookLbl.setText("Book Maintenance Panel");
		librLbl.setText("Library Content");
		
		//Add Panel Labels
		userPanel.add(userLbl);
		bookPanel.add(bookLbl);
		librPanel.add(librLbl);
		
		//Panel Layouts
		bookPanel.setLayout(new BorderLayout());
		librPanel.setLayout(new BorderLayout());
		userPanel.setLayout(new BorderLayout());
		lBtnPanel.setLayout(new FlowLayout());
		addUPanel.setLayout(new GridLayout(3,2,2,2));
		usrBPanel.setLayout(new FlowLayout());
		addBPanel.setLayout(new GridLayout(4,2,2,2));
		BkBtPanel.setLayout(new FlowLayout());
		libSPanel.setLayout(new GridLayout(3,2,2,2));
		subSPanel.setLayout(new GridLayout(2,2,2,2));
		
		//Panel Backgrounds 
		
		//User Maintenance Panel Components
		fNameTxt = new JTextField();
		lNameTxt = new JTextField();
		emailTxt = new JTextField();
		
		JLabel fNameLbl = new JLabel("First Name: ");
		JLabel lNameLbl = new JLabel("Last Name: ");
		JLabel emailLbl = new JLabel("Email: ");
		
		addUPanel.add(fNameLbl);
		addUPanel.add(fNameTxt);
		addUPanel.add(lNameLbl);
		addUPanel.add(lNameTxt);
		addUPanel.add(emailLbl);
		addUPanel.add(emailTxt);
		
		userPanel.add(addUPanel, BorderLayout.NORTH);
		
		addBrwrBtn = new JButton("Add User");
		
		usrBPanel.add(addBrwrBtn);
		
		userPanel.add(usrBPanel, BorderLayout.SOUTH);
		
		//Book Maintenance Panel
		bTitle = new JTextField();
		ISBN = new JTextField();
		edtTxt = new JTextField();
		subTxt = new JTextField();
			
		JLabel bTitleLbl = new JLabel("Book Title: ");
		JLabel ISBNLbl = new JLabel("ISBN: ");
		JLabel editLbl = new JLabel("Edition #: ");
		JLabel subjLbl = new JLabel("Subject: ");
		
		addBPanel.add(bTitleLbl);
		addBPanel.add(bTitle);
		addBPanel.add(ISBNLbl);
		addBPanel.add(ISBN);
		addBPanel.add(editLbl);
		addBPanel.add(edtTxt);
		addBPanel.add(subjLbl);
		addBPanel.add(subTxt);
		
		bookPanel.add(addBPanel, BorderLayout.NORTH);
		
		addBookBtn = new JButton("Add Book");
		
		BkBtPanel.add(addBookBtn);
		
		bookPanel.add(BkBtPanel, BorderLayout.EAST);
		
		//LIBRARY CONTENT PANEL COMPONENTS
		//Author Search
		afNameTxt = new JTextField();
		alNameTxt = new JTextField();
		
		JLabel authFLbl = new JLabel("Author First Name: ");
		JLabel authLLbl = new JLabel("Author Last Name: ");
		
		srchAthrBtn = new JButton("Search Author");
		
		libSPanel.add(authFLbl);
		libSPanel.add(afNameTxt);
		libSPanel.add(authLLbl);
		libSPanel.add(alNameTxt);
		libSPanel.add(srchAthrBtn);
		
		librPanel.add(libSPanel, BorderLayout.NORTH);
		
		//Subject Search
		subjTxt = new JTextField();
		
		JLabel subjSrchLbl = new JLabel("Subject: ");
		
		srchSubjBtn = new JButton("Search Subject");
		
		subSPanel.add(subjSrchLbl);
		subSPanel.add(subjTxt);
		subSPanel.add(srchSubjBtn);
		
		librPanel.add(subSPanel, BorderLayout.CENTER);
		
		//List Content Buttons
		listBkBtn = new JButton("List Books");
		listLnBtn = new JButton("List Loans");
		listBrwBtn = new JButton("List Borrowers");
		listOvDBtn = new JButton("List Over Due");
		
		lBtnPanel.add(listBkBtn);
		lBtnPanel.add(listLnBtn);
		lBtnPanel.add(listBrwBtn);
		lBtnPanel.add(listOvDBtn);
		
		librPanel.add(lBtnPanel, BorderLayout.SOUTH);
		
		//Add Tabs
		libraryPane.addTab("Borrowers", userPanel);
		libraryPane.addTab("Books", bookPanel);
		libraryPane.addTab("Contents", librPanel);
		
		this.setVisible(true);
	}
	
	public void addButtonListener(ActionListener a)
	{
		listBkBtn.addActionListener(a);
		listLnBtn.addActionListener(a);
		listBrwBtn.addActionListener(a);
		listOvDBtn.addActionListener(a);
		addBrwrBtn.addActionListener(a);
		addBookBtn.addActionListener(a);
		srchAthrBtn.addActionListener(a);
		srchSubjBtn.addActionListener(a);
	}
	
	//Fields Getters
	public String getFirstName()
	{
		return fNameTxt.getText();
	}
	public String getLastName()
	{
		return lNameTxt.getText();
	}
	public String getEmail()
	{
		return emailTxt.getText();
	}
	public String getBookTitle()
	{
		return bTitle.getText();
	}
	public String getBookISBN()
	{
		return ISBN.getText();
	}
	public String getBookEdit()
	{
		return edtTxt.getText();
	}
	public String getBookSubj()
	{
		return subTxt.getText();
	}
	public String getSearchSubj()
	{
		return subjTxt.getText();
	}
	public String getSearchAuthFirst()
	{
		return afNameTxt.getText();
	}
	public String getSearchAuthLast()
	{
		return alNameTxt.getText();
	}
	
	//Button Getters
	public JButton getBooksButton()
	{
		return listBkBtn;
	}
	public JButton getLoanButton()
	{
		return listLnBtn;
	}
	public JButton getBorwButton()
	{
		return listBrwBtn;
	}
	public JButton getOvrDButton()
	{
		return listOvDBtn;
	}
	public JButton addBrwrButton()
	{
		return addBrwrBtn;
	}
	public JButton addBookButton()
	{
		return addBookBtn;
	}
	public JButton srchSubjButton()
	{
		return srchSubjBtn;
	}
	public JButton srchAuthButton()
	{
		return srchAthrBtn;
	}
}








