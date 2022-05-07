import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MultiTree<T> {
	JFrame g = new JFrame();

	JPanel pannel = new JPanel();
	protected multinode root;
	protected int nbnodes = 1;
	protected multinode current = null;

	public MultiTree() {
		root = null;
	}

	public MultiTree(T data) {
		root = new multinode(data, 1);
	}

	public void setRoot(T data) {
		root = new multinode(data, 1);
	}

	public multinode search_id(int id) {
		current = null;
		search_id(id, root);
		return current;
	}

	public void search_id(int id, multinode node) {
		if (node == null) {
			return;
		}
		if (node.id == id) {
			current = node;
			return;
		} else {
			for (int i = 0; i < node.children.size(); i++) {
				search_id(id, (multinode) node.children.get(i));
			}
		}
	}

	public multinode search_data(T t) {
		current = null;
		search_data(t, root);
		return current;
	}

	public void search_data(T t, multinode node) {
		if (node == null) {
			return;
		}
		T tt = (T) node.getData();
		if (tt == t) {
			current = node;
			return;
		} else {
			for (int i = 0; i < node.children.size(); i++) {
				search_data(t, (multinode) node.children.get(i));
			}
		}
	}

	public boolean insertnode(T data, int Parentid) {
		multinode n = search_id(Parentid);
		if (n != null) {
			n.insertchildren(data, ++nbnodes);
			return true;
		}
		return false;
	}

	public void display() {
		display(root, null);
	}

	public void display(multinode node, multinode nodep) {
		if (node != null) {
			if (nodep != null)
				System.out.println(nodep.getData() + ": " + node.id + ": " + node.getData());
			else
				System.out.println("N: " + node.id + ": " + node.getData());
			for (int i = 0; i < node.children.size(); i++) {
				display(node.getNode(i), node);
			}
		}
	}

	public void display_solution(multinode node) {
		if (node != null) {
			display_solution(node.getParent());
			System.out.println(node.getData());
		}
	}

}
