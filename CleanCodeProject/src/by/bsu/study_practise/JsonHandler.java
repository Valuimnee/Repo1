package by.bsu.study_practise;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

public class JsonHandler {
    public HashMap<Long, ChatMessage> readJsonFile(String file) {
        //System.out.println("JsonReadFileMethod!");
        HashMap<Long, ChatMessage> chatMessages = new HashMap<>();
        JsonArray arrObj = new JsonArray();
        try {
            JsonParser parser = new JsonParser();
            arrObj = (JsonArray) parser.parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Object obj : arrObj) {
            JsonObject jObj = (JsonObject) obj;
            String mes = jObj.get("message").getAsString();
            String author = jObj.get("author").getAsString();
            UUID id = UUID.fromString(jObj.get("id").getAsString());
            Timestamp tStamp = new Timestamp(jObj.get("timestamp").getAsLong());
            ChatMessage chMes = new ChatMessage(id, mes, author, tStamp);
            chatMessages.put(tStamp.getTime(), chMes);
        }
        //System.out.println(obj);
        return chatMessages;
    }

    public void writeJsonFile(String file, HashMap<Long, ChatMessage> hm) {
        System.out.println("JsonWriteFileMethod!");
        JsonArray arr = new JsonArray();
        hm.values().stream().filter(chm -> chm != null).forEach(chm -> {
            JsonObject obj = new JsonObject();
            obj.addProperty("id", chm.getId().toString());
            obj.addProperty("author", chm.getAuthor());
            obj.addProperty("timestamp", chm.getTimestamp().getTime());
            obj.addProperty("message", chm.getMessage());
            arr.add(obj);
        });
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(arr.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(arr);
    }

}
