package SME;

public class WhileDemo {

    public static void run() {
        System.out.println("1. WHILE: API RETRY");

        int retryCount = 0;
        boolean connected = false;

        while (!connected && retryCount < 3) {
            retryCount++;
            System.out.println("Connecting attempt " + retryCount);

            if (retryCount == 3) {
                connected = true;
            }
        }

        if (connected) {
            System.out.println("Connection successful");
        } else {
            System.out.println("Connection failed");
        }
    }

}
