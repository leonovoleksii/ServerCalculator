import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.concurrent.*;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Server 'host ip' 'port'");
            return;
        }
        ExecutorService exec = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket(7373);
        while (true) {
            clientSocket = serverSocket.accept();
            Calculator calculator = new Calculator(clientSocket);
            System.out.println("Client #" + calculator.getId() + " connected");
            exec.execute(calculator);
        }
    }
}
