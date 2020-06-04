package users.financial_executive;

import branches.BranchListFrame;
import primary_accounts.PrimaryAccountList;
import users.UserListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FinancialExecutiveFrame extends JFrame {
    private JButton primaryAccountsButton;
    private JPanel panel1;
    private JButton indicatorsButton;
    private JButton notificationsButton;

    public FinancialExecutiveFrame() {
        this.setTitle("Κύρια Φόρμα Διαχειριστή");
        this.setSize(600, 600);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        setButtonListeners();
    }

    private void setButtonListeners() {
        primaryAccountsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ShowPrimaryAccountForm(new ShowPrimaryAccountJPanel());
                dispose();
            }
        });

        indicatorsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ShowIndicatorForm(new ShowIndicatorJPanel());
                dispose();
            }
        });

        notificationsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BranchListFrame();
                dispose();
            }
        });

    }
}
