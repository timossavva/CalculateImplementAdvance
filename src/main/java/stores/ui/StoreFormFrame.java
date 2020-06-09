package stores.ui;

import stores.objects.Store;
import stores.objects.StoreList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StoreFormFrame extends JFrame {
    private JTextField nameTxtField;
    private JTextField codeNameTxtField;
    private JTextField stateTxtField;
    private JTextField cityTxtField;
    private JTextField addressTxtField;
    private JTextField postalCodeTxtField;
    private JTextField phoneNumbTxtField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JPanel panel;
    private ArrayList<Store> storeList;
    private Store editStore = null;
    private boolean editMode = false;
    private final StoreList storeListManager;

    public StoreFormFrame(Store store) {

        this.setTitle("Επεξεργάσιμη φόρμα Καταστήματος");
        this.setSize(600, 600);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        if (store != null) {
            // We are about to edit the store object provided.
            loadUserDataToFields(store);
            editStore = store;
            editMode = true;
        }

        storeListManager = new StoreList();

        addButtonListeners();

    }

    private void loadUserDataToFields(Store store) {


        nameTxtField.setText(store.getName());
        codeNameTxtField.setText(store.getCodeName());
        stateTxtField.setText(store.getState());
        cityTxtField.setText(store.getCity());
        addressTxtField.setText(store.getAddress());
        postalCodeTxtField.setText(store.getPostalCode());
        phoneNumbTxtField.setText(store.getPhoneNumber());

    }

    private void addButtonListeners() {
        // Add listener to cancel button
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StoreListFrame();
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
                        storeListManager.deleteStore(editStore);
                        new StoreListFrame();
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
                    Store store = new Store();
                    //store.setId();
                    store.setName(nameTxtField.getText());
                    store.setCodeName(codeNameTxtField.getText());
                    store.setState(stateTxtField.getText());
                    store.setCity(cityTxtField.getText());
                    store.setAddress(addressTxtField.getText());
                    store.setPostalCode(postalCodeTxtField.getText());
                    store.setPhoneNumber(phoneNumbTxtField.getText());


                    // We are updating a store
                    if (editMode) {
                        // Update the store.
                        store.setId(editStore.getId());
                        storeListManager.update(store);
                        JOptionPane.showMessageDialog(null, "Updated");
                        new StoreListFrame();
                        dispose();
                    } else {
                        // Create new store.
                        boolean storeCreated = storeListManager.create(store);
                        if (!storeCreated) {
                            JOptionPane.showMessageDialog(null, "Αυτο το όνομα Καταστήματος υπάρχει ήδη.");
                        } else {
                            new StoreListFrame();
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
        return !nameTxtField.getText().equals("") && !codeNameTxtField.getText().equals("") &&
                !stateTxtField.getText().equals("") && !cityTxtField.getText().equals("") &&
                !addressTxtField.getText().equals("") && !postalCodeTxtField.getText().equals("")
                && !phoneNumbTxtField.getText().equals("");
    }

}
