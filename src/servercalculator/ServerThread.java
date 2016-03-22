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
    

    public static double sabiranje(String znak, String odKlijenta) {
        String[] brojeviString = odKlijenta.split(znak);
        double[] brojevi = new double[brojeviString.length];
        brojevi[0] = Double.parseDouble(brojeviString[0]);
        brojevi[1] = Double.parseDouble(brojeviString[1]);

        double zbir = brojevi[0] + brojevi[1];
        return zbir;

    }

    public double oduzimanje(String znak, String odKlijenta) {
        String[] brojeviString = odKlijenta.split(znak);
        double[] brojevi = new double[brojeviString.length];
        brojevi[0] = Double.parseDouble(brojeviString[0]);
        brojevi[1] = Double.parseDouble(brojeviString[1]);

        double razlika = brojevi[0] - brojevi[1];
        return razlika;

    }

    public double mnozenje(String znak, String odKlijenta) {
        String[] brojeviString = odKlijenta.split(znak);
        double[] brojevi = new double[brojeviString.length];
        brojevi[0] = Double.parseDouble(brojeviString[0]);
        brojevi[1] = Double.parseDouble(brojeviString[1]);

        double proizvod = brojevi[0] * brojevi[1];
        return proizvod;

    }

    public double deljenje(String znak, String odKlijenta) {
        String[] brojeviString = odKlijenta.split(znak);
        double[] brojevi = new double[brojeviString.length];
        brojevi[0] = Double.parseDouble(brojeviString[0]);
        brojevi[1] = Double.parseDouble(brojeviString[1]);

        double kolicnik = brojevi[0] / brojevi[1];
        return kolicnik;

    }

    @Override
    public void run() {
        try {
            BufferedReader inputStreamClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream outputStreamClient = new PrintStream(clientSocket.getOutputStream());

            String ulazOdKalkulatora = inputStreamClient.readLine();

            if (ulazOdKalkulatora.startsWith("-")) {
                String ulazOdKalkulatoraBezZnaka = ulazOdKalkulatora.substring(1);
                if (ulazOdKalkulatoraBezZnaka.contains("+")) {
                    String[] brojeviString = ulazOdKalkulatoraBezZnaka.split("\\+");
                    double[] brojevi = new double[brojeviString.length];
                    brojevi[0] = Double.parseDouble(brojeviString[0]);
                    brojevi[1] = Double.parseDouble(brojeviString[1]);
                    double zbir = brojevi[1] - brojevi[0];
                    String rezultatZbir = "" + zbir;
                    outputStreamClient.println(rezultatZbir);

                }
                if (ulazOdKalkulatoraBezZnaka.contains("-")) {
                    String[] brojeviString = ulazOdKalkulatoraBezZnaka.split("-");
                    double[] brojevi = new double[brojeviString.length];
                    brojevi[0] = Double.parseDouble(brojeviString[0]);
                    brojevi[1] = Double.parseDouble(brojeviString[1]);
                    double razlika = -brojevi[0] - brojevi[1];

                    String rezultatRazlika = "" + razlika;
                    outputStreamClient.println(rezultatRazlika);

                }
                if (ulazOdKalkulatoraBezZnaka.contains("*")) {
                    String[] brojeviString = ulazOdKalkulatoraBezZnaka.split("\\*");
                    double[] brojevi = new double[brojeviString.length];
                    brojevi[0] = Double.parseDouble(brojeviString[0]);
                    brojevi[1] = Double.parseDouble(brojeviString[1]);
                    double proizvod = -(brojevi[0] * brojevi[1]);

                    String rezultatMnozenje = "" + proizvod;
                    outputStreamClient.println(rezultatMnozenje);

                }
                if (ulazOdKalkulatoraBezZnaka.contains("/")) {
                    String[] brojeviString = ulazOdKalkulatoraBezZnaka.split("/");
                    double[] brojevi = new double[brojeviString.length];
                    brojevi[0] = Double.parseDouble(brojeviString[0]);
                    brojevi[1] = Double.parseDouble(brojeviString[1]);
                    double kolicnik = -(brojevi[1] / brojevi[0]);

                    String rezultatDeljenje = "" + kolicnik;
                    outputStreamClient.println(rezultatDeljenje);

                }

            } else {
                if (ulazOdKalkulatora.contains("+")) {
                    String rezultatZbir = "" + sabiranje("\\+", ulazOdKalkulatora);
                    outputStreamClient.println(rezultatZbir);

                }
                if (ulazOdKalkulatora.contains("-")) {

                    String rezultatRazlika = "" + oduzimanje("-", ulazOdKalkulatora);
                    outputStreamClient.println(rezultatRazlika);

                }
                if (ulazOdKalkulatora.contains("*")) {

                    String rezultatMnozenje = "" + mnozenje("\\*", ulazOdKalkulatora);
                    outputStreamClient.println(rezultatMnozenje);

                }
                if (ulazOdKalkulatora.contains("/")) {

                    String rezultatDeljenje = "" + deljenje("/", ulazOdKalkulatora);
                    outputStreamClient.println(rezultatDeljenje);

                }
            }

            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
