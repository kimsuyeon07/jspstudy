<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		if('${param.result}' > 0) {
			alert('댓글이 등록되었습니다.');
			location.href='/11_MYBATIS/selectList.do';
		} else {
			alert('댓글 등록이 실패했습니다.'); 
			history.back();
		}
	</script>
</head>
<body>

	

</body>
</html>