package by.bsu.study_practise;

import java.io.*;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        /*Date date = new Date();
        Timestamp tst = new Timestamp(date.getTime());
        System.out.println(tst.getTime());
        UUID id = UUID.randomUUID();
        System.out.println(id);*/
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
        //boolean isWorking = true;
        Scanner sc = new Scanner(System.in);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("logfile.txt"));
            String command;
            int com = sc.nextInt();
            while (com!=0) {
                switch (com) {
                    case 1: {
                        command = sc.next();
                        chat.addMessages(command);
                        break;
                    }
                    case 2: {
                        chat.writeMessages(sc.next());
                        break;
                    }
                    case 3: {
                        sc.nextLine();
                        String message = sc.nextLine();
                        String author = sc.nextLine();
                        String tstamp = sc.nextLine();
                        long stamp = 0;
                        try {
                            stamp = Long.parseLong(tstamp);
                        } catch (Exception e) {
                            String errMessage = "Wrong message timestamp: " + tstamp + ".\n";
                            bw.write("Add new massage: " + errMessage);
                        }
                        ChatMessage chMes = new ChatMessage(message, author, new Timestamp(stamp));
                        chat.addMessage(chMes);
                        break;
                    }
                    case 4: {
                        chat.viewByTime();
                        break;
                    }
                    case 5: {
                        UUID id = UUID.fromString(sc.next());
                        if(chat.deleteById(id)){
                            System.out.println("Message with "+id.toString()+" id deleted.");
                        }else{
                            System.out.println("There is no message with "+id+" id.");
                        }
                        break;
                    }
                    case 6: {
                        break;
                    }
                    case 7: {
                        break;
                    }
                    case 8: {
                        break;
                    }
                    case 9: {
                        break;
                    }
                }
                com = sc.nextInt();
            }
            bw.close();
            sc.close();
            System.out.println("Program has finished work.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
