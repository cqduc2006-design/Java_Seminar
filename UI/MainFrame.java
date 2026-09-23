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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int BASE_WIDTH = 700;
    private static final int BASE_HEIGHT = 520;
    private static final double MIN_SCALE = 0.75;
    private static final double MAX_SCALE = 1.8;

    private static final int LABEL_BASE_SIZE = 13;
    private static final int FIELD_BASE_SIZE = 13;
    private static final int BUTTON_BASE_SIZE = 13;
    private static final int ERROR_BASE_SIZE = 12;
    private static final int LOG_BASE_SIZE = 12;

    private final JLabel fieldLabel;
    private final JTextField balanceField;
    private final JLabel errorLabel;
    private final JTextArea logArea;
    private final JButton submitButton;
    private final JButton saveLogButton;

    public MainFrame() {
        super("SME Credit Scoring System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(520, 380));
        setSize(BASE_WIDTH, BASE_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel formRow = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        fieldLabel = new JLabel("So du tai khoan hien tai (trieu VND):");
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formRow.add(fieldLabel, gbc);

        balanceField = new JTextField(15);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formRow.add(balanceField, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formRow.add(submitButton, gbc);

        saveLogButton = new JButton("Luu Log");
        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formRow.add(saveLogButton, gbc);

        formRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        formRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, formRow.getPreferredSize().height));
        topPanel.add(formRow);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 8, 0));
        topPanel.add(errorLabel);

        add(topPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        submitButton.addActionListener(e -> onSubmit());
        balanceField.addActionListener(e -> onSubmit());
        saveLogButton.addActionListener(e -> onSaveLog());

        applyResponsiveScale();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                applyResponsiveScale();
            }
        });
    }

    private void applyResponsiveScale() {
        double scaleW = getWidth() / (double) BASE_WIDTH;
        double scaleH = getHeight() / (double) BASE_HEIGHT;
        double scale = Math.min(scaleW, scaleH);
        scale = Math.max(MIN_SCALE, Math.min(scale, MAX_SCALE));

        fieldLabel.setFont(new Font("SansSerif", Font.PLAIN, scaled(LABEL_BASE_SIZE, scale)));
        balanceField.setFont(new Font("SansSerif", Font.PLAIN, scaled(FIELD_BASE_SIZE, scale)));
        submitButton.setFont(new Font("SansSerif", Font.BOLD, scaled(BUTTON_BASE_SIZE, scale)));
        saveLogButton.setFont(new Font("SansSerif", Font.BOLD, scaled(BUTTON_BASE_SIZE, scale)));
        errorLabel.setFont(new Font("SansSerif", Font.BOLD, scaled(ERROR_BASE_SIZE, scale)));
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, scaled(LOG_BASE_SIZE, scale)));

        revalidate();
        repaint();
    }

    private int scaled(int baseSize, double scale) {
        return Math.max(9, (int) Math.round(baseSize * scale));
    }

    private void onSaveLog() {
        if (logArea.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chua co log de luu.", "Thong bao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("credit_scoring_log.txt"));

        int result = fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(logArea.getText());
            JOptionPane.showMessageDialog(this, "Da luu log vao:\n" + file.getAbsolutePath(), "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Loi khi luu file: " + ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onSubmit() {
        String rawInput = balanceField.getText();

        try {
            double balance = InputValidator.validateBalance(rawInput);

            errorLabel.setText(" ");
            logArea.setText("");
            submitButton.setEnabled(false);
            balanceField.setEnabled(false);

            runCreditScoringPipeline(balance);

        } catch (InputValidator.InvalidBalanceException ex) {
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
            System.out.println("Bat dau quet lich su giao dich:");
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
