package Database_connector;
import javax.xml.transform.Result;
import java.sql.*;

public class DataConnector {
    static Connection conn = null;
    static Statement stmt = null;
    public static void SQL_Code(String sql) throws SQLException {
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print( rsmd.getColumnName(i) + " : "+ columnValue );
                }
            }}
        catch (SQLException e ) {
            throw new Error("Problem", e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public static void main(String args[])
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hung_debug","root", "");
            //Anh em chỉ cần viết câu lệnh vào đây
            String sql = "SELECT * FROM `ho_gia_dinh`  ";
            SQL_Code(sql);
            System.out.print("Database is connected !");
            //conn.close();
        }
        catch(Exception e)
        {
            System.out.print("Do not connect to DB - Error:"+e);
        }

    }
}
