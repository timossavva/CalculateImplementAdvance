package products;

import branches.Branch;
import branches.BranchList;
import users.StoreKeeperFrame;
import utils.tables_init.BranchProductsLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Set;

public class ProductListFrame extends JFrame {
    private JPanel panel;
    private JButton addProductsButton;
    private JTable table1;
    private JButton cancelButton;
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
//                table1.repaint();
            }
        });

        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreKeeperFrame(branchOfUser);
                dispose();
            }
        });
    }


    private void loadTable() {
        this.branchProductList = branchOfUser.getBranchProducts();
        ArrayList<BranchProduct> branchProductsList = new ArrayList<>(branchProductList);
        System.out.println(branchProductsList.get(0).getQuantity());
        table1.setModel(BranchProductsLoadTableModel.loadTableBranchProduct(this.branchProductList));
    }


}
