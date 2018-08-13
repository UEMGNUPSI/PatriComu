
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
        pst.setInt(2, log.getRequerente().getId());
        pst.setInt(3, log.getUsuario().getId());
        pst.setString(4, log.getData());
        pst.setString(5, log.getHora());
        pst.setString(6, log.getAcao());   
        pst.execute();
        pst.close();
  
    } 
    
}

