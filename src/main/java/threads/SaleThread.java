package threads;

import branches.objects.Branch;
import branches.objects.BranchList;
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
    private final double[] workingHoursSalesSum = new double[BranchList.getBranchList().size()];
    private volatile boolean suspended;
    private volatile boolean stopped;

    public SaleThread(int delay, Calendar stopTime) {
        suspended = false;
        stopped = false;

        this.delay = delay;
        this.stopTime = stopTime;

        Thread saleThread = new Thread(this);
        saleThread.setDaemon(true);
        saleThread.start();
    }

    public void run() {
        try {
            boolean stopSales = false;
            while (!stopSales) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }

                Calendar currentTime = Calendar.getInstance();
                if (currentTime.compareTo(stopTime) >= 0) {
                    stopSales = true;
                }

                ArrayList<Branch> branchList = BranchList.getBranchList();
                for (int i = 0; i < branchList.size(); i++) {
                    double stockAdded=0;
                    Branch branch = branchList.get(i);
                    Random rand = new Random(System.currentTimeMillis());
                    BranchProduct[] arrayOfProducts = branch.getBranchProducts().toArray(new BranchProduct[0]);
                    double receipt_stock_price = 0, receipt_final_price = 0;
                    for (int j = 0; j < arrayOfProducts.length/2; j++) {
                        BranchProduct bp = arrayOfProducts[rand.nextInt(arrayOfProducts.length)];
                        if (bp.getQuantity() > MIN_PRODUCTS_NUMBER) {
                            int quantity = rand.nextInt(5) + 1;
                            bp.setQuantity(bp.getQuantity() - quantity);
                            double price = quantity * bp.getProduct().getPrice();
                            receipt_stock_price += price;
                            receipt_final_price += price * 1.33;
                            workingHoursSalesSum[i] += price * 1.33;
                            System.out.println(workingHoursSalesSum[i] + "  |  " + branch.getName());
                        } else {
                            // Storage of Branch is lower than MIN_PRODUCTS_NUMBER, so we are filling the storage
                            bp.setQuantity(bp.getQuantity() + PRODUCT_NUMBER_TO_UPDATE);
                            double priceToSubtractFromCash = bp.getProduct().getPrice() * PRODUCT_NUMBER_TO_UPDATE;
                            PrimaryAccount prAcc = PrimaryAccountsManager.getPrimaryAccountByID(8);
                            String dateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                            if (priceToSubtractFromCash < PrimaryAccountsManager.calcPrimaryAccount(false, prAcc, null, branch, dateString)) {
                                stockAdded += priceToSubtractFromCash;
                                updatePrimaryAccounts(branch, -priceToSubtractFromCash, 8); // Χρηματικά διαθέσιμα και ισοδύναμα
                            }

                        }
                    }

                    // For every branch, every 50 euro worth of sales, update the working hours primary account by 1.
                    if (workingHoursSalesSum[i] > 50) {
                        updatePrimaryAccounts(branch, 1, 32);
                        workingHoursSalesSum[i] = 0;
                        System.out.println("Working hours for branch with name -> " + branch.getName() + " was increased by 1 because 50 euro worth of sales were made.");
                    }


                    updatePrimaryAccounts(branch, (stockAdded-receipt_stock_price), 6); // Αποθέματα
                    updatePrimaryAccounts(branch, receipt_stock_price, 21); // Ημερήσιο Κόστος Πωληθέντων
                    updatePrimaryAccounts(branch, receipt_final_price, 8); // Χρηματικά διαθέσιμα και ισοδύναμα
                    updatePrimaryAccounts(branch, receipt_final_price, 19); // Ημερήσιες Πωλήσεις


                    BranchList.update(branch);

                    Thread.sleep(delay * 1000);
                    if (suspended || stopped) synchronized (this) {
                        while (suspended) wait();
                        if (stopped) {
                            stopSales = true;
                            break;
                        }
                    }
                }

            }
        } catch (InterruptedException e) {
            System.out.println("Sales Interrupted");
        }
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

    public synchronized void stopThread() {
        stopped = true;
        suspended = false;
        notify();
    }

    public synchronized void suspendThread() {
        suspended = true;
    }

    public synchronized void resumeThread() {
        suspended = false;
        notify();
    }
}
