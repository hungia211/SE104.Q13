package main;

import View.DangNhapFrame;

public class Main {

    public static void main(String[] args) {
        DangNhapFrame dialog = new DangNhapFrame(null, true);
        dialog.setTitle("Đăng nhập hệ thống");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
