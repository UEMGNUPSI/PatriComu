package CONTROLLER;

import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexao implements Serializable {

    private static Conexao conexao = null;
    private static Connection connection;
    private String usuario;
    private String senha;
    private String url;

    private Conexao() {
        
        
        //usuario = "server";senha = "server";url = "jdbc:mysql://127.0.0.1:3306/patrimonio_comunicacao?autoReconnect=true&useSSL=false"; 
        

        
        usuario = "patricom";senha = "Uemg2018";url = "jdbc:mysql://10.93.10.10:3306/patrimonio_comunicacao?autoReconnect=true&useSSL=false";    
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com o Banco de Dados!\nEntre em contato com o NUPSI!");
            System.exit(0);
        }
    }

    public static Connection getInstance() {
        if (conexao == null) {
            synchronized (Conexao.class) {
                conexao = new Conexao();
            }
        }
        return connection;
    }

    public static void closeInstance() throws SQLException {
        if (conexao != null) {
            connection.close();
        }
    }

    public static void setAutoCommit(boolean vlr) throws SQLException {
        connection.setAutoCommit(vlr);
    }

    public static void commit() throws SQLException {
        connection.commit();
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }
}
