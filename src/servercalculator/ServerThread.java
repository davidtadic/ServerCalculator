package servercalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {

    Socket clientSocket = null;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inputStreamClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream outputStreamClient = new PrintStream(clientSocket.getOutputStream());

            String ulazOdKalkulatora = inputStreamClient.readLine();

            if (ulazOdKalkulatora.contains("+")) {
                String[] brojeviString = ulazOdKalkulatora.split("\\+");
                double[] brojevi = new double[brojeviString.length];
                brojevi[0] = Double.parseDouble(brojeviString[0]);
                brojevi[1] = Double.parseDouble(brojeviString[1]);

                double zbir = brojevi[0] + brojevi[1];
                String rezultatZbir = "" + zbir;
                outputStreamClient.println(rezultatZbir);

            }
            if (ulazOdKalkulatora.contains("-")) {
                String[] brojeviString = ulazOdKalkulatora.split("-");
                double[] brojevi = new double[brojeviString.length];
                brojevi[0] = Double.parseDouble(brojeviString[0]);
                brojevi[1] = Double.parseDouble(brojeviString[1]);

                double razlika = brojevi[0] - brojevi[1];
                String rezultatRazlika = "" + razlika;
                outputStreamClient.println(rezultatRazlika);

            }
            if (ulazOdKalkulatora.contains("*")) {
                String[] brojeviString = ulazOdKalkulatora.split("\\*");
                double[] brojevi = new double[brojeviString.length];
                brojevi[0] = Double.parseDouble(brojeviString[0]);
                brojevi[1] = Double.parseDouble(brojeviString[1]);

                double mnozenje = brojevi[0] * brojevi[1];
                String rezultatMnozenje = "" + mnozenje;
                outputStreamClient.println(rezultatMnozenje);

            }
            if (ulazOdKalkulatora.contains("/")) {
                String[] brojeviString = ulazOdKalkulatora.split("/");
                double[] brojevi = new double[brojeviString.length];
                brojevi[0] = Double.parseDouble(brojeviString[0]);
                brojevi[1] = Double.parseDouble(brojeviString[1]);

                double deljenje = brojevi[0] / brojevi[1];
                String rezultatDeljenje = "" + deljenje;
                outputStreamClient.println(rezultatDeljenje);

            }
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
