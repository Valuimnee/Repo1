package by.bsu.study_practise;

import java.util.ArrayList;

public class Chat {
    private ArrayList<ChatMessage> messages;

    public Chat() {
        messages = new ArrayList<>();
    }

    public void addMessages(String file) {
        JsonHandler jsonHandler = new JsonHandler(file);
        messages.addAll(jsonHandler.readJsonFile(file));
    }

    public void writeMessages(String file) {

    }

    public void addMessage(ChatMessage mes) {

    }
}
