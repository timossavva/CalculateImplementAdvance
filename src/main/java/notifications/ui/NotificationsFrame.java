package notifications.ui;

import notifications.objects.Notification;
import notifications.objects.NotificationList;
import users.objects.User;
import users.ui.AdministrativeFrame;
import users.ui.FinancialExecutiveFrame;
import users.ui.UserFormFrame;
import utils.tables_init.NotificationsLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NotificationsFrame extends JFrame {
    private JButton crateNotificationButton;
    private JTable indicatorsNotificationsTable;
    private JTable primaryAccountsNotificationsTable;
    private JPanel panel;
    private JButton cancelButton;
    private ArrayList<Notification> notifications;

    public NotificationsFrame() {
        this.setTitle("Λίστα Χρηστών");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        addButtonListeners();
        loadTables();
        setTableCLickListeners();
    }


    private void addButtonListeners() {
        crateNotificationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new NotificationCreateForm();
                dispose();
            }
        });

        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new FinancialExecutiveFrame();
                dispose();
            }
        });
    }

    private void loadTables() {
        notifications = NotificationList.getNotifications();
        indicatorsNotificationsTable.setModel(NotificationsLoadTableModel.loadTableNotifications(notifications));
    }

    private void setTableCLickListeners() {
        indicatorsNotificationsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    Notification notification = notifications.get(row);
                    dispose();
                    new UserFormFrame(notification);
                }
            }
        });
        primaryAccountsNotificationsTable.addMouseListener(new MouseAdapter() {
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
