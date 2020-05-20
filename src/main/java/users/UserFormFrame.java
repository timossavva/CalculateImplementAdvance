package users;

import branches.Branch;
import branches.BranchList;
import stores.Store;
import stores.StoreList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class UserFormFrame extends JFrame {
    private JPanel panel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox typeComboBox;
    private JComboBox storeComboBox;
    private JComboBox branchComboBox;
    private JButton saveButton;
    private JLabel storeLabel;
    private JLabel branchLabel;
    private JButton cancelButton;
    private JButton deleteButton;
    private String[] accountTypes;
    private ArrayList<Branch> allBranches;
    private ArrayList<Store> allStores;
    private User editUser = null;
    private boolean editMode = false;
    private final UserList userList;

    public UserFormFrame(User user) {

        this.setTitle(user == null ? "Δημιουργία Χρήστη" : "Επεξεργάσιμη Φόρμα Χρήστη");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        userList = new UserList();

        initComboBoxes();

        if (user != null) {
            // We are about to edit the user object provided.
            loadUserDataToFields(user);
            editUser = user;
            editMode = true;
        }

        addButtonListeners();
    }

    private void initComboBoxes() {
        /* Fill the combobox with the account types */
        accountTypes = AccountType.getAccountTypes();
        ComboBoxModel model = new DefaultComboBoxModel(accountTypes);
        typeComboBox.setModel(model);

        /* Add listener to typeComboBox */
        typeComboBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setConditionalLogicOnComboBoxes();
            }
        });

        /* Fill the combobox with the stores */
        StoreList storeList = new StoreList();
        allStores = storeList.getStoreList();
        String[] storeNames = new String[allStores.size()];
        for (int i = 0; i < allStores.size(); i++) {
            storeNames[i] = allStores.get(i).getName();
        }
        model = new DefaultComboBoxModel(storeNames);
        storeComboBox.setModel(model);

        /* Fill the combobox with the branches */
        BranchList branchList = new BranchList();
        allBranches = branchList.getBranchList();
        String[] branchNames = new String[allBranches.size()];
        for (int i = 0; i < allBranches.size(); i++) {
            branchNames[i] = allBranches.get(i).getName();
        }
        model = new DefaultComboBoxModel(branchNames);
        branchComboBox.setModel(model);

    }

    private void loadUserDataToFields(User user) {
        nameTextField.setText(user.getName());
        usernameTextField.setText(user.getUsername());
        passwordTextField.setText(user.getPassword());

        // Select the type of the user in the typeComboBox.
        for (int i = 0; i < accountTypes.length; i++) {
            if (user.getType().equals(accountTypes[i])) {
                typeComboBox.setSelectedIndex(i);
                break;
            }
        }
        setConditionalLogicOnComboBoxes();


        // Select the store of the user in the storeComboBox (if exists).
        for (int i = 0; i < allStores.size(); i++) {
            Store store = user.getStore();
            if (store != null && store.getId() == allStores.get(i).getId()) {
                storeComboBox.setSelectedIndex(i);
                break;
            }
        }

        // Select the branch of the user in the branchComboBox (if exists).
        for (int i = 0; i < allBranches.size(); i++) {
            Branch branch = user.getBranch();
            if (branch != null && branch.getId() == allBranches.get(i).getId()) {
                branchComboBox.setSelectedIndex(i);
                break;
            }
        }

    }

    private void addButtonListeners() {
        /* Add listener to cancel button */
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UserListFrame();
                dispose();
            }
        });

        /* Add listener to delete button */
        if (editMode) {
            deleteButton.setVisible(true);
            deleteButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int result = JOptionPane.showConfirmDialog(null, "Είστε σίγουροι;");
                    if (result == JOptionPane.OK_OPTION) {
                        userList.deleteUser(editUser);
                        new UserListFrame();
                        dispose();
                    }
                }
            });
        }

        addSaveButtonListener();

    }


    private void addSaveButtonListener() {
        /* Add listener to saveButton */
        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (validateFields()) {
                    String accountType = (String) typeComboBox.getSelectedItem();
                    Branch branch = new Branch();
                    Store store = new Store();
                    if (accountType.equals(accountTypes[0])) {
                        store = null;
                        branch = null;
                    } else if (accountType.equals(accountTypes[1]) || accountType.equals(accountTypes[2]) ||
                            accountType.equals(accountTypes[4]) || accountType.equals(accountTypes[5])) {
                        store = null;
                        branch = allBranches.get(branchComboBox.getSelectedIndex());
                    } else if (accountType.equals(accountTypes[3])) {
                        store = allStores.get(storeComboBox.getSelectedIndex());
                        branch = null;
                    }

                    User user = new User();
                    user.setName(nameTextField.getText());
                    user.setUsername(usernameTextField.getText());
                    user.setPassword(passwordTextField.getText());
                    user.setType(accountType);
                    user.setStore(store);
                    user.setBranch(branch);


                    // We are updating a user
                    if (editMode) {
                        // Update the user.
                        user.setId(editUser.getId());
                        userList.update(user);
                        new UserListFrame();
                        dispose();
                    } else {
                        // Create new user.
                        boolean userCreated = userList.create(user);
                        if (!userCreated) {
                            JOptionPane.showMessageDialog(null, "Αυτο το όνομα χρήστη δεν είναι διαθέσιμο.");
                        } else {
                            new UserListFrame();
                            dispose();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "You must fill all fields!");
                }
            }
        });
    }

    private void setConditionalLogicOnComboBoxes() {
        String accountType = (String) typeComboBox.getSelectedItem();
        if (accountType.equals(accountTypes[1]) || accountType.equals(accountTypes[2]) ||
                accountType.equals(accountTypes[4]) || accountType.equals(accountTypes[5])) {
            storeComboBox.setVisible(false);
            storeLabel.setVisible(false);
            branchLabel.setVisible(true);
            branchComboBox.setVisible(true);
        } else if (accountType.equals(accountTypes[3])) {
            branchLabel.setVisible(false);
            branchComboBox.setVisible(false);
            storeComboBox.setVisible(true);
            storeLabel.setVisible(true);
        } else {
            branchLabel.setVisible(false);
            branchComboBox.setVisible(false);
            storeComboBox.setVisible(false);
            storeLabel.setVisible(false);
        }
    }

    private boolean validateFields() {
        return !nameTextField.getText().equals("") && !usernameTextField.getText().equals("") && !passwordTextField.getText().equals("");
    }

}
