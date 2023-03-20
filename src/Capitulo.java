public class Capitulo {
    private String titulo;
    private String contexto;
    private Capitulo escolha01;
    private Capitulo escolha02;
    private Boolean fim = false;

    public Boolean getFim() {
        return fim;
    }

    public void setFim() {
        this.fim = true;
    }

    public Capitulo(String titulo, String contexto) {
        this.titulo = titulo;
        this.contexto = contexto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Capitulo getEscolha01() {
        return escolha01;
    }

    public void setEscolha01(Capitulo escolha01) {
        this.escolha01 = escolha01;
    }

    public Capitulo getEscolha02() {
        return escolha02;
    }

    public void setEscolha02(Capitulo escolha02) {
        this.escolha02 = escolha02;
    }
}
