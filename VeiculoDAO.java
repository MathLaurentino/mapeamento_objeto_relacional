import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VeiculoDAO {
    
    private static Connection conn;
    private PreparedStatement st;

    public int inserir(Veiculo veiculo){
        PreparedStatement st = null;

        int idGerado = 0;
        try{
            this.conectar();

            st = conn.prepareStatement(
                "INSERT INTO tb_veiculos (marca, modelo, chassi, ano) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, veiculo.getMarca());
            st.setString(2, veiculo.getModelo());
            st.setString(3, veiculo.getChassi());
            st.setInt(4, veiculo.getAno());

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


    public Veiculo getVeiculo(int codigo) {
        Veiculo veiculo = null;
        try{
            this.conectar();
            String sql = "SELECT * FROM tb_veiculos WHERE codigo =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("codigo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setChassi(rs.getString("chassi"));
                veiculo.setAno(rs.getInt("ano"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } 

        return veiculo;
    }


    public int excluir(int codigo) {
        int qtde = 0;
        try{
            this.conectar();
            String sql = "DELETE FROM tb_veiculos WHERE codigo =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, codigo);
            qtde = st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        } 
        return qtde;
    }


    public boolean alterar(Veiculo veiculo){
        PreparedStatement st = null;
        try{
            this.conectar();
            String sql = "UPDATE tb_veiculos SET marca =?, modelo =?, chassi =?, ano =? WHERE codigo =?";
            st = conn.prepareStatement(sql);

            st.setString(1, veiculo.getMarca());
            st.setString(2, veiculo.getModelo());
            st.setString(3, veiculo.getChassi());
            st.setInt(4, veiculo.getAno());
            st.setInt(5, veiculo.getCodigo());

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




    public ArrayList<Veiculo> buscarTodos(){

        ArrayList<Veiculo> resultado = new ArrayList<>();
        try{
            this.conectar();
            String sql = "SELECT * FROM tb_veiculos";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("codigo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setChassi(rs.getString("chassi"));
                veiculo.setAno(rs.getInt("ano"));
                resultado.add(veiculo);
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
