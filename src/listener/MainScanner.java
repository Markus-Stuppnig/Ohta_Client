package listener;

import java.util.Scanner;

import main.Main;

public class MainScanner implements Runnable{

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;

        System.out.println("Username:");
        Main.setup(scanner.nextLine());
        for(String s : Main.connectionHandler.usersCurrentlyOnline) System.out.println(s);

        while(true) {

            message = scanner.nextLine();

            if(message.equals("!exit")) {
                Main.connectionHandler.sendMessage("Disconnect");
                scanner.close();
                Main.shutdown();

            }
            else if(message.equals("!force-exit")) {

                scanner.close();
                Main.connectionHandler.sendMessage("force-exit");
                Main.shutdown();

            }
            else if(message.startsWith("!messageto ")) {

                Main.connectionHandler.userSelected = message.substring("!messageto ".length());

            }
            else if(message.startsWith("!getchat ")) {

                if(Main.chathistory.getArray(message.substring("!getchat ".length())) != null) {

                    for(String a : Main.chathistory.getArray(message.substring("!getchat ".length()))) System.out.println(a);
                }
            }
            else if(message.equals("!getusers")) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}

                for(String s : Main.connectionHandler.usersCurrentlyOnline) System.out.println(s);
            }
            else if(!message.startsWith("!") && !message.equals("")) Main.connectionHandler.sendMessagetoClient(message);

        }

    }

}