package SME;

public class LabeledLoopDemo {

    public static void run() {
        System.out.println("6. LABELED LOOP: BRANCH SCANNING");

        double[][] branches = {
            {50.0, 80.0, 100.0},
            {70.0, 500.0, 90.0},
            {60.0, 110.0, 120.0}
        };

        outer:
        for (int branch = 0; branch < branches.length; branch++) {
            System.out.println(
                    "Scanning Branch " + (branch + 1)
            );

            for (double transaction : branches[branch]) {
                System.out.println(
                        "  Transaction: " + transaction
                );

                if (transaction >= 500) {
                    System.out.println("  High-risk transaction detected");

                    break outer;
                }
            }
        }
        System.out.println("Scanning stopped");
    }
}
