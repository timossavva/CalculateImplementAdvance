package users.ui;

import notifications.ui.NotificationsFrame;
import primary_accounts_and_indicators.ui.ShowPrimaryAccountOrIndicatorForm;
import primary_accounts_and_indicators.ui.ShowPrimaryAccountOrIndicatorJPanel;

import javax.swing.*;

public class FinancialExecutiveFrame extends JFrame {
    private JButton primaryAccountsButton;
    private JPanel panel1;
    private JButton indicatorsButton;
    private JButton notificationsButton;
    private JButton logoutButton;

    public FinancialExecutiveFrame() {
        this.setTitle("Κύρια Φόρμα Διαχειριστή");
        this.setSize(600, 600);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        primaryAccountsButton.addActionListener(e -> {
            new ShowPrimaryAccountOrIndicatorForm(new ShowPrimaryAccountOrIndicatorJPanel(true));
            dispose();
        });

        indicatorsButton.addActionListener(e -> {
            new ShowPrimaryAccountOrIndicatorForm(new ShowPrimaryAccountOrIndicatorJPanel(false));
            dispose();
        });

        notificationsButton.addActionListener(e -> {
            new NotificationsFrame();
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new LoginForm();
            dispose();
        });
    }

}
