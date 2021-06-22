package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PersonDAO;
import dto.Person;

class PersonTEST {
	// field
	int size = 0;
	Person person = null;
	
	@BeforeEach
	void setUp() throws Exception {
		size = PersonDAO.getInstance().selectPersonList().size();
		person = PersonDAO.getInstance().selectPersonBySno("111111");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertNotNull(person,"111111 주민번호는 없다.");
		assertEquals("스미스", person.getName(), "이름이 스미스가 아니다.");
		//fail("Not yet implemented");
	}
  
}
