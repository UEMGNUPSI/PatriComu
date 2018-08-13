package CONTROLLER;

import MODEL.ItenEmprestimoM;
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
public class ItemEmprestimoDao {

    RequerenteDao requerentedao = new RequerenteDao();
    PatrimonioDao patrimoniodao = new PatrimonioDao();
    EmprestimoDao emprestimodao = new EmprestimoDao();
    
    public List<ItenEmprestimoM> busca(int idvenda) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ItenEmprestimoM> listaitens = new ArrayList<>();
        sql = "select id, idEmprestimo, idPatrimonio, qualidade, quantidade, devolvido from ItemEmprestimo where idEmprestimo = ?";
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
    
    public ItenEmprestimoM buscapatrimonio(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        ItenEmprestimoM itememprestimo = null;        
        sql = "select id, idemprestimo, idpatrimonio, qualidade, quantidade, devolvido from itememprestimo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            itememprestimo = new ItenEmprestimoM(
                            rs.getInt("id"),
                            emprestimodao.busca(rs.getInt("idemprestimo")),
                            patrimoniodao.busca(rs.getInt("idpatrimonio")),
                            rs.getString("qualidade"),
                            rs.getInt("quantidade"),
                            rs.getBoolean("devolvido"));
        }
        pst.close();
        return itememprestimo;
    }
    
    public void alterarItemVendaTrue(ItenEmprestimoM iten, int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update ItemEmprestimo set devolvido  = ? where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setBoolean(1, iten.getDevolvido());
        pst.setInt(2,   id);
        pst.execute();
        pst.close();
        int aux = iten.getIdPatrimonio().getId();
        atualizaDisponivel(aux);
    }
    
    public void atualizaDisponivel(int id)throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update patrimonio set "
                        + "Ocupado  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setInt(2, id);
        pst.execute();
        pst.close();
    }
}
