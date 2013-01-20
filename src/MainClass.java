import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {
		char[] chars = new char[16];

		// System.out.println("Insert 16 chars.");
		// for (int i = 0; i < 16; i++)
		// chars[i] = (char) System.in.read();

		chars = args[0].toCharArray();

		Ruzzle r = new Ruzzle(chars);

		//r.printSchema();

		r.findWords();

		r.printSortedAlphabetically();
		//r.printSortedByLength();
	}

}
