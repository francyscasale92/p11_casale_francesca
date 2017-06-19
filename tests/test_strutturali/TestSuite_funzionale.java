package test_strutturali;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SQLDatabaseErrorTest.class, SQLDatabaseTest.class, UpdateSQLDatabaseErrorTest.class,
		UpdateSQLDatabaseTest.class })
public class TestSuite_funzionale {

}
