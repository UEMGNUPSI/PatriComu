package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class RequerenteM {
    
    private int Id;
    private String Nome;
    private String Endereco;
    private String Cidade;
    private String Telefone;
    private String Email;
    private String Nascimento;
    private String RA;
    private String Rg;
    private String Cpf;
    private String Curso;
    private Boolean Bloqueio;
    private String Senha;

    public RequerenteM() {
    }

    public RequerenteM(int Id, String Nome, String Endereco, String Cidade, String Telefone, String Email, String Nascimento, String RA, String Rg, String Cpf, String Curso, Boolean Bloqueio, String Senha) {
        this.Id = Id;
        this.Nome = Nome;
        this.Endereco = Endereco;
        this.Cidade = Cidade;
        this.Telefone = Telefone;
        this.Email = Email;
        this.Nascimento = Nascimento;
        this.RA = RA;
        this.Rg = Rg;
        this.Cpf = Cpf;
        this.Curso = Curso;
        this.Bloqueio = Bloqueio;
        this.Senha = Senha;
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

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNascimento() {
        return Nascimento;
    }

    public void setNascimento(String Nascimento) {
        this.Nascimento = Nascimento;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    public Boolean getBloqueio() {
        return Bloqueio;
    }

    public void setBloqueio(Boolean Bloqueio) {
        this.Bloqueio = Bloqueio;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

  

    
    
}
