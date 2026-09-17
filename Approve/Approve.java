package Approve;

public class Approve {

    public static boolean approveForLoan(int yearWorking, boolean badLoan, boolean isStartup, 
                                         boolean startupFund, boolean bigFigure, 
                                         boolean vipCustomer, boolean isBlacklisted) {
        
        if (isBlacklisted) {
            return false;
        }

        return (yearWorking > 3 && !badLoan) 
               || (isStartup && startupFund) 
               || (vipCustomer || bigFigure);
    }
}