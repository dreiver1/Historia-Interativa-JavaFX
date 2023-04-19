package com.example.historiainterativap2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Historia {
    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Capitulo getInicio() {
        return inicio;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    private Personagem personagem = new Personagem();
    private Capitulo inicio;
    private State estado;

    public void salvarHistoria(){
        try {
            Gson gson = new Gson();
            File arquivo = new File("src/main/java/com/example/historiainterativap2/assets/historia.json");
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
        File historia = new File("src/main/java/com/example/historiainterativap2/assets/historia.json");
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