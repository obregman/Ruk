package expression;

public class ExprOperator extends ExprNode {
	public ExprOperator(String strOperator) {
		this.str = strOperator;
	}
	
	@Override
	public ExprNode insert(ExprNode node) {
		if( node instanceof ExprOperand ) {
			if( this.right == null )
				this.right = node;
			else
				this.left = node;
			return this;
		}
		else
			if( node instanceof ExprOperator ) {
				node.left = this;
				return node;
			}
		return null;
	}
}
