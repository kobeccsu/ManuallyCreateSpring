import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8081);

        OutputStream outputStream = socket.getOutputStream();

        int i = 1;
        while (true) {
            System.out.println(i++);
            outputStream.write("1".getBytes());

        }

    }

}
