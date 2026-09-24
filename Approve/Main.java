package Approve;

public class Main {

    public static boolean checkCreditHistory() {
    System.out.println("Checking in progress ...");
    return true;
    }
    
    public static void main(String[] args) {
        boolean isBlacklisted = true;
        
        if (!isBlacklisted && checkCreditHistory()) {
            System.out.println("Eligible");
        } else {
            System.out.println("Not Eligible, Short-circuit evaluation kick-in");
        }



        boolean isApproved = Approve.approveForLoan(4, false, true, true, true, true, false);

        if (isApproved) {
            System.out.println("Loan Approved");

            char rank = ChooseRank.chooseRank(0);
        
            double interestRate = Ranking.getRate(rank);
            System.out.println("Assigned Interest Rate: " + (interestRate * 100) + "%");
            
        } else {
            System.out.println("Loan Denied");
        }
    }
}