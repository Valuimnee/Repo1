package by.bsu.study_practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Chat {
    private HashMap<Long, ChatMessage> messages;

    public Chat() {
        messages = new HashMap<>();
    }

    public void addMessages(String file) {
        JsonHandler jsonHandler = new JsonHandler();
        HashMap<Long, ChatMessage> newMess = jsonHandler.readJsonFile(file);
        if (newMess.size() != 0) {
            messages.putAll(newMess);
            System.out.println("Messages added.");
        }
    }

    public void writeMessages(String file) {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.writeJsonFile(file, messages);
        System.out.println("Messages written.");
    }

    public void addMessage(ChatMessage mes) {
        messages.put(mes.getTimestamp().getTime(), mes);
        System.out.println(mes);
    }

    public void viewByTime() {
        Set<Long> set = messages.keySet();
        Long tArr[] = new Long[messages.size()];
        set.toArray(tArr);
        Arrays.sort(tArr);
        for (Long time : tArr) {
            System.out.println(messages.get(time));
        }
    }

    public boolean deleteById(UUID id) {
        for (ChatMessage chm : messages.values()) {
            if (chm.getId().compareTo(id) == 0) {
                messages.remove(chm.getTimestamp().getTime(), chm);
                return true;
            }
        }
        return false;
    }
}
