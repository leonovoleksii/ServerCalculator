import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.concurrent.*;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(7373);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Calculator calculator = new Calculator(clientSocket);
            System.out.println("Client #" + calculator.getId() + " connected");
            exec.execute(calculator);
        }
    }
}
