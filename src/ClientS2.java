import java.io.*;
import java.net.Socket;

public class ClientS2 {
    public static void main(String[] args) throws IOException {
        try{
            Socket s = new Socket("127.0.0.1", 2344);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String liniaLlegidaDeLaConsola = "";
            String liniaRebudaDelServer;

            System.out.println("Escriu el que vulguis per enviar-ho al server");
            System.out.println("Escriu 'FI' per finalitzar");
            while(!liniaLlegidaDeLaConsola.equals("FI")){
                liniaLlegidaDeLaConsola = br.readLine();
                dout.writeUTF(liniaLlegidaDeLaConsola);
                dout.flush();
                liniaRebudaDelServer = din.readUTF();
                System.out.println("He rebut del server:" + liniaRebudaDelServer);
            }
            din.close();
            dout.close();
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
