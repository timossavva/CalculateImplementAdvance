package notifications.objects;

import branches.objects.Branch;
import branches.objects.BranchList;
import primary_accounts_and_indicators.indicators.Indicator;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccountsManager;
import stores.objects.Store;

public class NotificationPrimaryAccount extends Notification {

    public NotificationPrimaryAccount(Store store, Branch branch, String date, double value, boolean enable, String operator, Indicator indicator, PrimaryAccount primaryAccount) {
        super(store, branch, date, value, enable, operator, indicator, primaryAccount);
    }

    public boolean checkIfCompleted() {
        double result;
        if (branch != null) {
            branch = BranchList.getBranchById(branch.getId());
            result = PrimaryAccountsManager.calcPrimaryAccount(false, primaryAccount, null, branch, date);
        } else {
            // Store
            result = PrimaryAccountsManager.calcPrimaryAccount(true, primaryAccount, store, null, date);
        }
        System.out.println("result -> " + result + " | value -> " + value);
        return ((result - value > 0) && operator.equals(">")) || ((result - value < 0) && operator.equals("<"));
    }

}
