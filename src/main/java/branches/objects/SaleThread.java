package branches.objects;

import products.objects.BranchProduct;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class SaleThread implements Runnable {
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
                    double sum = 0;
                    for (int i = 0; i < arrayOfProducts.length / 2; i++) {
                        BranchProduct bp = arrayOfProducts[rand.nextInt(arrayOfProducts.length)];
                        if (bp.getQuantity() > 10) {
                            int quantity = rand.nextInt(5) + 1;
                            bp.setQuantity(bp.getQuantity() - quantity);
                            sum += quantity * bp.getProduct().getPrice();
                        } else {
                            bp.setQuantity(30);
                        }
                    }
                    BranchList.update(b);
                    System.out.println(b.getName() + " branch sold " + sum);

                }
                Thread.sleep(delay * 1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Sales Interrupted");
        }
    }
}
