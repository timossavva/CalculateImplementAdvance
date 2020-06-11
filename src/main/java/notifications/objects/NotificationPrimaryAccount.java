package notifications.objects;

import branches.objects.Branch;
import notifications.objects.Notification;
import stores.objects.Store;

import java.util.Date;

public class NotificationPrimaryAccount extends Notification {

    public NotificationPrimaryAccount(Store store, Branch branch, Date date, double value, boolean enable, String operator) {
        super(store, branch, date, value, enable, operator);
    }

    public boolean checkIfCompleted(){
        if(branch != null)
    }

}
