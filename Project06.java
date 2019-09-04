/*
 * @Author Frank He
 * @Version 4/23/18
 */

package osu.cse2123;

import java.util.Scanner;

public class Project06 {

	public static void main(String[] args) {

		// Create new expression tree object
		ExpressionTree tree = new ExpressionTree();
		String input = " ";
		String i = " ";
		char chr = i.charAt(0);

		// Prompt user to enter data
		Scanner in = new Scanner(System.in);
		System.out.println("No expression in memory");
		while (chr != 'Q' && chr != 'q') {

			System.out.println("Enter your choice:");
			System.out.println("[S]et the display format");
			System.out.println("[E]nter a new expression");
			System.out.println("[Q]uit");
			System.out.println("> ");

			i = in.nextLine();
			chr = i.charAt(0);

			// Check if the format is correct
			if (chr == 's' || chr == 'S' || chr == 'e' || chr == 'E' || chr == 'q' || chr == 'Q') {
				if (chr == 'E' || chr == 'e') {
					boolean check = false;

					while (check == false) {
						System.out.println("Enter your expression in postfix notation: ");
						input = in.nextLine();
						
						if (input == null) {
							System.out.println("ERROR");
						} else {
							check = true;
						}
					}

					// Create a new tree node
					TreeNode<String> equation = tree.buildTreeFromString(input);
					System.out.println(tree.toPostfixString(equation) + " = " + tree.evaluate(equation));
				}

				if (chr == 'S' || chr == 's') {

					System.out.println();
					System.out.println("Enter your preferred output display: ");
					System.out.println("[P]ostfix");
					System.out.println("[I]nfix");
					System.out.println("p[R]efix");
					System.out.println(">");

					String display = in.nextLine();
					chr = display.charAt(0);

					while (chr != 'P' && chr != 'p' && chr != 'I' && chr != 'i' && chr != 'R' && chr != 'r') {

						System.out.println("ERROR!");
						System.out.println();
						System.out.println("Enter your preferred output display: ");
						display = in.nextLine();
						chr = display.charAt(0);
					}

					// Print out expression output
					if (chr == 'P' || chr == 'p') {
						System.out.println(tree.toPostfixString(tree.buildTreeFromString(input)) + "= "
								+ tree.evaluate(tree.buildTreeFromString(input)));
					}
					if (chr == 'I' || chr == 'i') {
						System.out.println(tree.toInfixString(tree.buildTreeFromString(input)) + "= "
								+ tree.evaluate(tree.buildTreeFromString(input)));
					}
					if (chr == 'R' || chr == 'r') {
						System.out.println(tree.toPrefixString(tree.buildTreeFromString(input)) + "= "
								+ tree.evaluate(tree.buildTreeFromString(input)));
					}
					System.out.println();
				}
			} 
			else {
				System.out.println("ERROR");
			}
		}
		System.out.println("Goodbye!");
	}
}
