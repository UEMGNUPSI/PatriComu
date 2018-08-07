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
    
    public void salvar (RequerenteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Cliente set id = ?, nome = ?, cpf = ?, rg = ?, rua = ?, numero = ?, bairro = ?,"
                + " cidade = ?, telefone = ?, celular1 = ?, celular2 = ?,"
                + " Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), observacao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, Cliente.getNome());
        pst.setString(3, Cliente.getCpf());
        pst.setString(4, Cliente.getRg());
        pst.setString(5, Cliente.getRua());
        pst.setString(6, Cliente.getNumero());
        pst.setString(7, Cliente.getBairro()); 
        pst.setString(8, Cliente.getCidade());
        pst.setString(9, Cliente.getTelefone());
        pst.setString(10, Cliente.getCelular1());
        pst.setString(11, Cliente.getCelular2());
        pst.setString(12, Cliente.getNascimento());
        pst.setString(13, Cliente.getObservacao());         
        pst.execute();
        pst.close();
    }
    
    public void excluir(RequerenteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Cliente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, Cliente.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(RequerenteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Cliente set "
                        + "nome = ?, "
                        + "cpf = ?, "
                        + "rg = ?, "
                        + "rua = ?, "
                        + "numero = ?, "
                        + "bairro  = ?, "
                        + "cidade  = ?, "
                        + "telefone  = ?, "
                        + "celular1  = ?, "
                        + "celular2  = ?, "
                        + "Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "observacao  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, Cliente.getNome());
        pst.setString(2, Cliente.getCpf());
        pst.setString(3, Cliente.getRg());
        pst.setString(4, Cliente.getRua());
        pst.setString(5, Cliente.getNumero());
        pst.setString(6, Cliente.getBairro()); 
        pst.setString(7, Cliente.getCidade());
        pst.setString(8, Cliente.getTelefone());
        pst.setString(9, Cliente.getCelular1());
        pst.setString(10, Cliente.getCelular2());
        pst.setString(11, Cliente.getNascimento());
        pst.setString(12, Cliente.getObservacao());
        pst.setInt(13,Cliente.getId());
        pst.execute();
        pst.close();
     }
    
    public List<RequerenteM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<RequerenteM> listaCliente = new ArrayList<>();
        sql = "select id, nome,cpf, rg, rua, numero, bairro, cidade, telefone, celular1,celular2,"
                + " DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, observacao from Cliente order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaCliente.add(new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao")));
        }
        pst.close();
        return listaCliente;
    }
    
    public RequerenteM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        RequerenteM cliente = null;        
        sql = "select id, nome,cpf, rg, rua, numero, bairro, cidade, telefone, celular1,celular2,"
                + " DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, observacao from Cliente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            cliente = new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao"));
        }
        pst.close();
        return cliente;
    }
    
    public List<RequerenteM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<RequerenteM> listaCliente = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select id, nome,cpf, rg, rua, numero, bairro, cidade, telefone, celular1,celular2,"
                + " DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, observacao from Cliente where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaCliente.add(new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao")));
        }

        pst.close();
        return listaCliente;
    }
    
    public RequerenteM buscaNome2(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        RequerenteM cliente = new RequerenteM();
        String name = "%"+Nome+"%";
        sql = "select id, nome,cpf, rg, rua, numero, bairro, cidade, telefone, celular1,celular2,"
                + " DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, observacao from Cliente where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            cliente = new RequerenteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao"));
        }

        pst.close();
        return cliente;
    }
}
