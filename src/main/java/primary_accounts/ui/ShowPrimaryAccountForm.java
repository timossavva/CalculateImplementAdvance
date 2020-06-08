package primary_accounts.ui;

import users.ui.FinancialExecutiveFrame;

import javax.swing.*;
import java.awt.*;

public class ShowPrimaryAccountForm {

    private final JFrame jfrm;
    private final JButton jbtnComparePrimAccounts;
    private final JButton jbtnBack;
    private final JPanel buttonPanel;

    public ShowPrimaryAccountForm(ShowPrimaryAccountJPanel showPrimAccount) {
        jfrm = new JFrame("Προβολή Πρωτογενή Λογαριασμού");
        jfrm.setSize(600, 350);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfrm.add(showPrimAccount);

        jbtnComparePrimAccounts = new JButton("Σύγκριση Πρωτογενών Λογαριασμών");
        jbtnBack = new JButton("<-Πίσω");
        buttonPanel = new JPanel();
        buttonPanel.add(jbtnBack);
        buttonPanel.add(jbtnComparePrimAccounts);
        jfrm.add(buttonPanel, BorderLayout.SOUTH);

        jfrm.setVisible(true);

        // compareButton event...
        jbtnComparePrimAccounts.addActionListener(e -> {

        });

        jbtnBack.addActionListener(e -> {
            new FinancialExecutiveFrame();
            jfrm.dispose();
        });

    }

}
