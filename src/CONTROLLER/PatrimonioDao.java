package CONTROLLER;

import MODEL.PatrimonioM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo-NOTE
 */
public class PatrimonioDao {

    
    public void salvar (PatrimonioM patrimonio) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Patrimonio values (?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, patrimonio.getNome());
        pst.setString(3, patrimonio.getNumero());
        pst.setString(4, patrimonio.getQualidade());
        pst.setBoolean(5, patrimonio.getOcupado());
        pst.execute();
        pst.close();
    }
    
    public void excluir(PatrimonioM patrimonio) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Patrimonio where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, patrimonio.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(PatrimonioM patrimonio) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Patrimonio set "
                        + "nome = ?, "
                        + "numero = ?, "
                        + "qualidade = ?, "
                        + "ocupado = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, patrimonio.getNome());
        pst.setString(2, patrimonio.getNumero());
        pst.setString(3, patrimonio.getQualidade());
        pst.setBoolean(4, patrimonio.getOcupado());
        pst.setInt(5,patrimonio.getId());
        pst.execute();
        pst.close();
     }

    public List<PatrimonioM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<PatrimonioM> listaPatrimonio = new ArrayList<>();
        sql = "select * from Patrimonio order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaPatrimonio.add(new PatrimonioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero"),
                            rs.getString("qualidade"),
                            rs.getBoolean("ocupado")));
        }
        pst.close();
        return listaPatrimonio;
    }
    
    public PatrimonioM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM patrimonio = null;        
        sql = "select * from Patrimonio where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            patrimonio = new PatrimonioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero"),
                            rs.getString("qualidade"),
                            rs.getBoolean("ocupado"));
        }
        pst.close();
        return patrimonio;
    }
    
    public List<PatrimonioM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<PatrimonioM> listaPatrimonio = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Patrimonio where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaPatrimonio.add(new PatrimonioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero"),
                            rs.getString("qualidade"),
                            rs.getBoolean("ocupado")));
        }

        pst.close();
        return listaPatrimonio;
    }
    
    public boolean buscacodigo(String codigo) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM patrimonio = null;
        int i = 0;
        sql = "select * from Patrimonio where codigo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            patrimonio = new PatrimonioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero"),
                            rs.getString("qualidade"),
                            rs.getBoolean("ocupado"));
            i++;
        }
        pst.close();
        return !( i <= 0);
    }
    
    public PatrimonioM buscacodigo2(String codigo) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM patrimonio = null;
        int i = 0;
        sql = "select * from Patrimonio where codigo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            patrimonio = new PatrimonioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero"),
                            rs.getString("qualidade"),
                            rs.getBoolean("ocupado"));
            i++;
        }
        pst.close();
            
            return patrimonio;
        
    }
    
}
