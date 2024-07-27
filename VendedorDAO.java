import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendedorDAO {
    
    private static Connection conn;
    private PreparedStatement st;

    public int inserir(Vendedor Vendedor){
        PreparedStatement st = null;

        int idGerado = 0;
        try{
            this.conectar();

            st = conn.prepareStatement(
                "INSERT INTO tb_vendedor (nome, sobrenome, salario, quantVendas) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, Vendedor.getNome());
            st.setString(2, Vendedor.getSobrenome());
            st.setFloat(3, Vendedor.getSalario());
            st.setInt(4, Vendedor.getQuantVendas());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return idGerado;
    }


    public Vendedor getVendedor(int codigo) {
        Vendedor Vendedor = null;
        try{
            this.conectar();
            String sql = "SELECT * FROM tb_vendedor WHERE codigo =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Vendedor = new Vendedor();
                Vendedor.setCodigo(rs.getInt("codigo"));
                Vendedor.setNome(rs.getString("nome"));
                Vendedor.setSobrenome(rs.getString("sobrenome"));
                Vendedor.setSalario(rs.getFloat("salario"));
                Vendedor.setQuantVendas(rs.getInt("quantVendas"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } 

        return Vendedor;
    }


    public int excluir(int codigo) {
        int qtde = 0;
        try{
            this.conectar();
            String sql = "DELETE FROM tb_vendedor WHERE codigo =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, codigo);
            qtde = st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        } 
        return qtde;
    }


    public boolean alterar(Vendedor Vendedor){
        PreparedStatement st = null;
        try{
            this.conectar();
            String sql = "UPDATE tb_vendedor SET nome =?, sobrenome =?, salario =?, quantVendas =? WHERE codigo =?";
            st = conn.prepareStatement(sql);

            st.setString(1, Vendedor.getNome());
            st.setString(2, Vendedor.getSobrenome());
            st.setFloat(3, Vendedor.getSalario());
            st.setInt(4, Vendedor.getQuantVendas());
            st.setInt(5, Vendedor.getCodigo());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } 
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<Vendedor> buscarTodos(){

        ArrayList<Vendedor> resultado = new ArrayList<>();
        try{
            this.conectar();
            String sql = "SELECT * FROM tb_vendedor";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Vendedor Vendedor = new Vendedor();
                Vendedor.setCodigo(rs.getInt("codigo"));
                Vendedor.setNome(rs.getString("nome"));
                Vendedor.setSobrenome(rs.getString("sobrenome"));
                Vendedor.setSalario(rs.getFloat("salario"));
                Vendedor.setQuantVendas(rs.getInt("quantVendas"));
                resultado.add(Vendedor);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } 

        return resultado;
    }


    private void conectar(){    
        try{
            conn = GerenciadorConexao.pegarConexao();
        } 
        catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void desconectar(){
        try{
            st.close();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
