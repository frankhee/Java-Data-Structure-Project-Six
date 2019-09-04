/*
 * @Author Frank He
 * @Version 4/23/18
 */

package osu.cse2123;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionTree {

	public static TreeNode<String> buildTreeFromString(String expr) {
		
		//Create new stack to store nodes
		Stack<TreeNode> summary = new Stack<TreeNode>();
		
		String[] combined = expr.split("\\s+");
		
		//Issue try statement to catch errors
		try {		
			for (int i = 0; i < combined.length; i++) {
				
				if (combined[i].charAt(0) == '+' || combined[i].charAt(0) == '-' || combined[i].charAt(0) == '/'|| combined[i].charAt(0) == '*' || combined[i].charAt(0) == '%') {
					
					TreeNode rootNode = new TreeNode(combined[i]);
					rootNode.setRightChild(summary.pop());
					rootNode.setLeftChild(summary.pop());
					summary.push(rootNode);
				} 
				else {
					
					TreeNode operandNode = new TreeNode(combined[i]);
					summary.push(operandNode);
				}
			}
			return summary.pop();
		} 
		
		catch (EmptyStackException e) {
			return null;
		}
	}

	public static String toPostfixString(TreeNode<String> expr) {
	
		String returnValue = "";
		if (expr == null)
			return returnValue;

		returnValue = returnValue + toPostfixString(expr.getLeftChild());
		returnValue = returnValue + toPostfixString(expr.getRightChild());
		returnValue = returnValue + expr.getData() + " ";

		return returnValue;
	}

	public static String toPrefixString(TreeNode<String> expr) {
		
		String returnValue = "";
		if (expr == null)
			return returnValue;

		returnValue = returnValue + expr.getData() + " ";
		returnValue = returnValue + toPostfixString(expr.getLeftChild());
		returnValue = returnValue + toPostfixString(expr.getRightChild());

		return returnValue;
	}

	public static String toInfixString(TreeNode<String> expr) {
		String returnValue = "";
		if (expr == null)
			return returnValue;

		if (expr.getData().charAt(0) == '+' || expr.getData().charAt(0) == '-' || expr.getData().charAt(0) == '/'|| expr.getData().charAt(0) == '*' || expr.getData().charAt(0) == '%') {
			returnValue = "(" + returnValue + toInfixString(expr.getLeftChild());
			returnValue = returnValue + expr.getData() + " ";
			returnValue = returnValue + toInfixString(expr.getRightChild()) + ")";
		}
		
		else {
			returnValue = returnValue + toInfixString(expr.getLeftChild());
			returnValue = returnValue + expr.getData() + " ";
			returnValue = returnValue + toInfixString(expr.getRightChild());
		}
		return returnValue;
	}

	public static int evaluate(TreeNode<String> expr) {

		if (expr.getData().charAt(0) == '-' || expr.getData().charAt(0) == '+' || expr.getData().charAt(0) == '%'|| expr.getData().charAt(0) == '*' || expr.getData().charAt(0) == '/') {
			
			if (expr.getData().charAt(0) == '-') {
				return evaluate(expr.getLeftChild()) - evaluate(expr.getRightChild());
			}
			if (expr.getData().charAt(0) == '+') {
				return evaluate(expr.getLeftChild()) + evaluate(expr.getRightChild());
			}
			if (expr.getData().charAt(0) == '%') {
				return evaluate(expr.getLeftChild()) % evaluate(expr.getRightChild());
			}
			if (expr.getData().charAt(0) == '*') {
				return evaluate(expr.getLeftChild()) * evaluate(expr.getRightChild());
			}
			if (expr.getData().charAt(0) == '/') {
				return evaluate(expr.getLeftChild()) / evaluate(expr.getRightChild());
			}
		}
		return Integer.parseInt(expr.getData());
	}

}