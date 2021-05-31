package command.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.ModelAndView;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 첨부파일이 저장될 디렉터리 (생성 여부에 따라 생성까지 진행)
		final String DIRECTORY = "archive";
		String realPath = request.getServletContext().getRealPath(DIRECTORY);
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 파라미터 처리 : MultiparRequest가 담당
		MultipartRequest multipartRequest = null;
		
		return null;
	}

}
