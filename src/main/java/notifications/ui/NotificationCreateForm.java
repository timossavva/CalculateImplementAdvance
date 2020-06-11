package notifications.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NotificationCreateForm extends JFrame {
    private JRadioButton button1;
    private JRadioButton storeRadioButton;
    private JRadioButton branchRadioButton;
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox dateComboBox;
    private JRadioButton smallerRadioButton;
    private JTextField valueTextField;
    private JRadioButton biggerRadioButton;
    private JButton createButton;
    private JRadioButton primaryAccountRadioButton;
    private JRadioButton indicatorRadioButton;
    private JComboBox primaryAccountComboBox;
    private JComboBox indicatorComboBox;

    public NotificationCreateForm() {
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Δημιουργία Ειδοποίησης");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}
