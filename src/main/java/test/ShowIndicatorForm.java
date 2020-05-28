package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ShowIndicatorForm {

    private final JFrame jfrm;
    private final JButton jbtnCompareIndicators;
    private final JPanel buttonPanel;

    ShowIndicatorForm(ShowIndicatorJPanel showIndicator) {
        jfrm = new JFrame("Προβολή Δείκτη");
        jfrm.setSize(600, 350);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfrm.add(showIndicator);

        jbtnCompareIndicators = new JButton("Σύγκριση Δεικτών");
        buttonPanel = new JPanel();
        buttonPanel.add(jbtnCompareIndicators);
        jfrm.add(buttonPanel, BorderLayout.SOUTH);

        jfrm.setVisible(true);

        // compareButton event...
        jbtnCompareIndicators.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShowIndicatorForm(new ShowIndicatorJPanel());
            }
        });
    }

}
