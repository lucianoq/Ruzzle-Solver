public class Node implements Cloneable {

	public char		ch;
	public int		i;
	public int		j;
	public boolean	visited;

	public Node() {
		ch = 0;
		i = -1;
		j = -1;
		visited = false;
	}

	public Node(char ch, int i, int j) {
		this.ch = ch;
		this.i = i;
		this.j = j;
		this.visited = false;
	}

	@Override
	public Node clone() {
		Node n = new Node();
		n.ch = this.ch;
		n.i = this.i;
		n.j = this.j;
		n.visited = false;
		return n;
	}

}
