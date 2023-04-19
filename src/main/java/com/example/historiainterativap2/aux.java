package com.example.historiainterativap2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

public class aux {
    public static void main(String[] args) {
        Gson gson = new Gson();
        JsonArray capitulos = new JsonArray();
        Capitulo capitulo1 = new Capitulo("Capítulo 1: A Busca Pelo Tesouro", "Você é um aventureiro em busca de um tesouro lendário. Você chega em uma encruzilhada e precisa decidir qual caminho seguir.");
        Capitulo capitulo11 = new Capitulo("Opção: 01 Seguir o caminho conhecido e mais seguro.", "Você decide seguir o caminho já conhecido, mas descobre que há um grupo de bandidos que bloquearam a estrada. Eles exigem que você pague uma taxa de passagem, mas você não tem dinheiro suficiente. Você tem duas opções:");
        Capitulo capitulo12 = new Capitulo("Opção: 02 Tomar um atalho desconhecido e mais arriscado.", "Você decide tomar um atalho desconhecido e mais arriscado. O caminho é perigoso e você quase cai em um penhasco algumas vezes, mas você chega à base da Montanha Proibida em um tempo recorde. Você tem duas opções");
        Capitulo capitulo111 = new Capitulo("Opção: 01 Tentar convencer os bandidos a deixá-lo passar", "Você tenta convencer os bandidos a deixá-lo passar, mas eles são implacáveis. Eles o obrigam a trabalhar para eles por um tempo antes de permitir que você passe. Você perde muito tempo e chega tarde demais na Montanha Proibida. Você não encontra o tesouro lendário e volta para casa de mãos vazias.");
        Capitulo capitulo112 = new Capitulo("Opção: 02 Lutar contra os bandidos", "Você enfrenta os bandidos em uma batalha difícil, mas consegue vencê-los. Você continua sua jornada e chega à base da Montanha Proibida. Você vê a entrada para a trilha que leva ao topo. Você tem duas opções:");
        Capitulo capitulo1121 = new Capitulo("Opção: 01 Descansar antes de subir a trilha", "Você decide descansar antes de subir a trilha. Infelizmente, enquanto você estava dormindo, os bandidos que você lutou antes te encontraram e roubaram seus suprimentos. Você acorda sem nada e é forçado a voltar para casa com as mãos vazias.");
        Capitulo capitulo1122 = new Capitulo("Opção: 02 Subir mediatamente a trilha", "Você decide subir imediatamente a trilha. A subida é íngreme e difícil, mas você é um aventureiro experiente e consegue chegar ao topo. Lá, você encontra o tesouro lendário e volta para casa como um herói.");
        Capitulo capitulo121 = new Capitulo("Opção: 01 Seguir trilha que leva ao topo da montanha.", "Você segue a trilha que leva ao topo da montanha. A subida é difícil, mas você é um aventureiro experiente e consegue chegar ao topo. Lá, você encontra o tesouro lendário e volta para casa como um herói.");
        Capitulo capitulo122 = new Capitulo("Opção: 02 Procurar por outro caminho mais fácil.", "Você decide procurar por outro caminho mais fácil, mas acaba se perdendo na floresta. Você encontra um grupo de animais selvagens e fica preso lá por um tempo. Quando você finalmente consegue sair da floresta, já é tarde demais. Você perde a chance de encontrar o tesouro lendário e volta para casa de mãos vazias.");
        capitulo111.setperdeperdeSaude();
        capitulo1121.setperdeperdeSaude();
        capitulo1122.setperdeperdeSaude();
        capitulo121.setperdeperdeSaude();
        capitulo122.setperdeperdeSaude();

        capitulos.add(new Gson().toJsonTree(capitulo1));
        capitulos.add(new Gson().toJsonTree(capitulo11));
        capitulos.add(new Gson().toJsonTree(capitulo12));
        capitulos.add(new Gson().toJsonTree(capitulo111));
        capitulos.add(new Gson().toJsonTree(capitulo112));
        capitulos.add(new Gson().toJsonTree(capitulo121));
        capitulos.add(new Gson().toJsonTree(capitulo122));
        capitulos.add(new Gson().toJsonTree(capitulo1121));
        capitulos.add(new Gson().toJsonTree(capitulo1122));
        System.out.println(capitulos.toString());


        try {
            File arquivo = new File("/home/david/Documentos/workspace/historia-interativa-arvore-decisao/src/assets/capitulos01.json");
            FileWriter fileWriter = new FileWriter(arquivo);
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(capitulos, jsonWriter);
            jsonWriter.close();
            fileWriter.close();
            System.out.println("JSONArray salvo com sucesso no arquivo " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar JSONArray no arquivo: " + e.getMessage());
        }
        
    }
}
