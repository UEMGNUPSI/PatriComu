package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class LogM {
    
    private int Id;
    private String Data;
    private String Hora;
    private String Acao;
    private RequerenteM Requerente;
    private UsuarioM Usuario;

    public LogM() {
    }

    public LogM(int Id, String Data, String Hora, String Acao, RequerenteM Requerente, UsuarioM Usuario) {
        this.Id = Id;
        this.Data = Data;
        this.Hora = Hora;
        this.Acao = Acao;
        this.Requerente = Requerente;
        this.Usuario = Usuario;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getAcao() {
        return Acao;
    }

    public void setAcao(String Acao) {
        this.Acao = Acao;
    }

    public RequerenteM getRequerente() {
        return Requerente;
    }

    public void setRequerente(RequerenteM Requerente) {
        this.Requerente = Requerente;
    }

    public UsuarioM getUsuario() {
        return Usuario;
    }

    public void setUsuario(UsuarioM Usuario) {
        this.Usuario = Usuario;
    }

 

   
    
}
