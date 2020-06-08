package products.ui;

import branches.objects.Branch;
import org.hibernate.Session;
import products.objects.BranchProduct;
import products.objects.Product;
import products.objects.ProductList;
import utils.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Set;

public class AddBranchProductForm extends JFrame {
    private final JButton addButton;
    private JTextField[] quantityTextFields;
    private ArrayList<Product> allProducts;
    private final Branch branchOfUser;

    public AddBranchProductForm(Branch branchOfUser) {

        this.branchOfUser = branchOfUser;
        setTitle("JScrollablePanel Test");
        setLayout(new BorderLayout());
        JPanel panel = createPanel();

        add(BorderLayout.CENTER, new JScrollPane(panel));

        addButton = new JButton("Προσθήκη");
        add(addButton, BorderLayout.PAGE_END);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addListeners();
    }


    public JPanel createPanel() {
        ProductList productList = new ProductList();
        allProducts = productList.getProductList();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(allProducts.size(), 4, 10, 10));
        quantityTextFields = new JTextField[allProducts.size()];

        for (int i = 0; i < allProducts.size(); i++) {

            JLabel label = new JLabel(allProducts.get(i).getName());
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(label);

            JButton removeButton = new JButton("-");
            panel.add(removeButton);

            JTextField quantity = new JTextField();
            quantityTextFields[i] = quantity;
            quantity.setText("0");
            panel.add(quantity);

            JButton addButton = new JButton("+");
            panel.add(addButton);

            removeButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try {
                        int quantityInt = Integer.parseInt(quantity.getText());
                        if (quantityInt > 0) {
                            quantity.setText(String.valueOf(quantityInt - 1));
                        }
                    } catch (java.lang.NumberFormatException e) {
                        quantity.setText("0");
                    }


                }
            });

            addButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int quantityInt = Integer.parseInt(quantity.getText());
                        quantity.setText(String.valueOf(quantityInt + 1));
                    } catch (java.lang.NumberFormatException e) {
                        quantity.setText("0");
                    }
                }
            });

        }


        return panel;
    }


    private void addListeners() {
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                /* Check if all fields are integers */
                boolean validationPassed = true;
                for (JTextField field : quantityTextFields) {
                    try {
                        Integer.parseInt(field.getText());
                    } catch (java.lang.NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Λάθος Καταχωρήσεις. Εισάγετε Ακέραιες Ποσότητες Προϊόντων!");
                        validationPassed = false;
                        new AddBranchProductForm(branchOfUser);
                        dispose();
                        break;
                    }
                }


                if (validationPassed) {

                    /* Update the branch's products quantity for existing products */
                    Set<BranchProduct> branchProducts = branchOfUser.getBranchProducts();
                    for (int i = 0; i < quantityTextFields.length; i++) {
                        boolean exists = false;
                        for (BranchProduct branchProduct : branchProducts) {
                            if (branchProduct.getProduct().equals(allProducts.get(i))) {
                                branchProduct.setQuantity(branchProduct.getQuantity() + Integer.parseInt(quantityTextFields[i].getText()));
                                exists = true;
                            }
                        }
                        /* If the product doesn't exists, then create it with the selected quantity */
                        if (!exists) {
                            BranchProduct branchProduct = new BranchProduct();
                            branchProduct.setBranch(branchOfUser);
                            branchProduct.setProduct(allProducts.get(i));
                            branchProduct.setQuantity(Integer.parseInt(quantityTextFields[i].getText()));
                            branchProducts.add(branchProduct);
                        }
                    }

                    /* Update the branch's storage */
                    branchOfUser.setBranchProducts(branchProducts);
                    HibernateUtil hibernateUtil = new HibernateUtil();
                    Session session = hibernateUtil.beginSessionTransaction();
                    session.saveOrUpdate(branchOfUser);
                    session.getTransaction().commit();
                    session.close();
                    hibernateUtil.shutdown();

                    new ProductListFrame(branchOfUser);
                    dispose();
                }
            }
        });
    }
}
