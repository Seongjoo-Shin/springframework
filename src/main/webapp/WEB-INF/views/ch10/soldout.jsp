<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		soldout.jsp
	</div>
	<div class="card-body">
		<p>현재 상품이 모두 판매되었습니다.</p>
		<div>
			<a href="${pageContext.request.contextPath}/ch10/content" class="btn btn-info btn-sm">/ch10/content</a>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>