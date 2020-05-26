import javax.swing.*;

public class SalesListFrame extends JFrame{
    private JPanel panel;
    private JTable salesListTable;
    private JButton cancelButton;

    public SalesListFrame() {
        this.setTitle("Λίστα Πωλήσεων");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
