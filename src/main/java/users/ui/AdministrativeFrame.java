package users.ui;


import branches.ui.BranchListFrame;
import stores.ui.StoreListFrame;
import users.ui.LoginForm;
import users.ui.UserListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdministrativeFrame extends JFrame {
    private JButton usersButton;
    private JPanel panel;
    private JButton branchesButton;
    private JButton logoutButton;
    private JButton storesButton;

    public AdministrativeFrame() {

        this.setTitle("Κύρια Φόρμα Διαχειριστή");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        setButtonListeners();
    }

    private void setButtonListeners() {
        usersButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UserListFrame();
                dispose();
            }
        });

        branchesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BranchListFrame();
                dispose();
            }
        });

        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new LoginForm();
                dispose();
            }
        });

        storesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreListFrame();
                dispose();
            }
        });
    }
}
