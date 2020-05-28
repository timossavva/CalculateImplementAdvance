import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ComparePrimaryAccountsForm 
{

private JFrame jfrm;
	
	ComparePrimaryAccountsForm(ShowPrimaryAccountJPanel showPrimAccount1, ShowPrimaryAccountJPanel showPrimAccount2) 
	{
		jfrm = new JFrame("Συγκριση Πρωτογενών Λογαριασμών");
		jfrm.setLayout(new GridLayout(1, 3));
		jfrm.setSize(700, 400);
		jfrm.setResizable(false);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.VERTICAL);
		
		jfrm.add(showPrimAccount1);
		jfrm.add(showPrimAccount2);

		jfrm.setVisible(true);
		
	}

	

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ComparePrimaryAccountsForm(new ShowPrimaryAccountJPanel(), new ShowPrimaryAccountJPanel() );
			}
		});
	}
	
}
