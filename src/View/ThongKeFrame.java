package View;

import DAO.ThongKeDAO;
import Model.NhanVienModel;
import Model.ThongKeModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThongKeFrame extends JFrame {

    private static NhanVienModel currentUser;
    private final DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));

    private JComboBox<Integer> thangComboBox;
    private JComboBox<Integer> namComboBox;
    private JLabel tongDoanhThuLabel;
    private JTable thongKeTable;

    public ThongKeFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        setLocationRelativeTo(null);
        loadNam();
        loadThang();
        taiDuLieu();
    }

    private void initComponents() {
        setTitle("Thống kê doanh thu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);

        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("THỐNG KÊ DOANH THU");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(24, 24, 68));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        filterPanel.setBackground(Color.WHITE);
        JLabel thangLabel = new JLabel("Tháng:");
        thangLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        thangComboBox = new JComboBox<>();
        JLabel namLabel = new JLabel("Năm:");
        namLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        namComboBox = new JComboBox<>();
        JButton taiDuLieuButton = new JButton("Tải dữ liệu");
        taiDuLieuButton.setBackground(new Color(24, 24, 68));
        taiDuLieuButton.setForeground(Color.WHITE);
        taiDuLieuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taiDuLieuButton.addActionListener(evt -> taiDuLieu());

        namComboBox.addActionListener(evt -> loadThang());

        filterPanel.add(thangLabel);
        filterPanel.add(thangComboBox);
        filterPanel.add(namLabel);
        filterPanel.add(namComboBox);
        filterPanel.add(taiDuLieuButton);

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        JLabel tongLabel = new JLabel("Tổng doanh thu:");
        tongLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tongDoanhThuLabel = new JLabel("0");
        tongDoanhThuLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tongDoanhThuLabel.setForeground(new Color(0, 102, 204));
        summaryPanel.add(tongLabel);
        summaryPanel.add(tongDoanhThuLabel);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(filterPanel);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(summaryPanel);
        headerPanel.add(Box.createVerticalStrut(8));

        tableModel.addColumn("Mã loại");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Doanh thu");
        thongKeTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(thongKeTable);

        root.add(headerPanel, BorderLayout.NORTH);
        root.add(tableScroll, BorderLayout.CENTER);
        setContentPane(root);
    }

    private void loadNam() {
        namComboBox.removeAllItems();
        ArrayList<Integer> dsNam = ThongKeDAO.getNamThongKe();
        if (dsNam.isEmpty()) {
            namComboBox.addItem(LocalDate.now().getYear());
            return;
        }
        for (Integer nam : dsNam) {
            namComboBox.addItem(nam);
        }
        namComboBox.setSelectedItem(LocalDate.now().getYear());
    }

    private void loadThang() {
        thangComboBox.removeAllItems();
        Integer nam = (Integer) namComboBox.getSelectedItem();
        ArrayList<Integer> dsThang = new ArrayList<>();
        if (nam != null) {
            dsThang = ThongKeDAO.getThangThongKe(nam);
        }
        if (dsThang.isEmpty()) {
            for (int i = 1; i <= 12; i++) {
                thangComboBox.addItem(i);
            }
            thangComboBox.setSelectedItem(LocalDate.now().getMonthValue());
            return;
        }
        for (Integer thang : dsThang) {
            thangComboBox.addItem(thang);
        }
        thangComboBox.setSelectedItem(LocalDate.now().getMonthValue());
    }

    private void taiDuLieu() {
        Integer thang = (Integer) thangComboBox.getSelectedItem();
        Integer nam = (Integer) namComboBox.getSelectedItem();
        if (thang == null || nam == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tháng và năm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ArrayList<ThongKeModel> ds = ThongKeDAO.getThongKeByThangNam(thang, nam);
        tableModel.setRowCount(0);
        for (ThongKeModel tk : ds) {
            tableModel.addRow(new Object[]{
                tk.getMaLoai(),
                tk.getTenLoai(),
                currencyFormat.format(tk.getDoanhThu())
            });
        }
        double tongDoanhThu = ThongKeDAO.getTongDoanhThu(thang, nam);
        tongDoanhThuLabel.setText(currencyFormat.format(tongDoanhThu));
        if (ds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu thống kê cho tháng/năm đã chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(NhanVienModel args) {
        currentUser = args;
        currentUser.setMaNV(args.getMaNV());
        java.awt.EventQueue.invokeLater(() -> {
            new ThongKeFrame().setVisible(true);
        });
    }
}
