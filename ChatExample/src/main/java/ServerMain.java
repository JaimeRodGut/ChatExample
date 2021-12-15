import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int port = 4000;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Esperando usuario...");
            Socket socket = serverSocket.accept();

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
