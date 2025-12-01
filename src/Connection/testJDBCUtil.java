package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testJDBCUtil {

    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();

        JDBCUtil.printInfo(con);

        JDBCUtil.closeConnection(con);

    }

   
}
