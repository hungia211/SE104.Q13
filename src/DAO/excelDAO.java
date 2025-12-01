package DAO;

import Model.HoaDonModel;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDAO {

    public void exportFileND() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danhsachhoadon");
        XSSFRow row = null;

        Cell cell = null;

        row = sheet.createRow(0);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã Hóa đơn");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã Khuyến mãi");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Mã Hợp đồng");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Ngày lập hợp đồng");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Tổng tiền hóa đơn");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Tiền hỏng TB");

        ArrayList<HoaDonModel> arr_hd = HoaDonDAO.getDSHoaDon();

        for (int i = 0; i < arr_hd.size(); i++) {
            row = sheet.createRow(1 + i);
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(arr_hd.get(i).getMaHD());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(arr_hd.get(i).getMaKM());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(arr_hd.get(i).getMaHopDong());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(String.valueOf(arr_hd.get(i).getNgayLapHD()));

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(arr_hd.get(i).getTongTien());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(arr_hd.get(i).getTienHongTB());
        }

        // Thay đổi đường dẫn tệp đích
        File f = new File("D:\\JAVA\\HOA DON\\reportHoaDon.xlsx");
        try {
            FileOutputStream fis = new FileOutputStream(f);
            workbook.write(fis);
            fis.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public void exportFileSoKHTrongThang() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danhsachSoKH");
        XSSFRow row;
        Cell cell;

        // Tạo dòng tiêu đề
        row = sheet.createRow(0);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Tổng số người lớn");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Tháng");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Năm");

        // Lấy dữ liệu từ phương thức getSoKHTrongThang
        ArrayList<Vector> arr_soKH = HopDongDAO.getSoKHTrongThang();

        // Điền dữ liệu vào sheet
        for (int i = 0; i < arr_soKH.size(); i++) {
            row = sheet.createRow(1 + i);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue((Integer) arr_soKH.get(i).get(0)); // Tổng số người lớn

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) arr_soKH.get(i).get(1)); // Tháng

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Integer) arr_soKH.get(i).get(2)); // Năm
        }

        // Thay đổi đường dẫn tệp đích
        File f = new File("D:\\JAVA\\HOA DON\\reportSoKHTrongThang.xlsx");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exportFileHTThuePhong() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danhsachThongKeDP");
        XSSFRow row;
        Cell cell;

        // Tạo dòng tiêu đề
        row = sheet.createRow(0);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Hình thức thuê");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Số hợp đồng");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Tháng");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Năm");

        // Lấy dữ liệu từ phương thức getSoKHTrongThang
        ArrayList<Vector> arr_soKH = HopDongDAO.getHTThuePhong();

        // Điền dữ liệu vào sheet
        for (int i = 0; i < arr_soKH.size(); i++) {
            row = sheet.createRow(1 + i);

            Vector<Object> rowData = arr_soKH.get(i);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue((String) rowData.get(0)); // Hình thức thuê

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) rowData.get(1)); // Số hợp đồng

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Integer) rowData.get(2)); // Tháng

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue((Integer) rowData.get(3)); // Năm
        }

        // Thay đổi đường dẫn tệp đích
        File f = new File("D:\\JAVA\\HOA DON\\reportThongKeDatPhong.xlsx");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exportFileDoanhThuTheoNam() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("doanhthuTheoNam");
        XSSFRow row;
        Cell cell;

        // Tạo dòng tiêu đề
        row = sheet.createRow(0);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Doanh thu");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Năm");

        // Lấy dữ liệu từ phương thức getDoanhThuTheoNam
        ArrayList<Vector> arr_doanhThu = HoaDonDAO.getDoanhThuTheoNam();

        // Điền dữ liệu vào sheet
        for (int i = 0; i < arr_doanhThu.size(); i++) {
            row = sheet.createRow(1 + i);

            Vector<Object> rowData = arr_doanhThu.get(i);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue((Double) rowData.get(0)); // Doanh thu

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) rowData.get(1)); // Năm
        }

        // Thay đổi đường dẫn tệp đích
        File f = new File("D:\\JAVA\\HOA DON\\reportDoanhThuTheoNam.xlsx");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
