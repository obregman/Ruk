package core;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.TestCodeParser;
import tests.TestExpression;
import tests.TestScripts;


@RunWith(Suite.class)
@SuiteClasses({ TestExpression.class, TestScripts.class, TestCodeParser.class })
public class AllTests {

}
