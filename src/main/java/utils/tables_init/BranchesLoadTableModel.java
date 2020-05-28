package utils.tables_init;

import branches.Branch;
import stores.Store;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Set;

public class BranchesLoadTableModel {

        public static DefaultTableModel loadTableBranches(ArrayList<Branch> BranchList) {

            String[] columns = new String[]{
                    "Name", "Codename", "State", "City", "Address", "Postal Code", "Phone Number", "Store"
            };

            // Create an empty table
            Object[][] data = new Object[BranchList.size()][columns.length];

            // Fill the table with the data
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < columns.length; j++) {
                    switch (j) {
                        case 0:
                            data[i][j] = BranchList.get(i).getName();
                            break;
                        case 1:
                            data[i][j] = BranchList.get(i).getCodeName();
                            break;
                        case 2:
                            data[i][j] = BranchList.get(i).getState();
                            break;
                        case 3:
                            data[i][j] = BranchList.get(i).getCity();
                            break;
                        case 4:
                            data[i][j] = BranchList.get(i).getAddress();
                            break;
                        case 5:
                            data[i][j] = BranchList.get(i).getPostalCode();
                            break;
                        case 6:
                            data[i][j] = BranchList.get(i).getPhoneNumber();
                            break;
                        case 7:
                            Store store = BranchList.get(i).getStore();
                            data[i][j] =store.getName();
                            break;
                    }
                }
            }

            // Create the columns object type
            final Class[] columnClass = new Class[]{long.class,String.class, String.class, String.class, String.class,String.class,String.class,String.class, Store.class, Set.class};

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
