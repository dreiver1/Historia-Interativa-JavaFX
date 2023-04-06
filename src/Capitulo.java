public class Capitulo {
    private String titulo;
    private String contexto;
    private Capitulo escolha01;
    private Capitulo escolha02;
    private Boolean perdeSaude = false;
    private Boolean morre = false;

    public Boolean getMorre() {
        return morre;
    }

    public void setMorre() {
        this.morre = true;
    }

    public Boolean getperdeperdeSaude() {
        return this.perdeSaude;
    }

    public void setperdeperdeSaude() {
        this.perdeSaude = true;
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

    @Override
    public String toString() {
        return "Capitulo [titulo=" + titulo + ", contexto=" + contexto + ", escolha01=" + escolha01 + ", escolha02="
                + escolha02 + ", perdeSaude=" + perdeSaude + "]";
    }
}
