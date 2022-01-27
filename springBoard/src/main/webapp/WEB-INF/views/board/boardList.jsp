<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>list</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 10px;
}

</style>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#checkAll").on("click", function(){
			if($j("#checkAll").prop("checked")) {
				$j(".checkRow").prop("checked", true);
			}else{
				$j(".checkRow").prop("checked", false);
			}
        });
		// ���� üũ�ڽ� Ŭ�� �� ��ü üũ�ڽ� ����
		$j(".checkRow").click(function(){ 		
			var checkboxLen 	   = $j(".checkRow").length; // ���� üũ�ڽ��� ����
			var checkboxCheckedLen = $j(".checkRow:checked").length; // ���� üũ�ڽ� �� ���õ� üũ�ڽ��� ����
			
			if(checkboxLen == checkboxCheckedLen){
				$j("#checkAll").prop("checked", true);
			}else{
				$j("#checkAll").prop("checked", false);
			}
		});
		
		var checkList = new Array();
		$j("#inquire").on("click", function(){
            $j(".checkRow:checked").each(function() {
            	checkList.push(this.value);
            });
            $j('#moveForm').submit();
        	console.log(checkList);
		});
		
		let moveForm = $j("moveForm");
		$j(".pageInfo a").on("click", function(e){
			//e.preventDefault();
			//moveForm.find("input[name='checkList']").var($(this).attr("href"));
			 $j(".checkRow").prop('checked', true);
			$j('.checkRow').submit();
			//$j(".check").prop('checked');
			
		});
		
		  $j(".moveFrom").on("click", function(e){
	            //e.preventDefault();
	            //find("input[name='checkList']").val(checkList);
                $j(".checkRow").prop('checked', true);
	            
	            //moveForm.attr("action", "/board/boardList");
	           // moveform.submit();
	        });
		
	});
</script>
<body>
	<table align="center">
		<tr>
			<td align="right">total : ${totalCnt}</td>
		</tr>
		<tr>
			<td>
				<table id="boardTable" border="1">
					<tr>
						<td width="80" align="center">Type</td>
						<td width="40" align="center">No</td>
						<td width="300" align="center">Title</td>
					</tr>
					<c:forEach items="${boardList}" var="list">
						<tr>
							<td align="center">${list.codeName}</td>
							<td>${list.boardNum}</td>
							<td>
						        <a href="/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right"><a href="/board/boardWrite.do">�۾���</a></td>
		</tr>
    </table>
    <div>
      
    </div>
    <div class="pageInfo_wrap">
        <div class="pageInfo_area">
            <ul id="pageInfo" class="pageInfo">
            <c:choose>
                <c:when test="${pageVo.checkRow != null}">
	                <c:if test="${pageMakerVo.prev}">
	                    <li class="pageInfo_btn previous"><a href="boardList.do?checkRow=${pageVo.checkRow}&pageNo=${pageMakerVo.startPage -1}">Previous</a></li>
	                </c:if>
	                <c:forEach begin="${pageMakerVo.startPage}" end="${pageMakerVo.endPage}" var="num" >
	                    <li class="pageInfo_btn"><a href="boardList.do?checkRow=${pageVo.checkRow}&pageNo=${num}">${num}</a></li>
	                </c:forEach>
	                <c:if test="${pageMakerVo.next}">
	                    <li class="pageInfo_btn next"><a href="boardList.do?checkRow=${pageVo.checkRow}&pageNo=${pageMakerVo.endPage +1}">Next</a></li>
	                </c:if>
	            </c:when>
	            <c:otherwise>
	               <c:if test="${pageMakerVo.prev}">
                        <li class="pageInfo_btn previous"><a href="boardList.do?pageNo=${pageMakerVo.startPage -1}">Previous</a></li>
                    </c:if>
                    <c:forEach begin="${pageMakerVo.startPage}" end="${pageMakerVo.endPage}" var="num" >
                        <li class="pageInfo_btn"><a href="boardList.do?pageNo=${num}">${num}</a></li>
                    </c:forEach>
                    <c:if test="${pageMakerVo.next}">
                        <li class="pageInfo_btn next"><a href="boardList.do?pageNo=${pageMakerVo.endPage +1}">Next</a></li>
                    </c:if>
	            </c:otherwise>
	        </c:choose>
            </ul>
        </div>
    </div>
    <div>
        
    </div>
	<form id="moveForm" method="GET">
	   <input name="checkAll" id="checkAll" type="checkbox">��ü
            <c:forEach items="${codeList}" var="list">
                <input name="checkRow" class="checkRow" type="checkbox" value="${list.codeID}">${list.codeName}
                <c:if test="${checkRow eq codeList}">checked</c:if>
            </c:forEach>
	   <input id="inquire" type="button" value="��ȸ">
      <!--  <input type = "hidden" name = "checkRow" value = "${pageVo.checkRow}">-->
        <input type = "hidden" name = "pageNo" value = "${pageMakerVo.pageVo.pageNo}">
        <input type = "hidden" name = "perPageNum" value = "${pageMakerVo.pageVo.perPageNum}">
    </form>
</body>
</html>