import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

public class Historia{
    private Personagem personagem = new Personagem();
    private Capitulo inicio;
    private State estado;

    public void executar() throws InterruptedException, IOException{
        System.out.print("\033[H\033[2J");
        restaurarEstado();
        escolher(this.inicio);    
    }

    private void escolher(Capitulo capitulo) throws InterruptedException, IOException {
        System.out.print("\033[H\033[2J");
        Scanner scanner = new Scanner(System.in);
        System.out.println(capitulo.getTitulo() + '\n');
        System.out.println(capitulo.getContexto() + '\n');



        if(capitulo.getperdeperdeSaude() == true){
            this.personagem.perdeSaude();
        }

        System.out.println("saude personagem: " + this.personagem.getSaude() + "\n");

        

        if(capitulo.getMorre() == true){
            scanner.close();
            return ;
        }

        if(this.personagem.getSaude() <= 0){
            System.out.println("saude personagem: " + this.personagem.getSaude() + "\n");
            scanner.close();
            return ;
        }

        System.out.println(
            "Opção 01: " + capitulo.getEscolha01().getTitulo() + "\n" + 
            "Opção 02: " + capitulo.getEscolha02().getTitulo() + "\n" + 
            "Digite sua opção ou 0 para sair e salvar"
            );

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

    public void salvarHistoria(){
        try {
            Gson gson = new Gson();
            File arquivo = new File("src/assets/historia.json");
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(arquivo);
            Historia aux = this;
            String json = gson.toJson(aux);
            fileWriter.write(json);
            fileWriter.close();
            return ;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void recuperarHistoria() throws IOException{
        File historia = new File("src/assets/historia.json");
        if(historia.exists()){
            FileReader historiaJson = new FileReader(historia);
            Gson gson = new Gson();
            JsonReader jsonRD = new JsonReader(historiaJson);
            Historia aux = gson.fromJson(jsonRD, Historia.class);
            this.estado = aux.estado;
            this.inicio = aux.inicio;
            this.personagem = aux.personagem;
        }
    }
    
    private void salvarEstado(Capitulo capitulo) throws IOException {
        Gson gson = new Gson();
        File arquivo = new File("src/assets/estado.json");
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        this.estado = new State();
        this.estado.setSaudeAtual(this.personagem.getSaude());
        this.estado.setNome(this.personagem.getNome());
        this.estado.setCapituloAtual(capitulo);
        FileWriter fileWriter = new FileWriter(arquivo);
        String json = gson.toJson(this.estado);
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
                this.estado = gson.fromJson(json, State.class);
                this.inicio = this.estado.getCapituloAtual();
                this.personagem.setSaude(this.estado.getSaudeAtual());
                this.personagem.recuperaSaude();
                this.personagem.setNome(this.estado.getNome());
                return;
            }else{
                estado.delete();
            }
        }
    }

    public void setInicio(Capitulo inicio) {
        this.inicio = inicio;
    }

    public void buildArvoreHistoria(JsonArray a){
        Gson gson = new Gson();
        if(this.inicio == null){
            String json = a.get(0).toString();
            Capitulo aux = gson.fromJson(json, Capitulo.class);
            this.inicio = aux;
        }
        buildArvore(a);
    }

    private void buildArvore(JsonArray a) {
        Gson gson = new Gson();
        Capitulo inicio = this.inicio;
        String _escolha01 = a.get(1).toString();
        String _escolha02 = a.get(2).toString();
        Capitulo escolha01 = gson.fromJson(_escolha01, Capitulo.class);
        Capitulo escolha02 = gson.fromJson(_escolha02, Capitulo.class);

        inicio.setEscolha01(escolha01);
        inicio.setEscolha02(escolha02);
        buildArvore(inicio.getEscolha01(), a, 1);
        buildArvore(inicio.getEscolha02(), a, 2);
    }

    private void buildArvore(Capitulo pai, JsonArray a, int i) {
        if(i < a.size()/2){
            Gson gson = new Gson();
            String _escolha01 = a.get(i*2+1).toString();
            String _escolha02 = a.get(i*2 + 2).toString();
            Capitulo escolha01 = gson.fromJson(_escolha01, Capitulo.class);
            Capitulo escolha02 = gson.fromJson(_escolha02, Capitulo.class);
            pai.setEscolha01(escolha01);
            pai.setEscolha02(escolha02);
            buildArvore(pai.getEscolha01(), a, i+1);
            buildArvore(pai.getEscolha02(), a, i+2);
        }
    }

    
}
