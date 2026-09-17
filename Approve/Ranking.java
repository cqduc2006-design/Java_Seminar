package Approve;

public class Ranking {
    public static double getRate(char rank) {
        return switch(rank) {
            case 'A' -> {
                System.out.println("Very Low Risk, Very Low Interest Rate");
                yield 0.01;
            }
            case 'B' -> {
                System.out.println("Low Risk, Low Interest Rate");
                yield 0.02;
            }
            case 'C' -> {
                System.out.println("Medium Risk, Medium Interest Rate");
                yield 0.03;
            }
            case 'D' -> {
                System.out.println("High Risk, High Interest Rate");
                yield 0.04;
            }
            case 'E' -> {
                System.out.println("Very High Risk, Very High Interest Rate");
                yield 0.05;
            }
            default -> {
                System.out.println("Not ranked yet");
                yield 0.0;
            }
        };
    }
}
