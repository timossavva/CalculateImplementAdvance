package branches;

import users.AdministrativeFrame;
import utils.tables_init.BranchesLoadTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BranchListFrame extends JFrame {
    private JButton createNewBranchButton;
    private JButton cancelButton;
    private JTable BranchListTable;
    private JPanel panel;
    private ArrayList<Branch> branchList;

    public BranchListFrame() {

        this.setTitle("Λίστα Υποκαταστημάτων");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);

        addButtonListeners();
        loadTable();
        setTableCLickListener();
    }

    private void addButtonListeners() {
        createNewBranchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BranchFormFrame(null);
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
        BranchList branchList = new BranchList();
        this.branchList = branchList.getBranchList();
        BranchListTable.setModel(BranchesLoadTableModel.loadTableBranches(this.branchList));
    }

    private void setTableCLickListener() {
        BranchListTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    Branch BranchClicked = branchList.get(row);
                    dispose();
                    new BranchFormFrame(BranchClicked);
                }
            }
        });
    }
}
