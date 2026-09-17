package SME;

public class OffByOneDemo {

    public static void run() {
        System.out.println("7. OFF-BY-ONE ERROR");

        double[] revenues = {120.0, 150.0, 180.0};

        System.out.println(
                "Array length: " + revenues.length
        );

        for (int i = 0; i < revenues.length; i++) {
            System.out.println(
                    "revenues[" + i + "] = "
                    + revenues[i]
            );
        }
    }
}
