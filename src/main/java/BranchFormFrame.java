import javax.swing.*;

public class BranchFormFrame extends JFrame {


    private JPanel panel;
    private JTextField nameTextField;
    private JTextField codeNameTextField;
    private JTextField stateTextField;
    private JTextField cityTextField;
    private JTextField addressTextField;
    private JTextField postalCodeTextField;
    private JTextField phoneNumbTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton;

    public BranchFormFrame() {
        this.setTitle("Επεξεργάσιμη φόρμα χρήστη");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
