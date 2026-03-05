import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class BankingSystem extends JFrame {

    private JTextField nameField, balanceField, amountField;
    private JLabel balanceLabel;
    private DefaultTableModel tableModel;

    private double balance = 0;

    public BankingSystem() {

        setTitle("Professional Banking System");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(15, 45, 75));

        // ---------------- BANK LOGO ----------------
        ImageIcon logoIcon = new ImageIcon("logo.png");
        Image img = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setBounds(30, 20, 80, 80);
        add(logoLabel);

        JLabel title = new JLabel("My Bank - Banking Dashboard");
        title.setBounds(150, 40, 400, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        add(title);

        // ---------------- ACCOUNT SECTION ----------------
        JLabel nameLbl = new JLabel("Account Name:");
        nameLbl.setBounds(50, 120, 150, 25);
        nameLbl.setForeground(Color.WHITE);
        add(nameLbl);

        nameField = new JTextField();
        nameField.setBounds(200, 120, 180, 30);
        add(nameField);

        JLabel balLbl = new JLabel("Initial Balance:");
        balLbl.setBounds(50, 160, 150, 25);
        balLbl.setForeground(Color.WHITE);
        add(balLbl);

        balanceField = new JTextField();
        balanceField.setBounds(200, 160, 180, 30);
        add(balanceField);

        JButton createBtn = createButton("Create Account");
        createBtn.setBounds(420, 140, 150, 35);
        add(createBtn);

        // ---------------- BALANCE DISPLAY ----------------
        balanceLabel = new JLabel("Current Balance: ₹0.00");
        balanceLabel.setBounds(50, 210, 300, 25);
        balanceLabel.setForeground(Color.YELLOW);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(balanceLabel);

        // ---------------- TRANSACTION SECTION ----------------
        JLabel amtLbl = new JLabel("Amount:");
        amtLbl.setBounds(50, 250, 100, 25);
        amtLbl.setForeground(Color.WHITE);
        add(amtLbl);

        amountField = new JTextField();
        amountField.setBounds(200, 250, 180, 30);
        add(amountField);

        JButton creditBtn = createButton("Credit");
        creditBtn.setBounds(50, 300, 120, 35);
        add(creditBtn);

        JButton withdrawBtn = createButton("Withdraw");
        withdrawBtn.setBounds(200, 300, 120, 35);
        add(withdrawBtn);

        // ---------------- EXIT BUTTON ----------------
        JButton exitBtn = createButton("Exit");
        exitBtn.setBounds(350, 300, 120, 35);
        exitBtn.setBackground(new Color(200, 50, 50)); // Red color
        add(exitBtn);

        // ---------------- TRANSACTION HISTORY TABLE ----------------
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Amount (₹)");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 360, 630, 120);
        add(scrollPane);

        // ---------------- BUTTON ACTIONS ----------------
        createBtn.addActionListener(e -> {
            try {
                balance = Double.parseDouble(balanceField.getText());
                balanceLabel.setText("Current Balance: ₹" + String.format("%.2f", balance));
                tableModel.addRow(new Object[]{"Account Created", balance});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid balance!");
            }
        });

        creditBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                balance += amt;
                balanceLabel.setText("Current Balance: ₹" + String.format("%.2f", balance));
                tableModel.addRow(new Object[]{"Credit", amt});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                } else {
                    balance -= amt;
                    balanceLabel.setText("Current Balance: ₹" + String.format("%.2f", balance));
                    tableModel.addRow(new Object[]{"Withdraw", amt});
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        });

        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    public static void main(String[] args) {
        new BankingSystem();
    }
}