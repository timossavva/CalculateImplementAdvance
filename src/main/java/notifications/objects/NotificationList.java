package notifications.objects;

import branches.objects.Branch;

import java.util.ArrayList;

public class NotificationList {

    private static ArrayList<Notification> notificationsList;


    public void addNotification(Notification notification){
        notificationsList.add(notification);
    }

    public static ArrayList<Notification> getNotifications(){
        return notificationsList;
    }

}
