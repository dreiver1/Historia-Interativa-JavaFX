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

    Capitulo atual;


    Historia historia;

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
    void escolha01(ActionEvent event) {

    }

    @FXML
    void escolha02(ActionEvent event) {

    }
    @FXML
    void iniciar(ActionEvent event) throws IOException, InterruptedException {
        content.setText("ola, mundo!");
        inciar.setVisible(false);
        escolha01.setVisible(true);
        escolha02.setVisible(true);
        this.titulo.setText("titulo do capitulo");
        this.executar();
        continuar.setVisible(false);
        salvar.setVisible(true);
        //a cada iteracao alterar o titulo da sena e as escolhas
        //a cada iteracao exibir o contexto do capitulo dentro da textArea

    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }

    public void executar() throws InterruptedException, IOException {
        restaurarEstado();
        escolher(historia.getInicio());
    }

    private void escolher(Capitulo capitulo) throws InterruptedException, IOException {
        titulo.setText(capitulo.getTitulo());
        escolha01.setText(capitulo.getEscolha01().getTitulo());
        escolha02.setText(capitulo.getEscolha02().getTitulo());
        content.setText(capitulo.getContexto());

        if(capitulo.getperdeperdeSaude() == true){
            historia.getPersonagem().perdeSaude();
        }

        setAtual(capitulo);
        // to doo proxima escolha
    }

    private void salvarEstado(Capitulo capitulo) throws IOException {
        Gson gson = new Gson();
        File arquivo = new File("src/assets/estado.json");
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        historia.setEstado(new State());
        historia.getEstado().setSaudeAtual(historia.getPersonagem().getSaude());
        historia.getEstado().setNome(historia.getPersonagem().getNome());
        historia.getEstado().setCapituloAtual(capitulo);
        FileWriter fileWriter = new FileWriter(arquivo);
        String json = gson.toJson(historia.getEstado());
        fileWriter.write(json);
        fileWriter.close();
    }

    private void restaurarEstado() throws FileNotFoundException {
        File estado = new File("src/assets/estado.json");
        //to do restaurar estado
//        if(estado.exists()){
//            System.out.println("Progresso encontrado: Desejá continuar de onde parou? \n S/n: ");
//            System.out.println(opcao);
//            if(opcao.equals("s") || opcao.equals("s")){
//                FileReader arquivo = new FileReader(estado);
//                JsonReader json = new JsonReader(arquivo);
//                Gson gson = new Gson();
//                historia.setEstado(gson.fromJson(json, State.class));
//                historia.setInicio(historia.getEstado().getCapituloAtual());
//                historia.getPersonagem().setSaude(historia.getEstado().getSaudeAtual());
//                historia.getPersonagem().recuperaSaude();
//                historia.getPersonagem().setNome(historia.getEstado().getNome());
//                return;
//            }else{
//                estado.delete();
//            }
//        }
    }

    @FXML
    void restaurarProgresso(ActionEvent event) {

    }

    @FXML
    void salvarProgress(ActionEvent event) {

    }
}
