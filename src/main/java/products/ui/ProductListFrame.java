package products.ui;

import branches.objects.Branch;
import branches.objects.BranchList;
import products.objects.BranchProduct;
import users.ui.StoreKeeperFrame;
import utils.tables_init.BranchProductsLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Set;

public class ProductListFrame extends JFrame {
    private JPanel panel;
    private JButton addProductsButton;
    private JTable productTable;
    private JButton backButton;
    private JButton refreshProductsButton;
    private Set<BranchProduct> branchProductList;
    private Branch branchOfUser;

    public ProductListFrame(Branch branchOfUser) {

        this.setTitle("Λίστα Αποθεμάτων");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.branchOfUser = branchOfUser;

        addCLickListeners();
        loadTable();
    }

    private void addCLickListeners() {

        addProductsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddBranchProductForm(branchOfUser);
                dispose();
            }
        });

        refreshProductsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BranchList branchList = new BranchList();
                branchOfUser = branchList.refreshBranchData(branchOfUser);
                loadTable();
            }
        });

        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreKeeperFrame(branchOfUser);
                dispose();
            }
        });
    }


    private void loadTable() {
        branchProductList = branchOfUser.getBranchProducts();
        productTable.setModel(BranchProductsLoadTableModel.loadTableBranchProduct(branchProductList));
    }


}
