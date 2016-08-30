import lab.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringTCFAppTest {
	
	@Autowired
	private Person person;

	private Person expectedPerson = TestUtils.getExpectedPerson();

	@Test
	public void testInitPerson() {
		assertEquals(expectedPerson, person);
	}
}
