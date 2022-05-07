import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		Scanner con = new Scanner(System.in);
		JFrame g = new JFrame();

		SearchingTechniques S = new SearchingTechniques();

		int[][] initial = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 3, 1, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0, 0, 0, 0, 1, 1 },
				{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 9, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

		};

		table initial_table = new table(initial);

		int goal[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 2, 1, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 2, 1, 0, 0, 0, 1, 0, 1, 1 }, { 1, 2, 2, 2, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 2, 2, 2, 2, 2, 1, 1 },
				{ 1, 0, 1, 0, 1, 1, 1, 2, 1, 1 }, { 1, 0, 1, 0, 1, 0, 0, 2, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 2, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 3, 9, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

		};

		table goal_table = new table(goal);

		MultiTree tree = new MultiTree(initial_table);
		int x = 0;
		while (x != 5) {
			System.out.println("Enter the number of the method to implement:" + "\n'1':Depth First Search"
					+ "\n'2':Breadth Search" + "\n'3':Iterative Deepening Search" + "\n'4':A Star" + "\n'5':Break");
			x = con.nextInt();

			if (x == 1) {
				System.out.println("Depth First Search:");

				try {
					S.Depth(tree, goal_table);
				} catch (Exception e) {

				}

			} else if (x == 2) {
				System.out.println("Breadth Search:");
				try {
					S.Breadth(tree, goal_table);
				} catch (Exception e) {

				}

			} else if (x == 3) {
				System.out.println("Iterative Deepening Search:");
				try {
					S.IterativeDeepeningDepth(tree, goal_table);
				} catch (Exception e) {

				}

			} else if (x == 4) {
				System.out.println("Uniform-Cost Search:");
				try {
					S.AStar(tree, goal_table);
				} catch (Exception e) {

				}
			} else if (x == 5) {
				break;
			} else {
				System.out.println("Please enter a valid number or '5' to break.");
			}
		}

		try {

			createColouredMatrix(goal, 300);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createColouredMatrix(int[][] t, int sideLength) {

		JFrame frame = new JFrame();

		frame.setTitle("Maze Solver(Goal State)");
		frame.setSize(sideLength, sideLength);
		frame.setLayout(new GridLayout(10, 13));
		frame.setLocationRelativeTo(null);
		frame.setSize(sideLength, sideLength);

		frame.setLayout(new GridLayout(10, 13));

		for (int[] row : t) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, row.length));

			for (int number : row) {
				JPanel singleBox = new JPanel();
				singleBox.setBorder(BorderFactory.createLineBorder(Color.white));

				if (number == 1)
					singleBox.setBackground(Color.black);
				else if (number == 2)
					singleBox.setBackground(Color.green);
				else if (number == 3)
					singleBox.setBackground(Color.red);
				else if (number == 9)
					singleBox.setBackground(Color.yellow);
				else
					singleBox.setBackground(Color.white);
				panel.add(singleBox);
			}

			frame.add(panel);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}