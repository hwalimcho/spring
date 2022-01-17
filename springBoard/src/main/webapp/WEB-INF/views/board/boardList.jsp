<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#checkAll").on("click", function(){
			
			if($j("#checkAll").prop("checked")) {
				$j(".check").prop("checked", true);
			}else {
				$j(".check").prop("checked", false);
			}
        });
		
		// 개별 체크박스 클릭 시 전체 체크박스 선택
		$j(".check").click(function(){ 
						
			var checkboxLen 	   = $j(".check").length; // 개별 체크박스의 개수
			var checkboxCheckedLen = $j(".check:checked").length; // 개별 체크박스 중 선택된 체크박스의 개수
			
			if(checkboxLen == checkboxCheckedLen){
				
				$j("#checkAll").prop("checked", true);
				
			} else {
				$j("#checkAll").prop("checked", false);
			}
			
		});
		
		var checkList = new Array();

		$j("#inquire").on("click", function(){
			//var $frm = $j('.boardList :input');
			//var param = $frm.serialize();
			
            $j(".check:checked").each(function() {
            	checkList.push(this.value);
            });

        	console.log(checkList);
        	
        	
		});
		
	});
</script>
<body>
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.codeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
		
	</tr>
	<form action="/board/boardList.do">
		<tr>
			<td>
			
				<input name="checkAll" id="checkAll"  type="checkbox">전체
				<c:forEach items="${codeList}" var="list">
					<input name="checkRow" class="check" type="checkbox" value="${list.codeID}">${list.codeName}
				</c:forEach>
				<input id="inquire" type="submit" value="조회">
			</td>
		</tr>
	</form>
</table>	
</body>
</html>