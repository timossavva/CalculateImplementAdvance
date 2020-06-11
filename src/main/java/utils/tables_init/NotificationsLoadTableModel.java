package utils.tables_init;

import branches.objects.Branch;
import notifications.objects.Notification;
import stores.objects.Store;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;

public class NotificationsLoadTableModel {

    public static DefaultTableModel loadTableNotifications(ArrayList<Notification> notifications) {

        // Create the headers of the table
        String[] columns = new String[]{
                "Store", "Branch", "Date", "Enabled", "Value", "Operator"
        };

        // Create an empty table
        Object[][] data = new Object[notifications.size()][columns.length];

        // Fill the table with the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = notifications.get(i).getStore();
                        break;
                    case 1:
                        data[i][j] = notifications.get(i).getBranch();
                        break;
                    case 2:
                        data[i][j] = notifications.get(i).getDate();
                        break;
                    case 3:
                        data[i][j] = notifications.get(i).enabled();
                        break;
                    case 4:
                        data[i][j] = notifications.get(i).getValue();
                        break;
                    case 5:
                        data[i][j] = notifications.get(i).getOperator();
                        break;
                }
            }
        }

        // Create the columns object type
        final Class[] columnClass = new Class[]{Store.class, Branch.class, double.class, Date.class, boolean.class, String.class};

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
