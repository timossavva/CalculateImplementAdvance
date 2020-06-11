package utils.tables_init;

import branches.objects.Branch;
import notifications.objects.Notification;
import notifications.objects.NotificationIndicator;
import notifications.objects.NotificationPrimaryAccount;
import stores.objects.Store;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class NotificationsLoadTableModel {

    public static DefaultTableModel loadTableNotifications(ArrayList<Notification> notifications, boolean indicatorOrPrimary) {

        ArrayList<Notification> notificationsFiltered = new ArrayList<>();
        for (Notification notification : notifications) {
            if (indicatorOrPrimary && notification instanceof NotificationIndicator) {
                notificationsFiltered.add(notification);
            } else if (!indicatorOrPrimary && notification instanceof NotificationPrimaryAccount) {
                notificationsFiltered.add(notification);
            }
        }

        // Create the headers of the table
        String[] columns = new String[]{
                "Name", "Store", "Branch", "Date", "Completed", "Value", "Operator"
        };

        // Create an empty table
        Object[][] data = new Object[notificationsFiltered.size()][columns.length];

        // Fill the table with the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                Notification notification = notifications.get(i);
                switch (j) {
                    case 0:
                        data[i][j] = indicatorOrPrimary ? notification.getIndicator().getTitle() : notification.getPrimaryAccount().getName();
                        break;
                    case 1:
                        data[i][j] = notification.getStore() != null ? notification.getStore().getName() : null;
                        break;
                    case 2:
                        data[i][j] = notification.getBranch() != null ? notification.getBranch().getName() : null;
                        break;
                    case 3:
                        data[i][j] = notification.getDate();
                        break;
                    case 4:
                        data[i][j] = !notification.isEnable();
                        break;
                    case 5:
                        data[i][j] = notification.getValue();
                        break;
                    case 6:
                        data[i][j] = notification.getOperator();
                        break;
                }
            }
        }

        // Create the columns object type
        final Class[] columnClass = new Class[]{String.class, Store.class, Branch.class, double.class, String.class, boolean.class, String.class};

        // Override an object of type "DefaultTableModel" with the data and the ColumnClass and return it
        return new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
    }
}
