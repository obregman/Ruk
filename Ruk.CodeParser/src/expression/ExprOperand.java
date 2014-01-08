package expression;

public class ExprOperand extends ExprNode {
	public ExprOperand(String strVal) {
		this.str = strVal;
	}
	
	@Override
	public ExprNode insert(ExprNode node) {
		if( node instanceof ExprOperator ) {
			node.left = this;
			return node;
		}
		return null;
	}
}
