package by.bsu.study_practise;

import java.sql.Timestamp;
import java.util.UUID;

public class ChatMessage {
    private UUID id;
    private String message;
    private String author;
    private Timestamp timestamp;

    public ChatMessage(String message, String author, Timestamp timestamp) {
        id = UUID.randomUUID();
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public ChatMessage(UUID id, String message, String author, Timestamp timestamp) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\r\nmessage: " + message +
                "\r\nauthor: " + author +
                "\r\ntimestamp: " + timestamp;
    }
}
