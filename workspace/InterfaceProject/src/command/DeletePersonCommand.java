package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.PersonDAO;
 
@WebServlet("/deletePerson.do")
public class DeletePersonCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    public DeletePersonCommand() {
        super(); 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 파라미터 처리
		String sno = request.getParameter("sno");
		// DAO 호출
		int count = PersonDAO.getInstance().deletePerson(sno);
		// JSON 데이터로 만들기
		if (count > 0) {
			JSONObject obj = new JSONObject();
			obj.put("count", count);
			response.getWriter().println(obj);
		}
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
