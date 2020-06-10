package indicators.objects;

import branches.objects.Branch;
import primary_accounts.objects.PrimaryAccountsManager;
import stores.objects.Store;

import java.util.ArrayList;
import java.util.Objects;

import static primary_accounts.objects.PrimaryAccountsManager.calcPrimaryAccount;
import static primary_accounts.objects.PrimaryAccountsManager.calcPrimaryAccountSum;


public class IndicatorManager {

    public static ArrayList<Indicator> getIndicatorList() {
        ArrayList<Indicator> indicatorList = new ArrayList<>();
        indicatorList.add(new Indicator(1, "Γενικής ρευστότητας"));
        indicatorList.add(new Indicator(2, "Άμεσης ρευστότητας"));
        indicatorList.add(new Indicator(3, "Κυκλοφοριακή Ταχύτητα Αποθεμάτων (ΚΤΑ)"));
        indicatorList.add(new Indicator(4, "Μέση περίοδος αποθεματοποίησης (ΜΠΑ)"));
        indicatorList.add(new Indicator(5, "Μέση περίοδος είσπραξης απαιτήσεων (ΜΠΕΑ)"));
        indicatorList.add(new Indicator(6, "Μέση περίοδος εξόφλησης  πληρ. Λογαρισμών"));
        indicatorList.add(new Indicator(7, "Κυκλοφοριακή ταχύτητα ΠΠΣ"));
        indicatorList.add(new Indicator(8, "Κυκλοφοριακή ταχύτητα ενεργητικού"));
        indicatorList.add(new Indicator(9, "Δείκτης χρέους (Debt ratio)"));
        indicatorList.add(new Indicator(10, "Δείκτης χρέος προς ίδια κεφάλαια"));
        indicatorList.add(new Indicator(11, "Δείκτης κεφαλαιακής μόχλευσης"));
        indicatorList.add(new Indicator(12, "Χρηματοικονομική μόχλευση (πολλαπλασιαστής)"));
        indicatorList.add(new Indicator(13, "Δείκτης κάλυψης τόκων"));
        indicatorList.add(new Indicator(14, "Μικτό περθώριο κέρδους"));
        indicatorList.add(new Indicator(15, "Λειτουργικό περιθώριο κέρδους"));
        indicatorList.add(new Indicator(16, "Καθαρό περιθώριο κέρδους"));
        indicatorList.add(new Indicator(17, "Απόδοση ιδίων κεφαλαίων (ROE)"));
        indicatorList.add(new Indicator(18, "Απόδοση ενεργητικού (ROA)"));
        indicatorList.add(new Indicator(19, "Τρέχουσες (Ημερήσιες) Εργατοώρες"));
        indicatorList.add(new Indicator(20, "Ημερήσια Παραγωγικότητα"));
        indicatorList.add(new Indicator(21, "Συνολικές Εργατοώρες"));
        indicatorList.add(new Indicator(22, "Παραγωγικότητα"));
        return indicatorList;
    }

    public static String[] getIndicatorTitles() {
        ArrayList<Indicator> indicatorList = getIndicatorList();
        String[] indicatorTitles = new String[indicatorList.size()];
        for (int i = 0; i < indicatorList.size(); i++) {
            indicatorTitles[i] = indicatorList.get(i).getTitle();
        }
        return indicatorTitles;
    }

    public static Indicator getIndicatorByID(long id) {
        ArrayList<Indicator> indicatorList = getIndicatorList();
        for (Indicator indicator : indicatorList)
            if (indicator.getId() == id)
                return indicator;
        return null;
    }

    public static double calcIndicator(boolean storeOrBranch, Indicator indicator, Store store, Branch branch, String date) {
        switch ((int) indicator.getId()) {
            case 1:
                // Γενικής ρευστότητας	=	Σύνολο κυκλοφορούντος ενεργητικού	/	Σύνολο βραχυπρόθεσμων υποχρεώσεων
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(9), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(15), store, branch, date);
            case 2:
                //Άμεσης ρευστότητας	=	(Σύνολο κυκλοφορούντος ενεργητικού	-	Αποθέματα) / Σύνολο βραχυπρόθεσμων υποχρεώσεων
                return (
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(9), store, branch, date) -
                                calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(6), store, branch, date)
                ) / calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(15), store, branch, date);
            case 3:
                // Κυκλοφοριακή Ταχύτητα Αποθεμάτων (ΚΤΑ)	=	Κύκλος εργασιών (Πωλήσεις)	/	Αποθέματα
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(6), store, branch, date);
            case 4:
                // Μέση περίοδος αποθεματοποίησης (ΜΠΑ)	=	360	/	Κυκλοφοριακή Ταχύτητα Αποθεμάτων (ΚΤΑ)
                return 360.0 / calcIndicator(storeOrBranch, Objects.requireNonNull(getIndicatorByID(3)), store, branch, date);
            case 5:
                // Μέση περίοδος είσπραξης απαιτήσεων (ΜΠΕΑ)	=	[ Πελάτες και λοιπές βραχυπρόθεσμες απαιτήσεις / Κύκλος εργασιών (Πωλήσεις) ]	*	360
                return (
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(7), store, branch, date) /
                                calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date)
                ) * 360.0;
            case 6:
                // Μέση περίοδος είσπραξης απαιτήσεων (ΜΠΕΑ)	=	[ Προμηθευτές και λοιποί πιστωτές (Πληρ. Λογαρ.) / Κύκλος εργασιών (Πωλήσεις) ]	*	360
                return (
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(13), store, branch, date) /
                                calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date)
                ) * 360.0;
            case 7:
                // Κυκλοφοριακή ταχύτητα ΠΠΣ	=	Κύκλος εργασιών (Πωλήσεις)	/	Σύνολο παγίου ενεργητικού
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(5), store, branch, date);
            case 8:
                // Κυκλοφοριακή ταχύτητα ενεργητικού	=	Κύκλος εργασιών (Πωλήσεις)	/	Σύνολο Ενεργητικού
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(10), store, branch, date);
            case 9:
                // Δείκτης χρέους (Debt ratio)	=	Σύνολο υποχρεώσεων	/	Σύνολο Ενεργητικού
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(16), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(10), store, branch, date);
            case 10:
                // Δείκτης χρέος προς ίδια κεφάλαια	=	Σύνολο υποχρεώσεων	/	Σύνολο Ιδίων Κεφαλαίων
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(16), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(17), store, branch, date);
            case 11:
                // Δείκτης κεφαλαιακής μόχλευσης	=	Σύνολο υποχρεώσεων / (Σύνολο υποχρεώσεων	+	Σύνολο Ιδίων Κεφαλαίων)
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(16), store, branch, date) /
                        (
                                calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(16), store, branch, date) +
                                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(17), store, branch, date)
                        );
            case 12:
                // Χρηματοικονομική μόχλευση (πολλαπλασιαστής)	=	Σύνολο Ενεργητικού	/	Σύνολο Ιδίων Κεφαλαίων
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(10), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(17), store, branch, date);
            case 13:
                // Δείκτης κάλυψης τόκων	=	Κέρδη προ τοκων, φόρων & αποσβέσεων (EBITDA) ΚΠΤΦΑ	/	Χρηματοοικονομικά έξοδα (Τόκοι)
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(26), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(28), store, branch, date);
            case 14:
                // Μικτό περθώριο κέρδους	=	(Κύκλος εργασιών (Πωλήσεις)	-	Κόστος πωληθέντων) / Κύκλος εργασιών (Πωλήσεις)
                return (
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date) -
                                calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(22), store, branch, date)
                ) / calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date);
            case 15:
                // Λειτουργικό περιθώριο κέρδους	=	Κέρδη προ τόκων & φόρων (EBΙT), ΚΠΤΦ	/	Κύκλος εργασιών (Πωλήσεις)
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(27), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date);
            case 16:
                // Καθαρό περιθώριο κέρδους	=	Καθαρά κέρδη μετά από φόρους (ΚΜΦ)	/	Κύκλος εργασιών (Πωλήσεις)
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(31), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date);
            case 17:
                // Απόδοση ιδίων κεφαλαίων (ROE)	=	Καθαρά κέρδη μετά από φόρους (ΚΜΦ)	/	Σύνολο Ιδίων Κεφαλαίων
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(31), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(17), store, branch, date);
            case 18:
                // Απόδοση ενεργητικού (ROA)	=	Καθαρά κέρδη μετά από φόρους (ΚΜΦ)	/	Σύνολο Ενεργητικού
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(31), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(10), store, branch, date);
            case 19:
                // Τρέχουσες (Ημερήσιες) Εργατοώρες	=	Αριθμός
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(32), store, branch, date);
            case 20:
                // Ημερήσια Παραγωγικότητα	=	Ημερήσιες Πωλήσεις	/	Τρέχουσες (Ημερήσιες) Εργατοώρες
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(19), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(32), store, branch, date);
            case 21:
                // Συνολικές Εργατοώρες	=	Τρέχουσες (Ημερήσιες) Εργατοώρες		Απο 1/1 - προηγούμενη ημέρα	(Αθροισμα)
                return calcPrimaryAccountSum(PrimaryAccountsManager.getPrimaryAccountByID(32), branch);
            case 22:
                // Παραγωγικότητα	=	Συνολικές Εργατοώρες	/	Κύκλος εργασιών (Πωλήσεις)
                return calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(21), store, branch, date) /
                        calcPrimaryAccount(storeOrBranch, PrimaryAccountsManager.getPrimaryAccountByID(20), store, branch, date);
        }
        return 0;
    }

}