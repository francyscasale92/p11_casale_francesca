import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbbonamentoTest.class, FilmTest.class, ProgrammazioneTest.class, ScontoTest.class,
		SQLDatabaseTest.class, UpdateSQLDatabaseTest.class, SQLDatabaseErrorTest.class, UpdateSQLDatabaseErrorTest.class})
public class TestSuite {

}
