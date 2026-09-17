package SME;

public class EnhancedForDemo {

    public static void run() {
        System.out.println("4. ENHANCED FOR: TOTAL CASH FLOW");

        double[] revenues = {235.0, 270.5, 310.4, 170.9, 265.6, 270.5};
        double totalrevenue = 0;

        for (double revenue : revenues) {
            totalrevenue += revenue;
        }
        System.out.println(
                "Total accumulated revenue: " + totalrevenue + " million VND"
        );
    }
}
