package Approve;

public class Main {
    public static void main(String[] args) {
        boolean isApproved = Approve.approveForLoan(4, false, true, true, true, true, false);

        if (isApproved) {
            System.out.println("Loan Approved");
        
            double interestRate = Ranking.getRate('B');
            System.out.println("Assigned Interest Rate: " + (interestRate * 100) + "%");
            
        } else {
            System.out.println("Loan Denied");
        }
    }
}