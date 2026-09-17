package Approve;

public class Approve {

    public static boolean approveForLoan(int yearWorking, boolean badLoan, boolean isStartup, boolean startupFund, 
                                         boolean bigFigure, boolean vipCustomer, boolean isBlacklisted) {
        
        if (!isBlacklisted) {
            if (yearWorking > 3 && !badLoan) {
                return true;
            } else if (isStartup && startupFund) {
                return true;
            } else if (vipCustomer || bigFigure) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
