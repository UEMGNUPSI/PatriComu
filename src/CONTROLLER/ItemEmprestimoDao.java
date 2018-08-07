package CONTROLLER;

import MODEL.ItenEmprestimoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class ItemEmprestimoDao {

    RequerenteDao clientedao = new RequerenteDao();
    PatrimonioDao produtodao = new PatrimonioDao();
    EmprestimoDao vendadao = new EmprestimoDao();
    
    public List<ItenEmprestimoM> busca(int idvenda) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ItenEmprestimoM> listaitens = new ArrayList<>();
        sql = "select id, idvenda, idproduto, quantidade, preco, total, excluido from itemvenda where idvenda = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, idvenda);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaitens.add(new ItenEmprestimoM(
            rs.getInt("id"),
            vendadao.busca(rs.getInt("IdVenda")),
            produtodao.busca(rs.getInt("IdProduto")),
            rs.getInt("quantidade"),
            rs.getFloat("preco"),
            rs.getFloat("total"),
            rs.getBoolean("excluido")));
        }
        pst.close();
        return listaitens;
    }
    
    public void alterarItemVendaTrue(ItenEmprestimoM iten) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update ItemVenda set "
                        + "excluido  = ? "

                        + "where idvenda = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setBoolean(1, iten.getExcluido());
        pst.setInt(2,iten.getIdVenda().getId());
        pst.execute();
        pst.close();
    }
}
