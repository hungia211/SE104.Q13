package View;

import DAO.ChiTietDatPhongDAO;
import DAO.HoaDonDAO;
import DAO.HopDongDAO;
import DAO.KhachHangDAO;
import DAO.KhuyenMaiDAO;
import DAO.TrangBiDAO;
import DAO.TrangChuDAO;
import Model.HopDongModel;
import Model.KhachHangModel;
import Model.KhuyenMaiModel;
import Model.NhanVienModel;
import Model.TrangBiModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class HopDongFrame extends javax.swing.JFrame {

    ArrayList<Vector> tinhtoan = new ArrayList<>();
    Vector<Double> tien = new Vector<>();
    int tongsl = 0;
    double tong = 0.0;
    double thanhtienfinal;
    double tiengocfinal;
    double tongtienfinal;
    int maHopDongfinal;

    DecimalFormat df = new DecimalFormat("#");
    // Tạo NumberFormat với Local cho Việt Nam
    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
    int maHopDongTinh;
    String ngayden;
    String ngaydi;
    HopDongJDialog dialog = new HopDongJDialog();

    CardLayout cardlayout;

    private static NhanVienModel currentUser;

    private DefaultTableModel defaultTableModel = new DefaultTableModel() {
        public boolean isCellEdittable(int row, int column) {
            return false;
        }
    };

    int maHopDongSua;

    public HopDongFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        addSVG();
        khoiTaoBang(); // Gọi phương thức khởi tạo bảng
        inDanhSach(); // Gọi phương thức in danh sách ban đầu nếu cần thiết

        // Tạo các luồng sự kiện chuyển Panel
        cardlayout = (CardLayout) (mainCardLayoutHDong.getLayout());
        mainCardLayoutHDong.add(HopDongPanel, "hopDong");
        cardlayout.show(mainCardLayoutHDong, "hopDong");
        mainCardLayoutHDong.add(SuaHopDongPanel, "suaHopDong");

    }

    public void addSVG() {
        svgLogo.setSVGImage("Image/logo.svg", 110, 110);
        svgPhong.setSVGImage("Image/phong.svg", 30, 30);
        svgKhach.setSVGImage("Image/khachhang.svg", 30, 30);
        svgNV.setSVGImage("Image/nhanvien.svg", 30, 30);
        svgHopDong.setSVGImage("Image/hopdong.svg", 30, 30);
        svgKM.setSVGImage("Image/khuyenmai.svg", 30, 30);
        svgTB.setSVGImage("Image/trangbi.svg", 30, 30);
        svgHoaDon.setSVGImage("Image/hoadon.svg", 30, 30);
        svgCC.setSVGImage("Image/chamcong.svg", 30, 30);
        svgL.setSVGImage("Image/luong.svg", 30, 30);

        svgPhong.setColor();
        svgKhach.setColor();
        svgNV.setColor();
        svgHopDong.setYColor();
        svgKM.setColor();
        svgTB.setColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setColor();
    }

    public void khoiTaoBang() {
        defaultTableModel = new DefaultTableModel();

        defaultTableModel.addColumn("Mã Hợp Đồng");
        defaultTableModel.addColumn("Mã KH");
        defaultTableModel.addColumn("Ngày Lập Hợp Đồng");
        defaultTableModel.addColumn("Thời Gian Nhận Phòng");
        defaultTableModel.addColumn("Thời Gian Trả Phòng");
        defaultTableModel.addColumn("Tình Trạng HĐ");
        defaultTableModel.addColumn("SL Người Lớn");
        defaultTableModel.addColumn("SL Trẻ Em");
        defaultTableModel.addColumn("Trị Giá HĐ");
        defaultTableModel.addColumn("Hình thức thuê");

        tableHopDong.setModel(defaultTableModel);
    }

    public void inDanhSach() {
        // Tạo đối tượng danh sách hợp đồng
        ArrayList<HopDongModel> DS_HopDong = HopDongDAO.getDSHopDong();

        // Xóa tất cả các hàng hiện có trong bảng
        defaultTableModel.setRowCount(0);

        // Thêm dữ liệu vào bảng
        for (HopDongModel hopDong : DS_HopDong) {
            defaultTableModel.addRow(new Object[]{
                hopDong.getMaHopDong(), hopDong.getMaKH(), hopDong.getNgayLapHopDong(),
                hopDong.getTGNhanPhong(), hopDong.getTGTraPhong(), hopDong.getTinhTrangHD(),
                hopDong.getSoLuongNguoiLon(), hopDong.getSoLuongTreEm(), hopDong.getTriGiaHopDong(),
                hopDong.getHinhThucThue()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = tableHopDong.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < tableHopDong.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tableHopDong.getCellRenderer(row, i);
                Component c = tableHopDong.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }
    }

    public void TraCuuHopDong() {
        // Lấy tùy chọn tìm kiếm và đầu vào từ người dùng
        String option = (String) this.timTheoComboBox.getSelectedItem();
        String input = this.nhapjTextField.getText().toLowerCase();

        // Xóa tất cả các hàng trong bảng
        defaultTableModel.setRowCount(0);

        // Tạo danh sách Hợp Đồng để chứa kết quả tìm kiếm
        ArrayList<HopDongModel> DS_HopDong = HopDongDAO.TimHopDong(option, input);

        // Thêm dữ liệu vào bảng
        for (HopDongModel hopDong : DS_HopDong) {
            defaultTableModel.addRow(new Object[]{
                hopDong.getMaHopDong(), hopDong.getMaKH(), hopDong.getNgayLapHopDong(),
                hopDong.getTGNhanPhong(), hopDong.getTGTraPhong(), hopDong.getTinhTrangHD(),
                hopDong.getSoLuongNguoiLon(), hopDong.getSoLuongTreEm(), numberFormat.format((int) hopDong.getTriGiaHopDong()), hopDong.getHinhThucThue()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = tableHopDong.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < tableHopDong.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tableHopDong.getCellRenderer(row, i);
                Component c = tableHopDong.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }

        // Hiển thị thông báo nếu không tìm thấy kết quả
        if (DS_HopDong.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TraCuuHopDongTheoSoPhong() {
        // Lấy tùy chọn tìm kiếm và đầu vào từ người dùng
        String input = this.nhapjTextField.getText();

        int sophong = Integer.parseInt(input); // Chuyển đổi từ String sang int
        System.out.println(sophong);

        // Xóa tất cả các hàng trong bảng
        defaultTableModel.setRowCount(0);

        // Tạo danh sách Hợp Đồng để chứa kết quả tìm kiếm
        ArrayList<HopDongModel> DS_HopDong = HopDongDAO.TimHopDongTheoSoPhong(sophong);

        // Thêm dữ liệu vào bảng
        for (HopDongModel hopDong : DS_HopDong) {
            defaultTableModel.addRow(new Object[]{
                hopDong.getMaHopDong(), hopDong.getMaKH(), hopDong.getNgayLapHopDong(),
                hopDong.getTGNhanPhong(), hopDong.getTGTraPhong(), hopDong.getTinhTrangHD(),
                hopDong.getSoLuongNguoiLon(), hopDong.getSoLuongTreEm(), numberFormat.format((int) hopDong.getTriGiaHopDong()), hopDong.getHinhThucThue()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = tableHopDong.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < tableHopDong.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tableHopDong.getCellRenderer(row, i);
                Component c = tableHopDong.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }

        // Hiển thị thông báo nếu không tìm thấy kết quả
        if (DS_HopDong.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TraCuuHopDongTheoCCCD() {
        // Lấy tùy chọn tìm kiếm và đầu vào từ người dùng
        String input = this.nhapjTextField.getText().toLowerCase();

        // Xóa tất cả các hàng trong bảng
        defaultTableModel.setRowCount(0);

        // Tạo danh sách Hợp Đồng để chứa kết quả tìm kiếm
        ArrayList<HopDongModel> DS_HopDong = HopDongDAO.TimHopDongTheoCCCD(input);

        // Thêm dữ liệu vào bảng
        for (HopDongModel hopDong : DS_HopDong) {
            defaultTableModel.addRow(new Object[]{
                hopDong.getMaHopDong(), hopDong.getMaKH(), hopDong.getNgayLapHopDong(),
                hopDong.getTGNhanPhong(), hopDong.getTGTraPhong(), hopDong.getTinhTrangHD(),
                hopDong.getSoLuongNguoiLon(), hopDong.getSoLuongTreEm(), numberFormat.format((int) hopDong.getTriGiaHopDong()), hopDong.getHinhThucThue()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = tableHopDong.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < tableHopDong.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tableHopDong.getCellRenderer(row, i);
                Component c = tableHopDong.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }

        // Hiển thị thông báo nếu không tìm thấy kết quả
        if (DS_HopDong.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienDuLieuSua() {
        // Lấy thông tin hợp đồng từ cơ sở dữ liệu
        try {
            HopDongModel hopDong = HopDongDAO.getHDtheoMaHopDong(maHopDongSua);
            // Sử dụng thông tin hợp đồng để cập nhật giao diện
            if (hopDong != null) {

                sua_tinhTrangHDjComboBox.setSelectedItem(hopDong.getTinhTrangHD());
                sua_slNguoiLonjTextField.setText(String.valueOf(hopDong.getSoLuongNguoiLon()));
                sua_slTreEmjTextField.setText(String.valueOf(hopDong.getSoLuongTreEm()));

                // chuyển đổi sang định dạng LocalDateTime
                Date ngayLHD = convertToDate(hopDong.getNgayLapHopDong());
                //ngayLapHopDongjComboBox.setDate(ngayLHD);

                //triGiaHDjTextField.setText(String.valueOf(hopDong.getTriGiaHopDong()));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hợp đồng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin hợp đồng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDichVu1() {
        DichVuHong.setVisible(false);
        for (ActionListener al : dichvuComboBox.getActionListeners()) {
            dichvuComboBox.removeActionListener(al);
        }

        dichvuComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isDichVu = (String) dichvuComboBox.getSelectedItem();
                if (isDichVu.equals("Không")) {
                    DichVuHong.setVisible(false);
                } else if (isDichVu.equals("Có")) {
                    setDichVu2();
                    DichVuHong.setVisible(true);
                }
            }
        });
    }

    public void setDichVu2() {
        ArrayList<TrangBiModel> listTrangBi = TrangBiDAO.getTrangBiData();

        tentbComboBox.removeAllItems();
        tentbComboBox.addItem("");
        for (TrangBiModel trangBi : listTrangBi) {
            tentbComboBox.addItem(trangBi.getTenTB());
        }

        for (ActionListener al : tentbComboBox.getActionListeners()) {
            tentbComboBox.removeActionListener(al);
        }

        tentbComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) tentbComboBox.getSelectedItem();
                if (selectedItem != null) {
                    TrangBiModel tb = TrangBiDAO.getTrangBi_TenTB(selectedItem);
                    matbDV.setText(String.valueOf(tb.getMaTB()));
                    giatbDV.setText(String.valueOf(tb.getGiaTB()));
                    ((SpinnerNumberModel) slDV.getModel()).setMaximum(tb.getSoLuong());
                }
            }
        });
    }

    public void themTrangBiHong() {
        Vector v = new Vector();
        v.add(Integer.parseInt(matbDV.getText()));
        v.add(Integer.parseInt(slDV.getValue().toString()));
        tinhtoan.add(v);

        this.tongsl += (Integer) slDV.getValue();
        // Giả sử giatbDV là một JTextField và slDV là một JSpinner
        this.tong += Double.parseDouble(giatbDV.getText()) * ((Integer) slDV.getValue()).doubleValue();

        System.out.println(tongsl);
        System.out.println(numberFormat.format(tong));

        tongslDV.setText(String.valueOf(tongsl));
        tongDV.setText(String.valueOf(tong));

        reset();
    }

    public void reset() {
        matbDV.setText("");
        giatbDV.setText("");
        tentbComboBox.setSelectedItem("");

        slDV.setValue(0);
    }

    public void resetVector() {
        tien.clear();

        for (Vector<Double> vector : tinhtoan) {
            vector.clear();
        }
    }

    public static long tinhThoiGian(String checkInDateTime, String checkOutDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
        LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);

        long khoangThoiGianGiuaHaiThoiDiem = ChronoUnit.MINUTES.between(checkIn, checkOut);

        return khoangThoiGianGiuaHaiThoiDiem;
    }

    public static long tinhKhoangCach2Ngay(String startDateStr, String endDateStr) {
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển chuỗi ngày sang LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Tính khoảng cách giữa hai ngày
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public void xacnhan() {
        int row = tableHopDong.getSelectedRow();

        if (row != -1) {
            int maKH = Integer.parseInt(tableHopDong.getValueAt(row, 1).toString());
            try {
                // Tên KH
                KhachHangModel khach = KhachHangDAO.getKHtheoMaKHK(maKH);
                tenkh.setText("Tên khách hàng: " + khach.getTenKH());
                // Hạng KH
                String hang = "Thường";
                if (khach.getSoHopDong() >= 10) {
                    hang = "VIP";
                }
                hangkh.setText("Hạng khách hàng: " + hang);
                // Mã HĐ
                mahd.setText("Mã hợp đồng: " + tableHopDong.getValueAt(row, 0).toString());

                // Tính tiền
                HopDongModel hopdong = HopDongDAO.getHDtheoMaHopDong(maHopDongTinh);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                this.ngayden = hopdong.getTGNhanPhong().format(formatter);
                this.ngaydi = hopdong.getTGTraPhong().format(formatter);

                System.out.println(ngayden);
                System.out.println(ngaydi);

                String hinhthuc = hopdong.getHinhThucThue();

                double tiengoc = 0;
                long soluong = 0;
                if (hinhthuc.equals("Giờ")) {
                    soluong = tinhThoiGian(ngayden, ngaydi) / 60;
                    tiengoc = HopDongDAO.tongGiaPhongGio(maHopDongTinh, soluong);
                } else if (hinhthuc.equals("Ngày")) {
                    soluong = tinhKhoangCach2Ngay(ngayden, ngaydi);
                    tiengoc = HopDongDAO.tongGiaPhongNgay(maHopDongTinh, soluong);
                }
                
                

                System.out.println(tiengoc);
                this.tiengocfinal = tiengoc;
                System.out.println(soluong);

                this.maHopDongfinal = hopdong.getMaHopDong();
                System.out.println(maHopDongTinh);

                tien.add(tiengoc);
                double tongtien = tiengoc - hopdong.getTriGiaHopDong();
                this.tongtienfinal = tongtien;
                System.out.println("tong tien:" + tongtien);
                
                tien.add(tongtien);
                tt.setText("Tổng tiền phải thanh toán: " + numberFormat.format(tongtien));
                tiendv.setText("Tiền dịch vụ: " + numberFormat.format(tong));
                setMaKM(hang);
                chonMaKM(tiengoc, tongtien);
                

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setMaKM(String hang) {
        ArrayList<KhuyenMaiModel> kml = KhuyenMaiDAO.getDSKhuyenMaitheoNgay(ngayden, ngaydi);

        LocalDate currentDate = LocalDate.now();

        kmComboBox.addItem("");

        for (KhuyenMaiModel km : kml) {
            LocalDate ngayBatDau = km.getNgayBatDau();
            LocalDate ngayKetThuc = km.getNgayKetThuc();
            if (hang.equals("Thường")) {
                if (ngayBatDau != null && ngayKetThuc != null) {
                    kmComboBox.addItem(String.valueOf(km.getMaKM()));
                }
            } else if (hang.equals("VIP")) {
                if (ngayBatDau != null && ngayKetThuc != null && km.getPhanTramKM() >= 0.1) {
                    kmComboBox.addItem(String.valueOf(km.getMaKM()));
                }
            }
        }
    }

    public void chonMaKM(double tiengoc, double tongtien) {
        int row = tableHopDong.getSelectedRow();

        for (ActionListener al : kmComboBox.getActionListeners()) {
            kmComboBox.removeActionListener(al);
        }

        kmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!("".equals(kmComboBox.getSelectedItem()))) {
                    double giam = KhuyenMaiDAO.getGiamGia_MaKM(Integer.parseInt((String) kmComboBox.getSelectedItem()));
                    double tg = tiengoc * giam;
                    double tt = tongtien - tg;
                    
                    
                    tiengiam.setText("Tiền giảm: -" + numberFormat.format(tg));
                    thanhtien.setText("Thành tiền: " + numberFormat.format(tt));
                }
            }
        });
        
    }

    public void tinhtoanTrangBiHong() {
        int row = tableHopDong.getSelectedRow();
        int maHD = Integer.parseInt(tableHopDong.getValueAt(row, 0).toString());
        // Thêm
        TrangBiDAO.themHONGTRANGBI(tinhtoan, maHD);
        // Cập nhật
        TrangBiDAO.suaTBHong(tinhtoan);
    }

    // In hoá đơn
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter);
    }

    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    // Định dạng ngày
    public static String formatDateTime(String isoDateTime) {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter prettyFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, isoFormatter);
        return dateTime.format(prettyFormatter);
    }

    public void bill() {
        BillTextPane.setContentType("text/html");
        BillTextPane.setEditable(false);

        int row = tableHopDong.getSelectedRow();
        int maKH = Integer.parseInt(tableHopDong.getValueAt(row, 1).toString());
        KhachHangModel k = new KhachHangModel();
        try {
            k = KhachHangDAO.getKHtheoMaKHK(maKH);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int maHD = HoaDonDAO.getMaxRow() + 1;

        double tiengoc = tien.get(0);
        double tg = Double.parseDouble(tiengiam.getText().substring(11));
        //double tongtien = tiengoc - Double.parseDouble(tableHopDong.getValueAt(row, 8).toString()) - tg;
        double tongtien = tien.get(1);
        String tongStr = tiendv.getText().substring(14).replace(".", "");
        double dichvu = Double.parseDouble(tongStr);

        double thanhtien = dichvu + tongtien - tg;

        System.out.println(tableHopDong.getValueAt(row, 3).toString());

        String head = "<html><head>"
                + "<style>"
                + ".title {font-family: Arial; color: #191970; text-align: center; font-size: 14px;font-weight: bold;}"
                + ".info {font-family: Arial; color: #000000; text-align: center; font-size: 8px;}"
                + ".name {font-family: Arial; color: #191970; text-align: center; font-size: 11px;font-weight: bold;}"
                + ".center {text-align: center;}"
                + ".right {text-align: right;}"
                + ".left {text-align: left;}"
                + ".bold {font-weight: bold;}"
                + ".footer {text-align: center; font-size: 8px; color: #000000;}"
                + "table {width: 120%;}"
                + "td {padding: 5px;}"
                + "td.right {text-align: right;}"
                + ".signature { text-indent: 50px; }"
                + ".nv { text-indent: 7px; }"
                + "</style>"
                + "</head><body>";

        String body = "<br>"
                + "<div class='title'>BRIGHT STAR HOTEL</div>"
                + "<br>"
                + "<div class='info'>Địa chỉ: Trường Đại học Công nghệ Thông tin - ĐHQG TP.Hồ Chí Minh,</div>"
                + "<div class='info'>Khu phố 6, P.Linh Trung, TP.Thủ Đức, TP.Hồ Chí Minh</div>"
                + "<div class='info'>Liên hệ: (+84)39 415 0742</div>"
                + "<br>"
                + "<br>"
                + "<div class='name'>HOÁ ĐƠN BRIGHT STAR HOTEL</div>"
                + "<br>"
                + "<br>"
                + "<div class='content'>"
                + "<span class='bold'>Mã hoá đơn: " + maHD + "</span></p>"
                + "<p><span class='bold'>Ngày hoá đơn: " + getCurrentDateTime() + "</span></p>"
                + "<hr>"
                + "<p>Tên khách hàng: " + k.getTenKH() + "</span></p>"
                + "<p>Số điện thoại: " + k.getSDT() + "</span></p>"
                + "<p>Phòng: 10</span></p>"
                + "<p>Ngày đặt phòng: " + formatDateTime(tableHopDong.getValueAt(row, 3).toString()) + "</span></p>"
                + "<p>Ngày trả phòng: " + formatDateTime(tableHopDong.getValueAt(row, 4).toString()) + "</span></p>"
                + "<br>"
                + "<hr>"
                + "<table>"
                + "<tr><td class='left'>Tổng tiền:</td><td class='right'>" + numberFormat.format(tongtien) + " VND" + "</td></tr>"
                + "<tr><td class='left'>Mã khuyến mãi: " + (String) kmComboBox.getSelectedItem() + "</td><td class='right'>" + tg + "</td></tr>"
                + "<tr><td class='left'>Tiền dịch vụ:</td><td class='right'>" + numberFormat.format(dichvu) + " VND" + "</td></tr>"
                + "<tr><td class='left bold'>Thành tiền:</td><td class='right'>" + numberFormat.format(thanhtien) + " VND" + "</td></tr>"
                + "</table>"
                + "</div>"
                + "<br>"
                + "<div class='nv'>Nhân viên: " + currentUser.getTenNV() + "</span></p>"
                + "<br>"
                + "<br>"
                + "<br>";

        // Tạo footer hóa đơn
        String end = "<div class='footer'>"
                + "<p>Bright Star cảm ơn bạn vì đã đến!</p>"
                + "<p>Hẹn gặp lại!</p>";

        BillTextPane.setText(head + body + end);
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    public static void saveToPDF(JTextPane textPane) {
        Document document = new Document();
        try {
            // Tạo JPanel và thêm JTextPane và JButton vào đó
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JScrollPane(textPane), BorderLayout.CENTER);
            JButton saveButton = new JButton("Tải xuống");
            panel.add(saveButton, BorderLayout.SOUTH);

            // Tạo JFrame và hiển thị JPanel trong đó
            JFrame frame = new JFrame("HOÁ ĐƠN");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                String text = textPane.getText();
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);

                document.add(new Paragraph(text, font));

                document.close();

                JOptionPane.showMessageDialog(null, "Lưu file PDF thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu file PDF: " + e.getMessage());
        }
    }

    public void setDialog() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem xemThongTinItem = new JMenuItem("Xem thông tin");
        popupMenu.add(xemThongTinItem);

        tableHopDong.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int selectedRow = tableHopDong.rowAtPoint(e.getPoint());
                    if (selectedRow != -1) {
                        tableHopDong.setRowSelectionInterval(selectedRow, selectedRow);
                        popupMenu.show(tableHopDong, e.getX(), e.getY());
                    }
                }
            }
        });

        xemThongTinItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    display();
                } catch (SQLException ex) {
                    Logger.getLogger(HopDongFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void display() throws SQLException {
        int selectedrow = tableHopDong.getSelectedRow();
        if (selectedrow != -1) {
            HopDongModel hd = new HopDongModel();
            hd = HopDongDAO.getHopDongTheoMaHP((int) tableHopDong.getValueAt(selectedrow, 0));
            setCTDP(hd);
            dialog.setVisible(true);

        }

    }

    public void setCTDP(HopDongModel hd) throws SQLException {
        KhachHangModel kh = HopDongDAO.getKHtheoMaHopDong(hd.getMaHopDong());
        dialog.MaKhachHangjLabel.setText("Mã khách hàng : " + Integer.toString(kh.getMaKH()));
        dialog.TenKhachHangJLabel.setText("Tên khách hàng : " + kh.getTenKH());

        // Hiển thị tổng tiền, định dạng số để dễ đọc
        dialog.TienTongjLabel.setText("Tổng tiền: " + String.format("%,.0f", hd.getTriGiaHopDong()) + " VND");

        // Hiển thị mã hợp đồng, chuyển đổi giá trị int sang String để hiển thị
        dialog.MaHopDongjLabel.setText("Mã hợp đồng: " + Integer.toString(hd.getMaHopDong()));
        ArrayList<Vector> ctdp = ChiTietDatPhongDAO.getCTDP(hd.getMaHopDong());
        System.out.println("Dữ liệu CTHD: " + ctdp);
        DefaultTableModel tableModel = (DefaultTableModel) dialog.CTHDjTabel.getModel();
        tableModel.setRowCount(0);
        for (Vector row : ctdp) {
            tableModel.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        MenuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        KhachHangLb = new javax.swing.JLabel();
        NhanVienLb = new javax.swing.JLabel();
        HopDongLb = new javax.swing.JLabel();
        KhuyenMaiLb = new javax.swing.JLabel();
        TrangBiLb = new javax.swing.JLabel();
        HoaDonLb = new javax.swing.JLabel();
        ChamCongLb = new javax.swing.JLabel();
        DangXuatLb = new javax.swing.JLabel();
        phongLb = new javax.swing.JLabel();
        luongLb = new javax.swing.JLabel();
        svgLogo = new Image.SVGImage();
        svgPhong = new Image.SVGImage();
        svgKhach = new Image.SVGImage();
        svgNV = new Image.SVGImage();
        svgHopDong = new Image.SVGImage();
        svgKM = new Image.SVGImage();
        svgTB = new Image.SVGImage();
        svgHoaDon = new Image.SVGImage();
        svgCC = new Image.SVGImage();
        svgL = new Image.SVGImage();
        mainCardLayoutHDong = new javax.swing.JPanel();
        HopDongPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TieudeTK = new javax.swing.JLabel();
        timTheoComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nhapjTextField = new javax.swing.JTextField();
        traCuujButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHopDong = new javax.swing.JTable();
        suajButton = new javax.swing.JButton();
        xoajButton = new javax.swing.JButton();
        TraPhongButton = new javax.swing.JButton();
        SuaHopDongPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        gioiTinhjLabel = new javax.swing.JLabel();
        sua_tinhTrangHDjComboBox = new javax.swing.JComboBox<>();
        sua_slNguoiLonjTextField = new javax.swing.JTextField();
        sua_huyjButton = new javax.swing.JButton();
        sua_slTreEmjTextField = new javax.swing.JTextField();
        hoTenjLable = new javax.swing.JLabel();
        CCCDjLabel = new javax.swing.JLabel();
        sua_luujButton = new javax.swing.JButton();
        DichVuPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dichvuComboBox = new javax.swing.JComboBox<>();
        DichVuHong = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tentbComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        matbDV = new javax.swing.JLabel();
        giatbDV = new javax.swing.JLabel();
        slDV = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        tongslDV = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        tongslDV1 = new javax.swing.JLabel();
        tongDV = new javax.swing.JLabel();
        matbDV1 = new javax.swing.JLabel();
        giatbDV1 = new javax.swing.JLabel();
        XacNhanPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        thanhtien = new javax.swing.JLabel();
        kmComboBox = new javax.swing.JComboBox<>();
        hangkh = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tt = new javax.swing.JLabel();
        tiengiam = new javax.swing.JLabel();
        tenkh = new javax.swing.JLabel();
        tiendv = new javax.swing.JLabel();
        mahd = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        HoaDonPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BillTextPane = new javax.swing.JTextPane();
        InButton = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 220, 46));
        jLabel1.setText("BRIGHT STAR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        KhachHangLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhachHangLb.setForeground(new java.awt.Color(255, 255, 255));
        KhachHangLb.setText("Khách hàng");
        KhachHangLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachHangLbMouseClicked(evt);
            }
        });

        NhanVienLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NhanVienLb.setForeground(new java.awt.Color(255, 255, 255));
        NhanVienLb.setText("Nhân viên");
        NhanVienLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhanVienLbMouseClicked(evt);
            }
        });

        HopDongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HopDongLb.setForeground(new java.awt.Color(220, 220, 46));
        HopDongLb.setText("Hợp đồng");
        HopDongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HopDongLbMouseClicked(evt);
            }
        });

        KhuyenMaiLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhuyenMaiLb.setForeground(new java.awt.Color(255, 255, 255));
        KhuyenMaiLb.setText("Khuyến mãi");
        KhuyenMaiLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiLbMouseClicked(evt);
            }
        });

        TrangBiLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        TrangBiLb.setForeground(new java.awt.Color(255, 255, 255));
        TrangBiLb.setText("Trang bị");
        TrangBiLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TrangBiLbMouseClicked(evt);
            }
        });

        HoaDonLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HoaDonLb.setForeground(new java.awt.Color(255, 255, 255));
        HoaDonLb.setText("Hoá đơn");
        HoaDonLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonLbMouseClicked(evt);
            }
        });

        ChamCongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ChamCongLb.setForeground(new java.awt.Color(255, 255, 255));
        ChamCongLb.setText("Chấm công");
        ChamCongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChamCongLbMouseClicked(evt);
            }
        });

        DangXuatLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DangXuatLb.setForeground(new java.awt.Color(48, 90, 184));
        DangXuatLb.setText("Đăng xuất");
        DangXuatLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatLbMouseClicked(evt);
            }
        });

        phongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        phongLb.setForeground(new java.awt.Color(255, 255, 255));
        phongLb.setText("Phòng");
        phongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phongLbMouseClicked(evt);
            }
        });

        luongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        luongLb.setForeground(new java.awt.Color(255, 255, 255));
        luongLb.setText("Lương");
        luongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                luongLbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(phongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhachHangLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(NhanVienLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HopDongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhuyenMaiLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(TrangBiLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HoaDonLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ChamCongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(luongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(DangXuatLb))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhachHangLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhanVienLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HopDongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangBiLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoaDonLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luongLb))
                .addGap(20, 20, 20)
                .addComponent(DangXuatLb))
        );

        mainCardLayoutHDong.setBackground(new java.awt.Color(255, 255, 255));
        mainCardLayoutHDong.setPreferredSize(new java.awt.Dimension(700, 600));
        mainCardLayoutHDong.setLayout(new java.awt.CardLayout());

        HopDongPanel.setBackground(new java.awt.Color(255, 255, 255));
        HopDongPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("HỢP ĐỒNG");

        TieudeTK.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TieudeTK.setForeground(new java.awt.Color(0, 0, 0));
        TieudeTK.setText("Tìm kiếm theo");

        timTheoComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        timTheoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Hợp Đồng", "Mã KH", "Tình Trạng HĐ", "Số Phòng", "CCCD" }));
        timTheoComboBox.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nhập thông tin");

        nhapjTextField.setBackground(new java.awt.Color(255, 255, 255));
        nhapjTextField.setForeground(new java.awt.Color(0, 0, 0));

        traCuujButton.setBackground(new java.awt.Color(24, 24, 68));
        traCuujButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        traCuujButton.setForeground(new java.awt.Color(255, 255, 255));
        traCuujButton.setText("Tra cứu");
        traCuujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traCuujButtonActionPerformed(evt);
            }
        });

        tableHopDong.setBackground(new java.awt.Color(255, 255, 255));
        tableHopDong.setForeground(new java.awt.Color(0, 0, 0));
        tableHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hợp Đồng", "Mã KH", "Ngày Lập Hợp Đồng", "Thời Gian Nhận Phòng", "Thời Gian Trả Phòng", "Tình Trạng HĐ", "SL Người Lớn", "SL Trẻ Em", "Trị Giá HĐ", "Hinh thức thuê"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHopDongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHopDong);

        suajButton.setBackground(new java.awt.Color(24, 24, 68));
        suajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        suajButton.setForeground(new java.awt.Color(255, 255, 255));
        suajButton.setText("Sửa");
        suajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suajButtonActionPerformed(evt);
            }
        });

        xoajButton.setBackground(new java.awt.Color(24, 24, 68));
        xoajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        xoajButton.setForeground(new java.awt.Color(255, 255, 255));
        xoajButton.setText("Xóa");
        xoajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoajButtonActionPerformed(evt);
            }
        });

        TraPhongButton.setBackground(new java.awt.Color(0, 51, 153));
        TraPhongButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraPhongButton.setForeground(new java.awt.Color(255, 255, 255));
        TraPhongButton.setText("Trả phòng");
        TraPhongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraPhongButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HopDongPanelLayout = new javax.swing.GroupLayout(HopDongPanel);
        HopDongPanel.setLayout(HopDongPanelLayout);
        HopDongPanelLayout.setHorizontalGroup(
            HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HopDongPanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HopDongPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(269, 269, 269))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HopDongPanelLayout.createSequentialGroup()
                        .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timTheoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TieudeTK))
                        .addGap(50, 50, 50)
                        .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nhapjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(traCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
                    .addGroup(HopDongPanelLayout.createSequentialGroup()
                        .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(HopDongPanelLayout.createSequentialGroup()
                                .addComponent(xoajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TraPhongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );
        HopDongPanelLayout.setVerticalGroup(
            HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(25, 25, 25)
                .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TieudeTK)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timTheoComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nhapjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(traCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(HopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TraPhongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        mainCardLayoutHDong.add(HopDongPanel, "card2");

        SuaHopDongPanel.setBackground(new java.awt.Color(255, 255, 255));
        SuaHopDongPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 24, 68));
        jLabel9.setText("HỢP ĐỒNG");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nhập thông tin cần sửa");

        gioiTinhjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gioiTinhjLabel.setForeground(new java.awt.Color(0, 0, 0));
        gioiTinhjLabel.setText("Tình trạng Hợp đồng");

        sua_tinhTrangHDjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        sua_tinhTrangHDjComboBox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sua_tinhTrangHDjComboBox.setForeground(new java.awt.Color(0, 0, 0));
        sua_tinhTrangHDjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã xác nhận", "Chưa xác nhận", "Đã nhận phòng" }));

        sua_slNguoiLonjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_slNguoiLonjTextField.setForeground(new java.awt.Color(0, 0, 0));
        sua_slNguoiLonjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_slNguoiLonjTextFieldActionPerformed(evt);
            }
        });

        sua_huyjButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_huyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_huyjButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_huyjButton.setText("Hủy");
        sua_huyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_huyjButtonActionPerformed(evt);
            }
        });

        sua_slTreEmjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_slTreEmjTextField.setForeground(new java.awt.Color(0, 0, 0));

        hoTenjLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hoTenjLable.setForeground(new java.awt.Color(0, 0, 0));
        hoTenjLable.setText("Số lượng người lớn");

        CCCDjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CCCDjLabel.setForeground(new java.awt.Color(0, 0, 0));
        CCCDjLabel.setText("Số lượng trẻ em");

        sua_luujButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_luujButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_luujButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_luujButton.setText("Lưu");
        sua_luujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_luujButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SuaHopDongPanelLayout = new javax.swing.GroupLayout(SuaHopDongPanel);
        SuaHopDongPanel.setLayout(SuaHopDongPanelLayout);
        SuaHopDongPanelLayout.setHorizontalGroup(
            SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuaHopDongPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuaHopDongPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SuaHopDongPanelLayout.createSequentialGroup()
                        .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sua_tinhTrangHDjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gioiTinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sua_slNguoiLonjTextField)
                            .addComponent(hoTenjLable, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sua_slTreEmjTextField)
                            .addComponent(CCCDjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuaHopDongPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sua_luujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        SuaHopDongPanelLayout.setVerticalGroup(
            SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaHopDongPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioiTinhjLabel)
                    .addComponent(hoTenjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCCDjLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_tinhTrangHDjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_slNguoiLonjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_slTreEmjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addGroup(SuaHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_luujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        mainCardLayoutHDong.add(SuaHopDongPanel, "card3");

        DichVuPanel.setBackground(new java.awt.Color(255, 255, 255));
        DichVuPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(24, 24, 68));
        jLabel11.setText("DỊCH VỤ*");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Có tổn thất về trang bị không?");

        jButton1.setBackground(new java.awt.Color(0, 51, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tiếp theo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(24, 24, 68));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Quay lại");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dichvuComboBox.setBackground(new java.awt.Color(255, 255, 255));
        dichvuComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dichvuComboBox.setForeground(new java.awt.Color(0, 0, 0));
        dichvuComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không", "Có" }));

        DichVuHong.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tổng giá dịch vụ:");

        tentbComboBox.setBackground(new java.awt.Color(255, 255, 255));
        tentbComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tentbComboBox.setForeground(new java.awt.Color(0, 0, 0));
        tentbComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Chọn các trang bị hỏng:");

        matbDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        matbDV.setForeground(new java.awt.Color(0, 0, 0));

        giatbDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        giatbDV.setForeground(new java.awt.Color(0, 0, 0));

        slDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        slDV.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Số lượng hỏng:");

        tongslDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tongslDV.setForeground(new java.awt.Color(0, 0, 0));
        tongslDV.setText("0");

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tongslDV1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tongslDV1.setForeground(new java.awt.Color(0, 0, 0));
        tongslDV1.setText("Tổng số lượng:");

        tongDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tongDV.setForeground(new java.awt.Color(0, 0, 0));
        tongDV.setText("0");

        matbDV1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        matbDV1.setForeground(new java.awt.Color(0, 0, 0));
        matbDV1.setText("Mã trang bị:");

        giatbDV1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        giatbDV1.setForeground(new java.awt.Color(0, 0, 0));
        giatbDV1.setText("Giá trang bị:");

        javax.swing.GroupLayout DichVuHongLayout = new javax.swing.GroupLayout(DichVuHong);
        DichVuHong.setLayout(DichVuHongLayout);
        DichVuHongLayout.setHorizontalGroup(
            DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7)
            .addGroup(DichVuHongLayout.createSequentialGroup()
                .addComponent(tentbComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(DichVuHongLayout.createSequentialGroup()
                .addComponent(matbDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(matbDV, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(DichVuHongLayout.createSequentialGroup()
                .addComponent(giatbDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(giatbDV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tongslDV1)
                .addGap(17, 17, 17)
                .addComponent(tongslDV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(DichVuHongLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tongDV, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(slDV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DichVuHongLayout.setVerticalGroup(
            DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DichVuHongLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tentbComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matbDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matbDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(giatbDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giatbDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongslDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongslDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(DichVuHongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(slDV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout DichVuPanelLayout = new javax.swing.GroupLayout(DichVuPanel);
        DichVuPanel.setLayout(DichVuPanelLayout);
        DichVuPanelLayout.setHorizontalGroup(
            DichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DichVuPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(DichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(DichVuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(21, 21, 21)
                        .addComponent(dichvuComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DichVuHong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DichVuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        DichVuPanelLayout.setVerticalGroup(
            DichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DichVuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel11)
                .addGap(9, 9, 9)
                .addGroup(DichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DichVuPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dichvuComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(DichVuHong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(DichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        mainCardLayoutHDong.add(DichVuPanel, "card3");

        XacNhanPanel.setBackground(new java.awt.Color(255, 255, 255));
        XacNhanPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(24, 24, 68));
        jLabel10.setText("XÁC NHẬN THÔNG TIN *");

        thanhtien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        thanhtien.setForeground(new java.awt.Color(0, 0, 0));
        thanhtien.setText("Thành tiền: 0");

        kmComboBox.setBackground(new java.awt.Color(255, 255, 255));
        kmComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        kmComboBox.setForeground(new java.awt.Color(0, 0, 0));

        hangkh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hangkh.setForeground(new java.awt.Color(0, 0, 0));
        hangkh.setText("Hạng khách hàng:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Mã khuyến mãi:");

        tt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tt.setForeground(new java.awt.Color(0, 0, 0));
        tt.setText("Tổng tiền: 0");

        tiengiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tiengiam.setForeground(new java.awt.Color(0, 0, 0));
        tiengiam.setText("Tiền giảm: 0");

        tenkh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tenkh.setForeground(new java.awt.Color(0, 0, 0));
        tenkh.setText("Tên khách hàng:");

        tiendv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tiendv.setForeground(new java.awt.Color(0, 0, 0));
        tiendv.setText("Tiền dịch vụ: 0");

        mahd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mahd.setForeground(new java.awt.Color(0, 0, 0));
        mahd.setText("Mã hợp đồng:");

        jButton5.setBackground(new java.awt.Color(0, 102, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Thanh toán");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(24, 24, 68));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Quay lại");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout XacNhanPanelLayout = new javax.swing.GroupLayout(XacNhanPanel);
        XacNhanPanel.setLayout(XacNhanPanelLayout);
        XacNhanPanelLayout.setHorizontalGroup(
            XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XacNhanPanelLayout.createSequentialGroup()
                .addGroup(XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(hangkh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, XacNhanPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(mahd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(tiengiam, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(kmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(tiendv, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(thanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(XacNhanPanelLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        XacNhanPanelLayout.setVerticalGroup(
            XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XacNhanPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addGroup(XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hangkh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mahd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiengiam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(kmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tiendv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(thanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addGroup(XacNhanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainCardLayoutHDong.add(XacNhanPanel, "card3");

        HoaDonPanel.setBackground(new java.awt.Color(255, 255, 255));
        HoaDonPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(24, 24, 68));
        jLabel12.setText("HOÁ ĐƠN");

        BillTextPane.setBackground(new java.awt.Color(255, 255, 255));
        BillTextPane.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jScrollPane2.setViewportView(BillTextPane);

        InButton.setBackground(new java.awt.Color(0, 51, 153));
        InButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InButton.setForeground(new java.awt.Color(255, 255, 255));
        InButton.setText("In hoá đơn");
        InButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InButtonActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(24, 24, 68));
        jButton8.setText("Quay lại");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HoaDonPanelLayout = new javax.swing.GroupLayout(HoaDonPanel);
        HoaDonPanel.setLayout(HoaDonPanelLayout);
        HoaDonPanelLayout.setHorizontalGroup(
            HoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(HoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDonPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(416, 416, 416)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InButton, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        HoaDonPanelLayout.setVerticalGroup(
            HoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(HoaDonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(InButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainCardLayoutHDong.add(HoaDonPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainCardLayoutHDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainCardLayoutHDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLbMouseClicked
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLbMouseClicked

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked
        System.out.println(currentUser.toString());
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            NhanVienFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_NhanVienLbMouseClicked

    private void ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLbMouseClicked
        System.out.println(currentUser.toString());
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            ChamCongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_ChamCongLbMouseClicked

    private void DangXuatLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatLbMouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            dispose();
            DangNhapFrame.main(null);
        }
    }//GEN-LAST:event_DangXuatLbMouseClicked

    private void phongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phongLbMouseClicked
        dispose();
        PhongFrame.main(currentUser);
    }//GEN-LAST:event_phongLbMouseClicked

    private void luongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            LuongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_luongLbMouseClicked

    private void KhuyenMaiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiLbMouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            KhuyenMaiFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_KhuyenMaiLbMouseClicked

    private void HopDongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HopDongLbMouseClicked
        // TODO add your handling code here:
        cardlayout.show(mainCardLayoutHDong, "hopDong");
        inDanhSach();
    }//GEN-LAST:event_HopDongLbMouseClicked

    private void traCuujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traCuujButtonActionPerformed
        // Lấy đầu vào từ người dùng
        String input = this.nhapjTextField.getText();

        String option = (String) this.timTheoComboBox.getSelectedItem();

        // Kiểm tra đầu vào và gọi phương thức tương ứng
        if (input.equals("")) {
            inDanhSach();
        } else if (option.equals("Số Phòng")) {
            TraCuuHopDongTheoSoPhong();
        } else if (option.equals("CCCD")) {
            TraCuuHopDongTheoCCCD();
        } else {
            TraCuuHopDong();
        }
    }//GEN-LAST:event_traCuujButtonActionPerformed

    private void xoajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoajButtonActionPerformed
        // TODO add your handling code here:
        int removeIndex = this.tableHopDong.getSelectedRow();
        try {
            if (removeIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần xoá!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.tableHopDong.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maHopDongObject = this.tableHopDong.getValueAt(removeIndex, 0);
                Object maKHObject = this.tableHopDong.getValueAt(removeIndex, 1);

                int maHopDong = (Integer) maHopDongObject;
                int maKH = (Integer) maKHObject;

                int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa", JOptionPane.OK_CANCEL_OPTION);
                if (choice == JOptionPane.OK_OPTION) {
                    int row1 = ChiTietDatPhongDAO.XoaCTHDTheoMaHD(maHopDong);
                    System.out.println(row1);
                    if (row1 > 0) {
                        // Xử lý xóa hợp đồng ở đây
                        int row = HopDongDAO.XoaHopDong(maHopDong);
                        System.out.println(row);
                        // Kiểm tra nếu số lượng hợp đồng bé hơn 1 thì sẽ xóa luôn thông tin khách hàng đó
                        int sl = KhachHangDAO.demSoHopDong(maKH);
                        if (sl <= 1) {
                            KhachHangDAO.XoaKH(maKH);
                        } else {
                            sl = sl - 1;
                            KhachHangDAO.capNhatSoHopDong(maKH, sl);
                        }

                        if (row > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Xoá thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xoá không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Xoá không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // Người dùng hủy xóa
                    JOptionPane.showMessageDialog(rootPane, "Đã hủy xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

                inDanhSach();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_xoajButtonActionPerformed

    private void suajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suajButtonActionPerformed
        int selectedIndex = this.tableHopDong.getSelectedRow();

        try {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.tableHopDong.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maNVObject = this.tableHopDong.getValueAt(selectedIndex, 0);
                this.maHopDongSua = (Integer) maNVObject;
                cardlayout.show(mainCardLayoutHDong, "suaHopDong");
                hienDuLieuSua();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_suajButtonActionPerformed

    private void sua_huyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_huyjButtonActionPerformed
        cardlayout.show(mainCardLayoutHDong, "hopDong");
        inDanhSach();
    }//GEN-LAST:event_sua_huyjButtonActionPerformed

    private void sua_luujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_luujButtonActionPerformed
        // Lấy thông tin nhập liệu mới
        String slNL = this.sua_slNguoiLonjTextField.getText();
        int slNguoiLon = Integer.parseInt(slNL);
        String slTE = this.sua_slTreEmjTextField.getText();
        int slTreEm = Integer.parseInt(slTE);

        String tinhTrangHD = (String) this.sua_tinhTrangHDjComboBox.getSelectedItem();

        try {
            // Lấy thông tin cũ của hợp đồng từ cơ sở dữ liệu
            HopDongModel hopDongCu = HopDongDAO.getHDtheoMaHopDong(maHopDongSua);

            hopDongCu.setSoLuongTreEm(slTreEm);
            hopDongCu.setTinhTrangHD(tinhTrangHD);
            hopDongCu.setSoLuongNguoiLon(slNguoiLon);

            int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn sửa không?", "Xác nhận sửa", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                // Thực hiện cập nhật trong cơ sở dữ liệu
                if (HopDongDAO.CapNhatHopDong(maHopDongSua, hopDongCu)) {
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    this.sua_slNguoiLonjTextField.setText("");
                    this.sua_slTreEmjTextField.setText("");
                    cardlayout.show(mainCardLayoutHDong, "hopDong");
                    inDanhSach();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật không thành công. Vui lòng thử lại!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Người dùng hủy xóa
                JOptionPane.showMessageDialog(rootPane, "Đã hủy sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi: Tham chiếu null!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } // In ra thông tin lỗi chi tiết
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi không xác định!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sua_luujButtonActionPerformed

    private void sua_slNguoiLonjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_slNguoiLonjTextFieldActionPerformed

    }//GEN-LAST:event_sua_slNguoiLonjTextFieldActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        themTrangBiHong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TraPhongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraPhongButtonActionPerformed
        int selectedIndex = this.tableHopDong.getSelectedRow();
        try {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần xoá!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.tableHopDong.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maNVObject = this.tableHopDong.getValueAt(selectedIndex, 0);
                this.maHopDongTinh = (Integer) maNVObject;
                HopDongPanel.setVisible(false);
                DichVuPanel.setVisible(true);
                setDichVu1();
                DichVuHong.setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_TraPhongButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        HopDongPanel.setVisible(true);
        DichVuPanel.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DichVuPanel.setVisible(false);
        xacnhan();
        XacNhanPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DichVuPanel.setVisible(true);
        XacNhanPanel.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = tableHopDong.getSelectedRow();

        String tongStr = tiendv.getText().substring(14).replace(".", "");
        double dichvu = Double.parseDouble(tongStr);
        int maNV = currentUser.getMaNV();
        
        
        double giam = KhuyenMaiDAO.getGiamGia_MaKM(Integer.parseInt((String) kmComboBox.getSelectedItem()));
        System.out.println("giam tien: " + giam);
        double tg = tiengocfinal * giam;
        System.out.println("tien giam: " + tg);
        double thanhtien = tongtienfinal - tg;
        this.thanhtienfinal = thanhtien;
        System.out.println("thanh tien: " + thanhtien);
        
        
        System.out.println(tiengocfinal);
        System.out.println(tong);
        System.out.println(thanhtienfinal);

        int maHD = Integer.parseInt(tableHopDong.getValueAt(row, 0).toString());
        if ("".equals(kmComboBox.getSelectedItem().toString())) {
            if (HoaDonDAO.themHoaDonKhongMaKM(maHopDongTinh, thanhtien, dichvu, maNV)) {
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                HopDongDAO.capnhatTinhTrang(maHD);
                XacNhanPanel.setVisible(false);
                bill();
                HoaDonPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
            }
        } else {
            int maKM = Integer.parseInt(kmComboBox.getSelectedItem().toString());

            if (HoaDonDAO.themHoaDon(maKM, maHD, thanhtien, dichvu, maNV)) {
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                HopDongDAO.capnhatTinhTrang(maHD);
                XacNhanPanel.setVisible(false);
                bill();
                HoaDonPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
            }
      
        }
        

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        inDanhSach();
        resetVector();
        HopDongPanel.setVisible(true);
        HoaDonPanel.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void TrangBiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangBiLbMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangBiFrame.main(currentUser);
    }//GEN-LAST:event_TrangBiLbMouseClicked

    private void HoaDonLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonLbMouseClicked
        // TODO add your handling code here:
        dispose();
        HoaDonFrame.main(currentUser);
    }//GEN-LAST:event_HoaDonLbMouseClicked

    private void InButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InButtonActionPerformed
        saveToPDF(BillTextPane);
    }//GEN-LAST:event_InButtonActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tableHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHopDongMouseClicked
        // TODO add your handling code here:
        setDialog();
    }//GEN-LAST:event_tableHopDongMouseClicked

    public static void main(NhanVienModel args) {
        currentUser = args;
        currentUser.setMaNV(args.getMaNV());

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HopDongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new HopDongFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane BillTextPane;
    private javax.swing.JLabel CCCDjLabel;
    private javax.swing.JLabel ChamCongLb;
    private javax.swing.JLabel DangXuatLb;
    private javax.swing.JPanel DichVuHong;
    private javax.swing.JPanel DichVuPanel;
    private javax.swing.JLabel HoaDonLb;
    private javax.swing.JPanel HoaDonPanel;
    private javax.swing.JLabel HopDongLb;
    private javax.swing.JPanel HopDongPanel;
    private javax.swing.JButton InButton;
    private javax.swing.JLabel KhachHangLb;
    private javax.swing.JLabel KhuyenMaiLb;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel NhanVienLb;
    private javax.swing.JPanel SuaHopDongPanel;
    private javax.swing.JLabel TieudeTK;
    private javax.swing.JButton TraPhongButton;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JPanel XacNhanPanel;
    private javax.swing.JComboBox<String> dichvuComboBox;
    private javax.swing.JLabel giatbDV;
    private javax.swing.JLabel giatbDV1;
    private javax.swing.JLabel gioiTinhjLabel;
    private javax.swing.JLabel hangkh;
    private javax.swing.JLabel hoTenjLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> kmComboBox;
    private javax.swing.JLabel luongLb;
    private javax.swing.JLabel mahd;
    private javax.swing.JPanel mainCardLayoutHDong;
    private javax.swing.JLabel matbDV;
    private javax.swing.JLabel matbDV1;
    private javax.swing.JTextField nhapjTextField;
    private javax.swing.JLabel phongLb;
    private javax.swing.JSpinner slDV;
    private javax.swing.JButton sua_huyjButton;
    private javax.swing.JButton sua_luujButton;
    private javax.swing.JTextField sua_slNguoiLonjTextField;
    private javax.swing.JTextField sua_slTreEmjTextField;
    private javax.swing.JComboBox<String> sua_tinhTrangHDjComboBox;
    private javax.swing.JButton suajButton;
    private Image.SVGImage svgCC;
    private Image.SVGImage svgHoaDon;
    private Image.SVGImage svgHopDong;
    private Image.SVGImage svgKM;
    private Image.SVGImage svgKhach;
    private Image.SVGImage svgL;
    private Image.SVGImage svgLogo;
    private Image.SVGImage svgNV;
    private Image.SVGImage svgPhong;
    private Image.SVGImage svgTB;
    private javax.swing.JTable tableHopDong;
    private javax.swing.JLabel tenkh;
    private javax.swing.JComboBox<String> tentbComboBox;
    private javax.swing.JLabel thanhtien;
    private javax.swing.JLabel tiendv;
    private javax.swing.JLabel tiengiam;
    private javax.swing.JComboBox<String> timTheoComboBox;
    private javax.swing.JLabel tongDV;
    private javax.swing.JLabel tongslDV;
    private javax.swing.JLabel tongslDV1;
    private javax.swing.JButton traCuujButton;
    private javax.swing.JLabel tt;
    private javax.swing.JButton xoajButton;
    // End of variables declaration//GEN-END:variables

}
