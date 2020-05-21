package notifications;

import java.util.Date;

public abstract class Notification {

    protected long id;
    protected long storeId;
    protected long branchId;
    protected float value;
    protected Date date;


    public abstract boolean checkIfCompleted();

}
