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

    RequerenteDao requerentedao = new RequerenteDao();
    PatrimonioDao patrimoniodao = new PatrimonioDao();
    EmprestimoDao emprestimodao = new EmprestimoDao();
    
    public List<ItenEmprestimoM> busca(int idvenda) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ItenEmprestimoM> listaitens = new ArrayList<>();
        sql = "select id, idEmprestimo, idPatrimonio, qualidade, quantidade, devolvido from itemvenda where idvenda = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, idvenda);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
                    listaitens.add(new ItenEmprestimoM(
                    rs.getInt("id"),
                    emprestimodao.busca(rs.getInt("idEmprestimo")),
                    patrimoniodao.busca(rs.getInt("idPatrimonio")),
                    rs.getString("qualidade"),
                    rs.getInt("quantidade"),
                    rs.getBoolean("devolvido")));
        }
        pst.close();
        return listaitens;
    }
    
    public void alterarItemVendaTrue(ItenEmprestimoM iten) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update ItemEmprestimo set "
                        + "devolvido  = ? "

                        + "where idEmprestimo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setBoolean(1, iten.getDevolvido());
        pst.setInt(2,iten.getIdEmprestimo().getId());
        pst.execute();
        pst.close();
    }
}
