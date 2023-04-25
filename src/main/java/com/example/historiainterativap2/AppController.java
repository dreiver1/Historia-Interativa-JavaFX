package com.example.historiainterativap2;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AppController {
  public Capitulo getAtual() {
    return atual;
  }

  @FXML
  private ImageView capituloImagem;

  @FXML
  private Button continuar;

  @FXML
  private Button salvar;

  public void setAtual(Capitulo atual) {
    this.atual = atual;
  }

  private Capitulo atual;

  private Historia historia;

  @FXML
  private Label titulo;

  @FXML
  private TextArea content;

  @FXML
  private Button escolha01;

  @FXML
  private Button escolha02;

  @FXML
  private Button inciar;

  @FXML
  private Button Sair;

  @FXML
  private Label saude;

  @FXML
  void sair(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void escolha01(ActionEvent event) {
    setAtual(getAtual().getEscolha01());
    atualizaInterface(getAtual());
  }

  @FXML
  void escolha02(ActionEvent event) {
    setAtual(getAtual().getEscolha02());
    atualizaInterface(getAtual());
  }

  @FXML
  void iniciar(ActionEvent event) throws IOException, InterruptedException {
    inciar.setVisible(false);
    escolha01.setVisible(true);
    escolha02.setVisible(true);
    this.executar();
    salvar.setVisible(true);
    continuar.setVisible(false);
  }

  public void carregarEstado() throws FileNotFoundException {
    if(historia.restaurarEstado()){
      continuar.setVisible(true);
    }
  }

  public void setHistoria(Historia historia) {
    this.historia = historia;
  }

  public void executar() throws InterruptedException, IOException {
    escolher(historia.getInicio());
  }

  /**
   * A partir das informações de um capitulo recarrega todas as informações da
   * interface.
   * 
   **/
  private void atualizaInterface(Capitulo capitulo) {
    titulo.setText(capitulo.getTitulo());
    if (capitulo.getEscolha01() != null || capitulo.getEscolha02() != null) {
      escolha01.setText(capitulo.getEscolha01().getTitulo());
      escolha02.setText(capitulo.getEscolha02().getTitulo());
    } else {
      Sair.setVisible(true);
      escolha01.setVisible(false);
      escolha02.setVisible(false);
      salvar.setVisible(false);
    }
    saude.setText(historia.getPersonagem().getSaudeString());
    content.setText(capitulo.getContexto());
    if (capitulo.getperdeperdeSaude()) {
      historia.getPersonagem().perdeSaude();
    }
  }

  private void escolher(Capitulo capitulo) throws InterruptedException, IOException {
    atualizaInterface(capitulo);
    if (capitulo.getperdeperdeSaude()) {
      historia.getPersonagem().perdeSaude();
    }
    setAtual(capitulo);
  }


  /**
   * Salva todas informações atuais em um arquivo Json para ser recuperado
   * posteriormente.
   **/
  private void salvarEstado(Capitulo capitulo) throws IOException {
    Gson gson = new Gson();
    File arquivo = new File("src/main/java/com/example/historiainterativap2/assets/estado.json");
    if(!arquivo.exists()){
      arquivo.createNewFile();
    }
    historia.setEstado(new Estado());
    historia.getEstado().setSaudeAtual(historia.getPersonagem().getSaude());
    historia.getEstado().setNome(historia.getPersonagem().getNome());
    historia.getEstado().setCapituloAtual(capitulo);
    FileWriter fileWriter = new FileWriter(arquivo);
    String json = gson.toJson(historia.getEstado());
    fileWriter.write(json);
    fileWriter.close();
    System.exit(0);
  }


  @FXML
  void restaurarProgresso(ActionEvent event) throws InterruptedException, IOException {
    historia.continuar();
    continuar.setVisible(false);
    this.executar();
    escolha01.setVisible(true);
    escolha02.setVisible(true);
    inciar.setVisible(false);
  }

  @FXML
  void salvarProgress(ActionEvent event) throws IOException {
    salvarEstado(this.getAtual());
  }
}
