package primary_accounts.ui;

import branches.objects.Branch;
import branches.objects.BranchList;
import primary_accounts.objects.PrimaryAccount;
import primary_accounts.objects.PrimaryAccountList;
import stores.objects.Store;
import stores.objects.StoreList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowPrimaryAccountJPanel extends JPanel implements ActionListener {
    private static final String euro = "\u20ac";
    private final JButton jbtnOK;
    private final JRadioButton jrbStore;
    private final JLabel jLabelSelectPrimaryAccount;
    private final JLabel jLabelPrice;
    private final JComboBox primaryAccountCompoBox;
    private final ButtonGroup buttonGroup;
    private final ArrayList<PrimaryAccount> primaryAccountList;
    private final ArrayList<Store> storeList;
    private final ArrayList<Branch> branchList;
    private final JComboBox<String> branchCombobox;
    private final String[] primaryAccountTitles;
    private final String[] branchTitles;
    private final String[] storeTitles;
    private final JRadioButton jRadioButtonBranch;
    JFormattedTextField dateTextField;

    public ShowPrimaryAccountJPanel() {
        super(new GridLayout(4, 1));
        this.primaryAccountList = PrimaryAccountList.getPrimaryAccountList();

        this.storeList = StoreList.getStoreList();
        this.branchList = BranchList.getBranchList();

        this.storeTitles = StoreList.getStoreNames(storeList);
        this.branchTitles = BranchList.getBranchTitles(branchList);

        this.primaryAccountTitles = PrimaryAccountList.getPrimaryAccountTitles();

        jLabelSelectPrimaryAccount = new JLabel("Επιλέξτε Πρωτογενή Λογαριασμό:");
        primaryAccountCompoBox = new JComboBox<>(primaryAccountTitles);
        primaryAccountCompoBox.setPreferredSize(new Dimension(300, 30));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel1.add(jLabelSelectPrimaryAccount);
        panel1.add(primaryAccountCompoBox);


        jrbStore = new JRadioButton("Εταιρία", true);
        jrbStore.addActionListener(this);
        jRadioButtonBranch = new JRadioButton("Υποκατάστημα", false);
        jRadioButtonBranch.addActionListener(this);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jrbStore);
        buttonGroup.add(jRadioButtonBranch);
        branchCombobox = new JComboBox<>(branchTitles);
        branchCombobox.setPreferredSize(new Dimension(300, 30));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 15));
        panel2.add(jrbStore);
        panel2.add(jRadioButtonBranch);
        panel2.add(branchCombobox);

        JLabel jlabDate = new JLabel("Ημερομηνία:");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        DateFormatter df = new DateFormatter(format);
        dateTextField = new JFormattedTextField(df);
        dateTextField.setPreferredSize(new Dimension(100, 25));
        dateTextField.setValue(new Date());
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel3.add(jlabDate);
        panel3.add(dateTextField);

        jbtnOK = new JButton("OK");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        jLabelPrice = new JLabel("Τιμή:");
        jLabelPrice.setBorder(border);
        jLabelPrice.setPreferredSize(new Dimension(130, 25));
        //jlabPrice.setEnabled(false);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel4.add(jbtnOK);
        panel4.add(jLabelPrice);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        setButtonListeners();
    }

    private void setButtonListeners() {
        // calculate the Price
        jbtnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcPrimaryAccountValue();
            }
        });
    }

    private void calcPrimaryAccountValue() {
        double value;
        // Get selected primary account object
        PrimaryAccount primaryAccount = primaryAccountList.get(primaryAccountCompoBox.getSelectedIndex());
        // Get selected branch object
        Branch branch = BranchList.getBranchList().get(branchCombobox.getSelectedIndex());
        // Get selected date and convert it to string
        String dateString = new SimpleDateFormat("dd-MM-yyyy").format((Date) dateTextField.getValue());

        // Calculate primary account
        if (jRadioButtonBranch.isSelected())  // Branch Radio Button is Selected
            value = PrimaryAccountList.calcPrimaryAccountForBranch(primaryAccount, branch, dateString);
        else  // Store Radio Button is Selected
            value = PrimaryAccountList.calcPrimaryAccountForStore(primaryAccount, branch.getStore(), dateString);

        System.out.println("value -> " + value);
        if (value != -1) {
            jLabelPrice.setText(String.valueOf(value));
        } else {
            jLabelPrice.setText("Not available");
        }
    }

    // change the storeBranchList
    public void actionPerformed(ActionEvent e) {
        if (jrbStore.isSelected()) {
            // Store selected
            branchCombobox.disable();
        } else {
            // Branch selected
            branchCombobox.enable();
        }
    }

} 
