
package View;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class HopDongJDialog extends javax.swing.JDialog {


    
    public HopDongJDialog() {
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
        ChiTietHoaDonjLabel = new javax.swing.JLabel();
        TienTongjLabel = new javax.swing.JLabel();
        MaKhachHangjLabel = new javax.swing.JLabel();
        MaHopDongjLabel = new javax.swing.JLabel();
        TenKhachHangJLabel = new javax.swing.JLabel();

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
                "Số phòng", "Hình thức thuê", "Ngày nhận phòng ", "Ngày trả phòng", "Thành tiền  "
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

        ChiTietHoaDonjLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ChiTietHoaDonjLabel.setForeground(new java.awt.Color(100, 19, 19));
        ChiTietHoaDonjLabel.setText("CHI TIẾT ĐẶT PHÒNG");

        TienTongjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TienTongjLabel.setForeground(new java.awt.Color(0, 0, 0));
        TienTongjLabel.setText("Tiền tổng:");

        MaKhachHangjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaKhachHangjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaKhachHangjLabel.setText("Mã khách hàng:");

        MaHopDongjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MaHopDongjLabel.setForeground(new java.awt.Color(0, 0, 0));
        MaHopDongjLabel.setText("Mã hợp đồng ");

        TenKhachHangJLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenKhachHangJLabel.setForeground(new java.awt.Color(0, 0, 0));
        TenKhachHangJLabel.setText("Tên khách hàng ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(TienTongjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(ChiTietHoaDonjLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(MaKhachHangjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TenKhachHangJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(MaHopDongjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ChiTietHoaDonjLabel)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaKhachHangjLabel)
                    .addComponent(TenKhachHangJLabel))
                .addGap(40, 40, 40)
                .addComponent(MaHopDongjLabel)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(TienTongjLabel)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable CTHDjTabel;
    private javax.swing.JLabel ChiTietHoaDonjLabel;
    public javax.swing.JLabel MaHopDongjLabel;
    public static javax.swing.JLabel MaKhachHangjLabel;
    public javax.swing.JLabel TenKhachHangJLabel;
    public static javax.swing.JLabel TienTongjLabel;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
