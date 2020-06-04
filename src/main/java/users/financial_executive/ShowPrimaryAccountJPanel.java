package users.financial_executive;

import branches.Branch;
import branches.BranchList;
import primary_accounts.PrimaryAccount;
import primary_accounts.PrimaryAccountBranch;
import primary_accounts.PrimaryAccountList;
import stores.Store;
import stores.StoreList;

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
import java.util.Set;

public class ShowPrimaryAccountJPanel extends JPanel implements ActionListener {
    private final JButton jbtnOK;
    private final JRadioButton jrbStore;
    private final JLabel jlabSelectPrimaryAccount;
    private final JLabel jlabPrice;
    private final JComboBox primaryAccountCompoBox;
    private final ButtonGroup buttonGroup;
    private final ArrayList<PrimaryAccount> primaryAccountList;
    private final ArrayList<Store> storeList;
    private final ArrayList<Branch> branchList;
    private static final String euro = "\u20ac";
    JFormattedTextField dateTextField;
    private final JComboBox<String> branchCombobox;
    private final String[] primaryAccountTitles;
    private final String[] branchTitles;
    private final String[] storeTitles;
    private final JRadioButton jrbBranch;

    public ShowPrimaryAccountJPanel() {
        super(new GridLayout(4, 1));
        this.primaryAccountList = PrimaryAccountList.getPrimaryAccountList();

        this.storeList = StoreList.getStoreList();
        this.branchList = BranchList.getBranchList();

        this.storeTitles = StoreList.getStoreNames(storeList);
        this.branchTitles = BranchList.getBranchTitles(branchList);

        this.primaryAccountTitles = PrimaryAccountList.getPrimaryAccountTitles();

        jlabSelectPrimaryAccount = new JLabel("Επιλέξτε Πρωτογενή Λογαριασμό:");
        primaryAccountCompoBox = new JComboBox<>(primaryAccountTitles);
        primaryAccountCompoBox.setPreferredSize(new Dimension(300, 30));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel1.add(jlabSelectPrimaryAccount);
        panel1.add(primaryAccountCompoBox);


        jrbStore = new JRadioButton("Εταιρία", true);
        jrbStore.addActionListener(this);
        jrbBranch = new JRadioButton("Υποκατάστημα", false);
        jrbBranch.addActionListener(this);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jrbStore);
        buttonGroup.add(jrbBranch);
        branchCombobox = new JComboBox<>(branchTitles);
        branchCombobox.setPreferredSize(new Dimension(300, 30));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 15));
        panel2.add(jrbStore);
        panel2.add(jrbBranch);
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
        jlabPrice = new JLabel("Τιμή:");
        jlabPrice.setBorder(border);
        jlabPrice.setPreferredSize(new Dimension(130, 25));
        //jlabPrice.setEnabled(false);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel4.add(jbtnOK);
        panel4.add(jlabPrice);

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
        if (jrbBranch.isSelected()) {
            // Branch selected

            boolean found = false;
            PrimaryAccount primaryAccount = primaryAccountList.get(primaryAccountCompoBox.getSelectedIndex());
            Branch branch = BranchList.getBranchList().get(branchCombobox.getSelectedIndex());
            Set<PrimaryAccountBranch> primaryAccountBranchHistory = branch.getPrimaryAccountBranchHistory();
            String dateString = new SimpleDateFormat("dd-MM-yyyy").format((Date) dateTextField.getValue());
            for (PrimaryAccountBranch p : primaryAccountBranchHistory) {
                if (p.getDate().equals(dateString) && p.getPrimaryAccount().equals(primaryAccount)) {
                    jlabPrice.setText(String.valueOf(p.getValue()));
                    found = true;
                    break;
                }
            }
            if (!found) {
                jlabPrice.setText("Not found");
            }

        } else {
            // Store selected

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
