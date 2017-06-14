import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbbonamentoTest.class, FilmTest.class, ProgrammazioneTest.class, ScontoTest.class,
		SQLDatabaseErrorTest.class, SQLDatabaseTest.class, UpdateSQLDatabaseErrorTest.class,
		UpdateSQLDatabaseTest.class })
public class TestSuite {

}
