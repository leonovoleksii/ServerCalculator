import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Client {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Client 'host ip' 'port'");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String request, answer;
            do {
                System.out.print(">>> ");
                request = reader.readLine();
                out.write(request + "\n");
                out.flush();
                answer = in.readLine();
                System.out.println(answer);
            } while (!request.equals("quit"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("You are disconnected");
        }
    }
}