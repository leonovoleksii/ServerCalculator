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
        try {
            socket = new Socket("localhost", 7373);
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