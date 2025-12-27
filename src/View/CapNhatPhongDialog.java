
package View;

import DAO.PhongDAO;
import Model.LoaiPhongModel;
import Model.PhongModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CapNhatPhongDialog extends javax.swing.JDialog {

    private int maPhong;
    
    private void initTieuDe() {
        TieuDejLabel.setText("Cập nhật phòng " + maPhong);
    }
    
    private void loadTinhTrang() {
        cbTinhTrang.removeAllItems();
        cbTinhTrang.addItem("Bình thường");
        cbTinhTrang.addItem("Bảo trì");
    }
    
    private void loadLoaiPhong() throws SQLException {
        cbLoaiPhong.removeAllItems();

        for (LoaiPhongModel lp : PhongDAO.getAllLoaiPhong()) {
            cbLoaiPhong.addItem(lp.getTenloai());
        }
    }

    private void loadThongTinPhong() throws SQLException {
        PhongModel p = PhongDAO.getPhongTheoMa(maPhong);

        if (p != null) {
            cbTinhTrang.setSelectedItem(p.getTinhTrang());
            cbLoaiPhong.setSelectedItem(
                p.getLoaiPhong().getTenloai()
            );
        }
    }



    public CapNhatPhongDialog(java.awt.Frame parent, boolean modal, int maPhong) {
        super(parent, modal);
        initComponents();
        this.maPhong = maPhong;

        initTieuDe();
        loadTinhTrang();

        try {
            loadLoaiPhong();
            loadThongTinPhong();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Lỗi load dữ liệu phòng: " + ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            dispose(); // đóng dialog nếu load lỗi
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TieuDejLabel = new javax.swing.JLabel();
        tinhtrangjLabel = new javax.swing.JLabel();
        loaiphongjLabel = new javax.swing.JLabel();
        cbTinhTrang = new javax.swing.JComboBox<>();
        cbLoaiPhong = new javax.swing.JComboBox<>();
        btnXacNhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TieuDejLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        TieuDejLabel.setForeground(new java.awt.Color(0, 0, 0));
        TieuDejLabel.setText("Cập nhật phòng");

        tinhtrangjLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tinhtrangjLabel.setForeground(new java.awt.Color(0, 0, 0));
        tinhtrangjLabel.setText("Tình trạng: ");

        loaiphongjLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        loaiphongjLabel.setForeground(new java.awt.Color(0, 0, 0));
        loaiphongjLabel.setText("Loại phòng");

        cbTinhTrang.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình thường", "Bảo trì" }));

        cbLoaiPhong.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbLoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        btnXacNhan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(loaiphongjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(TieuDejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tinhtrangjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnXacNhan)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(TieuDejLabel)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tinhtrangjLabel)
                    .addComponent(cbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loaiphongjLabel)
                    .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXacNhan)
                    .addComponent(btnHuy))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String tinhTrang = cbTinhTrang.getSelectedItem().toString();
        String tenLoai = cbLoaiPhong.getSelectedItem().toString();

        int maLoai = PhongDAO.getMaLoaiTheoTen(tenLoai);

        if (maLoai == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Không tìm thấy loại phòng!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // HỎI XÁC NHẬN LẦN CUỐI
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn cập nhật phòng " + maPhong + " ?",
            "Xác nhận cập nhật",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return; // người dùng bấm NO
        }

        boolean ok = PhongDAO.updatePhong(
            maPhong,
            tinhTrang,
            maLoai
        );

        if (ok) {
            JOptionPane.showMessageDialog(
                this,
                "Cập nhật phòng thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
            );
            dispose(); // đóng dialog
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Cập nhật thất bại!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TieuDejLabel;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbLoaiPhong;
    private javax.swing.JComboBox<String> cbTinhTrang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loaiphongjLabel;
    private javax.swing.JLabel tinhtrangjLabel;
    // End of variables declaration//GEN-END:variables
}
