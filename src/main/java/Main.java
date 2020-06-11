import branches.objects.SaleThread;
import users.ui.LoginForm;

import javax.swing.*;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);

        Calendar stopTime = Calendar.getInstance();
        stopTime.set(Calendar.HOUR, 12);
        SaleThread saleThread = new SaleThread(5, stopTime);
        try {
            Thread.sleep(5000);
            System.out.println("Suspend Thread");
            saleThread.suspendThread();
            Thread.sleep(15000);
            System.out.println("Resume Thread");
            saleThread.resumeThread();
            Thread.sleep(5000);
            System.out.println("Stop Thread");
            saleThread.stopThread();
        }
        catch(InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
    }

}