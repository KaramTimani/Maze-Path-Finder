
import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SearchingTechniques {

	JFrame g = new JFrame();

	JPanel pannel = new JPanel();
	protected MultiTree<table> tree;
	protected int jDepth = 100;
	protected int jBreadth = 18000;
	protected boolean found = false;
	public static int jUCS = 500;
	protected int jAStar = 3500;

	public static table apply(char op, table before) {
		table after = new table();
		after.insertMatrix(before.getA());
		before.findentry(3);
		int i = before.getI();
		int j = before.getJ();
		switch (op) {
		case 'L':
			if (j > 1 && after.a[i][j - 1] == 0) {
				after.a[i][j - 1] = 3;
				after.a[i][j] = 2;
			} else {
				return null;

			}
			break;

		case 'R':
			if (j < 9 && after.a[i][j + 1] == 0) {
				after.a[i][j + 1] = 3;
				after.a[i][j] = 2;
			} else {
				return null;

			}
			break;

		case 'D':
			if (i < 9 && after.a[i + 1][j] == 0) {
				after.a[i + 1][j] = 3;
				after.a[i][j] = 2;

			} else {
				return null;

			}
			break;
		case 'U':
			if (i > 1 && after.a[i - 1][j] == 0) {
				after.a[i - 1][j] = 3;
				after.a[i][j] = 2;

			} else {
				return null;

			}
			break;
		default:
			return null;
		}
		return after;
	}

	public void Depth(MultiTree tree, table goal) {
		this.tree = tree;
		found = false;
		Depth(tree.root, goal, 0);
	}

	public void Depth(multinode node, table goal, int c) {
		c++;
		if (c > jDepth || found) {

			return;
		}
		table table_node = (table) node.data;

		if (table_node.isequal(goal)) {

			tree.display_solution(node);

			found = true;
			return;
		} else {

			table new_table_Left = apply('L', table_node);
			table new_table_Right = apply('R', table_node);
			table new_table_Up = apply('U', table_node);
			table new_table_Down = apply('D', table_node);

			if (new_table_Left != null) {

				tree.insertnode(new_table_Left, node.id);
				Depth(tree.search_data(new_table_Left), goal, c);
			}
			if (new_table_Right != null) {

				tree.insertnode(new_table_Right, node.id);
				Depth(tree.search_data(new_table_Right), goal, c);
			}
			if (new_table_Up != null) {

				tree.insertnode(new_table_Up, node.id);
				Depth(tree.search_data(new_table_Up), goal, c);
			}
			if (new_table_Down != null) {

				tree.insertnode(new_table_Down, node.id);
				Depth(tree.search_data(new_table_Down), goal, c);
			}
		}

	}

	public void IterativeDeepeningDepth(MultiTree tree, table goal) {
		int temp = jDepth;
		this.tree = tree;
		found = false;
		for (jDepth = 1; jDepth <= temp; jDepth++) {
			IterativeDeepeningDepth(tree.root, goal, 0);
		}
		jDepth = temp;
	}

	public void IterativeDeepeningDepth(multinode node, table goal, int c) {
		c++;
		if (c > jDepth || found) {
			return;
		}
		table table_node = (table) node.data;

		if (table_node.isequal(goal)) {
			tree.display_solution(node);
			found = true;
			return;
		} else {
			table new_table_Left = apply('L', table_node);
			table new_table_Right = apply('R', table_node);
			table new_table_Up = apply('U', table_node);
			table new_table_Down = apply('D', table_node);

			if (new_table_Left != null) {
				tree.insertnode(new_table_Left, node.id);
				IterativeDeepeningDepth(tree.search_data(new_table_Left), goal, c);
			}
			if (new_table_Right != null) {
				tree.insertnode(new_table_Right, node.id);
				IterativeDeepeningDepth(tree.search_data(new_table_Right), goal, c);
			}
			if (new_table_Up != null) {
				tree.insertnode(new_table_Up, node.id);
				IterativeDeepeningDepth(tree.search_data(new_table_Up), goal, c);
			}
			if (new_table_Down != null) {
				tree.insertnode(new_table_Down, node.id);
				IterativeDeepeningDepth(tree.search_data(new_table_Down), goal, c);
			}
		}
	}

	public void Breadth(MultiTree tree1, table goal) {
		int c = 0;
		this.tree = tree1;
		Queue<multinode> q = new LinkedList();
		q.add(tree.root);
		table table_node = new table();
		multinode<table> t;

		while (c < jBreadth) {
			c++;
			t = q.remove();
			table_node = (table) t.data;

			if (table_node.isequal(goal)) {
				tree.display_solution(t);
				break;
			} else {

				table new_table_Left = apply('L', table_node);
				table new_table_Right = apply('R', table_node);
				table new_table_Up = apply('U', table_node);
				table new_table_Down = apply('D', table_node);

				if (new_table_Left != null) {
					tree.insertnode(new_table_Left, t.id);
					q.add(tree.search_data(new_table_Left));
				}
				if (new_table_Right != null) {
					tree.insertnode(new_table_Right, t.id);
					q.add(tree.search_data(new_table_Right));
				}
				if (new_table_Up != null) {
					tree.insertnode(new_table_Up, t.id);
					q.add(tree.search_data(new_table_Up));
				}
				if (new_table_Down != null) {
					tree.insertnode(new_table_Down, t.id);
					q.add(tree.search_data(new_table_Down));
				}

			}
		}
	}

	public int gn(table t, table goal) {
		int g = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (t.a[i][j] != goal.a[i][j]) {
					g++;
				}
			}
		}
		return g;
	}

	public int hn(table t, table tgoal) {
		int h = 0;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++) {
				t.findentry(tgoal.a[x][y]);
				h = h + Math.abs(t.i - x) + Math.abs(t.j - y);
			}
		return h;
	}

	public void AStar(MultiTree tree1, table goal) {
		int c = 0;
		this.tree = tree1;
		PriorityQueue<multinode> q = new PriorityQueue<multinode>();
		int pr = gn((table) tree.root.data, goal) + hn((table) tree.root.data, goal);

		q.enqueue(tree.root, pr);
		table table_node = new table();
		multinode<table> t;

		while (c < jAStar) {
			c++;
			t = q.dequeue();
			table_node = (table) t.data;

			if (table_node.isequal(goal)) {
				tree.display_solution(t);
				break;
			} else {
				table new_table_Left = apply('L', table_node);
				table new_table_Right = apply('R', table_node);
				table new_table_Up = apply('U', table_node);
				table new_table_Down = apply('D', table_node);

				if (new_table_Left != null) {
					tree.insertnode(new_table_Left, t.id);
					pr = gn((table) new_table_Left, goal) + hn((table) new_table_Left, goal);
					q.enqueue(tree.search_data(new_table_Left), pr);
				}
				if (new_table_Right != null) {
					tree.insertnode(new_table_Right, t.id);
					pr = gn((table) new_table_Right, goal) + hn((table) new_table_Right, goal);
					q.enqueue(tree.search_data(new_table_Right), pr);
				}
				if (new_table_Up != null) {
					tree.insertnode(new_table_Up, t.id);
					pr = gn((table) new_table_Up, goal) + hn((table) new_table_Up, goal);
					q.enqueue(tree.search_data(new_table_Up), pr);
				}
				if (new_table_Down != null) {
					tree.insertnode(new_table_Down, t.id);
					pr = gn((table) new_table_Down, goal) + hn((table) new_table_Down, goal);
					q.enqueue(tree.search_data(new_table_Down), pr);
				}

			}
		}
	}

}
