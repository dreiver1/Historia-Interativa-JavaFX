import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

public class App {

    public static JsonArray recuperaCapitulos(){
        try {
            File capitulos = new File("src/assets/capitulos.json");
            FileReader fileReader = new FileReader(capitulos);
            JsonReader jsonReader = new JsonReader(fileReader);
            Gson gson = new Gson();
            JsonArray array = gson.fromJson(jsonReader, JsonArray.class);
            jsonReader.close();
            fileReader.close();
            return array;
        } catch (IOException e) {
            JsonArray array = new JsonArray();
            return array;
        }
    }

    public static void salvarHistoria(Historia m){
        try {
            File arquivo = new File("/home/david/Documentos/workspace/historia-interativa-arvore-decisao/src/assets/historia.json");
            FileWriter fileWriter = new FileWriter(arquivo);
            Gson gson = new Gson();
            gson.toJson(m, fileWriter);
            fileWriter.close();
            return ;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        JsonArray capitulos = recuperaCapitulos();
        Historia historia = new Historia();
        historia.buildArvoreHistoria(capitulos);
        historia.executar();
        // salvarHistoria(historia);
    }


}
