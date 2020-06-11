package notifications.objects;

import branches.objects.Branch;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountsManager;
import stores.objects.Store;

import java.util.Date;

public class NotificationIndicator extends Notification {

    public NotificationIndicator(Store store, Branch branch, Date date, double value, boolean enable, String operator) {
        super(store, branch, date, value, enable, operator);
    }
    public boolean checkIfCompleted() {
        if (branch != null) {
            double value = PrimaryAccountsManager.calcPrimaryAccount(false, primaryAccount, null, branch, dateString);
        }
        else {

        }
    }
}
