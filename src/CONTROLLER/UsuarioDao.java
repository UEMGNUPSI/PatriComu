package CONTROLLER;

import MODEL.UsuarioM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class UsuarioDao {
    
    public void salvar (UsuarioM usuario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Usuario set id = ?, nome = ?, cpf = ?, rg = ?, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + " telefone = ?, celular = ?, login = ?, senha = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, usuario.getNome());
        pst.setString(3, usuario.getCpf());
        pst.setString(4, usuario.getRg());
        pst.setString(5, usuario.getNascimento());
        pst.setString(6, usuario.getTelefone());
        pst.setString(7, usuario.getCelular());
        pst.setString(8, usuario.getLogin());
        pst.setString(9, usuario.getSenha());     
        pst.execute();
        pst.close();
    }
    
    public void excluir(UsuarioM usuario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Usuario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, usuario.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(UsuarioM usuario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Usuario set "
                        + "nome = ?, "
                        + "cpf = ?, "
                        + "rg = ?, "
                        + "Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "telefone = ?, "
                        + "celular = ?, "
                        + "login = ?, "
                        + "senha = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, usuario.getNome());
        pst.setString(2, usuario.getCpf());
        pst.setString(3, usuario.getRg());
        pst.setString(4, usuario.getNascimento());
        pst.setString(5, usuario.getTelefone());
        pst.setString(6, usuario.getCelular());
        pst.setString(7, usuario.getLogin());
        pst.setString(8, usuario.getSenha());
        pst.setInt(9, usuario.getId());
        pst.execute();
        pst.close();
     }
    
    public List<UsuarioM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<UsuarioM> listaUsuario = new ArrayList<>();
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, login, senha from Usuario order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaUsuario.add(new UsuarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular"),
                            rs.getString("login"),
                            rs.getString("senha")));
        }
        pst.close();
        return listaUsuario;
    }
    
    public UsuarioM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM usuario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Usuario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            usuario = new UsuarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular"),
                            rs.getString("login"),
                            rs.getString("senha"));
        }
        pst.close();
        return usuario;
    }
    
    public UsuarioM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM usuario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Usuario where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            usuario = new UsuarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular"),
                            rs.getString("login"),
                            rs.getString("senha"));
        }
        pst.close();
        return usuario;
    }
    
    public List<UsuarioM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<UsuarioM> listaUsuario = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select id,nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Usuario where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaUsuario.add(new UsuarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular"),
                            rs.getString("login"),
                            rs.getString("senha")));
        }
        pst.close();
        return listaUsuario;
    }
    
    public UsuarioM valida(String user, String senha) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM usuario = null;
           sql = "select * from Usuario where login = ? and senha = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, user);
           pst.setString(2, senha);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               usuario = new UsuarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular"),
                            rs.getString("login"),
                            rs.getString("senha"));
            }
            pst.close();
            return usuario;
    }
}
