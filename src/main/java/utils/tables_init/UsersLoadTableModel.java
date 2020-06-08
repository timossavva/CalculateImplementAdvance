package utils.tables_init;

import branches.objects.Branch;
import stores.Store;
import users.objects.User;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UsersLoadTableModel {

    public static DefaultTableModel loadTablePeople(ArrayList<User> userList) {

        // Create the headers of the table
        String[] columns = new String[]{
                "Name", "Username", "Account Type", "Store", "Branch"
        };

        // Create an empty table
        Object[][] data = new Object[userList.size()][columns.length];

        // Fill the table with the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = userList.get(i).getName();
                        break;
                    case 1:
                        data[i][j] = userList.get(i).getUsername();
                        break;
                    case 2:
                        data[i][j] = userList.get(i).getType();
                        break;
                    case 3:
                        Store store = userList.get(i).getStore();
                        data[i][j] = store == null ? "---" : store.getName();
                        break;
                    case 4:
                        Branch branch = userList.get(i).getBranch();
                        data[i][j] = branch == null ? "---" : branch.getName();
                        break;
                }
            }
        }

        // Create the columns object type
        final Class[] columnClass = new Class[]{String.class, String.class, String.class, Store.class, Branch.class};

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
