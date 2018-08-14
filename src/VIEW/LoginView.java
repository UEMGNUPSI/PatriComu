package VIEW;

import CONTROLLER.LogDAO;
import CONTROLLER.UsuarioDao;
import MODEL.UsuarioM;
import MODEL.LogM;
import MODEL.RequerenteM;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import util.LimiteDigitos;

/**
 *
 * @author Danilo-NOTE
 */
public class LoginView extends javax.swing.JFrame {

    UsuarioM usuario = new UsuarioM();
    UsuarioDao usuariodao = new UsuarioDao();
    
    RequerenteM requerente = new RequerenteM();
    //Log
    LogM log = new LogM();
    LogDAO logdao = new LogDAO();
    UsuarioM usulog = new UsuarioM();

    public LoginView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        lblErro.setVisible(false);
        txtUser.setDocument(new LimiteDigitos(20));
        txtSenha.setDocument(new LimiteDigitos(20));
        //txtUser.setText("root");
        //txtSenha.setText("root");
        
        btnLogin.setUI(new BasicButtonUI());

        //URL url = this.getClass().getResource("Icones Icon/icone.png");
        //Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        //this.setIconImage(imagemTitulo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblversão = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Senha.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 550, -1, -1));

        txtSenha.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(32, 33, 41));
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setBorder(null);
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        jPanel1.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 550, 137, 32));

        jSeparator3.setForeground(new java.awt.Color(40, 50, 89));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 202, 10));

        jSeparator2.setForeground(new java.awt.Color(40, 50, 89));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 202, 10));

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(34, 38, 53));
        btnLogin.setText("ENTRAR");
        btnLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 650, 110, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Login.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, -1, -1));

        txtUser.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtUser.setForeground(new java.awt.Color(32, 33, 41));
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setBorder(null);
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 550, 137, 30));

        lblversão.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblversão.setForeground(new java.awt.Color(32, 33, 41));
        lblversão.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblversão.setText("Patrimônio Comunicação 0.8 Beta");
        jPanel1.add(lblversão, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, -1));

        lblErro.setFont(new java.awt.Font("Segoe UI Light", 1, 22)); // NOI18N
        lblErro.setForeground(new java.awt.Color(199, 65, 65));
        lblErro.setText("Senha ou usuário incorretos!");
        jPanel1.add(lblErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
        });
        jPanel1.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(88, 145, 193));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Patrimonio Comunicação");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1370, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        usuario = null;
        try {
            if (txtUser.getText().isEmpty()) {
                //erro.setText("O nome do usuario deve ser preechido");
                JOptionPane.showMessageDialog(null, "O usuário deve ser preenchido", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtUser.requestFocus();

            } else if (txtSenha.getText().isEmpty()) {
                //erro.setText("A senha deve ser preechido");
                //erro.setVisible(true);
                JOptionPane.showMessageDialog(null, "A senha deve ser preenchida", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtSenha.requestFocus();
            } else {
                usuario = usuariodao.valida(txtUser.getText(), txtSenha.getText());
                if(usuario == null){
                    lblErro.setVisible(true);
                    txtSenha.setText("");
                    txtSenha.requestFocus();
                }else{
       
                    PrincipalView3 principal = new PrincipalView3(usuario);
                    //Log
                    log.setUsuario(usuario);
                    log.setRequerente(requerente);            
                    log.setData(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date(System.currentTimeMillis())));
                    log.setHora(new SimpleDateFormat("HH:mm").format(new java.sql.Date(System.currentTimeMillis())));
                    log.setAcao("Entrando com Usuário: "+usuario.getNome());
                    logdao.salvarLog(log);
                    this.dispose();
                    
                }
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            txtUser.setText("");
            txtSenha.setText("");
            txtUser.requestFocus();
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        
        usuario = null;
        try {
            if (txtUser.getText().isEmpty()) {
                //erro.setText("O nome do usuario deve ser preechido");
                JOptionPane.showMessageDialog(null, "O usuário deve ser preenchido", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtUser.requestFocus();

            } else if (txtSenha.getText().isEmpty()) {
                //erro.setText("A senha deve ser preechido");
                //erro.setVisible(true);
                JOptionPane.showMessageDialog(null, "A senha deve ser preenchida", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtSenha.requestFocus();
            } else {
                usuario = usuariodao.valida(txtUser.getText(), txtSenha.getText());
                if(usuario == null){
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtSenha.setText("");
                    txtSenha.requestFocus();
                }else{
       
                    PrincipalView3 principal = new PrincipalView3(usuario);
                    //Log
                    log.setUsuario(usuario);
                    log.setRequerente(requerente);            
                    log.setData(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date(System.currentTimeMillis())));
                    log.setHora(new SimpleDateFormat("HH:mm").format(new java.sql.Date(System.currentTimeMillis())));
                    log.setAcao("Entrando com Usuário: "+usuario.getNome());
                    logdao.salvarLog(log);
                    this.dispose();
                    
                }
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            txtUser.setText("");
            txtSenha.setText("");
            txtUser.requestFocus();
            ex.printStackTrace();
            
        } 
      }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSenha.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
         //Set the Nimbus look and feel 
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        //If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        //For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblversão;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
