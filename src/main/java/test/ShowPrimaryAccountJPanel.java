import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat; 
import javax.swing.text.DateFormatter;
import javax.swing.border.*;

public class ShowPrimaryAccountJPanel extends JPanel implements ActionListener 
{
	private JButton jbtnOK;
	private JRadioButton jrbStore;
	private JRadioButton jrbBranch;
	private JLabel jlabSelectPrimaryAccount;
	private JLabel jlabDate;
	private JLabel jlabPrice;
	private Border border;
	private JComboBox primaryAccountList;
	private ButtonGroup buttonGroup;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private String[] primaryAccountsTitles = new String[] {"aaaaaaaaaa", "bbbbbbbbb"};
	private String[] storeTitles = new String[] {"aaaaaaaaaaaaa", "bbbbbbbbbbbb"};	
	private static final String euro = "\u20ac";

	public ShowPrimaryAccountJPanel()
	{
		super(new GridLayout(4, 1));

		jlabSelectPrimaryAccount = new JLabel("Επιλέξτε Πρωτογενή Λογαριασμό:");
		JComboBox<String> primaryAccountList = new JComboBox<>(primaryAccountsTitles);
		primaryAccountList.setPreferredSize(new Dimension(300, 30));
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		panel1.add(jlabSelectPrimaryAccount); 
		panel1.add(primaryAccountList);
		

		jrbStore = new JRadioButton("Εταιρία", false);
		jrbStore.addActionListener(this);
		jrbBranch = new JRadioButton("Υποκατάστημα", true);
		jrbBranch.addActionListener(this);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jrbStore);
		buttonGroup.add(jrbBranch);
		JComboBox<String> storeBranchList  = new JComboBox<>(storeTitles);
		storeBranchList.setPreferredSize(new Dimension(300, 30));
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 16,15));
		panel2.add(jrbStore); 
		panel2.add(jrbBranch);
		panel2.add(storeBranchList);
	
		jlabDate = new JLabel("Ημερομηνία:");
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		DateFormatter df = new DateFormatter(format);
		JFormattedTextField dateTextField = new JFormattedTextField(df);
		dateTextField.setPreferredSize(new Dimension(100, 25));
		dateTextField.setValue(new Date());
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 22,15));
		panel3.add(jlabDate);
		panel3.add(dateTextField);
		
		jbtnOK = new JButton("OK");
		border = BorderFactory.createLineBorder(Color.BLACK);
		jlabPrice = new JLabel("Τιμή:");
		jlabPrice.setBorder(border);
		jlabPrice.setPreferredSize(new Dimension(130, 25));
		//jlabPrice.setEnabled(false);		
		panel4 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 22,15));
		panel4.add(jbtnOK);
      		panel4.add(jlabPrice); 

		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);

		// calculate the Price
		jbtnOK.addActionListener(new ActionListener(){  
    			public void actionPerformed(ActionEvent e){  
            			jlabPrice.setText("ssasdsd");				  
    			}  
    		});

		// for enter click
		dateTextField.addActionListener(new ActionListener(){  
    			public void actionPerformed(ActionEvent e){  
				  
    			}  
    		});
	
	}

	// change the storeBranchList
	public void actionPerformed(ActionEvent e) { 
    		if(jrbStore.isSelected()) { }
		else { } 
	}

} 
