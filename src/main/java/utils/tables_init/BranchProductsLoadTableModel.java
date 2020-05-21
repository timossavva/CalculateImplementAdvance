package utils.tables_init;

import branches.Branch;
import products.BranchProduct;
import products.Product;
import stores.Store;
import users.User;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Set;

public class BranchProductsLoadTableModel {

    public static DefaultTableModel loadTableBranchProduct(Set<BranchProduct> branchProductSet) {

        ArrayList<BranchProduct> branchProductsList = new ArrayList<>(branchProductSet);

        // Create the headers of the table
        String[] columns = new String[]{"Όνομα", "Κωδικός", "Τιμή", "Ποσότητα"};

        // Create an empty table
        Object[][] data = new Object[branchProductSet.size()][columns.length];

        // Fill the table with the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                Product product = branchProductsList.get(i).getProduct();
                switch (j) {
                    case 0:
                        data[i][j] = product.getName();
                        break;
                    case 1:
                        data[i][j] = product.getCode();
                        break;
                    case 2:
                        data[i][j] = product.getPrice();
                        break;
                    case 3:
                        data[i][j] = branchProductsList.get(i).getQuantity();
                        break;
                }
            }
        }

        // Create the columns object type
        final Class[] columnClass = new Class[]{String.class, String.class, Double.class, Integer.class};

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
