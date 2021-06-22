package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Calculator;

class CalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Before");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("After");
	}

	@Test
	@DisplayName("1 + 1 = 2")
		// ↑ JUnit창의 Test창에 확인 가능하다.
	void test() {
		Calculator calculator = new Calculator();
		
		//assertNull(calculator); 
		// ↑ calculator가 null값인가 _확인 (null == test 통과.)
		
		//assertNotNull(calculator); 
		// ↑ calculator가 null값인가 _확인 (not null == test 통과.)
		
		assertEquals(3, calculator.add(1, 1),"1+1=2 이여야 한다.");  
		// ↑ assertEquals(expected : 결과 값 , actual : 실행문법);
		
		
		System.out.println("Test");
		//fail("Not yet implemented"); >> 실패시키겠다. 
		
		/* ↓ (실패의 확인)을 아래의 방식으로 사용할 수 있다.
		if (DAO.getInstance().insert(person) == 0) {
			fail("삽입실패");
		}
		assertEquals(1, DAO.getInstance().insert(person),"삽입실패");  
		*/
	}

}
