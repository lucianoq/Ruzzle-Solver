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

	public ArrayList<Node>		nodes;
	public Dictionary				diz;
	public TreeSet<String>		wordsSaid;
	public TreeSet<String>		wordsAll;
	public ArrayList<String>	wordsFound;

	public Ruzzle(char[] list) {
		nodes = new ArrayList<Node>(16);
		diz = new Dictionary();
		wordsSaid = new TreeSet<String>();
		wordsAll = new TreeSet<String>();

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

	private ArrayList<Node> neighbors(Node n) {
		ArrayList<Node> l = new ArrayList<Node>(8);
		for (Node nn : this.nodes) {
			if (nn.visited == false)
				if ((Math.abs(n.i - nn.i) < 2) && (Math.abs(n.j - nn.j) < 2)) {
					l.add(nn);
				}
		}
		return l;
	}

	private void generateAll() {
		for (Node n : this.nodes) {
			n.visited = true;
			stringsFromN(n, "");
			n.visited = false;
		}

		this.unvisitAll();
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
		for (String s : wordsSaid) {
			System.out.println(s);
		}
	}

	public void printSortedByLength() {
		wordsFound = new ArrayList<String>(wordsSaid.size() + 1);
		wordsFound.addAll(wordsSaid);
		Collections.sort(wordsFound, new LengthComparator(LengthComparator.DESC));
		for (String s : wordsFound) {
			System.out.println(s);
		}
	}

	private void stringsFromN(Node n, String s) {
		n.visited = true;
		String str = s + n.ch;
		wordsAll.add(str);
		for (Node na : neighbors(n)) {
			stringsFromN(na, str);
		}
		n.visited = false;
	}

	public void findWords() {
		this.generateAll();
		for (String s : wordsAll) {
			if (!wordsSaid.contains(s))
				if (s.length() > 1)
					if (diz.diz.contains(s))
						wordsSaid.add(s);
		}
	}

	private void unvisitAll() {
		for (Node n : this.nodes)
			n.visited = false;
	}
}