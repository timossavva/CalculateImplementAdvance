import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ShowPrimaryAccountForm 
{

private JFrame jfrm;
private JButton jbtnComparePrimAccounts;
private JPanel buttonPanel;
	
	ShowPrimaryAccountForm(ShowPrimaryAccountJPanel showPrimAccount) 
	{
		jfrm = new JFrame("Προβολή Πρωτογενή Λογαριασμού");
		jfrm.setSize(600, 350);
		jfrm.setResizable(false);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jfrm.add(showPrimAccount);

		jbtnComparePrimAccounts = new JButton("Σύγκριση Πρωτογενών Λογαριασμών");
		buttonPanel = new JPanel();
		buttonPanel.add(jbtnComparePrimAccounts);
		jfrm.add(buttonPanel, BorderLayout.SOUTH);

		jfrm.setVisible(true);

		// compareButton event...
		jbtnComparePrimAccounts.addActionListener(new ActionListener(){  
    			public void actionPerformed(ActionEvent e){  
            					  
    			}  
    		});
		
	}

	

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ShowPrimaryAccountForm(new ShowPrimaryAccountJPanel() );
			}
		});
	}
	
}
