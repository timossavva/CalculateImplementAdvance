package notifications.objects;
import java.util.ArrayList;
import java.util.Calendar;


public class NotificationThread implements Runnable {

    private volatile boolean suspended;
    private volatile boolean stopped;

    private final int delay;
    private final Calendar stopTime;

    public NotificationThread(int delay, Calendar stopTime) {
        suspended=false;
        stopped=false;

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
                    if( notification.isEnable() ) {
                        boolean completed = notification.checkIfCompleted();

                    }
                }

                Thread.sleep(delay * 1000);
                if (suspended || stopped) synchronized (this) {
                        while(suspended) wait();
                        if (stopped) {
                            break;
                        }
                    }
            }

        } catch (InterruptedException e) {
            System.out.println("Notifications Thread Interrupted");
        }
    }

    public synchronized void stopThread(){
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
