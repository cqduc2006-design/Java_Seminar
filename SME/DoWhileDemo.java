package SME;

public class DoWhileDemo {

    public static void run() {
        System.out.println("2. DO-WHILE: VALIDATE MONTH");

        int month = 15;

        do {
            System.out.println("Checking month " + month);
            if (month < 1 || month > 12) {
                System.out.println("invalid month!");
                month = 12;
            }
        } while (month < 1 || month > 12);
        System.out.println("valid month " + month);
    }
}
