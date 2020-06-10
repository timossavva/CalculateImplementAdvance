import branches.objects.SaleThread;
import users.ui.LoginForm;

import javax.swing.*;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);

        Calendar stopTime = Calendar.getInstance();
        stopTime.set(Calendar.HOUR, 12);
        SaleThread salethread = new SaleThread(2, stopTime);
    }

}