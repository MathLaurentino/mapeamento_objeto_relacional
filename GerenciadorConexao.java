import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GerenciadorConexao{

    private static Connection conexao;

    public static Connection pegarConexao() 
            throws ClassNotFoundException, SQLException{
        String url = "jdbc:mysql://127.0.0.1/dblocadora?characterEncoding=latin1";
        String usuario = "root";
        String senha = "bancodedados";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection(
            url, usuario, senha);
        return conexao;
    }

    // private static Connection conn = null;

    // public static Connection pegarConexao() {
    //     if (conn == null) {
    //         try {
    //             String url = "jdbc:mysql://127.0.0.1/dblocadora?characterEncoding=latin1";
    //             String usuario = "root";
    //             String senha = "bancodedados";

    //             Class.forName("com.mysql.cj.jdbc.Driver");
    //             conn = DriverManager.getConnection(url, usuario, senha);
    //             return conn;
    //         }
    //         catch (SQLException e) {
    //             e.printStackTrace();
    //         } catch (ClassNotFoundException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return conn;
    // }

    // public static void closeConnection() {
    //     if (conn != null) {
    //         try {
    //             conn.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}