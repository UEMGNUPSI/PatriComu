package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItenEmprestimoM {
    
    private int Id;
    private EmprestimoM IdEmprestimo;
    private PatrimonioM IdPatrimonio;
    private String Qualidade;
    private int Quantidade;
    private Boolean Devolvido;

    public ItenEmprestimoM() {
    }

    public ItenEmprestimoM(int Id, EmprestimoM IdEmprestimo, PatrimonioM IdPatrimonio, String Qualidade, int Quantidade, Boolean Devolvido) {
        this.Id = Id;
        this.IdEmprestimo = IdEmprestimo;
        this.IdPatrimonio = IdPatrimonio;
        this.Qualidade = Qualidade;
        this.Quantidade = Quantidade;
        this.Devolvido = Devolvido;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public EmprestimoM getIdEmprestimo() {
        return IdEmprestimo;
    }

    public void setIdEmprestimo(EmprestimoM IdEmprestimo) {
        this.IdEmprestimo = IdEmprestimo;
    }

    public PatrimonioM getIdPatrimonio() {
        return IdPatrimonio;
    }

    public void setIdPatrimonio(PatrimonioM IdPatrimonio) {
        this.IdPatrimonio = IdPatrimonio;
    }

    public String getQualidade() {
        return Qualidade;
    }

    public void setQualidade(String Qualidade) {
        this.Qualidade = Qualidade;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public Boolean getDevolvido() {
        return Devolvido;
    }

    public void setDevolvido(Boolean Devolvido) {
        this.Devolvido = Devolvido;
    }

    
}
