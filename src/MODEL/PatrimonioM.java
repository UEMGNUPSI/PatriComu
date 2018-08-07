package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class PatrimonioM {
    
    private int Id;
    private String Nome;
    private String Numero;
    private String Qualidade;
    private Boolean Ocupado;

    public PatrimonioM() {
    }

    public PatrimonioM(int Id, String Nome, String Numero, String Qualidade, Boolean Ocupado) {
        this.Id = Id;
        this.Nome = Nome;
        this.Numero = Numero;
        this.Qualidade = Qualidade;
        this.Ocupado = Ocupado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getQualidade() {
        return Qualidade;
    }

    public void setQualidade(String Qualidade) {
        this.Qualidade = Qualidade;
    }

    public Boolean getOcupado() {
        return Ocupado;
    }

    public void setOcupado(Boolean Ocupado) {
        this.Ocupado = Ocupado;
    }

    
    
}
