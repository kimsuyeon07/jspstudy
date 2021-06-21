package command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

/**
 * Servlet implementation class InsertPersonCommand
 */
@WebServlet("/insertPerson.do")
public class InsertPersonCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public InsertPersonCommand() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		try {
			
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age")); // NumberFormatException 발생(자동으로 발생한다)
			if (age < 0 || age > 100) { // 유효범위를 벗어난 나이 입력 (개발자가 직접 예외를 발생시킨다)
				// 강제로 예외를 발생시킨다.
				// (예외의 강제 발생)
				throw new RuntimeException();
			}
			String birthday = request.getParameter("birthday");
			
			Person p = new Person();
			p.setSno(sno);
			p.setName(name);
			p.setAge(age);
			p.setBirthday(birthday);
			
			int count = PersonDAO.getInstance().insertPerson(p);
			
			/* 반환되는결과를 "JSON"으로 만든다. */
			JSONObject obj = new JSONObject();
			obj.put("count", count);
			/* 응답 */
			response.getWriter().println(obj);
			
		} catch (NumberFormatException e) {  // catch >> ajax의 "error"로 넘어간다.
			// 나이 : 정수 이외의 값이 입력됨.
			response.setStatus(3001); // 에러 코드 값을 작성, xhr.status를 통해서 확인
			response.getWriter().println("나이는 정수만 입력 가능합니다.");
		} catch (RuntimeException e) {
			// 나이 : 유효범위를 벗어난 숫자일 경우, "강제로 예외를 발생"시켰다.
			response.setStatus(3002);
			response.getWriter().println("나이는 0 ~ 100 사이의 정수로만 입력 가능합니다.");
		} catch (SQLException e) { 
			// ※  PersonDAO의 insertPerson( )에 throws 처리하여 해당 Command에서 처리한다 
			// ↓
			// 주민등록번호는 "6자리"만 받을 수밖에 없다. (정규식확인)때문에
			// 이름, 생일 : 칼럼의 크기보다 길이가 긴 값이 입력됨
			response.setStatus(3003);
			response.getWriter().println("입력 데이터의 길이를 확인하세요.");
		}
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
