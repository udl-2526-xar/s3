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
				Thread t = new Thread (new Server (s), "Servidor-" + (++id));
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

		public Server (Socket s)
		{
			this.s = s;
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
