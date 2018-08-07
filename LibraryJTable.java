/**
 * Program Name: JTableViewer.java
 * Purpose: a helper class that will work with the DbUtils.java to 
 *          create and populate a JTable with data from a ResultSet
 *          returned from a query.
 * Coder: Bill Pulling
 * Date: Jul 7, 2017 
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.table.TableModel;


public class LibraryJTable extends JFrame
{
	//constructor
	public LibraryJTable(TableModel model)
	{
		super("Professor Mohan's Library Contents");
		
		//boilerplate
		this.setLayout(new FlowLayout() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		
		//create the JTable and pass it the TableModel object that is 
		//the parameter of this method
		JTable table = new JTable(model);
		
		//create a JScrollPane so we can see the column names on the table
		JScrollPane scrollPane = new JScrollPane(table);		
		this.add(scrollPane);
		
		//last line
		this.setVisible(true);
		
	}//end cons
}
//end class