import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UserMain {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int serverPort = 4000;
        String serverIp = "localhost";
        try {
            Socket socket = new Socket(serverIp, serverPort);

            Thread readMessageTask = new Thread(new ReadTask(socket));
            Thread sendMessageTask = new Thread(new WriteTask(socket, sc));

            sendMessageTask.start();
            readMessageTask.start();

            sendMessageTask.join();
            readMessageTask.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}