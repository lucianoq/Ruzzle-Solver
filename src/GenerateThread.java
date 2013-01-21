import java.util.ArrayList;

public class GenerateThread extends Thread {

	private ArrayList<Node>		nodes;
	private int						elem1;
	private int						elem2;
	public ArrayList<String>	generated;

	public GenerateThread(ArrayList<Node> nodes, int elem1, int elem2) {
		this.generated = new ArrayList<String>(2000000);
		this.nodes = nodes;
		this.elem1 = elem1;
		this.elem2 = elem2;
	}

	public void run() {
		Node n;

		n = nodes.get(elem1);
		n.visited = true;
		stringsFromN(n, "");
		n.visited = false;

		n = nodes.get(elem2);
		n.visited = true;
		stringsFromN(n, "");
		n.visited = false;
	}

	private void stringsFromN(Node n, String s) {
		n.visited = true;
		String str = s + n.ch;
		generated.add(str);
		for (Node na : neighbors(n)) {
			stringsFromN(na, str);
		}
		n.visited = false;
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

}
