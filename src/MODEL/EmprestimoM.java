package MODEL;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Danilo-NOTE
 */
public class EmprestimoM {
    
    private int Id;
    private RequerenteM IdRequerente;
    private UsuarioM IdUsuario;
    private String Professor;
    private String Descricao;
    private String Hora;
    private String DataEmprestimo;
    private String DataPrevista;
    private String DataDevolucao;
    

    public EmprestimoM() {
    }

    public EmprestimoM(int Id, RequerenteM IdCliente, UsuarioM IdUsuario, String Professor, String Descricao, String Hora, String DataEmprestimo, String DataPrevista, String DataDevolucao) {
        this.Id = Id;
        this.IdRequerente = IdCliente;
        this.IdUsuario = IdUsuario;
        this.Professor = Professor;
        this.Descricao = Descricao;
        this.Hora = Hora;
        this.DataEmprestimo = DataEmprestimo;
        this.DataPrevista = DataPrevista;
        this.DataDevolucao = DataDevolucao;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public RequerenteM getIdRequerente() {
        return IdRequerente;
    }

    public void setIdCliente(RequerenteM IdRequerente) {
        this.IdRequerente = IdRequerente;
    }

    public UsuarioM getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(UsuarioM IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String Professor) {
        this.Professor = Professor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getDataEmprestimo() {
        return DataEmprestimo;
    }

    public void setDataEmprestimo(String DataEmprestimo) {
        this.DataEmprestimo = DataEmprestimo;
    }

    public String getDataPrevista() {
        return DataPrevista;
    }

    public void setDataPrevista(String DataPrevista) {
        this.DataPrevista = DataPrevista;
    }

    public String getDataDevolucao() {
        return DataDevolucao;
    }

    public void setDataDevolucao(String DataDevolucao) {
        this.DataDevolucao = DataDevolucao;
    }

    
}
