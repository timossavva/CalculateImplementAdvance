package notifications.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationCreateForm extends JFrame{
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JRadioButton button6;
    private JTextField textField1;
    private JRadioButton bigRadioButton;
    private JButton createButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox comboBox3;

    public NotificationCreateForm(){
        this.setSize(600,600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Δημιουργία Ειδοποίησης");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (button1.isSelected()) {
                    ;
                }
                else {
                    ;
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // if (comboBox1.getSelectedItem()==);
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // if (comboBox2.getSelectedItem()==);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ;
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };
        button6.addActionListener(listener);
        bigRadioButton.addActionListener(listener);
    }
}
