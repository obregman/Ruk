import static org.junit.Assert.*;

import org.junit.Test;

import core.Context;
import core.Value;
import expression.Expression;


public class TestExpression {

	@Test
	public void simpleExpression1() {
		Context context = new Context();
		Value result = Expression.evaluate("44 + 56", context);
		
		if( result.val() != (44 + 56) )
			fail("Expression [44+56] evaluation failed");
	}
	
	@Test
	public void simpleExpression2() {
		Context context = new Context();
		Value result = Expression.evaluate("5 + 3 * 2", context);
		
		if( result.val() != (5 + 3 * 2) )
			fail("Expression [5 + 3 * 2] evaluation failed");
	}
	
	@Test
	public void simpleExpression3() {
		Context context = new Context();
		Value result = Expression.evaluate("43 - 6 / 2", context);
		
		if( result.val() != (43 - 6 / 2) )
			fail("Expression [43 - 6 / 2] evaluation failed");
	}
	
	@Test
	public void variable1() {
		Context context = new Context();
		context.updateVariable("x", new Value(20));
		Value result = Expression.evaluate("x + 3", context);
		
		if( result.val() != (20 + 3) )
			fail("Expression [x + 3] evaluation failed");
	}
	
	@Test
	public void variable2() {
		Context context = new Context();
		context.updateVariable("a", new Value(16));
		context.updateVariable("b", new Value(22));
		context.updateVariable("c", new Value(4));
		context.updateVariable("d", new Value(12));
		context.updateVariable("e", new Value(3));
		Value result = Expression.evaluate("a + b * c - d / e", context);
		
		if( result.val() != (16 + 22 * 4 - 12 / 3) )
			fail("Expression [a + b * c - d / e] evaluation failed");
	}
	
	@Test
	public void stringExpression() {
		Context context = new Context();
		context.updateVariable("a", new Value("hello "));
		context.updateVariable("b", new Value("world"));
		Value result = Expression.evaluate("a + b", context);
		
		if( !result.toString().equals("hello world") )
			fail("Expression [hello + world] evaluation failed");
	}

}
