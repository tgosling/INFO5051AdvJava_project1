import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LibraryView extends JFrame
{
	//Components
	JTextField fNameTxt, lNameTxt, emailTxt, bTitle, ISBN, edtTxt, subTxt, authTxt,
			   avlbTxt, afSrcNameTxt, alSrcNameTxt, subjTxt;
	JButton  listBkBtn, listLnBtn, listBrwBtn, listOvDBtn, addBrwrBtn, addBookBtn, 
			 srchAthrBtn, srchSubjBtn;
	
	public LibraryView()
	{
		super("Professor Mohan's Personal Library");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		
		
		//Tabbed Pane
		JTabbedPane libraryPane = new JTabbedPane();
		libraryPane.setBackground(new Color(203,163,0));
		
		
		this.add(libraryPane);
		//Panels
		JPanel addPanel = new JPanel();
		JPanel bookPanel = new JPanel();
		JPanel librPanel = new JPanel();
		JPanel lBtnPanel = new JPanel();
		JPanel addUPanel = new JPanel();
		JPanel usrBPanel = new JPanel();
		JPanel addBPanel = new JPanel();
		JPanel BkBtPanel = new JPanel();
		JPanel bkSrchPanel = new JPanel();
		JPanel subSPanel = new JPanel();
		JPanel panelConstrain = new JPanel(new BorderLayout());
		
		//Panel Labels
		JLabel userLbl = new JLabel();
		JLabel bookLbl = new JLabel();
		JLabel librLbl = new JLabel();
		
		//Set Panel Labels
		userLbl.setText("Borrower Maintenance Panel");
		bookLbl.setText("Book Maintenance Panel");
		librLbl.setText("Library Content");
		
		//Add Panel Labels
		addPanel.add(userLbl);
		bookPanel.add(bookLbl);
		librPanel.add(librLbl);
		
		//Panel Layouts
		bookPanel.setLayout(new BorderLayout());
		librPanel.setLayout(new BorderLayout());
		addPanel.setLayout(new BorderLayout());
		lBtnPanel.setLayout(new GridLayout(2,2,2,2));
		addUPanel.setLayout(new GridLayout(4,2,2,2));
		usrBPanel.setLayout(new FlowLayout());
		addBPanel.setLayout(new GridLayout(6,2,2,2));
		BkBtPanel.setLayout(new FlowLayout());
		bkSrchPanel.setLayout(new GridLayout(3,2,2,2));
		subSPanel.setLayout(new GridLayout(2,2,2,2));
		
		addUPanel.setBackground(Color.BLACK);
		addBPanel.setBackground(Color.BLACK);
		bkSrchPanel.setBackground(Color.BLACK);
		subSPanel.setBackground(Color.BLACK);
		panelConstrain.setBackground(Color.BLACK);
		/*GroupLayout srchGroup = new GroupLayout(subSPanel);
		subSPanel.setLayout(srchGroup);
		
		srchGroup.setAutoCreateContainerGaps(true);
		srchGroup.setAutoCreateGaps(true);*/
		
		//Panel Backgrounds 
		
		//User Maintenance Panel Components
		//Add User
		fNameTxt = new JTextField();
		lNameTxt = new JTextField();
		emailTxt = new JTextField();
		
		JLabel fNameLbl = new JLabel("First Name: ");
		JLabel lNameLbl = new JLabel("Last Name: ");
		JLabel emailLbl = new JLabel("Email: ");
		
		fNameLbl.setForeground(new Color(203,163,0));
		lNameLbl.setForeground(new Color(203,163,0));
		emailLbl.setForeground(new Color(203,163,0));
		
		addUPanel.add(fNameLbl);
		addUPanel.add(fNameTxt);
		addUPanel.add(lNameLbl);
		addUPanel.add(lNameTxt);
		addUPanel.add(emailLbl);
		addUPanel.add(emailTxt);
		
		addBrwrBtn = new JButton("Add User");
		
		addBrwrBtn.setBackground(new Color(15,54,99));
		addBrwrBtn.setForeground(new Color(172,176,180));
		
		addUPanel.add(addBrwrBtn);
		addPanel.add(addUPanel, BorderLayout.NORTH);
		
		
		//Add Book
		bTitle = new JTextField();
		authTxt = new JTextField("Lastname, FirstName");
		ISBN = new JTextField();
		edtTxt = new JTextField();
		subTxt = new JTextField();
			
		JLabel authLbl = new JLabel("Author: ");
		JLabel bTitleLbl = new JLabel("Book Title: ");
		JLabel ISBNLbl = new JLabel("ISBN: ");
		JLabel editLbl = new JLabel("Edition #: ");
		JLabel subjLbl = new JLabel("Subject: ");
		
		authLbl.setForeground(new Color(203,163,0));
		bTitleLbl.setForeground(new Color(203,163,0));
		ISBNLbl.setForeground(new Color(203,163,0));
		editLbl.setForeground(new Color(203,163,0));
		subjLbl.setForeground(new Color(203,163,0));
		
		addBPanel.add(authLbl);
		addBPanel.add(authTxt);
		addBPanel.add(bTitleLbl);
		addBPanel.add(bTitle);
		addBPanel.add(ISBNLbl);
		addBPanel.add(ISBN);
		addBPanel.add(editLbl);
		addBPanel.add(edtTxt);
		addBPanel.add(subjLbl);
		addBPanel.add(subTxt);
		addBookBtn = new JButton("Add Book");
		
		addBookBtn.setBackground(new Color(15,54,99));
		addBookBtn.setForeground(new Color(172,176,180));
		
		addBPanel.add(addBookBtn);
		addPanel.add(addBPanel, BorderLayout.CENTER);
		
	
		
		//LIBRARY CONTENT PANEL COMPONENTS
		//Author Search
		afSrcNameTxt = new JTextField();
		alSrcNameTxt = new JTextField();
		
		JLabel authFLbl = new JLabel("Author First Name: ");
		JLabel authLLbl = new JLabel("Author Last Name: ");
		
		authFLbl.setForeground(new Color(203,163,0));
		authLLbl.setForeground(new Color(203,163,0));
		
		srchAthrBtn = new JButton("Search Author");
		
		srchAthrBtn.setBackground(new Color(15,54,99));
		srchAthrBtn.setForeground(new Color(172,176,180));
		
		bkSrchPanel.add(authFLbl);
		bkSrchPanel.add(afSrcNameTxt);
		bkSrchPanel.add(authLLbl);
		bkSrchPanel.add(alSrcNameTxt);
		bkSrchPanel.add(srchAthrBtn);
		
		bookPanel.add(bkSrchPanel, BorderLayout.NORTH);
		
		//Subject Search
		subjTxt = new JTextField();
		
		JLabel subjSrchLbl = new JLabel("Subject: ");
		
		subjSrchLbl.setForeground(new Color(203,163,0));
		
		srchSubjBtn = new JButton("Search Subject");
		
		srchSubjBtn.setBackground(new Color(15,54,99));
		srchSubjBtn.setForeground(new Color(172,176,180));
		
		subSPanel.add(subjSrchLbl);
		subSPanel.add(subjTxt);
		subSPanel.add(srchSubjBtn);
		panelConstrain.add(subSPanel, BorderLayout.NORTH);
		bookPanel.add(panelConstrain, BorderLayout.CENTER);
		
		//List Content Buttons
		listBkBtn = new JButton("List Books");
		listLnBtn = new JButton("List Loans");
		listBrwBtn = new JButton("List Borrowers");
		listOvDBtn = new JButton("List Over Due");
		
		listBkBtn.setBackground(new Color(15,54,99));
		listBkBtn.setForeground(new Color(172,176,180));
		listLnBtn.setBackground(new Color(15,54,99));
		listLnBtn.setForeground(new Color(172,176,180));
		listBrwBtn.setBackground(new Color(15,54,99));
		listBrwBtn.setForeground(new Color(172,176,180));
		listOvDBtn.setBackground(new Color(15,54,99));
		listOvDBtn.setForeground(new Color(172,176,180));
		
		
		lBtnPanel.add(listBkBtn);
		lBtnPanel.add(listLnBtn);
		lBtnPanel.add(listBrwBtn);
		lBtnPanel.add(listOvDBtn);
		
		librPanel.add(lBtnPanel, BorderLayout.CENTER);
		bookPanel.add(lBtnPanel, BorderLayout.SOUTH);
		
		//Add Tabs
		libraryPane.addTab("Search Content", bookPanel);
		libraryPane.addTab("Add Content", addPanel);
		
		
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
	public String getAuthName()
	{
		return authTxt.getText();
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
		return afSrcNameTxt.getText();
	}
	public String getSearchAuthLast()
	{
		return alSrcNameTxt.getText();
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








