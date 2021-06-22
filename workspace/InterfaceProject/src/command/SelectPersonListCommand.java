package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

/**
 * Servlet implementation class SelectPersonListCommand
 */
@WebServlet("/selectPersonList.do")
public class SelectPersonListCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SelectPersonListCommand() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 처리 (X)
		
		// DAO호출
		List<Person> list = PersonDAO.getInstance().selectPersonList();
		
		// JSON데이터로 변환
			// list -> Person -> JSONObject  (변환 단계)
		JSONArray arr = new JSONArray();
		for (Person person : list) {
			// list안의 person을 JSONObject에 담아준다.
			JSONObject obj = new JSONObject();
			obj.put("sno", person.getSno());
			obj.put("name", person.getName());
			obj.put("age", person.getAge());
			obj.put("birthday", person.getBirthday());
			obj.put("regdate", person.getRegdate().toString()); 
				// 문자열로 바꾸는 방법 :	1) person.getRegdate() + ""
				// 				  	2) person.getRegdate().toString( )
				// ↑ 날짜 데이터는 ("")문자열로 바꿔주지 않으면 데이터가 읽히지 않는다.
				
			// JSONArray에 추가한다.
			arr.add(obj);
		}
		// System.out.println(arr); // JSON을 보낼때는 한 번 확인 후 보내는 것이 좋다.
								 // (int)숫자로 선언한 데이터를 제외한 모든 데이터는 ("")문자열이여야한다.
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(arr);
		
		
		
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
