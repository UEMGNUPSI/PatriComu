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
    
    public void salvar (UsuarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Funcionario set id = ?, nome = ?, cpf = ?, rg = ?, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + " telefone = ?, celular = ?, login = ?, senha = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, funcionario.getNome());
        pst.setString(3, funcionario.getCpf());
        pst.setString(4, funcionario.getRg());
        pst.setString(5, funcionario.getNascimento());
        pst.setString(6, funcionario.getTelefone());
        pst.setString(7, funcionario.getCelular());
        pst.setString(8, funcionario.getLogin());
        pst.setString(9, funcionario.getSenha());     
        pst.execute();
        pst.close();
    }
    
    public void excluir(UsuarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Funcionario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, funcionario.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(UsuarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Funcionario set "
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
        pst.setString(1, funcionario.getNome());
        pst.setString(2, funcionario.getCpf());
        pst.setString(3, funcionario.getRg());
        pst.setString(4, funcionario.getNascimento());
        pst.setString(5, funcionario.getTelefone());
        pst.setString(6, funcionario.getCelular());
        pst.setString(7, funcionario.getLogin());
        pst.setString(8, funcionario.getSenha());
        pst.setInt(9, funcionario.getId());
        pst.execute();
        pst.close();
     }
    
    public List<UsuarioM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<UsuarioM> listaFuncionario = new ArrayList<>();
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, login, senha from Funcionario order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaFuncionario.add(new UsuarioM(
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
        return listaFuncionario;
    }
    
    public UsuarioM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM funcionario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Funcionario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            funcionario = new UsuarioM(
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
        return funcionario;
    }
    
    public UsuarioM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM funcionario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Funcionario where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            funcionario = new UsuarioM(
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
        return funcionario;
    }
    
    public List<UsuarioM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<UsuarioM> listaFuncionario = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select id,nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular, "
                + "login, senha from Funcionario where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaFuncionario.add(new UsuarioM(
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
        return listaFuncionario;
    }
    
    public UsuarioM valida(String user, String senha) throws SQLException{
        PreparedStatement pst;
        String sql;
        UsuarioM funcionario = null;
           sql = "select * from Funcionario where login = ? and senha = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, user);
           pst.setString(2, senha);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               funcionario = new UsuarioM(
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
            return funcionario;
    }
}
