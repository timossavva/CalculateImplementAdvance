package notifications.ui;

import notifications.objects.Notification;
import notifications.objects.NotificationList;
import users.ui.FinancialExecutiveFrame;
import utils.tables_init.NotificationsLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class NotificationsFrame extends JFrame {
    private JButton crateNotificationButton;
    private JTable indicatorsNotificationsTable;
    private JTable primaryAccountsNotificationsTable;
    private JPanel panel;
    private JButton cancelButton;

    public NotificationsFrame() {
        this.setTitle("Λίστα Χρηστών");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        addButtonListeners();
        loadTables();
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
        ArrayList<Notification> notifications = NotificationList.getNotifications();
        indicatorsNotificationsTable.setModel(NotificationsLoadTableModel.loadTableNotifications(notifications, true));
        primaryAccountsNotificationsTable.setModel(NotificationsLoadTableModel.loadTableNotifications(notifications, false));
    }
}
