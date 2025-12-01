
package Connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;

public class JDBCUtil {

    private static DatabaseMetaData mtdt;
    public static Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            // Không cần đăng ký Oracle Driver từ Java 6 trở đi

            // Thông số kết nối
            // SID: ORCL
            // Sửa lại chút
            String url  = "jdbc:oracle:thin:@//26.73.94.5:1521/orcl";

//            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            String user = "database_doan_NPNT";
            String pass = "password";

            // Tạo kết nối
            c = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            e.printStackTrace();
            // Đóng kết nối trước khi ném ngoại lệ hoặc trả về null
            if (c != null) {
                c.close();
            }
            throw e;
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void printInfo(Connection c) throws SQLException{
        
        try {
            if (c != null){
                mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}

