package by.bsu.study_practise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class JsonHandler {
    private String inputFile;

    public JsonHandler(String inFile) {
        inputFile = inFile;
    }

    public HashMap readJsonFile(String file) {
        //System.out.println("JsonReadFileMethod!");
        HashMap<Long, ChatMessage> chatMessages = new HashMap<>();
        JSONArray obj = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONArray) parser.parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Iterator it = obj.iterator();
        while (it.hasNext()) {
            JSONObject jObj = (JSONObject) it.next();
            String mes = (String) jObj.get("message");
            String author = (String) jObj.get("author");
            UUID id = UUID.fromString((String) jObj.get("id"));
            Timestamp tStamp = new Timestamp((long)jObj.get("timestamp"));
            ChatMessage chMes = new ChatMessage(id, mes, author, tStamp);
            chatMessages.put(tStamp.getTime(), chMes);
        }
        //System.out.println(obj);
        return chatMessages;
    }

    public void writeJsonFile(String file, HashMap hm) {
        System.out.println("JsonWriteFileMethod!");
        HashMap<Long, ChatMessage> chatMessages = hm;
        JSONArray arr = new JSONArray();
        for (ChatMessage chm: chatMessages.values()) {
            JSONObject obj = new JSONObject();
            obj.put("id", chm.getId().toString());
            obj.put("author", chm.getAuthor());
            obj.put("timestamp", chm.getTimestamp().getTime());
            obj.put("message", chm.getMessage());
            arr.add(obj);
        }
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
