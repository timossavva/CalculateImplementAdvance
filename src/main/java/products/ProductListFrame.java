package products;

import branches.Branch;
import users.StoreKeeperFrame;
import utils.tables_init.BranchProductsLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Set;

public class ProductListFrame extends JFrame {
    private JPanel panel;
    private JButton addProductsButton;
    private JTable table1;
    private JButton cancelButton;
    private Set<BranchProduct> branchProductList;
    private final Branch userBranch;

    public ProductListFrame(Branch userBranch) {

        this.setTitle("Λίστα Αποθεμάτων");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.userBranch = userBranch;

        addCLickListeners();
        loadTable();
    }

    private void addCLickListeners() {
        addProductsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddBranchProductForm(userBranch);
                dispose();
            }
        });

        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreKeeperFrame(userBranch);
                dispose();
            }
        });
    }


    private void loadTable() {
        this.branchProductList = userBranch.getBranchProducts();
        table1.setModel(BranchProductsLoadTableModel.loadTableBranchProduct(this.branchProductList));
    }


}
