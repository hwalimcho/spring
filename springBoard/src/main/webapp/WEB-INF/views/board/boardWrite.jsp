<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			console.log(param);
			
			var paramlist = new Array();
			paramlist = param.split("&");
			paramlen = paramlist.length;
			for (var i=0; i<paramlen; i++) {
				if(paramlist[i]=="boardTitle=" || paramlist[i]=="boardComment=") {
					alert("빈칸 없이 작성해주세요.")
					return false;
				}
				
			}

			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});

			 //행 개수			>> 굳이 이럴 필요 없었다 처음 테이블에 체크박스 없애면 됨;;

	      	var count=1 ;
	      	$j("#addRow").on("click",function(){
	      		
	      		var addTable = '<tr name=trTitle>'+
	        	'<td width="120" align="center">'+
	        		'<input name="checkRow" type="checkbox">'+
	         		'Title'+
	         	'</td>'+
	         	'<td width="400">'+
	         		'<input name="boardVoList['+count+'].boardTitle" type="text" size="50" value="${board.boardTitle}">'+ 
	         	'</td>'+
	      	'</tr>'   +
	      	'<tr name="comment">'+
	         	'<td height="300" align="center">'+
	         		'Comment'+
	         	'</td>'+
	         	'<td valign="top">'+
	         		'<textarea name="boardVoList['+count+'].boardComment"  rows="20" cols="55" value="${board.boardComment}"/>'+
	         	'</td>'+
	      	'</tr>';
	      		      		
	      		var trHtml = $j( "tr[name=comment]:last" );
	      		trHtml.after(addTable);
	      		++count ;
	      		console.log(count);
	      		console.log("addTable:", addTable);
			});
	      	
	      	

		    $j("#delRow").on("click", function(){
	                if($j("input:checkBox[name='checkRow']:checked").length == 0) {
	                	alert("삭제할 항목을 선택하세요.");
	                	return;
	                }

	                if(count<=1){
	                	alert("더 이상 삭제할 수 없습니다.");
	                	return;	
	                }
	                $j("input:checkbox[name='checkRow']:checked").each(function(k,kVal){
	                	
	                	//어느 테이블이 삭제되는 지 확인해주는 함수
	               		//console.log("kVal ::", kVal.parentElement.parentElement);
	               		//console.log("kVal ::", kVal.parentElement.parentElement.nextElementSibling);
	                	let a = kVal.parentElement.parentElement;
	                	let b = kVal.parentElement.parentElement.nextElementSibling;
	                	$j(a).remove();
	                	$j(b).remove();
	                	count--;
	                	console.log(count);
	               
	                });
		          	
	            
	                
	                
	                
		      	});
	});
	      	
</script>
<body>
<form:form commandName="boardVo" class="boardWrite" action="/board/boardWriteAction.do">
	<table align="center">
		<tr>
			<td align="right">
			<input id="addRow" type="button" value="행추가">
			<input id="delRow" type="button" value="행삭제">
			<input id="submit" type="button" value="작성">
			</td>
		</tr>
		

		<tr>
			<td>
			
				<table border ="1">
					<tr name="title">
						<td width="120" align="center">
						<input name="checkRow" type="checkbox">
						Title
						</td>
						<td width="400">
						<!-- <input name="title" type="text" size="50" value="${board.boardTitle}">  -->
						 <input name="boardVoList[0].boardTitle" type="text" size="50" value="${board.boardTitle}"  maxlength="24">
						</td>
						
					</tr>
					<tr name="comment">
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<!-- <textarea name="comment"  rows="20" cols="55" value="${board.boardComment}"> </textarea>-->
						<textarea name="boardVoList[0].boardComment"rows="20" cols="55" value="${board.boardComment}"></textarea>
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
</form:form>	
</body>
</html>