package primary_accounts.ui;

import branches.objects.Branch;
import branches.objects.BranchList;
import indicators.objects.Indicator;
import indicators.objects.IndicatorManager;
import primary_accounts.objects.PrimaryAccount;
import primary_accounts.objects.PrimaryAccountsManager;

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

public class ShowPrimaryAccountOrIndicatorJPanel extends JPanel implements ActionListener {
    private static final String euro = "\u20ac";
    private final JRadioButton jrbStore;
    private final JLabel jLabelPrice;
    private final JComboBox primaryAccountOrIndicatorCompoBox;
    private ArrayList<PrimaryAccount> primaryAccountList;
    private ArrayList<Indicator> indicatorList;
    private final JComboBox<String> branchCombobox;
    private final JRadioButton jRadioButtonBranch;
    JFormattedTextField dateTextField;
    private final boolean primaryOrIndicator;

    public ShowPrimaryAccountOrIndicatorJPanel(boolean primaryOrIndicator) {
        super(new GridLayout(4, 1));
        this.primaryOrIndicator = primaryOrIndicator;

        String[] primaryAccountOrIndicatorTitles;
        if (primaryOrIndicator) {
            this.primaryAccountList = PrimaryAccountsManager.getPrimaryAccountList();
            primaryAccountOrIndicatorTitles = PrimaryAccountsManager.getPrimaryAccountTitles();
        } else {
            this.indicatorList = IndicatorManager.getIndicatorList();
            primaryAccountOrIndicatorTitles = IndicatorManager.getIndicatorTitles();
        }

        ArrayList<Branch> branchList = BranchList.getBranchList();
        String[] branchTitles = BranchList.getBranchTitles(branchList);


        JLabel jLabelSelectPrimaryAccount = new JLabel(primaryOrIndicator ? "Επιλέξτε Πρωτογενή Λογαριασμό:" : "Επιλέξτε Δείκτη:");
        primaryAccountOrIndicatorCompoBox = new JComboBox<>(primaryAccountOrIndicatorTitles);
        primaryAccountOrIndicatorCompoBox.setPreferredSize(new Dimension(300, 30));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel1.add(jLabelSelectPrimaryAccount);
        panel1.add(primaryAccountOrIndicatorCompoBox);


        jrbStore = new JRadioButton("Εταιρία", true);
        jrbStore.addActionListener(this);
        jRadioButtonBranch = new JRadioButton("Υποκατάστημα", false);
        jRadioButtonBranch.addActionListener(this);
        ButtonGroup buttonGroup = new ButtonGroup();
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

        JButton jButtonOK = new JButton("OK");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        jLabelPrice = new JLabel("Τιμή:");
        jLabelPrice.setBorder(border);
        jLabelPrice.setPreferredSize(new Dimension(130, 25));
        //jlabPrice.setEnabled(false);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel4.add(jButtonOK);
        panel4.add(jLabelPrice);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        jButtonOK.addActionListener(e -> {
            okButtonAction();
        });
    }

    private void okButtonAction() {
        if (primaryOrIndicator) {
            calcPrimaryAccountValue();
        } else {
            // Indicator
            calcIndicatorValue();
        }
    }

    private void calcPrimaryAccountValue() {
        double value;
        // Get selected primary account object
        PrimaryAccount primaryAccount = primaryAccountList.get(primaryAccountOrIndicatorCompoBox.getSelectedIndex());
        // Get selected branch object
        Branch branch = BranchList.getBranchList().get(branchCombobox.getSelectedIndex());
        // Get selected date and convert it to string
        String dateString = new SimpleDateFormat("dd-MM-yyyy").format((Date) dateTextField.getValue());

        // Calculate primary account
        if (jRadioButtonBranch.isSelected())  // Branch Radio Button is Selected
            value = PrimaryAccountsManager.calcPrimaryAccount(false, primaryAccount, null, branch, dateString);
        else  // Store Radio Button is Selected
            value = PrimaryAccountsManager.calcPrimaryAccount(true, primaryAccount, branch.getStore(), null, dateString);

        System.out.println("value -> " + value);
        if (value != -1) {
            jLabelPrice.setText(String.valueOf(value));
        } else {
            jLabelPrice.setText("Not available");
        }
    }

    private void calcIndicatorValue() {
        double value;
        // Get selected primary account object
        Indicator indicator = indicatorList.get(primaryAccountOrIndicatorCompoBox.getSelectedIndex());
        // Get selected branch object
        Branch branch = BranchList.getBranchList().get(branchCombobox.getSelectedIndex());
        // Get selected date and convert it to string
        String dateString = new SimpleDateFormat("dd-MM-yyyy").format((Date) dateTextField.getValue());

        // Calculate primary account
        if (jRadioButtonBranch.isSelected())  // Branch Radio Button is Selected
            value = IndicatorManager.calcIndicator(false, indicator, null, branch, dateString);
        else  // Store Radio Button is Selected
            value = IndicatorManager.calcIndicator(true, indicator, branch.getStore(), null, dateString);

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

    public boolean getPrimaryOrIndicator() {
        return primaryOrIndicator;
    }
}
