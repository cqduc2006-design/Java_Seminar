package SME;

public class BreakContinueDemo {

    public static void run() {
        System.out.println("5. BREAK & CONTINUE: TRANSACTION SCANNING");

        double[] transactions = {50.0, -1.0, 80.0, 120.0, 500.0, 70.0};

        for (double transaction : transactions) {
            if (transaction < 0) {
                System.out.println("Invalid transaction -> CONTINUE");
                continue;
            }
            if (transaction >= 500) {
                System.out.println("High-risk transaction: " + transaction + " -> BREAK");
                break;
            }

            System.out.println("Processed: " + transaction + " million VND");
        }
    }
}
