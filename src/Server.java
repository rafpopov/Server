import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("Минск", 33);
        hashMap.put("Могилев", 26);
        hashMap.put("Гомель", 34);
        hashMap.put("Москва", 21);
        hashMap.put("Санкт-Петербург", 19);

        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket socket = serverSocket.accept();

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            Integer temp = hashMap.get(bufferedReader.readLine());

            if (temp==null) bufferedWriter.write("Об этом городе нет информации");
            else bufferedWriter.write(temp.toString());

            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();

          ///  Thread.sleep(1000);

            bufferedReader.close();
            socket.close();
        }

    }
}
