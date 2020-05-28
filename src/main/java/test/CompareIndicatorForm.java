package test;

import javax.swing.*;
import java.awt.*;

class CompareIndicatorForm {

    private final JFrame jfrm;

    CompareIndicatorForm(ShowIndicatorJPanel showIndicator1, ShowIndicatorJPanel showIndicator2) {
        jfrm = new JFrame("Σύγκριση Δεικτών");
        jfrm.setLayout(new GridLayout(1, 3));
        jfrm.setSize(700, 400);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.VERTICAL);

        jfrm.add(showIndicator1);
        jfrm.add(showIndicator2);

        jfrm.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CompareIndicatorForm(new ShowIndicatorJPanel(), new ShowIndicatorJPanel());
            }
        });
    }

}
