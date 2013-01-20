public class Node {

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

}
