package notifications.objects;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Calendar;


public class NotificationThread implements Runnable {

    private final int delay;
    private final Calendar stopTime;
    private volatile boolean suspended;
    private volatile boolean stopped;

    public NotificationThread(int delay, Calendar stopTime) {
        suspended = false;
        stopped = false;

        this.delay = delay;
        this.stopTime = stopTime;

        Thread notificationThrd = new Thread(this);
        notificationThrd.setDaemon(true);
        notificationThrd.start();
    }

    public void run() {
        try {

            boolean stopNotifications = false;
            while (!stopNotifications) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }

                Calendar currentTime = Calendar.getInstance();
                if (currentTime.compareTo(stopTime) >= 0) {
                    stopNotifications = true;
                    System.out.println(("Notifications stoped."));
                }

                ArrayList<Notification> notificationList = NotificationList.getNotifications();
                for (Notification notification : notificationList) {
                    if (notification.isEnable()) {
                        boolean completed = notification.checkIfCompleted();
                        if (completed) {
                            notification.setEnable(false);
                            System.out.println("completed");
                            if (SystemTray.isSupported()) {
                                String accountTitle = notification.getPrimaryAccount() != null ? notification.getPrimaryAccount().getName() : notification.getIndicator().getTitle();
                                String operator = notification.getOperator().equals(">") ? "above" : "below";
                                displayTray("CIA - Goal Achieved", accountTitle + " just got " + operator + " " + notification.getValue());
                            } else {
                                System.err.println("System tray not supported!");
                            }
                        }
                    }
                }

                Thread.sleep(delay * 1000);
                if (suspended || stopped) synchronized (this) {
                    while (suspended) wait();
                    if (stopped) {
                        break;
                    }
                }
            }

        } catch (InterruptedException | AWTException e) {
            System.out.println("Notifications Thread Interrupted");
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

    public void displayTray(String caption, String message) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(caption, message, MessageType.INFO);
    }
}
