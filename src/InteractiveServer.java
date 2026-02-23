//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    IO.println(String.format("Hello soc l'InteractiveServer"));
    try{
        System.out.println("Esperant client");
        ServerSocket ss = new ServerSocket(2234);
        for(;;){
            Socket s = ss.accept();
            System.out.println("Established connection");
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String liniaLlegida = "";
            String liniaTransformada;

            while(!liniaLlegida.equals("FI")){
                liniaLlegida = din.readUTF();
                System.out.println("He llegit: " + liniaLlegida);
                liniaTransformada = liniaLlegida.toUpperCase();
                dout.writeUTF(liniaTransformada);
                dout.flush();
            }

            din.close();
            dout.close();
            s.close();
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
