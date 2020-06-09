package utils.tables_init;


import stores.objects.Store;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class StoresLoadTableModel {

    public static DefaultTableModel loadTableStores(ArrayList<Store> StoreList) {

        String[] columns = new String[]{
                "Name", "Codename", "State", "City", "Address", "Postal Code", "Phone Number"
        };

        // Create an empty table
        Object[][] data = new Object[StoreList.size()][columns.length];

        // Fill the table with the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = StoreList.get(i).getName();
                        break;
                    case 1:
                        data[i][j] = StoreList.get(i).getCodeName();
                        break;
                    case 2:
                        data[i][j] = StoreList.get(i).getState();
                        break;
                    case 3:
                        data[i][j] = StoreList.get(i).getCity();
                        break;
                    case 4:
                        data[i][j] = StoreList.get(i).getAddress();
                        break;
                    case 5:
                        data[i][j] = StoreList.get(i).getPostalCode();
                        break;
                    case 6:
                        data[i][j] = StoreList.get(i).getPhoneNumber();
                        break;
                }
            }
        }

        // Create the columns object type
        final Class[] columnClass = new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class};

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
