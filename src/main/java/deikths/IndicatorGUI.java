package deikths;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndicatorGUI extends JFrame{
    private JRadioButton RadioButton2;
    private JRadioButton RadioButton1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public IndicatorGUI(){

        this.setSize(600,600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Προβολή Δείκτη");
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        RadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (RadioButton1.isSelected()) {

                }
                else {

                }
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField1.getText();
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
                // if (comboBox1.getSelectedItem()==);
            }
        });
    }
}
