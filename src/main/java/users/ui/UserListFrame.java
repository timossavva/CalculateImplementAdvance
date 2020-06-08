package users.ui;

import users.objects.User;
import users.objects.UserList;
import utils.tables_init.UsersLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserListFrame extends JFrame {
    private JButton createNewUserButton;
    private JTable userListTable;
    private JPanel panel;
    private JButton cancelButton;
    private ArrayList<User> userList;

    public UserListFrame() {

        this.setTitle("Λίστα Χρηστών");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        addButtonListeners();
        loadTable();
        setTableCLickListener();
    }

    private void addButtonListeners() {
        createNewUserButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UserFormFrame(null);
                dispose();
            }
        });

        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdministrativeFrame();
                dispose();
            }
        });
    }

    private void loadTable() {
        UserList userList = new UserList();
        this.userList = userList.getUserList();
        userListTable.setModel(UsersLoadTableModel.loadTablePeople(this.userList));
    }

    private void setTableCLickListener() {
        userListTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    User userClicked = userList.get(row);
                    dispose();
                    new UserFormFrame(userClicked);
                }
            }
        });
    }
}
