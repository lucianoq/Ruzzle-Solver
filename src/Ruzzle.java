import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

class LengthComparator implements java.util.Comparator<String> {
	public static final int	ASC	= 1;
	public static final int	DESC	= -1;

	private int					type;

	public LengthComparator() {
		this.type = ASC;
	}

	public LengthComparator(int type) {
		this.type = type;
	}

	@Override
	public int compare(String o1, String o2) {
		return type * (o1.length() - o2.length());
	}
}

public class Ruzzle {

	public GenerateThread[]		threads;
	public ArrayList<Node>		nodes;
	public Dictionary				diz;
	public TreeSet<String>		wordsSaid;
	public ArrayList<String>	wordsSortedLength;
	public ArrayList<String>	wordsSortedAlph;

	public Ruzzle(char[] list) {
		nodes = new ArrayList<Node>(16);
		diz = new Dictionary();
		wordsSaid = new TreeSet<String>();

		int i = 0;
		int j = 0;

		assert (list.length == 16);
		for (char c : list) {
			Node n = new Node(c, i, j);
			nodes.add(n);

			i++;
			if (i == 4) {
				i = 0;
				j++;
			}
		}
	}

	private void generateAll() {
		// System.out.println("\nAVVIO I THREAD");
		// MainClass.now = System.currentTimeMillis();
		// MainClass.when();

		threads = new GenerateThread[8];

		for (int i = 0; i < 8; i++) {
			ArrayList<Node> t = new ArrayList<Node>(16);
			for (Node n : this.nodes) {
				t.add(n.clone());
			}

			threads[i] = new GenerateThread(t, i, i + 8);
			threads[i].start();
		}

		for (int i = 0; i < 8; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// System.out.println("PAROLE GENERATE: " + wordsAll.size());

		// MainClass.when();
	}

	public void printSchema() {
		int i = 0;
		for (Node n : nodes) {
			System.out.print(n.ch + " ");
			i++;
			if (i % 4 == 0)
				System.out.println();
		}
	}

	public void printSortedAlphabetically() {
		wordsSortedAlph = new ArrayList<String>(wordsSaid.size() + 1);
		wordsSortedAlph.addAll(wordsSaid);
		Collections.sort(wordsSortedAlph);
		for (String s : wordsSortedAlph) {
			System.out.print(s + "\t");
		}
		System.out.println();
	}

	public void printSortedByLength() {
		wordsSortedLength = new ArrayList<String>(wordsSaid.size() + 1);
		wordsSortedLength.addAll(wordsSaid);
		Collections.sort(wordsSortedLength, new LengthComparator(LengthComparator.DESC));
		for (String s : wordsSortedLength) {
			System.out.print(s + "\t");
		}
		System.out.println();
	}

	public void findWords() {
		this.generateAll();

		for (GenerateThread gt : threads) {
			for (String s : gt.generated)
				if (!wordsSaid.contains(s))
					if (s.length() > 1)
						if (diz.diz.contains(s))
							wordsSaid.add(s);
		}
	}

}