package notifications.objects;

import branches.objects.Branch;
import primary_accounts_and_indicators.indicators.Indicator;
import primary_accounts_and_indicators.indicators.IndicatorManager;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import stores.objects.Store;

public class NotificationIndicator extends Notification {

    public NotificationIndicator(Store store, Branch branch, String date, double value, boolean enable, String operator, Indicator indicator, PrimaryAccount primaryAccount) {
        super(store, branch, date, value, enable, operator, indicator, primaryAccount);

    }

    public boolean checkIfCompleted() {
        double result;
        if (branch != null) {
            result = IndicatorManager.calcIndicator(false, indicator, null, branch, date);
        } else {
            // Store
            result = IndicatorManager.calcIndicator(true, indicator, store, null, date);
        }
        return ((result > value) && operator.equals(">")) || ((result < value) && operator.equals("<"));
    }
}
