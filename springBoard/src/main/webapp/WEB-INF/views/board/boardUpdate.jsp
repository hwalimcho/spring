<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>boardUpdate</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#update").on("click",function(){
			var $frm = $j('.boardUpdate :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardUpdateAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("수정완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	});
	

</script>
<body>
	<form class="boardUpdate">
		<table align="center">
			<tr>
				<td align="right">
				<input id="update" type="button" value="수정">
				</td>
			</tr>
			<tr>
				<td>
					<table border ="1"> 
						<tr>
							<td width="120" align="center">
							Title
							</td>
							<td width="400">
							<input name="boardTitle" type="text" size="50" value="${board.boardTitle}" > 
							</td>
						</tr>
						<tr>
							<td height="300" align="center">
							Comment
							</td>
							<td valign="top">
							<textarea name="boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center">
							Writer
							</td>
							<td>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">
					<a href="/board/boardList.do">List</a>
				</td>
			</tr>
		</table>
		<input name="boardType" type="hidden" value="${board.boardType}">
		<input name="boardNum" type="hidden" value="${board.boardNum}">
	</form>
</body>
</html>