package Approve;

public class ChooseRank {
    public static char chooseRank(int balance) {
        if (balance <= 500) {
            return 'E';
        } else if (balance <= 1000) {
            return 'D';
        } else if (balance <= 1500) {
            return 'C';
        } else if (balance <= 2000) {
            return 'B';
        } else {
            return 'A';
        }
    }
}