package stores.ui;


import stores.objects.Store;
import stores.objects.StoreList;
import users.ui.AdministrativeFrame;
import utils.tables_init.StoresLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StoreListFrame extends JFrame {
    private JButton createNewStoreButton;
    private JTable StoreListTable;
    private JButton cancelButton;
    private JPanel panel;
    private ArrayList<Store> storeList;

    public StoreListFrame() {

        this.setTitle("Λίστα Καταστημάτων");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);

        addButtonListeners();
        loadTable();
        setTableCLickListener();
    }

    private void addButtonListeners() {
        createNewStoreButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreFormFrame(null);
                dispose();
            }
        });

        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdministrativeFrame();
                dispose();
            }
        });
    }

    private void loadTable() {
        StoreList storeList = new StoreList();
        this.storeList = storeList.getStoreList();
        StoreListTable.setModel(StoresLoadTableModel.loadTableStores(this.storeList));
    }

    private void setTableCLickListener() {
        StoreListTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    Store StoreClicked = storeList.get(row);
                    dispose();
                    new StoreFormFrame(StoreClicked);
                }
            }
        });
    }
}