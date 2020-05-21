package users;

import branches.Branch;
import products.ProductListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StoreKeeperFrame extends JFrame {
    private JPanel panel;
    private JButton logoutbutton;
    private JButton productListButton;
    private final Branch userBranch;

    public StoreKeeperFrame(Branch branch) {

        this.setTitle("Κύρια Φόρμα Αποθηκάριου");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.userBranch = branch;
        addButtonListeners();
    }

    private void addButtonListeners() {
        logoutbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new LoginForm();
                dispose();
            }
        });

        productListButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ProductListFrame(userBranch);
                dispose();
            }
        });
    }

}
