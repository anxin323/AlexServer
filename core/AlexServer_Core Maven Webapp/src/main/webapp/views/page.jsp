<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>

<body>

	<%
		/**
			JSP页码显示模块
			
			使用示例：
				<jsp:include page="components/pageNumber.jsp">
					<jsp:param name="itemSum" value="11"/> 
					<jsp:param name="pageUrl" value="back/MyJsp.jsp?parmar1=abc&parmar2=cde"/> 
		    	</jsp:include> 
		    参数：
		    	itemSum:元素的总个数
		    	pageUrl:每一页共同地址
		    	
		    引用页码获取参数：
		    String pageIndexStr = request.getParameter("pageIndex");
			String pageCountStr = request.getParameter("pageCount");
			int pageIndex = 0;
			int pageCount = 0;
			if (pageIndexStr != null)
				pageIndex = Integer.parseInt(pageIndexStr);
			if (pageCountStr != null)
				pageCount = Integer.parseInt(pageCountStr);
			
			当前页：pageIndex(0~n)
			每页显示的个数：pageCount(1~n)
			
			注意：
				id和js方法的命名空间：“pageNumber_”
		 */
	%>

	<script type="text/javascript">
		function pageNumber_goto() {
			var pageNum = document.getElementById('pageNumber_pageNum').value;
			var pageUrl = document.getElementById('pageNumber_pageUrl').value;
			var i = document.getElementById('pageNumber_pageInput').value;
			if (isNaN(i)) {
				alert("输入的页码不合法，请输入 1~" + (pageNum + 1) + " 的数字");
				document.getElementById('pageNumber_pageInput').value = '';
				return;
			}
			pageNum = parseInt(pageNum);
			i = parseInt(i);
			if (i >= 1 && i <= pageNum + 1) {
				self.location = pageUrl + '&pageIndex=' + (i - 1);
			} else {
				alert("输入的页码不合法，请输入 1~" + (pageNum + 1) + " 的数字");
				document.getElementById('pageNumber_pageInput').value = '';
				return;
			}
		}
	</script>

	<%
		// 运行参数：最大显示的页码个数
		int maxShowPageNum = 6;
		int pageIndexDef = 0;
		int pageCountDef = 10;

		// 获取父页面传来的参数
		// 从参数获取元素总个数
		int itemSum = Integer.parseInt(request.getParameter("itemSum"));
		// 转向的地址（包含其它参数）
		String pageUrl = request.getParameter("pageUrl");

		// 获取上次分页传来的参数
		String pageCountStr = request.getParameter("pageCount");
		String pageIndexStr = request.getParameter("pageIndex");

		// 解析参数
		int pageCount = pageCountDef;
		int pageIndex = pageIndexDef;
		if (pageCountStr != null)
			pageCount = Integer.parseInt(pageCountStr);
		if (pageIndexStr != null)
			pageIndex = Integer.parseInt(pageIndexStr);
		if (pageUrl.indexOf("?") == -1) {
			pageUrl = pageUrl + "?pageCount=" + pageCount;
		} else {
			pageUrl = pageUrl + "&pageCount=" + pageCount;
		}

		// 计算总页数
		int pageNum = (int) (itemSum / pageCount + 0.5);
		if (pageCount * (pageNum - 1) == itemSum)
			pageNum = pageNum - 1;
	%>

	<!-- 向js传递参数 -->
	<input type="hidden" id="pageNumber_pageNum" value="<%=pageNum%>">
	<input type="hidden" id="pageNumber_pageUrl" value="<%=pageUrl%>">

	<!-- 显示元素的总数量 -->
	<input disabled="disabled" value="<%=itemSum%>" style="width:40px;">

	<!-- 显示当前多少页和总共多少页 -->
	<input disabled="disabled"
		value="<%="" + (pageIndex + 1) + "/" + (pageNum + 1)%>" style="width:50px;">

	<!-- 如果当前页大于最大显示的页数（第一页页码不可见），显示回到第一页 -->
	<%
		if (pageIndex > (maxShowPageNum / 2)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + 0%>"><%="|<"%></a>
	<%
		}
	%>

	<!-- 如果不是在第一页，显示向上一页 -->
	<%
		if (pageIndex > 0) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageIndex - 1)%>"><%="<<"%></a>
	<%
		}
	%>

	<!-- 显示页码 -->
	<%
		int startPage = 0;
		int endPage = 0;
		if (pageIndex <= maxShowPageNum / 2) {
			// 如果当前页码小于显示最大页码数的一半
			startPage = 1;
			if (pageNum <= maxShowPageNum) {
				endPage = pageNum + 1;
			} else {
				endPage = maxShowPageNum;
			}
		} else if (pageIndex > (pageNum - maxShowPageNum / 2)) {
			// 如果当前页面大于最大页数-显示最大页码数的一半
			startPage = pageNum - maxShowPageNum;
			endPage = pageNum;
		} else {
			// 否则，保持当前页码在输出页码数的中间
			startPage = pageIndex - maxShowPageNum / 2 + 1;
			endPage = pageIndex + maxShowPageNum / 2;
		}
		if (startPage < 1)
			startPage = 1;
		if (endPage > (pageNum + 1))
			endPage = (pageNum + 1);
	%>
	<%
		for (int i = startPage; i <= endPage; i++) {
			if (i == (pageIndex + 1)) {
	%>
	<%=i%>
	<%
		} else {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (i - 1)%>"><%=i%></a>
	<%
		}
		}
	%>

	<!-- 如果不是最后一页，显示下一页按钮 -->
	<%
		if (pageIndex < (pageNum)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageIndex + 1)%>"><%=">>"%></a>
	<%
		}
	%>

	<!-- 如果小于最大页数-支持显示的页码数，显示到最后一页 -->
	<%
		if (pageIndex < (pageNum - maxShowPageNum / 2)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageNum)%>"><%=">|"%></a>
	<%
		}
	%>

	<!-- 显示页码输入框 -->
	<input id="pageNumber_pageInput" type="text"
		value="<%=(pageIndex + 1)%>"
		onclick="document.getElementById('pageNumber_pageInput').value='';"
		onkeydown="if(event.keyCode==13){pageNumber_goto()}"
		style="width:40px;">
	<button onclick="pageNumber_goto()">转到</button>


</body>
</html>