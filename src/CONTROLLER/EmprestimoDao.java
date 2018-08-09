package CONTROLLER;

import MODEL.RequerenteM;
import MODEL.EmprestimoM;
import MODEL.ItenEmprestimoM;
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
public class EmprestimoDao {
    
    PreparedStatement pst;
    String sql;
    RequerenteDao requerentedao = new RequerenteDao();
    PatrimonioDao patrimoniodao = new PatrimonioDao();
    UsuarioDao usuariodao = new UsuarioDao();
    ItemEmprestimoDao itememprestimodao = new ItemEmprestimoDao();
        
    public void salvar (EmprestimoM emp,List<ItenEmprestimoM> item ) throws SQLException{

        int idEmp = 0;
        sql = "insert into emprestimo set id = ?, IdRequerente = ?, IdUsuario = ?, Professor = ?, Descricao = ?, Hora = ?,"
                + " DataEmprestimo = STR_TO_DATE( ?, \"%d/%m/%Y\" ), DataPrevista = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + " DataDevolucao = STR_TO_DATE( ?, \"%d/%m/%Y\" )";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1,0);
        pst.setInt(2, emp.getIdRequerente().getId());
        pst.setInt(3, emp.getIdUsuario().getId());
        pst.setString(4, emp.getProfessor());
        pst.setString(5, emp.getDescricao());
        pst.setString(6, emp.getHora());
        pst.setString(7, emp.getDataEmprestimo());
        pst.setString(8, emp.getDataPrevista());
        pst.setString(9, emp.getDataDevolucao());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()) {
            idEmp = rs.getInt(1);
        }
        pst.close();
        salvarItensEmprestimo((List<ItenEmprestimoM>) item, idEmp);
        
    }
    
    public void salvarItensEmprestimo (List<ItenEmprestimoM> item, int idEmp) throws SQLException{
        for(ItenEmprestimoM itens : item){
            
            sql = "insert into itememprestimo values(?,?,?,?,?,?)";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1,0);
            pst.setInt(2, itens.getIdEmprestimo().getId());
            pst.setInt(3, itens.getIdPatrimonio().getId());
            pst.setString(4, itens.getQualidade());
            pst.setInt(5, itens.getQuantidade());      
            pst.setBoolean(6, itens.getDevolvido());
            pst.execute();
            pst.close();
            //buscaquantidade(itens.getIdPatrimonio().getId(), itens.getQuantidade());
        }
    }
    
    /*public void buscaquantidade(int id, float quantidade) throws SQLException{
        PatrimonioM pat = null;
        int count = 0;          // Contador para saber quantos patrimonios estão disponíveis
        sql = "select * from patrimonio where id = ? and ocupado = 0";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            pat = new PatrimonioM(
                            rs.getInt("id"),
                            itememprestimodao.busca(rs.getInt("idcategoria")),
                            patrimoniodao.busca(rs.getInt("idmarca")),
                            rs.getString("Nome"),
                            rs.getString("Numero"),
                            rs.getString("Qualidade"),
                            rs.getBoolean("Ocupado"));
            count++;
        }
        
        pst.close();
        if(count >= quantidade){
            atualizaOcupado(pat.getId());
        }
        else{
            // Não possuí itens suficiente (FAZER TRATATIVA)
        }
        
    }
    */
    
    public void atualizaOcupado(int id)throws SQLException{
        sql = "update patrimonio set "
                        + "ocupado  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 1);
        pst.setInt(2, id);
        pst.execute();
        pst.close();
    }
   
    public void alterarVendaTrue(EmprestimoM venda) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Emprestimo set "
                        + "datadevolucao  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, venda.getDataDevolucao());
        pst.setInt(2,venda.getId());
        pst.execute();
        pst.close();
    }
    
    public void excluir(EmprestimoM venda) throws SQLException{
        sql = "delete from emprestimo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, venda.getId());
        pst.execute();
        excluirItem(venda);
        pst.close();
    }
    public void excluirItem(EmprestimoM venda) throws SQLException{
        sql = "delete from ItemEmprestimo where IdEmprestimo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, venda.getId());
        pst.execute();
        pst.close();
    }
      
    
    
    public List<EmprestimoM> listaTodos() throws SQLException{
        List<EmprestimoM> listavenda = new ArrayList<>();
        sql = "select id, IdRequerente, IdUsuario, Professor, Descricao, Hora, DATE_FORMAT( dataemprestimo, \"%d/%m/%Y\" ) AS DataEmprestimo, DATE_FORMAT( datapresvista, \"%d/%m/%Y\" ) AS DataPrevista,"
                + " DATE_FORMAT( datadevolucao, \"%d/%m/%Y\" ) AS DataDevolucao from Emprestimo ORDER BY id DESC";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            listavenda.add(new EmprestimoM(
                        rs.getInt("id"),
                        requerentedao.busca(rs.getInt("idRequerente")),
                        usuariodao.busca(rs.getInt("idUsuario")),
                        rs.getString("professor"),
                        rs.getString("descricao"),
                        rs.getString("hora"),
                        rs.getString("dataemprestimo"),
                        rs.getString("dataprevista"),
                        rs.getString("datadevolucao")));
        }
        pst.close();
    return listavenda;
    }
    
    public EmprestimoM busca(int id) throws SQLException{
        EmprestimoM venda = null;
        sql = "select id, IdRequerente, IdUsuario, Professor, Descricao, Hora, DATE_FORMAT( dataemprestimo, \"%d/%m/%Y\" ) AS DataEmprestimo, DATE_FORMAT( datapresvista, \"%d/%m/%Y\" ) AS DataPrevista,"
                + " DATE_FORMAT( datadevolucao, \"%d/%m/%Y\" ) AS DataDevolucao from Venda where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new EmprestimoM(
                        rs.getInt("id"),
                        requerentedao.busca(rs.getInt("idRequerente")),
                        usuariodao.busca(rs.getInt("idUsuario")),
                        rs.getString("professor"),
                        rs.getString("descricao"),
                        rs.getString("hora"),
                        rs.getString("dataemprestimo"),
                        rs.getString("dataprevista"),
                        rs.getString("datadevolucao"));
        }
        pst.close();
        return venda;
    }
    
    public List<EmprestimoM> buscaNomeLista(int Nome) throws SQLException{
        List<EmprestimoM> listavenda = new ArrayList<>();
        //String name = "%"+Nome+"%";
        sql = "select id, IdRequerente, IdUsuario, Professor, Descricao, Hora, DATE_FORMAT( dataemprestimo, \"%d/%m/%Y\" ) AS DataEmprestimo, DATE_FORMAT( datapresvista, \"%d/%m/%Y\" ) AS DataPrevista,"
                + " DATE_FORMAT( datadevolucao, \"%d/%m/%Y\" ) AS DataDevolucao from Venda where idcliente like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, Nome);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new EmprestimoM(
                        rs.getInt("id"),
                        requerentedao.busca(rs.getInt("idRequerente")),
                        usuariodao.busca(rs.getInt("idUsuario")),
                        rs.getString("professor"),
                        rs.getString("descricao"),
                        rs.getString("hora"),
                        rs.getString("dataemprestimo"),
                        rs.getString("dataprevista"),
                        rs.getString("datadevolucao")));
        }

        pst.close();
        return listavenda;
    }
    
    /*public List<EmprestimoM> buscaDataLista(String de, String ate) throws SQLException{
        List<EmprestimoM> listavenda = new ArrayList<>();
        //String name = "%"+Nome+"%";
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda where Data >= STR_TO_DATE( ?, \"%d/%m/%Y\" ) and Data <= STR_TO_DATE( ?, \"%d/%m/%Y\" ) ";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new EmprestimoM(
            rs.getInt("id"),
            requerentedao.busca(rs.getInt("idcliente")),
            usuariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }

        pst.close();
        return listavenda;
    }*/
    
    /*public List<EmprestimoM> buscaClienteLista(String nome) throws SQLException{
        List<EmprestimoM> listavenda = new ArrayList<>();
        String name = "%"+nome+"%";
        sql = "select venda.id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda, Cliente where Cliente.id = Venda.IdCliente and Cliente.Nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new EmprestimoM(
            rs.getInt("id"),
            requerentedao.busca(rs.getInt("idcliente")),
            usuariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }

        pst.close();
        return listavenda;
    }*/
    
    
}
