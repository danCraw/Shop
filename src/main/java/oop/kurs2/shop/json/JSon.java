package oop.kurs2.shop.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import oop.kurs2.shop.services.ShopWorkService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSon {

   public JSon(){

    }

    public FileWriter serialize(ShopWorkService shopWorkService, String fileName) throws IOException {
        Gson gson = initGSon();
        FileWriter fileWriter = new FileWriter(fileName);
        gson.toJson(shopWorkService, fileWriter);
        fileWriter.close();
        return fileWriter;
    }

    public ShopWorkService deserialize(String fileName) throws FileNotFoundException {
        Gson gson = initGSon();
        FileReader fileReader = new FileReader(fileName);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, ShopWorkService.class);
    }

    private Gson initGSon() {
        return new GsonBuilder().setPrettyPrinting()
//                .registerTypeAdapter(ProductLocation.class, new ProductLocationConverter())
//                .registerTypeAdapter(ProductsType.class, new ProductTypeConverter())
//                .registerTypeAdapter(ShopWork.class, new ShopWorkConverter())
//                .enableComplexMapKeySerialization()
                .create();
    }
}

