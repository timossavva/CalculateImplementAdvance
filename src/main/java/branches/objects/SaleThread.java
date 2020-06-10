package branches.objects;

import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountBranch;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountsManager;
import products.objects.BranchProduct;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SaleThread implements Runnable {
    private static final int MIN_PRODUCTS_NUMBER = 50;
    private static final int PRODUCT_NUMBER_TO_UPDATE = 100;
    private final int delay;
    private final Calendar stopTime;

    public SaleThread(int delay, Calendar stopTime) {
        this.delay = delay;
        this.stopTime = stopTime;

        Thread saleThread = new Thread(this);
        saleThread.setDaemon(true);
        saleThread.start();
    }

    public void run() {
        try {
            boolean stopSales = false;
            insertStaticPrimaryAccountsForToday();
            while (!stopSales) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }

                Calendar currentTime = Calendar.getInstance();
                if (currentTime.compareTo(stopTime) >= 0) {
                    stopSales = true;
                    System.out.println(("Sales stoped."));
                }


                ArrayList<Branch> branchList = BranchList.getBranchList();
                for (Branch b : branchList) {
                    Random rand = new Random(System.currentTimeMillis());
                    BranchProduct[] arrayOfProducts = b.getBranchProducts().toArray(new BranchProduct[0]);
                    double receipt_stock_price = 0, receipt_final_price = 0;
                    for (int i = 0; i < arrayOfProducts.length / 2; i++) {
                        BranchProduct bp = arrayOfProducts[rand.nextInt(arrayOfProducts.length)];
                        if (bp.getQuantity() > MIN_PRODUCTS_NUMBER) {
                            int quantity = rand.nextInt(5) + 1;
                            bp.setQuantity(bp.getQuantity() - quantity);
                            double price = quantity * bp.getProduct().getPrice();
                            receipt_stock_price += price;
                            receipt_final_price += price * 1.33;
                        } else {
                            // Storage of Branch is lower than MIN_PRODUCTS_NUMBER, so we are filling the storage
                            bp.setQuantity(PRODUCT_NUMBER_TO_UPDATE);
                            double priceToSubtractFromCash = bp.getProduct().getPrice() * 1.33 * PRODUCT_NUMBER_TO_UPDATE;
                            updatePrimaryAccounts(b, -priceToSubtractFromCash, 8); // Χρηματικά διαθέσιμα και ισοδύναμα
                        }
                    }
                    updatePrimaryAccounts(b, -receipt_stock_price, 6); // Αποθέματα
                    updatePrimaryAccounts(b, receipt_stock_price, 21); // Ημερήσιο Κόστος Πωληθέντων
                    updatePrimaryAccounts(b, receipt_final_price, 8); // Χρηματικά διαθέσιμα και ισοδύναμα
                    updatePrimaryAccounts(b, receipt_final_price, 19); // Ημερήσιες Πωλήσεις

                    BranchList.update(b);
                    System.out.println(b.getName() + " branch sold " + receipt_stock_price);

                }
                Thread.sleep(delay * 1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Sales Interrupted");
        }
    }

    private void insertStaticPrimaryAccountsForToday() {
        // insert primary accounts with id 1,2,3,7,11,13,14,25,
        //TODO
    }

    private void updatePrimaryAccounts(Branch branch, double value, long primaryAccountId) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = df.format(new Date());
        ArrayList<PrimaryAccountBranch> primaryAccountBranches = new ArrayList<>(branch.getPrimaryAccountBranchHistory());
        PrimaryAccount primaryAccountToUpdate = PrimaryAccountsManager.getPrimaryAccountByID(primaryAccountId);
        boolean found = false;
        if (primaryAccountToUpdate != null) {
            for (PrimaryAccountBranch p : primaryAccountBranches) {
                if (p.getPrimaryAccount().getId() == primaryAccountToUpdate.getId() && p.getDate() != null && p.getDate().equals(todayDate)) {
                    p.setValue(p.getValue() + (float) value);
                    BranchList.update(branch);
                    System.out.println("Primary Account with name '" + primaryAccountToUpdate.getName()
                            + "' updated for branch with name -> " + branch.getName() + " to the value of ->" + value);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Primary Account with name '" + primaryAccountToUpdate.getName()
                        + "' for the branch with name -> " + branch.getName() + " and date -> " +
                        todayDate + " not existed so creating the record with the value of -> " + value);
                PrimaryAccountBranch primaryAccountBranch = new PrimaryAccountBranch();
                primaryAccountBranch.setDate(todayDate);
                primaryAccountBranch.setValue((float) value);
                primaryAccountBranch.setBranch(branch);
                primaryAccountBranch.setPrimaryAccount(primaryAccountToUpdate);
                PrimaryAccountsManager.create(primaryAccountBranch);
            }
        }
    }
}
