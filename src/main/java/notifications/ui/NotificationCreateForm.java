package notifications.ui;

import branches.objects.Branch;
import branches.objects.BranchList;
import notifications.objects.Notification;
import notifications.objects.NotificationIndicator;
import notifications.objects.NotificationList;
import notifications.objects.NotificationPrimaryAccount;
import primary_accounts_and_indicators.indicators.Indicator;
import primary_accounts_and_indicators.indicators.IndicatorManager;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountsManager;
import stores.objects.Store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationCreateForm extends JFrame {
    private final ArrayList<PrimaryAccount> primaryAccountList;
    private final ArrayList<Indicator> indicatorList;
    private final String[] primaryAccountTitles;
    private final String[] indicatorTitles;
    private final ArrayList<Branch> branchList;
    private final String[] branchTitles;
    private JButton backButton;
    private JRadioButton storeRadioButton;
    private JRadioButton branchRadioButton;
    private JPanel panel;
    private JComboBox branchComboBox;
    private JRadioButton smallerRadioButton;
    private JTextField valueTextField;
    private JRadioButton biggerRadioButton;
    private JButton createButton;
    private JRadioButton primaryAccountRadioButton;
    private JRadioButton indicatorRadioButton;
    private JComboBox primaryAccountComboBox;
    private JComboBox indicatorComboBox;
    private JTextField dateTextField;

    public NotificationCreateForm() {
        this.setSize(800, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Δημιουργία Ειδοποίησης");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.primaryAccountList = PrimaryAccountsManager.getPrimaryAccountList();
        primaryAccountTitles = PrimaryAccountsManager.getPrimaryAccountTitles();
        this.indicatorList = IndicatorManager.getIndicatorList();
        indicatorTitles = IndicatorManager.getIndicatorTitles();

        branchList = BranchList.getBranchList();
        branchTitles = BranchList.getBranchTitles(branchList);

        initializeComboBoxes();
        dateTextField.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createNotificationAction();
            }
        });

        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new NotificationsFrame();
                dispose();
            }
        });
    }

    private void initializeComboBoxes() {
        ComboBoxModel model = new DefaultComboBoxModel(primaryAccountTitles);
        primaryAccountComboBox.setModel(model);
        model = new DefaultComboBoxModel(indicatorTitles);
        indicatorComboBox.setModel(model);
        model = new DefaultComboBoxModel(branchTitles);
        branchComboBox.setModel(model);
    }

    private void createNotificationAction() {
        Branch selectedBranch = BranchList.getBranchList().get(branchComboBox.getSelectedIndex());
        // Get selected primary account object
        System.out.println("selectedBranch -> " + selectedBranch);
        Store store = storeRadioButton.isSelected() ? selectedBranch.getStore() : null;
        Branch branch = store == null ? selectedBranch : null;
        String date = dateTextField.getText();
        double value = Double.parseDouble(valueTextField.getText());
        String operator = biggerRadioButton.isSelected() ? ">" : "<";

        Notification notification;
        if (indicatorRadioButton.isSelected()) {
            Indicator indicator = indicatorList.get(indicatorComboBox.getSelectedIndex());
            System.out.println("store -> " + store + " | branch -> " + branch + " | date -> " + date + " | value -> " + value + " | operator -> " + operator + " | indicator -> " + indicator.getTitle());
            notification = new NotificationIndicator(store, branch, date, value, true, operator, indicator, null);
        } else {
            PrimaryAccount primaryAccount = primaryAccountList.get(primaryAccountComboBox.getSelectedIndex());
            System.out.println("store -> " + store + " | branch -> " + branch + " | date -> " + date + " | value -> " + value + " | operator -> " + operator + " | primaryAccount -> " + primaryAccount.getName());
            notification = new NotificationPrimaryAccount(store, branch, date, value, true, operator, null, primaryAccount);
        }

        NotificationList.addNotification(notification);

    }

}
