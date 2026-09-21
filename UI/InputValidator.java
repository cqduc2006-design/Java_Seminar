package UI;

public class InputValidator {
    public static class InvalidBalanceException extends Exception {
        public InvalidBalanceException(String message) {
            super(message);
        }
    }

    public static double validateBalance(String rawInput) throws InvalidBalanceException {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            throw new InvalidBalanceException("Số dư tài khoản phải là số dương hợp lệ!");
        }

        String cleaned = rawInput.trim();

        int dotCount = 0;
        for (int i = 0; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);
            boolean isDigit = Character.isDigit(c);
            boolean isDot = (c == '.');

            if (isDot) {
                dotCount++;
            }

            if (!isDigit && !isDot) {
                throw new InvalidBalanceException("Số dư tài khoản phải là số dương hợp lệ!");
            }
        }

        if (dotCount > 1) {
            throw new InvalidBalanceException("Số dư tài khoản phải là số dương hợp lệ!");
        }

        double balance;
        try {
            balance = Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            throw new InvalidBalanceException("Số dư tài khoản phải là số dương hợp lệ!");
        }

        if (balance < 0) {
            throw new InvalidBalanceException("Số dư tài khoản phải là số dương hợp lệ!");
        }

        return balance;
    }
}
