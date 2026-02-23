public class ThreadsExample2
{
	public static void main (String[] args)
	{
		try
		{
			for (int id = 1; id <= 5; id++)
			{
				Thread t = new Thread (new ExampleThread (id));
				t.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static class ExampleThread implements Runnable
	{
		private int id;

		public ExampleThread (int id) {
			this.id = id;
		}

		public void run()
		{
			try
			{
				String name = "Thread-" + id;
				System.err.println (name + ": Hola");

				for (int i = 0; i < 5; i++)
				{
					Thread.sleep (1000);
					System.err.println (name + ": i = " + i);
				}
				Thread.sleep (1000);
				System.err.println (name + ": Adeu");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
