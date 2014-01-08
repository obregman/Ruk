package expression;

import java.util.ArrayList;
import java.util.List;

import core.Context;
import core.TextHelper;
import core.Value;

public class ExprTree {
	
	ExprNode _root;
	
	public static ExprTree generateTree(String expr) {
		
		ExprTree tree = new ExprTree();
		tree.makeTree(expr);
		return tree;
	}
	
	public Value evaluate(Context context) {
		Value val = evaluateNode(_root, context);
		return val;
	}
	
	private Value evaluateNode(ExprNode node, Context context) {
		
		Value val = null;
		if( node instanceof ExprOperator ) {
			if( node.str.equals("+") ) {
				Value v1 = evaluateNode(node.left, context);
				Value v2 = evaluateNode(node.right, context);
				val = Value.add(v1, v2);
			}
			else
			if( node.str.equals("-") ) {
				Value v1 = evaluateNode(node.left, context);
				Value v2 = evaluateNode(node.right, context);
				val = Value.subtract(v1, v2);
			}
			else
			if( node.str.equals("*") ) {
				Value v1 = evaluateNode(node.left, context);
				Value v2 = evaluateNode(node.right, context);
				val = Value.mult(v1, v2);
			}
			else
			if( node.str.equals("/") ) {
				Value v1 = evaluateNode(node.left, context);
				Value v2 = evaluateNode(node.right, context);
				val = Value.div(v1, v2);
			}
				
		}
		else
			if(node instanceof ExprOperand) {
				val = Expression.evaluate(node.str, context);
			}
		
		return val;
	}
	
	private static List<String> breakExpr(String expr) {
		// Break into parts
		List<String> parts = new ArrayList<String>();
		int pos = 0;
		while(pos < expr.length()) {
			
			int start = TextHelper.nextRealCharacter(expr, pos);
			if( start < 0 )
				break;
			int end = nextEndCharacter(expr, start + 1);
			if( end < 0 )
				break;
			
			String part = expr.substring(start, end);
			parts.add(part);
			
			pos = end;					
		}
		return parts;
	}
	
	private void makeTree(String expr) {
		
		List<String> parts = ExprTree.breakExpr(expr);
		
		for(String str:parts) {
			ExprNode node = null;
			if( isAlphanumeric(str) )
				node = new ExprOperand(str);
			else
				if( isValidOperator(str) )
					node = new ExprOperator(str);
			
			if( node != null )
				if( _root == null)
					_root = node;
				else
				{
					_root = _root.insert(node);
				}
		}
	}
	
	private static int nextEndCharacter(String text, int from) {
		int pos = from;
		while(pos < text.length()) {
			char ch = text.charAt(pos);			
			if( !isAlphanumeric(ch) )
				return pos;
			pos++;
		}
		return pos;
	}
	
	private static boolean isAlphanumeric(char ch) {
		return (ch > 'A' && ch < 'z' || ch > '0' && ch < '9');
	}
	
	private static boolean isAlphanumeric(String str) {
		return str.matches("[A-z0-9]+");
	}
	
	private static boolean isValidOperator(String str) {
		return str.matches("[\\+\\-/\\*]");
	}

}
