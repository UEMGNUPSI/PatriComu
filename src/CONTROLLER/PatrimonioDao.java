package CONTROLLER;

import MODEL.PatrimonioM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class PatrimonioDao {
    CategoriaDao categoriaDao = new CategoriaDao();
    ModeloDao modeloDao = new ModeloDao();
    MarcaDao marcaDao = new MarcaDao();
    public void salvar (PatrimonioM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Produto values (?,?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setInt(2, produto.getIdCategoria().getId());
        pst.setInt(3, produto.getIdMarca().getId());
        pst.setInt(4, produto.getIdModelo().getId());
        pst.setString(5, produto.getNome());
        pst.setFloat(6, produto.getValorCusto());
        pst.setFloat(7, produto.getValorMax());
        pst.setFloat(8, produto.getValorMini());
        pst.setString(9, produto.getCodigo());
        pst.setInt(10, produto.getQuantidade()); 
        pst.execute();
        pst.close();
    }
    
    public void excluir(PatrimonioM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Produto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, produto.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(PatrimonioM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Produto set "
                        + "idcategoria = ?, "
                        + "idmarca = ?, "
                        + "idmodelo = ?, "
                        + "nome = ?, "
                        + "valorcusto = ?, "
                        + "valormax = ?, "
                        + "valormini = ?, "
                        + "codigo  = ?, "
                        + "quantidade  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, produto.getIdCategoria().getId());
        pst.setInt(2, produto.getIdMarca().getId());
        pst.setInt(3, produto.getIdModelo().getId());
        pst.setString(4, produto.getNome());
        pst.setDouble(5, produto.getValorCusto());
        pst.setDouble(6, produto.getValorMax()); 
        pst.setDouble(7, produto.getValorMini());
        pst.setString(8, produto.getCodigo());
        pst.setInt(9, produto.getQuantidade());
        pst.setInt(10,produto.getId());
        pst.execute();
        pst.close();
     }
    
    public void alterarQuantidade(PatrimonioM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Produto set "
                        + "quantidade  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, produto.getQuantidade());
        pst.setInt(2,produto.getId());
        pst.execute();
        pst.close();
     }
    
    public List<PatrimonioM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<PatrimonioM> listaProduto = new ArrayList<>();
        sql = "select * from Produto order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaProduto.add(new PatrimonioM(
                            rs.getInt("id"),
                            categoriaDao.busca(rs.getInt("idcategoria")),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade")));
        }
        pst.close();
        return listaProduto;
    }
    
    public PatrimonioM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM produto = null;        
        sql = "select * from Produto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            produto = new PatrimonioM(
                            rs.getInt("id"),
                            categoriaDao.busca(rs.getInt("idcategoria")),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade"));
        }
        pst.close();
        return produto;
    }
    
    public List<PatrimonioM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<PatrimonioM> listaProduto = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Produto where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaProduto.add(new PatrimonioM(
                            rs.getInt("id"),
                            categoriaDao.busca(rs.getInt("idcategoria")),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade")));
        }

        pst.close();
        return listaProduto;
    }
    
    public boolean buscacodigo(String codigo) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM produto = null;
        int i = 0;
        sql = "select * from Produto where codigo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            produto = new PatrimonioM(
                            rs.getInt("id"),
                            categoriaDao.busca(rs.getInt("idcategoria")),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade"));
            i++;
        }
        pst.close();
        return !( i <= 0);
    }
    
    public PatrimonioM buscacodigo2(String codigo) throws SQLException{
        PreparedStatement pst;
        String sql;
        PatrimonioM produto = null;
        int i = 0;
        sql = "select * from Produto where codigo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            produto = new PatrimonioM(
                            rs.getInt("id"),
                            categoriaDao.busca(rs.getInt("idcategoria")),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade"));
            i++;
        }
        pst.close();
            
            return produto;
        
    }
    
}
