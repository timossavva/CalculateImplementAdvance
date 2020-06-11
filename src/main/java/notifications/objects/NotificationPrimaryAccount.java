package notifications.objects;

import branches.objects.Branch;
import notifications.objects.Notification;
import primary_accounts_and_indicators.indicators.Indicator;
import primary_accounts_and_indicators.indicators.IndicatorManager;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountsManager;
import stores.objects.Store;

import java.util.Date;

public class NotificationPrimaryAccount extends Notification {

    public NotificationPrimaryAccount(Store store, Branch branch, String date, double value, boolean enable, String operator, Indicator indicator, PrimaryAccount primaryAccount) {
        super(store, branch, date, value, enable, operator, indicator, primaryAccount);
    }

    public boolean checkIfCompleted(){
        double result;
        if (branch != null) {
            result = PrimaryAccountsManager.calcPrimaryAccount(false, primaryAccount, null, branch, date);
        } else {
            // Store
            result = PrimaryAccountsManager.calcPrimaryAccount(true, primaryAccount, store, null, date);
        }
        if ( ( (result - value > 0) && operator.equals(">") ) || ( (result - value < 0) && operator.equals("<") ) )
            return true;
        else
            return false;
    }

}
