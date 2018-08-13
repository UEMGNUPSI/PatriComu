package VIEW;

import CONTROLLER.EmprestimoDao;
import CONTROLLER.UsuarioDao;
import CONTROLLER.ItemEmprestimoDao;
import CONTROLLER.RequerenteDao;
import CONTROLLER.PatrimonioDao;
import MODEL.*;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author Danilo-NOTE
 */
public class EmprestimoView extends javax.swing.JInternalFrame {

    /**
     * Creates new form 
     */
    //Compro ficha = new FichaM();
    Document doc;
    String caminho;
    
    PatrimonioM patrimonio = new PatrimonioM();
    PatrimonioDao patrimoniodao = new PatrimonioDao();
    List<PatrimonioM> listaPatrimonio = new ArrayList<>();
    
    RequerenteM requerente = new RequerenteM();
    RequerenteDao requerentedao = new RequerenteDao();
    List<RequerenteM> listaRequerente = new ArrayList<>();
    
    UsuarioM usuario = new UsuarioM();
    UsuarioDao usuariodao = new UsuarioDao();
    List<UsuarioM> listaUsuario = new ArrayList<>();
    
    EmprestimoM emprestimo = new EmprestimoM();
    EmprestimoDao emprestimodao = new EmprestimoDao();
    List<EmprestimoM> listaEmprestimo = new ArrayList<>();
    
    ItenEmprestimoM itemEmprestimo = new ItenEmprestimoM();
    ItemEmprestimoDao itemEmprestimodao  = new ItemEmprestimoDao();
    private List<ItenEmprestimoM> listaItemEmprestimo = new ArrayList<>();
  
    
    public List<ItenEmprestimoM> getListaItemVenda() {
        return listaItemEmprestimo;
    }

    public void setListaItemVenda(List<ItenEmprestimoM> listaItemVenda) {
        this.listaItemEmprestimo = listaItemVenda;
    }
    
    
    public EmprestimoView() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaEmprestimo();
        atualizaTabelaItemEmprestimo();
        jTabbedPane1.setUI(new BasicTabbedPaneUI());
        tblEmprestimo.getTableHeader().setFont(new java.awt.Font("Segoe UI", 0, 18));
        tblItenVenda.getTableHeader().setFont(new java.awt.Font("Segoe UI", 0, 18));
        tblClienteDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblClienteDialog.getTableHeader().setReorderingAllowed(false);
        tblFuncionarioDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblFuncionarioDialog.getTableHeader().setReorderingAllowed(false);
        tblItenVenda.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblItenVenda.getTableHeader().setReorderingAllowed(false);
        tblItensDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblItensDialog.getTableHeader().setReorderingAllowed(false);
        tblProdutoDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
        tblEmprestimo.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblEmprestimo.getTableHeader().setReorderingAllowed(false);
        
        
        txtDataAtual.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        txtIdEmprestimo.setVisible(false);
        txtIdProduto.setVisible(false);
        txtIDIten.setVisible(false);
        txtQuantidadeTotal.setVisible(false);
        txtIdUsuario.setVisible(false);
        txtIdRequerente.setVisible(false);
        txtQualidade.setVisible(false);
        txtIdItemDevolu.setVisible(false);
        
        RequerenteDialog.setSize(525, 490);
        PatrimonioDialog.setSize(535, 500);
        UsuarioDialog.setSize(525, 490);
        ItensDialog.setSize(550, 500);
        FinalizaDialog.setSize(860, 365);
        btnAddItemVendas.setUI(new BasicButtonUI());
        btnCancelar.setUI(new BasicButtonUI());
        btnRemoverItemVenda.setUI(new BasicButtonUI());
        btnDevolver.setUI(new BasicButtonUI());
        btnFinalizar.setUI(new BasicButtonUI());
        btnNovo.setUI(new BasicButtonUI());
        btnSalvar.setUI(new BasicButtonUI());
        btnVoltar.setUI(new BasicButtonUI());
    }

    //Atualiza todos os usuario para a tabela
    public void atualizaTabelaEmprestimo(){
        emprestimo = new EmprestimoM();
        try {
        listaEmprestimo = emprestimodao.listaTodos();
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        Object dados[][] = new String[listaEmprestimo.size()][7];
            int i = 0;
            for (EmprestimoM venda2 : listaEmprestimo) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdRequerente().getNome();
                dados[i][2] = venda2.getDataEmprestimo();
                dados[i][3] = venda2.getDataPrevista();
                
                if(venda2.getDataDevolucao() == ""){
                    dados[i][4] = "Não Devolvido";
                }else{
                    dados[i][4] = venda2.getDataDevolucao();
                }
                dados[i][5] = venda2.getProfessor();
                dados[i][6] = venda2.getDescricao();
                i++;
            }
            String tituloColuna[] = {"ID", "Requerente", "Data Emprestimo","Data Prevista","Devolução","Professor","Justificativa"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblEmprestimo.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblEmprestimo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEmprestimo.getColumnModel().getColumn(0).setMinWidth(0);
            tblEmprestimo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEmprestimo.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblEmprestimo.getColumnModel().getColumn(3).setPreferredWidth(50);
            
            tblEmprestimo.getColumnModel().getColumn(4).setMaxWidth(0);
            tblEmprestimo.getColumnModel().getColumn(4).setMinWidth(0);
            tblEmprestimo.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblEmprestimo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblEmprestimo.getColumnModel().getColumn(2).setCellRenderer(centralizado);
            tblEmprestimo.getColumnModel().getColumn(3).setCellRenderer(centralizado);
            tblEmprestimo.setRowHeight(35);
            tblEmprestimo.updateUI();
    }
    
    public void atualizaTabelaVendabusca(){
        emprestimo = new EmprestimoM();
        
        Object dados[][] = new String[listaEmprestimo.size()][7];
            int i = 0;
            for (EmprestimoM venda2 : listaEmprestimo) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdRequerente().getNome();
                dados[i][2] = venda2.getDataEmprestimo();
                dados[i][3] = venda2.getDataPrevista();
                
                if(venda2.getDataDevolucao() == ""){
                    dados[i][4] = "Não Devolvido";
                }else{
                    dados[i][4] = venda2.getDataDevolucao();
                }
                dados[i][5] = venda2.getProfessor();
                dados[i][6] = venda2.getDescricao();
                i++;
            }
            String tituloColuna[] = {"ID", "Requerente", "Data Emprestimo","Data Prevista","Devolução","Professor","Justificativa"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblEmprestimo.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblEmprestimo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEmprestimo.getColumnModel().getColumn(0).setMinWidth(0);
            tblEmprestimo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEmprestimo.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblEmprestimo.getColumnModel().getColumn(3).setPreferredWidth(50);
            
            tblEmprestimo.getColumnModel().getColumn(4).setMaxWidth(0);
            tblEmprestimo.getColumnModel().getColumn(4).setMinWidth(0);
            tblEmprestimo.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblEmprestimo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblEmprestimo.getColumnModel().getColumn(2).setCellRenderer(centralizado);
            tblEmprestimo.getColumnModel().getColumn(3).setCellRenderer(centralizado);
            tblEmprestimo.setRowHeight(35);
            tblEmprestimo.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaClienteDialogBusca(){
        requerente = new RequerenteM();
        
        String dados[][] = new String[listaRequerente.size()][4];
            int i = 0;
            for (RequerenteM cliente : listaRequerente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getRA();
                dados[i][3] = cliente.getCurso();
                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "RA", "Curso"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblClienteDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblClienteDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblClienteDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClienteDialog.getColumnModel().getColumn(2).setPreferredWidth(50);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblClienteDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblClienteDialog.setRowHeight(35);
            tblClienteDialog.updateUI();
    }
    
    public void atualizaTabelaClienteDialog(){
        requerente = new RequerenteM();
        try {
            listaRequerente = requerentedao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaRequerente.size()][4];
            int i = 0;
            for (RequerenteM cliente : listaRequerente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getRA();
                dados[i][3] = cliente.getCurso();
                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "RA", "Curso"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblClienteDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblClienteDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblClienteDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClienteDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblClienteDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblClienteDialog.setRowHeight(35);
            tblClienteDialog.updateUI();
    }
    
    public void atualizaTabelaFuncionarioDialogBusca(){
        usuario = new UsuarioM();

        
        String dados[][] = new String[listaUsuario.size()][3];
            int i = 0;
            for (UsuarioM funcionario : listaUsuario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getNascimento();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Nascimento"};
            DefaultTableModel tabelaFuncionario = new DefaultTableModel();
            tabelaFuncionario.setDataVector(dados, tituloColuna);
            tblFuncionarioDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionarioDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionarioDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionarioDialog.setRowHeight(35);
            tblFuncionarioDialog.updateUI();
    }
    
    public void atualizaTabelaFuncionarioDialog(){
        usuario = new UsuarioM();
        try {
            listaUsuario = usuariodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaUsuario.size()][3];
            int i = 0;
            for (UsuarioM funcionario : listaUsuario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getNascimento();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Nascimento"};
            DefaultTableModel tabelaFuncionario = new DefaultTableModel();
            tabelaFuncionario.setDataVector(dados, tituloColuna);
            tblFuncionarioDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionarioDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionarioDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionarioDialog.setRowHeight(35);
            tblFuncionarioDialog.updateUI();
    }
    
    public void atualizaTabelaProdutoDialogBusca(){
        patrimonio = new PatrimonioM();

        String dados[][] = new String[listaPatrimonio.size()][5];
            int i = 0;
            for (PatrimonioM produto : listaPatrimonio) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = produto.getNumero();
                dados[i][3] = produto.getQualidade();
                    
                if(produto.getOcupado() == true){
                    dados[i][4] = "Ocupado";
                }else{
                    dados[i][4] = "Disponível";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Patrimônio", "Qualidade", "Ocupado"};
            DefaultTableModel tabelaproduto = new DefaultTableModel();
            tabelaproduto.setDataVector(dados, tituloColuna);
            tblProdutoDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblProdutoDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProdutoDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblProdutoDialog.getColumnModel().getColumn(3).setPreferredWidth(70);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblProdutoDialog.setRowHeight(35);
            tblProdutoDialog.updateUI();
    }
    
    
    public void atualizaTabelaProdutoDialog(){
        patrimonio = new PatrimonioM();
        try {
            listaPatrimonio = patrimoniodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaPatrimonio.size()][5];
            int i = 0;
            for (PatrimonioM produto : listaPatrimonio) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = produto.getNumero();
                dados[i][3] = produto.getQualidade();
                    
                if(produto.getOcupado() == true){
                    dados[i][4] = "Ocupado";
                }else{
                    dados[i][4] = "Disponível";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Patrimônio", "Qualidade", "Ocupado"};
            DefaultTableModel tabelaproduto = new DefaultTableModel();
            tabelaproduto.setDataVector(dados, tituloColuna);
            tblProdutoDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false,false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblProdutoDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProdutoDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblProdutoDialog.getColumnModel().getColumn(3).setPreferredWidth(70);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblProdutoDialog.setRowHeight(35);
            tblProdutoDialog.updateUI();
    }
    
    private void atualizaTabelaItemEmprestimo() {
        
        String dados[][] = new String[listaItemEmprestimo.size()][4];
        int i = 0;
        for(ItenEmprestimoM iv : listaItemEmprestimo){
            dados[i][0] = String.valueOf(iv.getId());
            dados[i][1] = iv.getIdPatrimonio().getNome();
            dados[i][2] = iv.getQualidade();
            dados[i][3] = String.valueOf(iv.getQuantidade());
            i++;
        }
        String tituloColuna[] = {"Id", "Patrimonio", "Qualidade", "Quantidade"};
        DefaultTableModel tabelaItens = new DefaultTableModel();
        tabelaItens.setDataVector(dados, tituloColuna);
        tblItenVenda.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        tblItenVenda.getColumnModel().getColumn(0).setMaxWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setMinWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(50);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        tblItenVenda.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    private void atualizaTabelaItemVendalimpa() {
        emprestimo = new EmprestimoM();
        String dados[][] = new String[listaItemEmprestimo.size()][4];
        int i = 0;
        for(ItenEmprestimoM iv : listaItemEmprestimo){
            dados[i][0] = String.valueOf(iv.getId());
            dados[i][1] = iv.getIdPatrimonio().getNome();
            dados[i][2] = iv.getQualidade();
            dados[i][3] = String.valueOf(iv.getQuantidade());
            i++;
        }
        String tituloColuna[] = {"Id", "Patrimonio", "Qualidade", "Quantidade"};
        DefaultTableModel tabelaItens = new DefaultTableModel();
        tabelaItens.setDataVector(dados, tituloColuna);
        tblItenVenda.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        tblItenVenda.getColumnModel().getColumn(0).setMaxWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setMinWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(50);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        tblItenVenda.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    public void atualizaTabelaItensDialogo(){
        
        String dados[][] = new String[listaItemEmprestimo.size()][4];
            int i = 0;
            for(ItenEmprestimoM iv : listaItemEmprestimo){
            dados[i][0] = String.valueOf(iv.getId());
            dados[i][1] = iv.getIdPatrimonio().getNome();
            dados[i][2] = iv.getQualidade();
            
            if(iv.getDevolvido() == false){
                dados[i][3] = "Pendente";
            }else{
                dados[i][3] = "Devolvido";
            }
            
            
            i++;
        }
        String tituloColuna[] = {"Id", "Patrimonio", "Quantidade", "Devolução"};
        DefaultTableModel tabelaItens = new DefaultTableModel();
        tabelaItens.setDataVector(dados, tituloColuna);
        tblItensDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblItensDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblItensDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblItensDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblItensDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblItensDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblItensDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblItensDialog.setRowHeight(35);
            tblItensDialog.updateUI();
    }

    
    public void PreparaVenda(){
        txtIdProduto.setText("");
        txtproduto.setText("");
    }
    

    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCampos(){
        txtIDIten.setText("");
        txtIdRequerente.setText("");
        txtCliente.setText("");
        txtDataAtual.setText("");
        txtDataPrev.setText("");
        txtHora.setText("");
    }
   
    public void ativarCampos(){
        txtIdRequerente.setEnabled(true);
        txtCliente.setEnabled(true);
        txtDataAtual.setEnabled(true);
    }

    public void desativarCampos(){
        txtIdRequerente.setEnabled(false);
        txtCliente.setEnabled(false);
        txtDataAtual.setEnabled(false);

    }
   
    public void prepararNovo() {
       btnNovo.setEnabled(false);
       btnFinalizar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblEmprestimo.setEnabled(false);
       tblEmprestimo.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       btnFinalizar.setEnabled(false);
       btnCancelar.setEnabled(false);
       tblEmprestimo.setEnabled(true);
    }
   
    public void prepararSelecaoTabela(){
       btnNovo.setEnabled(true);
    }
   
    public void prepararAlterar(){
       btnNovo.setEnabled(false);
       btnFinalizar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblEmprestimo.setEnabled(false);
       tblEmprestimo.clearSelection();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RequerenteDialog = new javax.swing.JDialog();
        jPanel34 = new javax.swing.JPanel();
        btnSair1 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtBuscaClienteDialog = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClienteDialog = new javax.swing.JTable();
        PatrimonioDialog = new javax.swing.JDialog();
        jPanel35 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        txtBuscaProdutoDialog = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblProdutoDialog = new javax.swing.JTable();
        btnSair2 = new javax.swing.JLabel();
        FinalizaDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        txtIdUsuario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtIdRequerente = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        txtProfessor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        txtDataAtual = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHora = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDataPrev = new javax.swing.JFormattedTextField();
        UsuarioDialog = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtBuscaFuncionarioDialog = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFuncionarioDialog = new javax.swing.JTable();
        btnSair3 = new javax.swing.JLabel();
        ItensDialog = new javax.swing.JDialog();
        jPanel37 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblItensDialog = new javax.swing.JTable();
        jSeparator9 = new javax.swing.JSeparator();
        btnDevolver = new javax.swing.JButton();
        txtIdEmprestimo = new javax.swing.JTextField();
        btnSair4 = new javax.swing.JLabel();
        txtIdItemDevolu = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmprestimo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblItenVenda = new javax.swing.JTable();
        btnAddItemVendas = new javax.swing.JButton();
        btnRemoverItemVenda = new javax.swing.JButton();
        txtproduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtIDIten = new javax.swing.JTextField();
        txtQuantidadeTotal = new javax.swing.JTextField();
        txtQualidade = new javax.swing.JTextField();
        btnFinalizar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();

        RequerenteDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        RequerenteDialog.setUndecorated(true);
        RequerenteDialog.setResizable(false);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSair1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSair1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSair1MouseExited(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(26, 26, 26));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Buscar Requerente");

        txtBuscaClienteDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaClienteDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtBuscaClienteDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaClienteDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaClienteDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaClienteDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaClienteDialogCaretUpdate(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator5.setForeground(new java.awt.Color(104, 129, 164));

        tblClienteDialog.setBackground(new java.awt.Color(248, 248, 248));
        tblClienteDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblClienteDialog.setFont(new java.awt.Font("Champagne & Limousines", 1, 12)); // NOI18N
        tblClienteDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Telefone", "Nascimento", "Cidade"
            }
        ));
        tblClienteDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteDialogMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblClienteDialog);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator5))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaClienteDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair1))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSair1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaClienteDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout RequerenteDialogLayout = new javax.swing.GroupLayout(RequerenteDialog.getContentPane());
        RequerenteDialog.getContentPane().setLayout(RequerenteDialogLayout);
        RequerenteDialogLayout.setHorizontalGroup(
            RequerenteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RequerenteDialogLayout.setVerticalGroup(
            RequerenteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PatrimonioDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        PatrimonioDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        PatrimonioDialog.setUndecorated(true);
        PatrimonioDialog.setResizable(false);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

        jLabel69.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(26, 26, 26));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Buscar Patrimônio");

        txtBuscaProdutoDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaProdutoDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtBuscaProdutoDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaProdutoDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaProdutoDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaProdutoDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaProdutoDialogCaretUpdate(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator6.setForeground(new java.awt.Color(104, 129, 164));

        tblProdutoDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblProdutoDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Valor Máximo"
            }
        ));
        tblProdutoDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoDialogMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblProdutoDialog);

        btnSair2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSair2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSair2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSair2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscaProdutoDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                    .addGap(0, 546, Short.MAX_VALUE)
                    .addComponent(btnSair2)))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaProdutoDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addComponent(btnSair2)
                    .addGap(0, 467, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout PatrimonioDialogLayout = new javax.swing.GroupLayout(PatrimonioDialog.getContentPane());
        PatrimonioDialog.getContentPane().setLayout(PatrimonioDialogLayout);
        PatrimonioDialogLayout.setHorizontalGroup(
            PatrimonioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PatrimonioDialogLayout.setVerticalGroup(
            PatrimonioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        FinalizaDialog.setBackground(new java.awt.Color(249, 249, 249));
        FinalizaDialog.setUndecorated(true);
        FinalizaDialog.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel9.setText("Administrador(a):");

        txtFuncionario.setBackground(new java.awt.Color(245, 245, 245));
        txtFuncionario.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuncionarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Requerente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel1.setText("Aluno(a):");

        txtCliente.setBackground(new java.awt.Color(245, 245, 245));
        txtCliente.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdRequerente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdRequerente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvar.setText("Finalizar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(255, 255, 255));
        btnVoltar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtProfessor.setBackground(new java.awt.Color(245, 245, 245));
        txtProfessor.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtProfessor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel11.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel11.setText("Professor(a):");

        jLabel12.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel12.setText("Justificativa do Emprestimo:");

        txtDescricao.setBackground(new java.awt.Color(245, 245, 245));
        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        jScrollPane1.setViewportView(txtDescricao);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtDataAtual.setBackground(new java.awt.Color(245, 245, 245));
        txtDataAtual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtDataAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtDataAtual.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel3.setText("Data:");

        jLabel4.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel4.setText("Entrega Prevista:");

        txtHora.setBackground(new java.awt.Color(245, 245, 245));
        txtHora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        txtHora.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel10.setText("Hora:");

        txtDataPrev.setBackground(new java.awt.Color(245, 245, 245));
        txtDataPrev.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtDataPrev.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDataPrev))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDataPrev))))
                .addContainerGap())
        );

        javax.swing.GroupLayout FinalizaDialogLayout = new javax.swing.GroupLayout(FinalizaDialog.getContentPane());
        FinalizaDialog.getContentPane().setLayout(FinalizaDialogLayout);
        FinalizaDialogLayout.setHorizontalGroup(
            FinalizaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FinalizaDialogLayout.setVerticalGroup(
            FinalizaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        UsuarioDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UsuarioDialog.setUndecorated(true);
        UsuarioDialog.setResizable(false);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(26, 26, 26));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Buscar Funcionario");

        txtBuscaFuncionarioDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaFuncionarioDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtBuscaFuncionarioDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaFuncionarioDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaFuncionarioDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaFuncionarioDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaFuncionarioDialogCaretUpdate(evt);
            }
        });

        jSeparator7.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator7.setForeground(new java.awt.Color(104, 129, 164));

        tblFuncionarioDialog.setBackground(new java.awt.Color(248, 248, 248));
        tblFuncionarioDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblFuncionarioDialog.setFont(new java.awt.Font("Champagne & Limousines", 1, 12)); // NOI18N
        tblFuncionarioDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Nascimento"
            }
        ));
        tblFuncionarioDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioDialogMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblFuncionarioDialog);

        btnSair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSair3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSair3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSair3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator7))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscaFuncionarioDialog)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                    .addGap(0, 546, Short.MAX_VALUE)
                    .addComponent(btnSair3)))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaFuncionarioDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addComponent(btnSair3)
                    .addGap(0, 467, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout UsuarioDialogLayout = new javax.swing.GroupLayout(UsuarioDialog.getContentPane());
        UsuarioDialog.getContentPane().setLayout(UsuarioDialogLayout);
        UsuarioDialogLayout.setHorizontalGroup(
            UsuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UsuarioDialogLayout.setVerticalGroup(
            UsuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ItensDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ItensDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        ItensDialog.setUndecorated(true);
        ItensDialog.setResizable(false);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator8.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator8.setForeground(new java.awt.Color(104, 129, 164));

        tblItensDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblItensDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Valor Máximo"
            }
        ));
        tblItensDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItensDialogMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblItensDialog);

        jSeparator9.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator9.setForeground(new java.awt.Color(104, 129, 164));

        btnDevolver.setBackground(new java.awt.Color(255, 255, 255));
        btnDevolver.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnDevolver.setText("Devolver Item");
        btnDevolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnDevolver.setEnabled(false);
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnSair4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSair4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSair4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSair4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSair4))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdItemDevolu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtIdEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(btnSair4)
                .addGap(8, 8, 8)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdItemDevolu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout ItensDialogLayout = new javax.swing.GroupLayout(ItensDialog.getContentPane());
        ItensDialog.getContentPane().setLayout(ItensDialogLayout);
        ItensDialogLayout.setHorizontalGroup(
            ItensDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ItensDialogLayout.setVerticalGroup(
            ItensDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setClosable(true);
        setMaximumSize(new java.awt.Dimension(1919, 800));
        setMinimumSize(new java.awt.Dimension(1919, 800));
        setPreferredSize(new java.awt.Dimension(1919, 800));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setForeground(new java.awt.Color(32, 33, 41));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblEmprestimo.setBackground(new java.awt.Color(248, 248, 248));
        tblEmprestimo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblEmprestimo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblEmprestimo.setForeground(new java.awt.Color(32, 33, 41));
        tblEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblEmprestimo);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(32, 33, 41))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(32, 33, 41));
        jPanel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtBusca.setBackground(new java.awt.Color(245, 245, 245));
        txtBusca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBusca.setForeground(new java.awt.Color(32, 33, 41));
        txtBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(32, 33, 41));
        jLabel13.setText("Requerente:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleção de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(32, 33, 41))); // NOI18N

        tblItenVenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblItenVenda.setForeground(new java.awt.Color(32, 33, 41));
        tblItenVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblItenVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItenVendaMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblItenVenda);

        btnAddItemVendas.setBackground(new java.awt.Color(255, 255, 255));
        btnAddItemVendas.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnAddItemVendas.setForeground(new java.awt.Color(32, 33, 41));
        btnAddItemVendas.setText("+ ADICIONAR ");
        btnAddItemVendas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnAddItemVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemVendasActionPerformed(evt);
            }
        });

        btnRemoverItemVenda.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoverItemVenda.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnRemoverItemVenda.setForeground(new java.awt.Color(32, 33, 41));
        btnRemoverItemVenda.setText("- REMOVER");
        btnRemoverItemVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnRemoverItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemVendaActionPerformed(evt);
            }
        });

        txtproduto.setBackground(new java.awt.Color(245, 245, 245));
        txtproduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtproduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtprodutoMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(32, 33, 41));
        jLabel2.setText("Patrimônio:");

        txtIdProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdProduto.setForeground(new java.awt.Color(32, 33, 41));

        txtIDIten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIDIten.setForeground(new java.awt.Color(32, 33, 41));

        txtQuantidadeTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQuantidadeTotal.setForeground(new java.awt.Color(32, 33, 41));

        txtQualidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQualidade.setForeground(new java.awt.Color(32, 33, 41));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtQualidade, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoverItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQualidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnFinalizar.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(32, 33, 41));
        btnFinalizar.setText("SALVAR");
        btnFinalizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(32, 33, 41));
        btnNovo.setText("NOVO");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        btnNovo.setMaximumSize(new java.awt.Dimension(63, 29));
        btnNovo.setMinimumSize(new java.awt.Dimension(63, 29));
        btnNovo.setName(""); // NOI18N
        btnNovo.setPreferredSize(new java.awt.Dimension(63, 29));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(32, 33, 41));
        btnCancelar.setText("CANCELAR");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Emprestimo", jPanel6);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(88, 145, 193));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Emprestimo");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 399, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnSair))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        listaEmprestimo = null;
        listaRequerente = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaEmprestimo();
        }else{
                    
            try {
                requerente = requerentedao.buscaNome2(txtBusca.getText());
                listaEmprestimo = emprestimodao.buscaNomeLista(requerente.getId());

                if(listaEmprestimo == null){
                    JOptionPane.showMessageDialog(null, "Nenhuma venda encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaEmprestimo();
                }else{
                    atualizaTabelaVendabusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        prepararSalvareCancelar();
        desativarCampos();
        atualizaTabelaItemVendalimpa();
        listaItemEmprestimo = new ArrayList<>();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        listaItemEmprestimo = new ArrayList<>();
        limparCampos();
        prepararNovo();
        ativarCampos();
        atualizaTabelaItemVendalimpa();
        txtCliente.requestFocusInWindow();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
            FinalizaDialog.setVisible(true);
            FinalizaDialog.setLocationRelativeTo(null);
            txtDataAtual.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            txtHora.setText(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtBuscaClienteDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaClienteDialogCaretUpdate
        listaRequerente = null;
        if(txtBuscaClienteDialog.getText().equals("")){
            atualizaTabelaClienteDialog();
        }else{

            try {
                listaRequerente = requerentedao.buscaNomeLista(txtBuscaClienteDialog.getText());

                if(listaRequerente == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaClienteDialog();
                }else{
                    atualizaTabelaClienteDialogBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaClienteDialogCaretUpdate

    private void tblProdutoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoDialogMouseClicked
        if(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 4) == "Disponível"){
        txtIdProduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 0).toString());
        txtproduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 1).toString());
        txtQuantidadeTotal.setText("1");
        patrimonio = new PatrimonioM();
        patrimonio.setId(Integer.parseInt(txtIdProduto.getText()));
        patrimonio.setNome(txtproduto.getText());
        PatrimonioDialog.dispose();
        }else if(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 4) == "Ocupado"){
            JOptionPane.showMessageDialog(null, "O Patrimônio selecionado esta Ocupado");
        }else{
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }//GEN-LAST:event_tblProdutoDialogMouseClicked

    private void txtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClienteMouseClicked
       RequerenteDialog.setVisible(true);
       RequerenteDialog.setLocationRelativeTo(null);
       atualizaTabelaClienteDialog();
       tblClienteDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtClienteMouseClicked

    private void tblClienteDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteDialogMouseClicked
        txtIdRequerente.setText(tblClienteDialog.getValueAt(tblClienteDialog.getSelectedRow(), 0).toString());
        txtCliente.setText(tblClienteDialog.getValueAt(tblClienteDialog.getSelectedRow(), 1).toString());
        requerente.setId(Integer.valueOf(txtIdRequerente.getText()));
        requerente.setNome(txtCliente.getText());
        RequerenteDialog.dispose();
    }//GEN-LAST:event_tblClienteDialogMouseClicked

    private void txtFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuncionarioMouseClicked
       UsuarioDialog.setVisible(true);
       UsuarioDialog.setLocationRelativeTo(null);
       atualizaTabelaFuncionarioDialog();
       tblFuncionarioDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtFuncionarioMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
            emprestimo.setIdCliente(requerente);
            emprestimo.setIdUsuario(usuario);
            emprestimo.setProfessor(txtProfessor.getText());
            emprestimo.setDescricao(txtDescricao.getText());
            emprestimo.setHora(txtHora.getText());
            emprestimo.setDataEmprestimo(txtDataAtual.getText());
            emprestimo.setDataPrevista(txtDataPrev.getText());
            try{
                int auxback = emprestimodao.salvar(emprestimo,listaItemEmprestimo);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            String nomediretorio = null;
            String nomepasta = "Comprovantes Emprestimo"; // Informa o nome da pasta que armazenará o relatório
            String separador = java.io.File.separator;
            try 
            {
                nomediretorio = caminho + separador + nomepasta;
                
                    if(auxback == 1){
                        gerarDocumento(requerente,usuario, emprestimo, listaItemEmprestimo);
                    }
                    else{
                    JOptionPane.showMessageDialog( null, "Remova o espaço depois do seu ultimo nome do orientador!");
                    }

            } catch (Exception e) 
            {
                e.printStackTrace();
            }
 
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaEmprestimo();
            atualizaTabelaItemVendalimpa();
            prepararSalvareCancelar();
            desativarCampos();
            limparCampos();
            FinalizaDialog.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtBuscaFuncionarioDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaFuncionarioDialogCaretUpdate
        atualizaTabelaClienteDialog();
        listaUsuario = null;
        if(txtBuscaFuncionarioDialog.getText().equals("")){
            atualizaTabelaClienteDialog();
        }else{

            try {
                listaUsuario = usuariodao.buscaNomeLista(txtBuscaFuncionarioDialog.getText());

                if(listaUsuario == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Funcionario encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaClienteDialog();
                }else{
                    atualizaTabelaClienteDialogBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaFuncionarioDialogCaretUpdate

    private void tblFuncionarioDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioDialogMouseClicked
        txtIdUsuario.setText(tblFuncionarioDialog.getValueAt(tblFuncionarioDialog.getSelectedRow(), 0).toString());
        txtFuncionario.setText(tblFuncionarioDialog.getValueAt(tblFuncionarioDialog.getSelectedRow(), 1).toString());
        usuario.setId(Integer.valueOf(txtIdUsuario.getText()));
        usuario.setNome(txtFuncionario.getText());
        UsuarioDialog.dispose();
    }//GEN-LAST:event_tblFuncionarioDialogMouseClicked

    private void tblItensDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItensDialogMouseClicked
        txtIdItemDevolu.setText(tblItensDialog.getValueAt(tblItensDialog.getSelectedRow(), 0).toString());
        btnDevolver.setEnabled(true);
    }//GEN-LAST:event_tblItensDialogMouseClicked

    private void txtBuscaProdutoDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaProdutoDialogCaretUpdate
        if (txtBuscaProdutoDialog.getText().equals("")) {
            atualizaTabelaProdutoDialog();
        }else{

            try {
                listaPatrimonio = patrimoniodao.buscaNomeLista(txtBuscaProdutoDialog.getText());

                if(listaPatrimonio == null){

                    JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaProdutoDialog();
                }else{
                    atualizaTabelaProdutoDialogBusca();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_txtBuscaProdutoDialogCaretUpdate

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
    FinalizaDialog.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        FinalizaDialog.dispose();
        ItensDialog.dispose();
    }//GEN-LAST:event_formMouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Devolver ?");
            if (confirma == 0) {
                try {
                    
                    int aux = Integer.valueOf(txtIdItemDevolu.getText());

                    emprestimo.setId(Integer.valueOf(txtIdEmprestimo.getText()));
                    emprestimo.setDataDevolucao(new SimpleDateFormat("dd/MM/YYYY").format(new Date(System.currentTimeMillis())));
                    emprestimodao.alterarVendaTrue(emprestimo);
                    
                    
                    itemEmprestimo.setIdEmprestimo(emprestimo);
                    
                    itemEmprestimo = itemEmprestimodao.buscapatrimonio(aux);
                            
                    itemEmprestimo.setDevolvido(true);
                    
                    itemEmprestimodao.alterarItemVendaTrue(itemEmprestimo , aux);
                    atualizaTabelaEmprestimo();
                    
                    listaItemEmprestimo = itemEmprestimodao.busca(Integer.valueOf(txtIdEmprestimo.getText()));
                    atualizaTabelaItensDialogo();

                } catch (SQLException ex) {
                    Logger.getLogger(EmprestimoView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }

            }
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void txtprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtprodutoMouseClicked
        PatrimonioDialog.setVisible(true);
        PatrimonioDialog.setLocationRelativeTo(null);
        atualizaTabelaProdutoDialog();
        tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtprodutoMouseClicked

    private void btnRemoverItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemVendaActionPerformed
        if(txtIDIten.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{
            listaItemEmprestimo.remove(tblItenVenda.getSelectedRow());
            atualizaTabelaItemEmprestimo();
            txtIDIten.setText("");
        }
    }//GEN-LAST:event_btnRemoverItemVendaActionPerformed

    private void btnAddItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemVendasActionPerformed
        if(txtproduto.getText().isEmpty() || txtIdProduto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{
            patrimonio.setId(Integer.valueOf(txtIdProduto.getText()));
            itemEmprestimo = new ItenEmprestimoM();
            itemEmprestimo.setIdPatrimonio(patrimonio);
            itemEmprestimo.setQuantidade(Integer.valueOf(txtQuantidadeTotal.getText()));
            itemEmprestimo.setQualidade(txtQualidade.getText());
            itemEmprestimo.setDevolvido(false);
            listaItemEmprestimo.add(itemEmprestimo);
            atualizaTabelaItemEmprestimo();
            PreparaVenda();

            txtQualidade.setText("");
            txtQuantidadeTotal.setText("");
            txtproduto.setText("");
            txtIdProduto.setText("");
        }
    }//GEN-LAST:event_btnAddItemVendasActionPerformed

    private void tblItenVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItenVendaMouseClicked
        txtIDIten.setText(tblItenVenda.getValueAt(tblItenVenda.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblItenVendaMouseClicked

    private void btnSair1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseClicked
        RequerenteDialog.dispose();
    }//GEN-LAST:event_btnSair1MouseClicked

    private void btnSair1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseEntered
        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSair1MouseEntered

    private void btnSair1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseExited
        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSair1MouseExited

    private void btnSair2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair2MouseClicked
        PatrimonioDialog.dispose();
    }//GEN-LAST:event_btnSair2MouseClicked

    private void btnSair2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair2MouseEntered
        btnSair2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSair2MouseEntered

    private void btnSair2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair2MouseExited
        btnSair2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSair2MouseExited

    private void btnSair3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair3MouseClicked
        UsuarioDialog.dispose();
    }//GEN-LAST:event_btnSair3MouseClicked

    private void btnSair3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair3MouseEntered
        btnSair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSair3MouseEntered

    private void btnSair3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair3MouseExited
        btnSair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSair3MouseExited

    private void btnSair4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair4MouseClicked
        ItensDialog.dispose();
    }//GEN-LAST:event_btnSair4MouseClicked

    private void btnSair4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair4MouseEntered
        btnSair4.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSair4MouseEntered

    private void btnSair4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair4MouseExited
        btnSair4.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSair4MouseExited

    private void tblEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmprestimoMouseClicked
        try{
            listaItemEmprestimo = itemEmprestimodao.busca(Integer.parseInt(tblEmprestimo.getValueAt(tblEmprestimo.getSelectedRow(),0).toString()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }

        ItensDialog.setVisible(true);
        txtIdEmprestimo.setText(tblEmprestimo.getValueAt(tblEmprestimo.getSelectedRow(),0).toString());
        ItensDialog.setLocationRelativeTo(null);
        tblItensDialog.getTableHeader().setReorderingAllowed(false);
        atualizaTabelaItensDialogo();
    }//GEN-LAST:event_tblEmprestimoMouseClicked

    
    public void gerarDocumento(RequerenteM requer, UsuarioM usua, EmprestimoM emprest, List<ItenEmprestimoM> listaItemEmprestimoCompro) throws IOException, DocumentException, SQLException{
      
        File pdf = null;
        JFileChooser chooser = null;
        
        doc = new Document(PageSize.A4);

 	try {
            pdf = File.createTempFile(requerente.getNome(),"");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            
            Paragraph nomeUniversidade = new Paragraph("Universidade do Estado de Minas Gerais",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            
            Paragraph nomeCompro = new Paragraph("Comprovante" ,f12);
            nomeCompro.setAlignment(Element.ALIGN_CENTER);
            nomeCompro.setSpacingAfter(10);
            
            Paragraph nomeUsu = new Paragraph("Atendimento: "+usua.getNome() ,f10);
            nomeUsu.setAlignment(Element.ALIGN_JUSTIFIED);
            nomeUsu.setSpacingAfter(5);
            
            Paragraph nomeAlun = new Paragraph("Aluno(a): "+requer.getNome() ,f10);
            nomeAlun.setAlignment(Element.ALIGN_JUSTIFIED);
            nomeAlun.setSpacingAfter(5);
            
            Paragraph Data = new Paragraph("Data do Emprestimo: " + emprest.getDataEmprestimo() + "\t\tData da Entrega: " + emprest.getDataPrevista() + "\n\n",fnormal);
            nomeAlun.setAlignment(Element.ALIGN_CENTER);
            nomeAlun.setSpacingAfter(10);

            
            doc.add(nomeUniversidade);
            doc.add(nomeCompro);
            doc.add(nomeUsu);
            doc.add(nomeAlun);
            doc.add(Data);
            
            PdfPTable tabela = new PdfPTable(3);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Nome", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoNumero = new PdfPCell(new Paragraph("Número de Patrimônio",f10));
            cabecalhoNumero.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNumero);
            
            PdfPCell cabecalhoQualidade = new PdfPCell(new Paragraph("Qualidade",f10));
            cabecalhoQualidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoQualidade);

            
            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (ItenEmprestimoM item : listaItemEmprestimoCompro){
                
                patrimonio = patrimoniodao.busca(item.getIdPatrimonio().getId());
                
                Paragraph pNome = new Paragraph(patrimonio.getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pNum = new Paragraph(patrimonio.getNumero(), fnormal);
                pNum.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colNum = new PdfPCell(pNum);
                
                Paragraph pQuali = new Paragraph(patrimonio.getQualidade(), fnormal);
                pQuali.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colQual = new PdfPCell(pQuali);

                
                tabela.addCell(colNome);
                tabela.addCell(colNum);
                tabela.addCell(colQual);

            }
            doc.add(tabela);
            
            Paragraph espacamento = new Paragraph("\n\n\n\n\n");
            doc.add(espacamento);
            
            doc.add(nomeUniversidade);
            doc.add(nomeCompro);
            doc.add(nomeUsu);
            doc.add(nomeAlun);
            doc.add(Data);
            doc.add(tabela);
            
            JOptionPane.showMessageDialog(null, "Comprovante Comprovado Com Sucesso");
        doc.close();
        JOptionPane.showMessageDialog(null, caminho);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FinalizaDialog;
    private javax.swing.JDialog ItensDialog;
    private javax.swing.JDialog PatrimonioDialog;
    private javax.swing.JDialog RequerenteDialog;
    private javax.swing.JDialog UsuarioDialog;
    private javax.swing.JButton btnAddItemVendas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemoverItemVenda;
    private javax.swing.JLabel btnSair;
    private javax.swing.JLabel btnSair1;
    private javax.swing.JLabel btnSair2;
    private javax.swing.JLabel btnSair3;
    private javax.swing.JLabel btnSair4;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblClienteDialog;
    private javax.swing.JTable tblEmprestimo;
    private javax.swing.JTable tblFuncionarioDialog;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTable tblItensDialog;
    private javax.swing.JTable tblProdutoDialog;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBuscaClienteDialog;
    private javax.swing.JTextField txtBuscaFuncionarioDialog;
    private javax.swing.JTextField txtBuscaProdutoDialog;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JFormattedTextField txtDataAtual;
    private javax.swing.JFormattedTextField txtDataPrev;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtIDIten;
    private javax.swing.JTextField txtIdEmprestimo;
    private javax.swing.JTextField txtIdItemDevolu;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtIdRequerente;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtProfessor;
    private javax.swing.JTextField txtQualidade;
    private javax.swing.JTextField txtQuantidadeTotal;
    private javax.swing.JTextField txtproduto;
    // End of variables declaration//GEN-END:variables
}
