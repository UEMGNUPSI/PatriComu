
package CONTROLLER;

import MODEL.LogM;
import java.sql.PreparedStatement;
import java.sql.SQLException;


 public class LogDAO {
    
     public void salvarLog (LogM log) throws SQLException{
        PreparedStatement pst;
        String sql;

        sql = "insert into log values(?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);     
        pst.setString(2, log.getData());
        pst.setString(3, log.getHora());
        pst.setString(4, log.getAcao());
        pst.setString(5, log.getRequerente().getNome());
        pst.setString(6, log.getUsuario().getNome());
        pst.execute();
        pst.close();
  
    } 
    
}

