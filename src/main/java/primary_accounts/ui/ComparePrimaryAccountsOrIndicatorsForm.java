package primary_accounts.ui;

import javax.swing.*;
import java.awt.*;

class ComparePrimaryAccountsOrIndicatorsForm {

    ComparePrimaryAccountsOrIndicatorsForm(ShowPrimaryAccountOrIndicatorJPanel showPrimAccount1, ShowPrimaryAccountOrIndicatorJPanel showPrimAccount2) {
        JFrame jfrm = new JFrame(showPrimAccount1.getPrimaryOrIndicator() ? "Σύγκριση Πρωτογενών Λογαριασμών" : "Σύγκριση Δεικτών");
        jfrm.setLayout(new GridLayout(1, 3));
        jfrm.setSize(700, 400);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.VERTICAL);

        jfrm.add(showPrimAccount1);
        jfrm.add(showPrimAccount2);

        jfrm.setVisible(true);
    }

}
