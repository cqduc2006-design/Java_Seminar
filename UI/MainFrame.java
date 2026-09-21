package UI;

import Approve.Approve;
import Approve.Ranking;
import SME.BreakContinueDemo;
import SME.DoWhileDemo;
import SME.EnhancedForDemo;
import SME.ForDemon;
import SME.LabeledLoopDemo;
import SME.OffByOneDemo;
import SME.WhileDemo;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.*;

public class MainFrame extends JFrame {

    private final JTextField balanceField;
    private final JLabel errorLabel;
    private final JTextArea logArea;
    private final JButton submitButton;

    public MainFrame() {
        super("SME Credit Scoring System - Nhap Du Lieu Dau Vao");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel formRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formRow.add(new JLabel("So du tai khoan hien tai (trieu VND):"));
        balanceField = new JTextField(15);
        formRow.add(balanceField);
        submitButton = new JButton("Submit");
        formRow.add(submitButton);
        topPanel.add(formRow);

        // Error
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 8, 0));
        topPanel.add(errorLabel);

        add(topPanel, BorderLayout.NORTH);

        // Logs
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        submitButton.addActionListener(e -> onSubmit());
        balanceField.addActionListener(e -> onSubmit());
    }

    private void onSubmit() {
        String rawInput = balanceField.getText();

        try {
            double balance = InputValidator.validateBalance(rawInput);

            // Valid input
            errorLabel.setText(" ");
            logArea.setText("");
            submitButton.setEnabled(false);
            balanceField.setEnabled(false);

            runCreditScoringPipeline(balance);

        } catch (InputValidator.InvalidBalanceException ex) {
            // Invalid input
            balanceField.setText("");
            errorLabel.setText("Lỗi: " + ex.getMessage());
        }
    }

    private void runCreditScoringPipeline(double balance) {
        PrintStream original = System.out;
        PrintStream captured = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                logArea.append(String.valueOf((char) b));
            }
        }, true);

        System.setOut(captured);
        try {
            System.out.println("Da xac thuc so du tai khoan: " + balance + " trieu VND");
            System.out.println("----------------------------------------");

            boolean bigFigure = balance >= 500;  
            boolean vipCustomer = balance >= 1000;

            boolean isApproved = Approve.approveForLoan(4, false, false, false, bigFigure, vipCustomer, false);

            if (isApproved) {
                System.out.println("Loan Approved");

                char rank = vipCustomer ? 'A' : (bigFigure ? 'B' : 'C');
                double rate = Ranking.getRate(rank);
                System.out.println("Assigned Interest Rate: " + (rate * 100) + "%");
            } else {
                System.out.println("Loan Denied");
            }

            System.out.println("----------------------------------------");
            System.out.println("Bat dau quet lich su giao dich (package SME):");
            System.out.println();

            WhileDemo.run();
            System.out.println();
            DoWhileDemo.run();
            System.out.println();
            ForDemon.run();
            System.out.println();
            EnhancedForDemo.run();
            System.out.println();
            BreakContinueDemo.run();
            System.out.println();
            LabeledLoopDemo.run();
            System.out.println();
            OffByOneDemo.run();

        } finally {
            System.setOut(original);
            submitButton.setEnabled(true);
            balanceField.setEnabled(true);
        }
    }
}
