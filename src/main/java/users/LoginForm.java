package users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginForm extends JFrame {
    private final JPanel panel;
    private final JTextField nameField;
    private final JPasswordField passwordField;
    private final JLabel welcomeLabel;
    private final JButton loginButton;
    private final JButton cancelButton;
    private final JButton troubleButton;
    private final LoginButtonListener listener;
    private final CancelButtonListener listener2;
    private final UserList userList;

    public LoginForm() {

        userList = new UserList();
        panel = new JPanel();
        Color formColor = new Color(42, 60, 80);
        panel.setBackground(formColor);
        panel.setLayout(null);


        JLabel label = new JLabel("Όνομα:");
        label.setFont(new Font("Liberation Sans", Font.BOLD, 20));
        label.setBackground(Color.WHITE);
        label.setForeground(Color.WHITE);
        label.setBounds(55, 116, 93, 15);
        panel.add(label);

        JLabel label_1 = new JLabel("Κωδικός:");
        label_1.setFont(new Font("Liberation Sans", Font.BOLD, 20));
        label_1.setForeground(Color.WHITE);
        label_1.setBounds(55, 156, 93, 25);
        panel.add(label_1);

        nameField = new JTextField();
        nameField.setBounds(178, 110, 158, 25);
        panel.add(nameField);
        nameField.setColumns(20);

        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        passwordField.setBounds(178, 155, 158, 25);
        panel.add(passwordField);

        loginButton = new JButton("Σύνδεση");
        loginButton.setFont(new Font("DialogInput", Font.BOLD, 15));
        loginButton.setIcon(null);
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(40, 198, 142, 36);
        panel.add(loginButton);
        listener = new LoginButtonListener();
        loginButton.addActionListener(listener);

        welcomeLabel = new JLabel("EΙΣΟΔΟΣ ΧΡΗΣΤΗ");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(new Color(255, 165, 0));
        welcomeLabel.setFont(new Font("FreeSans", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(0, 0, 450, 65);
        panel.add(welcomeLabel);

        troubleButton = new JButton("Δεν μπορείτε να συνδεθείτε?");
        troubleButton.setFont(new Font("Liberation Serif", Font.BOLD | Font.ITALIC, 12));
        troubleButton.setForeground(Color.WHITE);
        troubleButton.setBounds(173, 246, 175, 31);
        troubleButton.setOpaque(false);
        troubleButton.setContentAreaFilled(false);
        troubleButton.setBorderPainted(false);

        cancelButton = new JButton("Άκυρο");
        cancelButton.setBackground(Color.RED);
        cancelButton.setFont(new Font("Dialog", Font.BOLD, 15));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(188, 198, 148, 36);
        listener2 = new CancelButtonListener();
        panel.add(cancelButton);
        cancelButton.addActionListener(listener2);


        this.setTitle("Πρόγραμμα Διαχείρισης Σουπερμάρκετ");
        this.setSize(450, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Θέλετε να κλείσετε το πρόγραμμα?");
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        this.setContentPane(panel);
        this.setVisible(true);

    }


    public class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String pass = String.valueOf(passwordField.getPassword());
            String username = nameField.getText();
            User user = userList.checkData(username, pass);
            if (user != null) {
                userList.openCorrespondingUI(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Λάθος κωδικός ή όνομα χρήστη!");
            }
        }
    }

    public static class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }
}