<%@page import="java.util.ArrayList"%>
<%@page import="ex09_session.ProductDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("UTF-8");

	String product = request.getParameter("product");
	String quantity = request.getParameter("quantity");
	
	// product과 quantity를 하나의 데이터로 저장하려면 DTO 또는 Map을 사용한다.
	
	// 1. Map<product, quantity>
	// Map<String, String> map = new HashMap<>();
	// map.put(product, quantity);  // 하나의 데이터로 만든 것.
	
	// 2. ProductDTO
	ProductDTO dto = new ProductDTO();
	dto.setProduct(product);
	dto.setQuantity(quantity);
	
	// ProductDTO를 여러개 저장할 수 있는 cart를 만든다.
	/* ArrayList : 배열의 길이가 정해지 않아서 사용이 쉬움 */
	// 1. session에 저장된 cart를 꺼낸다.
	// 2. 만약 session에 없으면 그 떼 새로 cart를 만든다.
	ArrayList<ProductDTO> cart = ( ArrayList<ProductDTO> )session.getAttribute("cart"); // 캐스팅
	
	/* 첫 장바구니 담기 */
	if (cart == null) {  
		cart = new ArrayList<>();
		session.setAttribute("cart", cart);
	}
	/* 아니라면 목록을 추가하는 것.*/
	cart.add(dto);  // ArrayList에 추가하는 add() 메소드
	
	// cart : session과 연결된 것 : cart에 저장하면 session을 추가하는 것이 된다.
		
	
%>  

<script>
	alert('<%=dto.getProduct()%> 제품을 장바구니에 추가했습니다. '); 
	if (confirm('장바구니로 가려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')) {
		loc
		localhost.href = '/02_JSP/ex09_session/Ex03_cart.jsp';
	} else {
		localhost.href = '/02_JSP/ex09_session/Ex03_mall.jsp';
	}
</script>














 