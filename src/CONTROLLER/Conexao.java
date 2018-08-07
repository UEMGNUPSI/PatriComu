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
        // Altere o usuário e senha de acordo com o banco de dados instalado
        usuario = "root";               //for Workbench Notebook
        //usuario = "energysom";         //for Xampp Nupsi
        //usuario = "energysom2";       //for Xampp Loja
        senha = "root";
        
        // Defina aqui o nome do seu banco de dados
        url = "jdbc:mysql://localhost:3306/energysom";     //for Workbench Notebook
        //url = "jdbc:mysql://192.168.100.5/energysom";     //for Xampp Loja
        //url = "jdbc:mysql://192.168.0.107/energysom";      //for Xampp NUPSI
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com o Banco de Dados!\nSolicite seu Programador!");
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
