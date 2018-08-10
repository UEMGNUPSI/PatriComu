package VIEW;

import CONTROLLER.UsuarioDao;
import MODEL.UsuarioM;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.LimiteDigitos;

/**
 *
 * @author Danilo-NOTE
 */
public class UsuarioView extends javax.swing.JInternalFrame {

    /**
     * Creates new form Funcionario_DO_CU_QUENTE
     */
    
    UsuarioM usuario = new UsuarioM();
    UsuarioDao usuariodao = new UsuarioDao();
    List<UsuarioM> listaUsuario = new ArrayList<>();
    
    public UsuarioView() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaUsuario();
        jTabbedPane1.setUI(new  BasicTabbedPaneUI());
        tblFuncionario.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblFuncionario.getTableHeader().setReorderingAllowed(false);
        txtId.setVisible(false);
        prepararSalvareCancelar();
        desativarCampos();
        
        /*txtNome.setDocument(new LimiteDigitos(45));
        txtRg.setDocument(new LimiteDigitos(20));
        txtLogin.setDocument(new LimiteDigitos(45));
        txtsenha.setDocument(new LimiteDigitos(45));
        txtsenha2.setDocument(new LimiteDigitos(45));*/
        btnAlterar.setUI(new BasicButtonUI());
        btnCancelar.setUI(new BasicButtonUI());
        btnExcluir.setUI(new BasicButtonUI());
        btnNovo.setUI(new BasicButtonUI());
        btnSalvar.setUI(new BasicButtonUI());
    }

    //Atualiza todos os usuario para a tabela
    public void atualizaTabelaUsuario(){
        usuario = new UsuarioM();
        try {
            listaUsuario = usuariodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaUsuario.size()][5];
            int i = 0;
            for (UsuarioM funcionario1 : listaUsuario) {
                dados[i][0] = String.valueOf(funcionario1.getId());
                dados[i][1] = funcionario1.getNome();
                dados[i][2] = funcionario1.getNascimento();
                dados[i][3] = funcionario1.getTelefone();
                dados[i][4] = funcionario1.getCelular();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Nascimento","Telefone", "Celular"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblFuncionario.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionario.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionario.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionario.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionario.setRowHeight(35);
            tblFuncionario.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaFuncionarioBusca(){
        usuario = new UsuarioM();
        
        String dados[][] = new String[listaUsuario.size()][5];
            int i = 0;
            for (UsuarioM funcionario1 : listaUsuario) {
                dados[i][0] = String.valueOf(funcionario1.getId());
                dados[i][1] = funcionario1.getNome();
                dados[i][2] = funcionario1.getNascimento();
                dados[i][3] = funcionario1.getTelefone();
                dados[i][4] = funcionario1.getCelular();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Nascimento","Telefone", "Celular"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblFuncionario.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionario.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionario.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionario.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionario.setRowHeight(35);
            tblFuncionario.updateUI();
    }
    
    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCamposFuncionario(){
        txtId.setText("");
        txtNome.setText("");
        txtRg.setValue("");
        txtCpf.setValue("");
        txtLogin.setText("");
        txtsenha.setText("");
        txtsenha2.setText("");
        txtTelefone.setValue("");
        txtCelular1.setValue("");
        txtNascimento.setValue("");
    }
   
    public void ativarCampos(){
        txtId.setEnabled(true);
        txtNome.setEnabled(true);
        txtRg.setEnabled(true);
        txtCpf.setEnabled(true);
        txtLogin.setEnabled(true);
        txtsenha.setEnabled(true);
        txtsenha2.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtCelular1.setEnabled(true);
        txtNascimento.setEnabled(true);
    }

    public void desativarCampos(){
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtId.setEnabled(false);
        txtNome.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtLogin.setEnabled(false);
        txtsenha.setEnabled(false);
        txtsenha2.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular1.setEnabled(false);
        txtNascimento.setEnabled(false);

    }
   
    public void prepararNovo() {
       btnNovo.setEnabled(false);
       btnSalvar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblFuncionario.setEnabled(false);
       tblFuncionario.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       btnAlterar.setEnabled(true);
       btnExcluir.setEnabled(true);
       btnSalvar.setEnabled(false);
       btnCancelar.setEnabled(false);
       tblFuncionario.setEnabled(true);
    }
   
    public void prepararSelecaoTabela(){
       btnNovo.setEnabled(true);
       btnExcluir.setEnabled(true);
       btnAlterar.setEnabled(true);
    }
   
    public void prepararAlterar(){
       btnNovo.setEnabled(false);
       btnExcluir.setEnabled(false);
       btnAlterar.setEnabled(false);
       btnSalvar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblFuncionario.setEnabled(false);
       tblFuncionario.clearSelection();
    }
   
    public void prepararExcluir(){
       btnExcluir.setEnabled(false);
       btnAlterar.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        txtNascimento = new javax.swing.JFormattedTextField();
        txtRg = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCelular1 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtsenha = new javax.swing.JPasswordField();
        txtsenha2 = new javax.swing.JPasswordField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setClosable(true);
        setMaximumSize(new java.awt.Dimension(1919, 800));
        setMinimumSize(new java.awt.Dimension(1919, 800));
        setPreferredSize(new java.awt.Dimension(1919, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblFuncionario.setBackground(new java.awt.Color(248, 248, 248));
        tblFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblFuncionario.setFont(new java.awt.Font("Champagne & Limousines", 1, 12)); // NOI18N
        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Telefone", "Nascimento", "Cidade"
            }
        ));
        tblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblFuncionario);
        if (tblFuncionario.getColumnModel().getColumnCount() > 0) {
            tblFuncionario.getColumnModel().getColumn(0).setMinWidth(20);
        }

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBusca.setBackground(new java.awt.Color(245, 245, 245));
        txtBusca.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel13.setText("Nome:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel1.setText("Nome Completo:");

        txtNome.setBackground(new java.awt.Color(245, 245, 245));
        txtNome.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel8.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel8.setText("Data de Nascimento:");

        jLabel10.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel10.setText("CPF:");

        jLabel11.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel11.setText("RG:");

        txtCpf.setBackground(new java.awt.Color(245, 245, 245));
        txtCpf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtNascimento.setBackground(new java.awt.Color(245, 245, 245));
        txtNascimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNascimento.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtRg.setBackground(new java.awt.Color(245, 245, 245));
        txtRg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtRg.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtTelefone.setBackground(new java.awt.Color(245, 245, 245));
        txtTelefone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel6.setText("Telefone:");

        jLabel7.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel7.setText("Celular:");

        txtCelular1.setBackground(new java.awt.Color(245, 245, 245));
        txtCelular1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtCelular1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txtTelefone))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCelular1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txtRg)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNascimento)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acesso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel3.setText("Senha:");

        jLabel2.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel2.setText("Login:");

        jLabel4.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel4.setText("Repetir senha:");

        txtLogin.setBackground(new java.awt.Color(245, 245, 245));
        txtLogin.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        txtsenha.setBackground(new java.awt.Color(245, 245, 245));
        txtsenha.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtsenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        txtsenha2.setBackground(new java.awt.Color(245, 245, 245));
        txtsenha2.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtsenha2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtLogin)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(txtsenha, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtsenha2))
                        .addGap(0, 229, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsenha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Administradores", jPanel6);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Administradores");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        usuario = new UsuarioM();
        if(txtNome.getText().isEmpty() || txtNascimento.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha o Nome e Data de Nascimento!", "erro", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocusInWindow();       
        } else if(txtsenha.getText().equals(txtsenha2.getText())){
            if(txtId.getText().isEmpty()){
            //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            usuario.setNome(txtNome.getText());
            usuario.setCpf(txtCpf.getText());
            usuario.setRg(txtRg.getText());
            usuario.setNascimento(txtNascimento.getText());
            usuario.setTelefone(txtTelefone.getText());
            usuario.setCelular(txtCelular1.getText());
            usuario.setLogin(txtLogin.getText());
            usuario.setSenha(txtsenha.getText());
            try{
                usuariodao.salvar(usuario);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaUsuario();
            prepararSalvareCancelar();
            desativarCampos();
            limparCamposFuncionario();
        }
        else{
            //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
            usuario.setId(Integer.valueOf(txtId.getText()));
            usuario.setNome(txtNome.getText());
            usuario.setCpf(txtCpf.getText());
            usuario.setRg(txtRg.getText());
            usuario.setNascimento(txtNascimento.getText());
            usuario.setTelefone(txtTelefone.getText());
            usuario.setCelular(txtCelular1.getText());
            usuario.setLogin(txtLogin.getText());
            usuario.setSenha(txtsenha.getText());
        try{
            usuariodao.alterar(usuario);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);       
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaUsuario();
        prepararSalvareCancelar();
        desativarCampos();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Senhas diferentes!", "erro", JOptionPane.WARNING_MESSAGE);
            txtsenha.setText("");
            txtsenha2.setText("");
            txtsenha.requestFocusInWindow(); 
        }
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    limparCamposFuncionario();
    prepararSalvareCancelar();
    desativarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
    prepararAlterar();
    ativarCampos();
    jTabbedPane1.setSelectedIndex(1);
    txtNome.requestFocusInWindow(); 
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um Cliente", "erro", JOptionPane.WARNING_MESSAGE);
        }
        else{
            usuario.setId(Integer.parseInt(txtId.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ txtNome.getText());
            if(confirma ==0){
                try{
                    usuariodao.excluir(usuario);
                    limparCamposFuncionario();
                    txtNome.requestFocusInWindow();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
                }
                atualizaTabelaUsuario();
                prepararExcluir();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
        limparCamposFuncionario();
        usuario = new UsuarioM();

        txtId.setText(tblFuncionario.getValueAt(tblFuncionario.getSelectedRow(),0).toString());
        String integer = txtId.getText();
        int id = Integer.parseInt(integer);
        usuario.setId(id);
        
        try{
            usuario = usuariodao.busca(id);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
   
        tblFuncionario.getTableHeader().setReorderingAllowed(false);
        txtId.setText(Integer.toString(usuario.getId()));
        txtNome.setText(usuario.getNome());
        txtCpf.setText(usuario.getCpf());
        txtRg.setText(usuario.getRg());
        txtNascimento.setText(usuario.getNascimento());
        txtTelefone.setText(usuario.getTelefone());
        txtCelular1.setText(usuario.getCelular());
        txtLogin.setText(usuario.getLogin());
        txtsenha.setText(usuario.getSenha());
        txtsenha2.setText(usuario.getSenha());

        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_tblFuncionarioMouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCamposFuncionario();
        prepararNovo();
        ativarCampos();
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtNome.requestFocusInWindow();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        listaUsuario = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaUsuario();
        }else{
                    
            try {
                listaUsuario = usuariodao.buscaNomeLista(txtBusca.getText());

                if(listaUsuario == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaUsuario();
                }else{
                    atualizaTabelaFuncionarioBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JFormattedTextField txtCelular1;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JFormattedTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JPasswordField txtsenha;
    private javax.swing.JPasswordField txtsenha2;
    // End of variables declaration//GEN-END:variables
}
