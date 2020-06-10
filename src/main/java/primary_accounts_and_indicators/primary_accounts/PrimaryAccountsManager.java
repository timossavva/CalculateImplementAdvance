package primary_accounts_and_indicators.primary_accounts;

import branches.objects.Branch;
import org.hibernate.Session;
import stores.objects.Store;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class PrimaryAccountsManager {

    public static void create(PrimaryAccountBranch primaryAccountBranch) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.save(primaryAccountBranch);
        session.flush();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public static ArrayList<PrimaryAccount> getPrimaryAccountList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<PrimaryAccount> primaryAccountList = (ArrayList<PrimaryAccount>) session.createQuery("from PrimaryAccount ").list();
        session.flush();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return primaryAccountList;
    }

    public static String[] getPrimaryAccountTitles() {
        ArrayList<PrimaryAccount> primaryAccountsList = getPrimaryAccountList();
        String[] primaryAccountTitles = new String[primaryAccountsList.size()];
        for (int i = 0; i < primaryAccountsList.size(); i++) {
            primaryAccountTitles[i] = primaryAccountsList.get(i).getName();
        }
        return primaryAccountTitles;
    }

    public static PrimaryAccount getPrimaryAccountByID(long id) {
        ArrayList<PrimaryAccount> primaryAccountList = getPrimaryAccountList();
        for (PrimaryAccount primaryAccount : primaryAccountList)
            if (primaryAccount.getId() == id)
                return primaryAccount;
        return null;
    }

    public static double calcPrimaryAccount(boolean storeOrBranch, PrimaryAccount primaryAccount, Store store, Branch branch, String date) {
        if (storeOrBranch) {
            // Store
            return calcPrimaryAccountForStore(primaryAccount, store, date);
        } else {
            // Branch
            return calcPrimaryAccountForBranch(primaryAccount, branch, date);
        }
    }

    private static double calcPrimaryAccountForStore(PrimaryAccount primaryAccount, Store store, String date) {
        double sum = 0;
        for (Branch branch : store.getBranches())
            sum += calcPrimaryAccountForBranch(primaryAccount, branch, date);
        return sum;
    }

    private static double calcPrimaryAccountForBranch(PrimaryAccount primaryAccount, Branch branch, String date) {
        // Check if the this primary account its calculated dynamically
        if (primaryAccount.isDynamic()) {
            return calcComplexPrimaryAccountValue(primaryAccount, branch, date);
        } else {
            // If not then, get the value from db
            return getPrimaryAccountValueFromDB(primaryAccount, branch, date);
        }

    }


    private static double getPrimaryAccountValueFromDB(PrimaryAccount primaryAccount, Branch branch, String date) {
        // Get all the primary account values for this store
        Set<PrimaryAccountBranch> primaryAccountBranchHistory = branch.getPrimaryAccountBranchHistory();
        for (PrimaryAccountBranch p : primaryAccountBranchHistory) {
            // If this primary account is yearly then don't filter by date
            if (primaryAccount.isYearly()) {
                if (p.getPrimaryAccount().equals(primaryAccount)) {
                    return p.getValue();
                }
            } else {
                // Else filter those values with the date and this primary account
                if (p.getPrimaryAccount().equals(primaryAccount) && p.getDate().equals(date)) {
                    return p.getValue();
                }
            }
        }
        return -1;
    }

    private static double calcComplexPrimaryAccountValue(PrimaryAccount primaryAccount, Branch branch, String date) {
        switch ((int) primaryAccount.getId()) {
            case 4:
                // Σωρευμένες Αποσβέσεις Παγίων = Αποσβέσεις Προηγούμενων Ετών + Φετινές Αποσβέσεις
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(2), branch, date) +
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(3), branch, date);
            case 5:
                // Σύνολο παγίου ενεργητικού = Παγια -	Σωρευμένες Αποσβέσεις Παγίων
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(1), branch, date) -
                        calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(4)), branch, date);
            case 9:
                // Σύνολο κυκλοφορούντος ενεργητικού	=	Αποθέματα	+	Πελάτες και λοιπές βραχυπρόθεσμες απαιτήσεις	+	Χρηματικά διαθέσιμα και ισοδύναμα
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(6), branch, date) +
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(7), branch, date) +
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(8), branch, date);
            case 10:
                // Σύνολο Ενεργητικού	=	Σύνολο παγίου ενεργητικού	+	Σύνολο κυκλοφορούντος ενεργητικού
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(5)), branch, date) +
                        calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(9)), branch, date);
            case 15:
                // Σύνολο βραχυπρόθεσμων υποχρεώσεων	=	Προμηθευτές και λοιποί πιστωτές (Πληρ. Λογαρ.)	+	Δάνεια βραχυπρόθεσμα
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(13), branch, date) +
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(14), branch, date);
            case 16:
                // Σύνολο υποχρεώσεων	=	Σύνολο μακροπρόθεσμων υποχρεώσεων	+	Σύνολο βραχυπρόθεσμων υποχρεώσεων
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(11), branch, date) +
                        calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(15)), branch, date);
            case 18:
                // Σύνολο Υποχρεώσεων και Ιδίων Κεφαλαίων	=	Σύνολο υποχρεώσεων	+	Σύνολο Ιδίων Κεφαλαίων
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(16)), branch, date) +
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(17), branch, date);
            case 20:
                // Κύκλος εργασιών (Πωλήσεις)	=	Ημερήσιες Πωλήσεις	(Αθροισμα)
                return calcPrimaryAccountSum(PrimaryAccountsManager.getPrimaryAccountByID(19), branch);
            case 22:
                // Κόστος πωληθέντων	=	Ημερήσιο Κόστος Πωληθέντων (Αθροισμα)
                return calcPrimaryAccountSum(PrimaryAccountsManager.getPrimaryAccountByID(21), branch);
            case 23:
                // Μικτά Ημερήσια Κέρδη	=	Ημερήσιες Πωλήσεις	-	Ημερήσιο Κόστος Πωληθέντων	(Αφαίρεση)
                return getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(19), branch, date) -
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(21), branch, date);
            case 24:
                // Μικτά Κέρδη	=	Κύκλος εργασιών (Πωλήσεις)	-	Κόστος πωληθέντων
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(20)), branch, date) -
                        calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(22)), branch, date);
            case 26:
                // Κέρδη προ τοκων, φόρων & αποσβέσεων (EBITDA) ΚΠΤΦΑ	=	Μικτά Κέρδη	-	Λειτουργικές Δαπάνες
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(24)), branch, date) -
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(25), branch, date);
            case 27:
                // Κέρδη προ τόκων & φόρων (EBΙT), ΚΠΤΦ	=	Κέρδη προ τοκων, φόρων & αποσβέσεων (EBITDA) ΚΠΤΦΑ	-	Φετινές Αποσβέσεις
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(26)), branch, date) -
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(3), branch, date);
            case 29:
                // Καθαρά κέρδη χρήσης προ φόρων	=	Κέρδη προ τόκων & φόρων (EBΙT), ΚΠΤΦ	-	Χρηματοοικονομικά έξοδα (Τόκοι)
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(27)), branch, date) -
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(28), branch, date);
            case 31:
                // Καθαρά κέρδη μετά από φόρους (ΚΜΦ)	=	Καθαρά κέρδη χρήσης προ φόρων	-	Φόροι
                return calcComplexPrimaryAccountValue(Objects.requireNonNull(PrimaryAccountsManager.getPrimaryAccountByID(29)), branch, date) -
                        getPrimaryAccountValueFromDB(PrimaryAccountsManager.getPrimaryAccountByID(30), branch, date);
        }
        return -1;
    }

    public static double calcPrimaryAccountSum(PrimaryAccount primaryAccount, Branch branch) {
        double sum = 0;
        Set<PrimaryAccountBranch> primaryAccountBranchHistory = branch.getPrimaryAccountBranchHistory();
        for (PrimaryAccountBranch p : primaryAccountBranchHistory)
            if (p.getPrimaryAccount().equals(primaryAccount))
                sum += p.getValue();
        return sum;
    }

}
