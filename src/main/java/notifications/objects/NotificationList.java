package notifications.objects;

import java.util.ArrayList;

public class NotificationList {

    private static ArrayList<Notification> notificationsList = new ArrayList<>();

    public static ArrayList<Notification> getNotifications() {
        return notificationsList;
    }

    public static void addNotification(Notification notification) {
        notificationsList.add(notification);
    }

}
