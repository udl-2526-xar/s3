import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ConcurrentServer
{
	private static int port = 2344;

	public static void main (String[] args)
	{
		int id = 0;
		try
		{
			ServerSocket ss = new ServerSocket (port);

			for (;;)
			{
				Socket s = ss.accept();
				Thread t = new Thread (new Server (s, true, null), "Servidor-" + (++id));
				t.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static class Server implements Runnable
	{
		private Socket s;
		private Boolean creaUnFill;
		private Thread pare;

		public Server (Socket s, Boolean creaUnFill, Thread pare)
		{
			this.s = s;
			this.creaUnFill = creaUnFill;
			this.pare = pare;
		}

		public void run()
		{
			try
			{
				String name = Thread.currentThread().getName();
				System.err.println (name + ": Connexió acceptada.");
				DataInputStream  dis = new DataInputStream  (s.getInputStream());
				DataOutputStream dos = new DataOutputStream (s.getOutputStream());
				String str = "";

				if (this.creaUnFill){
					System.out.println (name + ": Creant un fill.");
					Thread t = new Thread (new Server (s, false, Thread.currentThread()), "Fill");
					t.start();
					System.out.println(name + ": He creat un fill i em poso a dormir 5s");
					Thread.sleep(5000);
					System.out.println(name + ": Acabo");
					return;
				}

				System.out.println (name + ": Primer primer espero que el pare acabi");
				pare.join();
				System.out.println (name + ": Ara si, vaig a respondre al client");

				while (!str.equals ("FI"))
				{
					str = dis.readUTF();
					System.err.println (name + ": He rebut el missatge \"" + str + "\"");
					str = str.toUpperCase();
					dos.writeUTF (str);
					dos.flush();
				}
				dis.close();
				dos.close();
				s.close();
				System.err.println (name + ": Connexió tancada.");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
