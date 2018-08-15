/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  
 */
package VIEW;

import MODEL.UsuarioM;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Danilo-NOTE
 */
public class PrincipalView3 extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalView
     */
    UsuarioM usu = new UsuarioM();
    public PrincipalView3(UsuarioM usuario) {
        initComponents();
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);

        usu = usuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPatrimonio = new javax.swing.JLabel();
        btnAdministrador = new javax.swing.JLabel();
        btnEmprestimo = new javax.swing.JLabel();
        btnAluno = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnPatrimonio.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnPatrimonio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPatrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Estoque.png"))); // NOI18N
        btnPatrimonio.setText("Patrimônio");
        btnPatrimonio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPatrimonio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPatrimonio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPatrimonioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPatrimonioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPatrimonioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPatrimonioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPatrimonioMouseReleased(evt);
            }
        });

        btnAdministrador.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAdministrador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Funcionario.png"))); // NOI18N
        btnAdministrador.setText("Servidores");
        btnAdministrador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdministrador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdministrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdministradorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdministradorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdministradorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdministradorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdministradorMouseReleased(evt);
            }
        });

        btnEmprestimo.setBackground(new java.awt.Color(204, 0, 255));
        btnEmprestimo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnEmprestimo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Vendas.png"))); // NOI18N
        btnEmprestimo.setText("Emprestimo");
        btnEmprestimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmprestimo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmprestimoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEmprestimoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEmprestimoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEmprestimoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEmprestimoMouseReleased(evt);
            }
        });

        btnAluno.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Cliente.png"))); // NOI18N
        btnAluno.setText("Alunos");
        btnAluno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAluno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlunoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlunoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlunoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAlunoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAlunoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(btnAluno)
                .addGap(15, 15, 15)
                .addComponent(btnEmprestimo)
                .addGap(15, 15, 15)
                .addComponent(btnPatrimonio)
                .addGap(15, 15, 15)
                .addComponent(btnAdministrador)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdministrador, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPatrimonio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEmprestimo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAluno, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmprestimoMouseClicked
        EmprestimoView Emprestimo = new EmprestimoView(usu);
        ((BasicInternalFrameUI)Emprestimo.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Emprestimo);
            pnlPrincipal.updateUI();
            btnEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Vendas.png")));
            btnAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnPatrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
    }//GEN-LAST:event_btnEmprestimoMouseClicked

    private void btnPatrimonioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatrimonioMouseClicked
        PatrimonioView Patrimonio = new PatrimonioView(usu);
        ((BasicInternalFrameUI)Patrimonio.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Patrimonio);
            pnlPrincipal.updateUI();
            btnPatrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Estoque.png")));
            btnAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
            btnEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
    }//GEN-LAST:event_btnPatrimonioMouseClicked

    private void btnAdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdministradorMouseClicked
        UsuarioView Usuario = new UsuarioView(usu);
        ((BasicInternalFrameUI)Usuario.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Usuario);
            pnlPrincipal.updateUI();
            btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Funcionario.png")));
            btnAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnPatrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
    }//GEN-LAST:event_btnAdministradorMouseClicked

    private void btnPatrimonioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatrimonioMouseEntered
    btnPatrimonio.setForeground(Color.yellow);
    }//GEN-LAST:event_btnPatrimonioMouseEntered

    private void btnPatrimonioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatrimonioMouseExited
    btnPatrimonio.setForeground(Color.black);
    }//GEN-LAST:event_btnPatrimonioMouseExited

    private void btnPatrimonioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatrimonioMousePressed
    btnPatrimonio.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnPatrimonioMousePressed

    private void btnPatrimonioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatrimonioMouseReleased
    btnPatrimonio.setForeground(Color.yellow);
    }//GEN-LAST:event_btnPatrimonioMouseReleased

    private void btnAdministradorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdministradorMouseEntered
    btnAdministrador.setForeground(Color.yellow);
    }//GEN-LAST:event_btnAdministradorMouseEntered

    private void btnAdministradorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdministradorMouseExited
    btnAdministrador.setForeground(Color.black);
    }//GEN-LAST:event_btnAdministradorMouseExited

    private void btnAdministradorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdministradorMousePressed
    btnAdministrador.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnAdministradorMousePressed

    private void btnAdministradorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdministradorMouseReleased
    btnAdministrador.setForeground(Color.yellow);
    }//GEN-LAST:event_btnAdministradorMouseReleased

    private void btnEmprestimoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmprestimoMouseEntered
    btnEmprestimo.setForeground(Color.YELLOW);
    }//GEN-LAST:event_btnEmprestimoMouseEntered

    private void btnEmprestimoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmprestimoMouseExited
    btnEmprestimo.setForeground(Color.black);
    }//GEN-LAST:event_btnEmprestimoMouseExited

    private void btnEmprestimoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmprestimoMousePressed
    btnEmprestimo.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnEmprestimoMousePressed

    private void btnEmprestimoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmprestimoMouseReleased
    btnEmprestimo.setForeground(Color.yellow);
    }//GEN-LAST:event_btnEmprestimoMouseReleased

    private void btnAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlunoMouseClicked
        RequerenteView cliente = new RequerenteView(usu);
        ((BasicInternalFrameUI)cliente.getUI()).setNorthPane(null);
        pnlPrincipal.removeAll();
        pnlPrincipal.add(cliente);
        pnlPrincipal.updateUI();
        btnAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Cliente.png")));
        btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
        btnPatrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
        btnEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
    }//GEN-LAST:event_btnAlunoMouseClicked

    private void btnAlunoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlunoMouseEntered
    btnAluno.setForeground(Color.yellow);
    }//GEN-LAST:event_btnAlunoMouseEntered

    private void btnAlunoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlunoMouseExited
    btnAluno.setForeground(Color.black);
    }//GEN-LAST:event_btnAlunoMouseExited

    private void btnAlunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlunoMousePressed
    btnAluno.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnAlunoMousePressed

    private void btnAlunoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlunoMouseReleased
    btnAluno.setForeground(Color.yellow);
    }//GEN-LAST:event_btnAlunoMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdministrador;
    private javax.swing.JLabel btnAluno;
    private javax.swing.JLabel btnEmprestimo;
    private javax.swing.JLabel btnPatrimonio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
