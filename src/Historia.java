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
    private Gson gson = new Gson();

    public void executar() throws InterruptedException, IOException{
        restaurarEstadoO();
        System.out.print("\033[H\033[2J");
        System.out.println("saude personagem: " + this.personagem.getSaude() + "\n");
        Capitulo aux = this.inicio;
        escolher(aux);
        
    }

    private void restaurarEstadoO() throws FileNotFoundException {
        File estado = new File("src/assets/estado.json");
        if(estado.exists()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Desejá continuar de onde parou? \n 1 = s, 0 = n: ");
            int opcao = scanner.nextInt();
            System.out.println(opcao);
            if(opcao == 1){
                FileReader arquivo;
                arquivo = new FileReader(estado);
                JsonReader json = new JsonReader(arquivo);
                this.estado = this.gson.fromJson(json, State.class);
                this.inicio = this.estado.getCapituloAtual();
                this.personagem.setSaude(this.estado.getSaudeAtual());
                this.personagem.recuperaSaude();
                this.personagem.setNome(this.estado.getNome());
            }else{
                estado.delete();
            }
        }
    }

    private void escolher(Capitulo capitulo) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(capitulo.getTitulo());
        System.out.println(" ");
        System.out.println(capitulo.getContexto());
        System.out.println(" ");

        if(capitulo.getperdeperdeSaude() == true){
            this.personagem.perdeSaude();
        }
        System.out.println("saude personagem: " + this.personagem.getSaude() + "\n");
        if(capitulo.getMorre() == true){
            return ;
        }
        if(this.personagem.getSaude() <= 0){
            System.out.println("saude personagem: " + this.personagem.getSaude() + "\n");
            return ;
        }

        System.out.println(capitulo.getEscolha01().getTitulo() + "\n");
        System.out.println(capitulo.getEscolha02().getTitulo() + "\n");

        System.out.println("Digite sua opção ou 0 para sair e salvar");

        int escolha = scanner.nextInt();

        if(escolha == 0){
            File arquivo = new File("/home/david/Documentos/workspace/historia-interativa-arvore-decisao/src/assets/estado.json");
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            this.estado = new State();
            this.estado.setSaudeAtual(this.personagem.getSaude());
            this.estado.setNome(this.personagem.getNome());
            this.estado.setCapituloAtual(capitulo);
            FileWriter fileWriter = new FileWriter(arquivo);
            this.gson.toJson(this.estado, fileWriter);
            fileWriter.close();
            return ;
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

    public void setInicio(Capitulo inicio) {
        this.inicio = inicio;
    }

    public void buildArvoreHistoria(JsonArray a){
        if(this.inicio == null){
            String json = a.get(0).toString();
            Capitulo aux = this.gson.fromJson(json, Capitulo.class);
            this.inicio = aux;
        }
        buildArvore(a);
    }

    private void buildArvore(JsonArray a) {
        Capitulo inicio = this.inicio;
        String _escolha01 = a.get(1).toString();
        String _escolha02 = a.get(2).toString();
        Capitulo escolha01 = this.gson.fromJson(_escolha01, Capitulo.class);
        Capitulo escolha02 = this.gson.fromJson(_escolha02, Capitulo.class);

        inicio.setEscolha01(escolha01);
        inicio.setEscolha02(escolha02);
        buildArvore(inicio.getEscolha01(), a, 1);
        buildArvore(inicio.getEscolha02(), a, 2);
    }

    private void buildArvore(Capitulo pai, JsonArray a, int i) {
        if(i < a.size()/2){
            String _escolha01 = a.get(i*2+1).toString();
            String _escolha02 = a.get(i*2 + 2).toString();
            Capitulo escolha01 = this.gson.fromJson(_escolha01, Capitulo.class);
            Capitulo escolha02 = this.gson.fromJson(_escolha02, Capitulo.class);
            pai.setEscolha01(escolha01);
            pai.setEscolha02(escolha02);
            buildArvore(pai.getEscolha01(), a, i+1);
            buildArvore(pai.getEscolha02(), a, i+2);
        }
    }
}
