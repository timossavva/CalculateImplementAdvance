package primary_accounts_and_indicators.ui;

import users.ui.FinancialExecutiveFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

class ComparePrimaryAccountsOrIndicatorsForm {

    ComparePrimaryAccountsOrIndicatorsForm(ShowPrimaryAccountOrIndicatorJPanel showPrimAccount1, ShowPrimaryAccountOrIndicatorJPanel showPrimAccount2) {
        JFrame jfrm = new JFrame(showPrimAccount1.getPrimaryOrIndicator() ? "Σύγκριση Πρωτογενών Λογαριασμών" : "Σύγκριση Δεικτών");
        jfrm.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        jfrm.setSize(700, 600);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JButton jbtnBack = new JButton("ΤΕΛΟΣ");
        jfrm.add(showPrimAccount1);
        jfrm.add(showPrimAccount2);
        jfrm.add(jbtnBack);

        jfrm.setVisible(true);


        jbtnBack.addActionListener(e -> {
            new FinancialExecutiveFrame();
            jfrm.dispose();
        });
    }

}
