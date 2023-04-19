package com.example.historiainterativap2;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import java.io.*;
import java.util.Scanner;

public class AppController {

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
        Scanner scanner = new Scanner(System.in);
        titulo.setText(capitulo.getTitulo());
        escolha01.setText(capitulo.getEscolha01().getTitulo());
        escolha02.setText(capitulo.getEscolha02().getTitulo());
        content.setText(capitulo.getContexto());

        if(capitulo.getperdeperdeSaude() == true){
            historia.getPersonagem().perdeSaude();
        }

        //System.out.println("saude getPersonagem(): " + historia.getPersonagem().getSaude() + "\n");

//        if(capitulo.getMorre() == true){
//            scanner.close();
//            return ;
//        }

//        if(historia.getPersonagem().getSaude() <= 0){
//            System.out.println("saude getPersonagem(): " + historia.getPersonagem().getSaude() + "\n");
//            scanner.close();
//            return ;
//        }

//        System.out.println(
//                "Opção 01: " + capitulo.getEscolha01().getTitulo() + "\n" +
//                        "Opção 02: " + capitulo.getEscolha02().getTitulo() + "\n" +
//                        "Digite sua opção ou 0 para sair e salvar"
//        );

        int escolha = scanner.nextInt();

        if(escolha == 0){
            salvarEstado(capitulo);
            scanner.close();
            return;
        }

        if (escolha == 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("...");
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Capitulo aux = capitulo.getEscolha01();
            escolher(aux);
        } else if (escolha == 2) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("...");
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Capitulo aux = capitulo.getEscolha02();
            escolher(aux);
        }
        scanner.close();
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
        if(estado.exists()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Progresso encontrado: Desejá continuar de onde parou? \n S/n: ");
            String opcao = scanner.nextLine();
            System.out.println(opcao);
            if(opcao.equals("s") || opcao.equals("s")){
                FileReader arquivo = new FileReader(estado);
                JsonReader json = new JsonReader(arquivo);
                Gson gson = new Gson();
                historia.setEstado(gson.fromJson(json, State.class));
                historia.setInicio(historia.getEstado().getCapituloAtual());
                historia.getPersonagem().setSaude(historia.getEstado().getSaudeAtual());
                historia.getPersonagem().recuperaSaude();
                historia.getPersonagem().setNome(historia.getEstado().getNome());
                return;
            }else{
                estado.delete();
            }
        }
    }
}
