package SME;

public class ForDemon {

    public static void run() {
        System.out.println("3. FOR: MONTHLY PROFIT ANALYSIS");

        double[] revenues = {235.0, 270.5, 310.4, 170.9, 265.6, 270.5};
        double[] costs = {120.0, 150.3, 210.4, 100.0, 153.4, 117.2};

        for (int i = 0; i < revenues.length; i++) {
            double profit = revenues[i] - costs[i];
            System.out.printf(
                    "Month %d | Revenue: %.1f | Cost: %.1f | Profit: %.1f%n",
                    i + 1,
                    revenues[i],
                    costs[i],
                    profit
            );
        }
    }
}
