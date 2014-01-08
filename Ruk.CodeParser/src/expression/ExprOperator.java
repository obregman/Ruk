package expression;

public class ExprOperator extends ExprNode {
	public ExprOperator(String strOperator) {
		this.str = strOperator;
	}
	
	@Override
	public ExprNode insert(ExprNode node) {
		if( node instanceof ExprOperand ) {
			if( this.left == null )
				this.left = node;
			else
				if( this.right == null)
					this.right = node;
				else
					this.right.insert(node);
			return this;
		}
		else
			if( node instanceof ExprOperator ) {
				// TBD - handle operator precedence
				if( node.str.equals("+") || node.str.equals("-") && str.equals("*") || str.equals("/") ) {
					node.left = this;
					return node;
				}
				else
					if( node.str.equals("*") || node.str.equals("/") && str.equals("+") || str.equals("-") ) {
						node.left = this.right;
						this.right = node;
						return this;
					}
				
				node.left = this;
				return node;
			}
		return null;
	}
}
