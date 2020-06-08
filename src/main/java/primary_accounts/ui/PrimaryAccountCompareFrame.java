package primary_accounts.ui;

import javax.swing.*;

public class PrimaryAccountCompareFrame extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField dateTextField1;
    private JTextField dateTextField2;
    private JPanel panel;

    public PrimaryAccountCompareFrame() {
        this.setTitle("Σύγκριση πρωτογενών λογαριασμών");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
