package by.bsu.study_practise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class JsonHandler {
    private String inputFile;

    public JsonHandler(String inFile) {
        inputFile = inFile;
    }

    public List readJsonFile(String file) {
        System.out.println("JsonReadFileMethod!");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while (str != null) {
                sb.append(str);
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        JSONArray obj = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONArray) parser.parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Iterator it = obj.iterator();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        while (it.hasNext()) {
            JSONObject jObj = (JSONObject) it.next();
            String mes = (String) jObj.get("message");
            String author = (String) jObj.get("author");
            UUID id = UUID.fromString((String) jObj.get("id"));
            Timestamp tStamp = new Timestamp((long)jObj.get("timestamp"));
            ChatMessage chMes = new ChatMessage(id, mes, author, tStamp);
            chatMessages.add(chMes);
        }
        System.out.println(obj);
        return chatMessages;
    }
}
