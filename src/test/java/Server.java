import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);

        Socket socket = serverSocket.accept();
        System.in.read();


        while (true) {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[100];

            int n = inputStream.read(bytes);

            System.out.println(new String(bytes, 0, n));
        }

    }
}
