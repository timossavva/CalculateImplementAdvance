package primary_accounts.ui;

import users.ui.FinancialExecutiveFrame;

import javax.swing.*;
import java.awt.*;

public class ShowPrimaryAccountOrIndicatorForm {

    private final JFrame jfrm;
    private final boolean primaryOrIndicator;

    public ShowPrimaryAccountOrIndicatorForm(ShowPrimaryAccountOrIndicatorJPanel showPrimAccountOrIndicator) {
        this.primaryOrIndicator = showPrimAccountOrIndicator.getPrimaryOrIndicator();

        jfrm = new JFrame(primaryOrIndicator ? "Προβολή Πρωτογενή Λογαριασμού" : "Προβολή Δείκτη");
        jfrm.setSize(600, 350);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfrm.add(showPrimAccountOrIndicator);

        JButton jbtnComparePrimAccounts = new JButton(primaryOrIndicator ? "Σύγκριση Πρωτογενών Λογαριασμών" : "Σύγκριση Δεικτών");
        JButton jbtnBack = new JButton("<-Πίσω");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jbtnBack);
        buttonPanel.add(jbtnComparePrimAccounts);
        jfrm.add(buttonPanel, BorderLayout.SOUTH);

        jfrm.setVisible(true);

        // compareButton event...
        jbtnComparePrimAccounts.addActionListener(e -> {
            new ComparePrimaryAccountsOrIndicatorsForm(new ShowPrimaryAccountOrIndicatorJPanel(primaryOrIndicator), new ShowPrimaryAccountOrIndicatorJPanel(primaryOrIndicator));
        });

        jbtnBack.addActionListener(e -> {
            new FinancialExecutiveFrame();
            jfrm.dispose();
        });

    }

}
