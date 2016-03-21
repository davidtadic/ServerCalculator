package servercalculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCalculator {

    public static void main(String[] args) throws IOException {
        int port = 4813;

        try {
            ServerSocket server = new ServerSocket(port);

            while (true) {
                Socket clientSocket = server.accept();
                ServerThread thread = new ServerThread(clientSocket);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }

}
