package by.bsu.study_practise;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.writeConditions("CooperationInterface.txt");
        main.chatOperations();
    }

    public void writeConditions(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void chatOperations() {
        Chat chat = new Chat();
        boolean isWorking = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("logfile.txt"));
            String command;
            //int command;
            while (isWorking) {
                //command = sc.nextInt();
                command = br.readLine();
                switch (command) {
                    case "0": {
                        isWorking = false;
                    }
                    break;
                    case "1": {
                        command = br.readLine();
                        chat.addMessages(command);
                    }
                    break;
                    case "2": {
                        chat.writeMessages(br.readLine());
                    }
                    break;
                    case "3": {
                        String message = br.readLine();
                        String author = br.readLine();
                        String tstamp = br.readLine();
                        Date date = new Date();
                        try {
                            date = dateFormat.parse(tstamp);
                        } catch (ParseException e) {
                            String errMessage = "Wrong message timestamp: " + tstamp + ".\n";
                            bw.write("Add new massage: " + errMessage);
                        }
                        ChatMessage chMes = new ChatMessage(message, author, new Timestamp(date.getTime()));
                        chat.addMessage(chMes);
                    }
                    break;
                    case "4": {
                    }
                    break;
                    case "5": {
                    }
                    break;
                    case "6": {
                    }
                    break;
                    case "7": {
                    }
                    break;
                    case "8": {
                    }
                    break;
                    case "9": {
                    }
                    break;
                }
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
