import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteTask implements Runnable {

    private Socket socket;
    private Scanner sc;

    public WriteTask(Socket socket, Scanner sc) {
        this.socket = socket;
        this.sc = sc;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String message = sc.nextLine();
                writer.println(message);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
