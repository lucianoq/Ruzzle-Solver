import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Dictionary {

	public TreeSet<String>	diz;

	public Dictionary() {
		diz = new TreeSet<String>();
		File name = new File("../dicts/it.txt");
		if (name.isFile()) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(name));
				String word;
				while ((word = input.readLine()) != null)
					diz.add(word);

				input.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}
