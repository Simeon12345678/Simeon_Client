/* CLIENT */
import java.io.*;
import java.net.*;

public class Main {

    public static void main(String args[]) {
        Socket client = null;

        // Default port number we are going to use
        int portnumber = 3000;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 10; i++) {
            String msg = "";

            try {
                // Create a Client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                // Create BufferedReader for standard input
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Write a SIMPLE math problem ie addition, multiplication, subtraction or division in the format of example 1+2");
                System.out.println("Write bye to exit");

                // Read data from standard input device and write it
                // to the output stream of the client socket.
                msg = stdin.readLine().trim();
                pw.println(msg);

                // Read data from the input stream of the client socket.
                System.out.println("Message returned from the server = " + br.readLine());

                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }

            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}
