package View;

import DAO.KhuyenMaiDAO;
import DAO.NhanVienDAO;
import Model.KhuyenMaiModel;
import javax.swing.JOptionPane;
import Model.NhanVienModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DangNhapFrame extends javax.swing.JDialog {

    NhanVienModel user;

    public DangNhapFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        addSVG();
    }

    DangNhapFrame() {
        this(null, true);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EnterAccount_JTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        KhachButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        svgLogo = new Image.SVGImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(24, 24, 68));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 600));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(24, 24, 68));
        jLabel2.setText("ĐĂNG NHẬP");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tài khoản");

        EnterAccount_JTextField.setBackground(new java.awt.Color(255, 255, 255));
        EnterAccount_JTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EnterAccount_JTextField.setForeground(new java.awt.Color(0, 0, 0));
        EnterAccount_JTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterAccount_JTextFieldActionPerformed(evt);
            }
        });
        EnterAccount_JTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterAccount_JTextFieldKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mật Khẩu");

        jPasswordField1.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        LoginButton.setBackground(new java.awt.Color(24, 24, 68));
        LoginButton.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("Đăng nhập");
        LoginButton.setBorder(null);
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
        });
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        KhachButton.setBackground(new java.awt.Color(228, 228, 228));
        KhachButton.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        KhachButton.setForeground(new java.awt.Color(0, 0, 0));
        KhachButton.setText("Tiếp tục với tư cách khách hàng");
        KhachButton.setToolTipText("");
        KhachButton.setBorder(null);
        KhachButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachButtonMouseClicked(evt);
            }
        });
        KhachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KhachButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnterAccount_JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KhachButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(129, 129, 129))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnterAccount_JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(KhachButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 220, 46));
        jLabel1.setText("BRIGHT STAR");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("KHÁCH SẠN BRIGHT STAR ĐẠT CHUẨN 5 SAO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addSVG() {
        svgLogo.setSVGImage("Image/logo.svg", 300, 300);
    }
    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        if (EnterAccount_JTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordField1.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {

            user = NhanVienDAO.getUser(EnterAccount_JTextField.getText(), jPasswordField1.getText());

            if (user != null) {
                //JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                hide();

                TrangChuNVFrame.main(user);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void KhachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KhachButtonActionPerformed

    }//GEN-LAST:event_KhachButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DangNhapFrame dialog = new DangNhapFrame(null, true);
            dialog.setTitle("Đăng nhập hệ thống ");
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }

    private void EnterAccount_JTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnterAccount_JTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            jPasswordField1.requestFocus();
    }//GEN-LAST:event_EnterAccount_JTextFieldKeyPressed

    private void EnterAccount_JTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterAccount_JTextFieldActionPerformed

    }//GEN-LAST:event_EnterAccount_JTextFieldActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LoginButton.doClick();
        }
    }

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed

    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked
        if (EnterAccount_JTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordField1.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } // Duyet tai khoan
        else {

            user = NhanVienDAO.getUser(EnterAccount_JTextField.getText(), jPasswordField1.getText());

            if (user != null) {
                //JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                hide();

                TrangChuNVFrame.main(user);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_LoginButtonMouseClicked

    private void KhachButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachButtonMouseClicked
        dispose();
        ArrayList<KhuyenMaiModel> khuyenMaiList = KhuyenMaiDAO.getDSKhuyenMai();
        new TrangChuKHFrame(khuyenMaiList).setVisible(true);
    }//GEN-LAST:event_KhachButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EnterAccount_JTextField;
    private javax.swing.JButton KhachButton;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private Image.SVGImage svgLogo;
    // End of variables declaration//GEN-END:variables
}
