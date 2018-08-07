package CONTROLLER;

import MODEL.RequerenteM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class RequerenteDao {
    
    public void salvar (RequerenteM requerente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Requerente set id = ?, nome = ?, endereco = ?, cidade = ?, telefone = ?, email = ?, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + "ra = ?, rg = ?, cpf = ?, curso = ?, bloqueio = ?, senha = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, requerente.getNome());
        pst.setString(3, requerente.getEndereco());
        pst.setString(4, requerente.getCidade());
        pst.setString(5, requerente.getTelefone());
        pst.setString(6, requerente.getEmail());
        pst.setString(7, requerente.getNascimento());
        pst.setString(8, requerente.getRA());
        pst.setString(9, requerente.getRg());
        pst.setString(10, requerente.getCpf());
        pst.setString(11, requerente.getCurso());
        pst.setBoolean(12, requerente.getBloqueio());
        pst.setString(13, requerente.getSenha());
        pst.execute();
        pst.close();
    }
    
    public void excluir(RequerenteM requerente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Requerente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, requerente.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(RequerenteM requerente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Requerente set "
                        + "nome = ?, "
                        + "endereco = ?, "
                        + "cidade = ?, "
                        + "telefone = ?, "
                        + "email = ?, "
                        + "Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "ra = ?, "
                        + "rg = ?, "
                        + "cpf = ?, "
                        + "curso  = ?, "
                        + "bloqueio  = ?, "
                        + "senha  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, requerente.getNome());
        pst.setString(2, requerente.getEndereco());
        pst.setString(3, requerente.getCidade());
        pst.setString(4, requerente.getTelefone());
        pst.setString(5, requerente.getEmail());
        pst.setString(6, requerente.getNascimento());
        pst.setString(7, requerente.getRA());
        pst.setString(8, requerente.getRg());
        pst.setString(9, requerente.getCpf());
        pst.setString(10, requerente.getCurso());
        pst.setBoolean(11, requerente.getBloqueio());
        pst.setString(12, requerente.getSenha());
        pst.setInt(13,requerente.getId());
        pst.execute();
        pst.close();
     }
    
    public List<RequerenteM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<RequerenteM> listaRequerente = new ArrayList<>();
        sql = "select id, nome, endereco, cidade, telefone, email, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + "ra, rg, cpf, curso, bloqueio, senha  from Requerente order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaRequerente.add(new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("nascimento"),
                            rs.getString("ra"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("curso"),
                            rs.getBoolean("bloqueio"),
                            rs.getString("senha")));
        }
        pst.close();
        return listaRequerente;
    }
    
    public RequerenteM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        RequerenteM requerente = null;        
        sql = "select id, nome, endereco, cidade, telefone, email, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + "ra, rg, cpf, curso, bloqueio, senha from Requerente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            requerente = new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("nascimento"),
                            rs.getString("ra"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("curso"),
                            rs.getBoolean("bloqueio"),
                            rs.getString("senha"));
        }
        pst.close();
        return requerente;
    }
    
    public List<RequerenteM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<RequerenteM> listaRequerente = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select id, nome, endereco, cidade, telefone, email, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + "ra, rg, cpf, curso, bloqueio, senha from Requerente where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaRequerente.add(new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("nascimento"),
                            rs.getString("ra"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("curso"),
                            rs.getBoolean("bloqueio"),
                            rs.getString("senha")));
        }

        pst.close();
        return listaRequerente;
    }
    
    public RequerenteM buscaNome2(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        RequerenteM requerente = new RequerenteM();
        String name = "%"+Nome+"%";
        sql = "select id, nome, endereco, cidade, telefone, email, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + "ra, rg, cpf, curso, bloqueio, senha from Requerente where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            requerente = new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("nascimento"),
                            rs.getString("ra"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("curso"),
                            rs.getBoolean("bloqueio"),
                            rs.getString("senha"));
        }

        pst.close();
        return requerente;
    }
}
