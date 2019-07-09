import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Calculator calculator;

    public static void main(String[] args) {
        while (true) {
            try {
                serverSocket = new ServerSocket(7373);
                try {
                    System.out.println("Waiting for connection...");
                    clientSocket = serverSocket.accept();
                    System.out.println("Client connected");
                    try {
                        InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                        OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
                        in = new BufferedReader(isr);
                        out = new BufferedWriter(osw);
                        calculator = new Calculator(osw);
                        String request;
                        while (true) {
                            request = in.readLine();
                            System.out.println("Client requested " + request + " to be calculated");
                            if (request.equals("quit")) {
                                out.write("Bye!");
                                out.flush();
                                break;
                            }
                            calculator.calculate(request);
                            System.out.println("Server calculated the request and returned the result");
                        }
                    } finally {
                        System.out.println("Client disconnected");
                        clientSocket.close();
                        in.close();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
