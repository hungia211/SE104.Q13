
package View;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class HoaDonJDialog extends javax.swing.JDialog {


    public HoaDonJDialog() {
        super();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CTHDjTabel = new javax.swing.JTable();
        MaHoaDonjLabel = new javax.swing.JLabel();
        ChiTietHoaDonjLabel = new javax.swing.JLabel();
        TienTongjLabel = new javax.swing.JLabel();
        MaKhachHangjLabel = new javax.swing.JLabel();
        MaKhuyenMaijLabel = new javax.swing.JLabel();
        TienGiamjLabel = new javax.swing.JLabel();
        NgayHoaDonjLabel = new javax.swing.JLabel();
        MaHopDongjLabel = new javax.swing.JLabel();
        TienHongTBjLabel = new javax.swing.JLabel();
        TenKhachHangJLabel = new javax.swing.JLabel();
        MaNVjLabel = new javax.swing.JLabel();
        TenNVjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 700));

        CTHDjTabel.setBackground(new java.awt.Color(255, 255, 255));
        CTHDjTabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CTHDjTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Hình thức thuê", "Ngày nhận phòng ", "Ngày trả phòng", "Thành tiền  "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CTHDjTabel);

        MaHoaDonjLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MaHoaDonjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaHoaDonjLabel.setText("Mã hoá đơn:");

        ChiTietHoaDonjLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ChiTietHoaDonjLabel.setForeground(new java.awt.Color(100, 19, 19));
        ChiTietHoaDonjLabel.setText("CHI TIẾT HOÁ ĐƠN");

        TienTongjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TienTongjLabel.setForeground(new java.awt.Color(0, 0, 0));
        TienTongjLabel.setText("Tiền tổng:");

        MaKhachHangjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaKhachHangjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaKhachHangjLabel.setText("Mã khách hàng:");

        MaKhuyenMaijLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaKhuyenMaijLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaKhuyenMaijLabel.setText("Mã hợp đồng:");

        TienGiamjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TienGiamjLabel.setForeground(new java.awt.Color(0, 0, 0));
        TienGiamjLabel.setText("Tiền giảm:");

        NgayHoaDonjLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NgayHoaDonjLabel.setForeground(new java.awt.Color(0, 0, 0));
        NgayHoaDonjLabel.setText("Ngày hoá đơn:");

        MaHopDongjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaHopDongjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaHopDongjLabel.setText("Mã khuyến mãi");

        TienHongTBjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TienHongTBjLabel.setForeground(new java.awt.Color(0, 0, 0));
        TienHongTBjLabel.setText("Tiền hỏng trang bị");

        TenKhachHangJLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenKhachHangJLabel.setForeground(new java.awt.Color(0, 0, 0));
        TenKhachHangJLabel.setText("Tên khách hàng ");

        MaNVjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaNVjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaNVjLabel.setText("Mã nhân viên");

        TenNVjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenNVjLabel.setForeground(new java.awt.Color(0, 0, 0));
        TenNVjLabel.setText("Tên nhân viên :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(MaKhachHangjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(TenKhachHangJLabel))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(310, 310, 310)
                            .addComponent(TienTongjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(310, 310, 310)
                            .addComponent(TienGiamjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(310, 310, 310)
                            .addComponent(TienHongTBjLabel))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MaKhuyenMaijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(MaNVjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(39, 39, 39)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MaHopDongjLabel)
                                .addComponent(TenNVjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(ChiTietHoaDonjLabel)
                            .addGap(37, 37, 37)
                            .addComponent(MaHoaDonjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(NgayHoaDonjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChiTietHoaDonjLabel)
                    .addComponent(MaHoaDonjLabel)
                    .addComponent(NgayHoaDonjLabel))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaKhachHangjLabel)
                    .addComponent(TenKhachHangJLabel))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaKhuyenMaijLabel)
                    .addComponent(MaHopDongjLabel))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaNVjLabel)
                    .addComponent(TenNVjLabel))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(TienTongjLabel)
                .addGap(40, 40, 40)
                .addComponent(TienGiamjLabel)
                .addGap(40, 40, 40)
                .addComponent(TienHongTBjLabel))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable CTHDjTabel;
    private javax.swing.JLabel ChiTietHoaDonjLabel;
    public static javax.swing.JLabel MaHoaDonjLabel;
    public javax.swing.JLabel MaHopDongjLabel;
    public static javax.swing.JLabel MaKhachHangjLabel;
    public static javax.swing.JLabel MaKhuyenMaijLabel;
    public javax.swing.JLabel MaNVjLabel;
    public static javax.swing.JLabel NgayHoaDonjLabel;
    public javax.swing.JLabel TenKhachHangJLabel;
    public javax.swing.JLabel TenNVjLabel;
    public static javax.swing.JLabel TienGiamjLabel;
    public javax.swing.JLabel TienHongTBjLabel;
    public static javax.swing.JLabel TienTongjLabel;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
