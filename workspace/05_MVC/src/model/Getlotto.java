package model;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Getlotto {

	public String excute(HttpServletRequest request) {
		
		// 중복된 숫자가 나올 수 없는 컬렉션
		Set<Integer> lotto = new HashSet<Integer>();
		
		while (lotto.size() < 6) {
			lotto.add( (int)(Math.random() * 45) + 1 );			
		}
		
		request.setAttribute("lotto", lotto);
		
		return "views/output.jsp";
		
	}
	
}
