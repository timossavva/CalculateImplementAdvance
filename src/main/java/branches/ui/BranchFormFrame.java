package branches.ui;

import branches.objects.Branch;
import branches.objects.BranchList;
import stores.Store;
import stores.StoreList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BranchFormFrame extends JFrame {


    private JPanel panel;
    private JTextField nameTextField;
    private JTextField codeNameTextField;
    private JTextField stateTextField;
    private JTextField cityTextField;
    private JTextField addressTextField;
    private JTextField postalCodeTextField;
    private JTextField phoneNumbTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JComboBox storeComboBox;
    private Branch editBranch = null;
    private boolean editMode = false;
    private final BranchList branchListManager;
    private final StoreList storeListManager;
    private ArrayList<Store> storeList;

    public BranchFormFrame(Branch branch) {

        this.setTitle("Επεξεργάσιμη φόρμα υποκαταστήματος");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        branchListManager = new BranchList();
        storeListManager = new StoreList();


        initComboBox();

        if (branch != null) {
            // We are about to edit the branch object provided.
            loadUserDataToFields(branch);
            editBranch = branch;
            editMode = true;
        }

        addButtonListeners();
    }

    private void initComboBox() {
        /* Fill the combobox with the stores */
        storeList = storeListManager.getStoreList();
        ComboBoxModel model = new DefaultComboBoxModel(storeListManager.getStoreNames(storeList));
        storeComboBox.setModel(model);
    }

    private void loadUserDataToFields(Branch branch) {
        // Select the store of this branch in the storeComboBox.
        for (int i = 0; i < storeList.size(); i++) {
            if (branch.getStore().getId() == storeList.get(i).getId()) {
                storeComboBox.setSelectedIndex(i);
                break;
            }
        }
        nameTextField.setText(branch.getName());
        codeNameTextField.setText(branch.getCodeName());
        stateTextField.setText(branch.getState());
        cityTextField.setText(branch.getCity());
        addressTextField.setText(branch.getAddress());
        postalCodeTextField.setText(branch.getPostalCode());
        phoneNumbTextField.setText(branch.getPhoneNumber());

    }

    private void addButtonListeners() {
        // Add listener to cancel button
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BranchListFrame();
                dispose();
            }
        });

        // Add listener to delete button
        if (editMode) {
            deleteButton.setVisible(true);
            deleteButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int result = JOptionPane.showConfirmDialog(null, "Είστε σίγουροι;");
                    if (result == JOptionPane.OK_OPTION) {
                        branchListManager.deleteBranch(editBranch);
                        new BranchListFrame();
                        dispose();
                    }
                }
            });
        }

        addSaveButtonListener();

    }


    private void addSaveButtonListener() {
        //Add listener to saveButton
        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (validateFields()) {
                    Branch branch = new Branch();
                    //branch.setId();
                    branch.setName(nameTextField.getText());
                    branch.setStore(storeList.get(storeComboBox.getSelectedIndex()));
                    branch.setCodeName(codeNameTextField.getText());
                    branch.setState(stateTextField.getText());
                    branch.setCity(cityTextField.getText());
                    branch.setAddress(addressTextField.getText());
                    branch.setPostalCode(postalCodeTextField.getText());
                    branch.setPhoneNumber(phoneNumbTextField.getText());


                    // We are updating a branch
                    if (editMode) {
                        // Update the branch.
                        branch.setId(editBranch.getId());
                        branchListManager.update(branch);
                        JOptionPane.showMessageDialog(null, "Updated");
                        new BranchListFrame();
                        dispose();
                    } else {
                        // Create new branch.
                        boolean branchCreated = branchListManager.create(branch);
                        if (!branchCreated) {
                            JOptionPane.showMessageDialog(null, "Αυτο το όνομα υποκαταστήματος υπάρχει ήδη.");
                        } else {
                            new BranchListFrame();
                            dispose();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "You must fill all fields!");
                }
            }
        });
    }

    private boolean validateFields() {
        return !nameTextField.getText().equals("") && !codeNameTextField.getText().equals("") &&
                !stateTextField.getText().equals("") && !cityTextField.getText().equals("") &&
                !addressTextField.getText().equals("") && !postalCodeTextField.getText().equals("")
                && !phoneNumbTextField.getText().equals("");
    }

}
