package com.example.historiainterativap2;

public class Estado {
    private Capitulo capituloAtual;
    private int saudeAtual;
    private String nome;

    public Capitulo getCapituloAtual() {
        return capituloAtual;
    }
    public void setCapituloAtual(Capitulo capituloAtual) {
        this.capituloAtual = capituloAtual;
    }
    public int getSaudeAtual() {
        return saudeAtual;
    }
    public void setSaudeAtual(int saudeAtual) {
        this.saudeAtual = saudeAtual;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "State [capituloAtual=" + capituloAtual + ", saudeAtual=" + saudeAtual + ", nome=" + nome + "]";
    }
}
