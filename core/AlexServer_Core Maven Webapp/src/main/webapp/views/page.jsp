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
			JSPҳ����ʾģ��
			
			ʹ��ʾ����
				<jsp:include page="components/pageNumber.jsp">
					<jsp:param name="itemSum" value="11"/> 
					<jsp:param name="pageUrl" value="back/MyJsp.jsp?parmar1=abc&parmar2=cde"/> 
		    	</jsp:include> 
		    ������
		    	itemSum:Ԫ�ص��ܸ���
		    	pageUrl:ÿһҳ��ͬ��ַ
		    	
		    ����ҳ���ȡ������
		    String pageIndexStr = request.getParameter("pageIndex");
			String pageCountStr = request.getParameter("pageCount");
			int pageIndex = 0;
			int pageCount = 0;
			if (pageIndexStr != null)
				pageIndex = Integer.parseInt(pageIndexStr);
			if (pageCountStr != null)
				pageCount = Integer.parseInt(pageCountStr);
			
			��ǰҳ��pageIndex(0~n)
			ÿҳ��ʾ�ĸ�����pageCount(1~n)
			
			ע�⣺
				id��js�����������ռ䣺��pageNumber_��
		 */
	%>

	<script type="text/javascript">
		function pageNumber_goto() {
			var pageNum = document.getElementById('pageNumber_pageNum').value;
			var pageUrl = document.getElementById('pageNumber_pageUrl').value;
			var i = document.getElementById('pageNumber_pageInput').value;
			if (isNaN(i)) {
				alert("�����ҳ�벻�Ϸ��������� 1~" + (pageNum + 1) + " ������");
				document.getElementById('pageNumber_pageInput').value = '';
				return;
			}
			pageNum = parseInt(pageNum);
			i = parseInt(i);
			if (i >= 1 && i <= pageNum + 1) {
				self.location = pageUrl + '&pageIndex=' + (i - 1);
			} else {
				alert("�����ҳ�벻�Ϸ��������� 1~" + (pageNum + 1) + " ������");
				document.getElementById('pageNumber_pageInput').value = '';
				return;
			}
		}
	</script>

	<%
		// ���в����������ʾ��ҳ�����
		int maxShowPageNum = 6;
		int pageIndexDef = 0;
		int pageCountDef = 10;

		// ��ȡ��ҳ�洫���Ĳ���
		// �Ӳ�����ȡԪ���ܸ���
		int itemSum = Integer.parseInt(request.getParameter("itemSum"));
		// ת��ĵ�ַ����������������
		String pageUrl = request.getParameter("pageUrl");

		// ��ȡ�ϴη�ҳ�����Ĳ���
		String pageCountStr = request.getParameter("pageCount");
		String pageIndexStr = request.getParameter("pageIndex");

		// ��������
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

		// ������ҳ��
		int pageNum = (int) (itemSum / pageCount + 0.5);
		if (pageCount * (pageNum - 1) == itemSum)
			pageNum = pageNum - 1;
	%>

	<!-- ��js���ݲ��� -->
	<input type="hidden" id="pageNumber_pageNum" value="<%=pageNum%>">
	<input type="hidden" id="pageNumber_pageUrl" value="<%=pageUrl%>">

	<!-- ��ʾԪ�ص������� -->
	<input disabled="disabled" value="<%=itemSum%>" style="width:40px;">

	<!-- ��ʾ��ǰ����ҳ���ܹ�����ҳ -->
	<input disabled="disabled"
		value="<%="" + (pageIndex + 1) + "/" + (pageNum + 1)%>" style="width:50px;">

	<!-- �����ǰҳ���������ʾ��ҳ������һҳҳ�벻�ɼ�������ʾ�ص���һҳ -->
	<%
		if (pageIndex > (maxShowPageNum / 2)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + 0%>"><%="|<"%></a>
	<%
		}
	%>

	<!-- ��������ڵ�һҳ����ʾ����һҳ -->
	<%
		if (pageIndex > 0) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageIndex - 1)%>"><%="<<"%></a>
	<%
		}
	%>

	<!-- ��ʾҳ�� -->
	<%
		int startPage = 0;
		int endPage = 0;
		if (pageIndex <= maxShowPageNum / 2) {
			// �����ǰҳ��С����ʾ���ҳ������һ��
			startPage = 1;
			if (pageNum <= maxShowPageNum) {
				endPage = pageNum + 1;
			} else {
				endPage = maxShowPageNum;
			}
		} else if (pageIndex > (pageNum - maxShowPageNum / 2)) {
			// �����ǰҳ��������ҳ��-��ʾ���ҳ������һ��
			startPage = pageNum - maxShowPageNum;
			endPage = pageNum;
		} else {
			// ���򣬱��ֵ�ǰҳ�������ҳ�������м�
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

	<!-- ����������һҳ����ʾ��һҳ��ť -->
	<%
		if (pageIndex < (pageNum)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageIndex + 1)%>"><%=">>"%></a>
	<%
		}
	%>

	<!-- ���С�����ҳ��-֧����ʾ��ҳ��������ʾ�����һҳ -->
	<%
		if (pageIndex < (pageNum - maxShowPageNum / 2)) {
	%>
	<a href="<%=pageUrl + "&pageIndex=" + (pageNum)%>"><%=">|"%></a>
	<%
		}
	%>

	<!-- ��ʾҳ������� -->
	<input id="pageNumber_pageInput" type="text"
		value="<%=(pageIndex + 1)%>"
		onclick="document.getElementById('pageNumber_pageInput').value='';"
		onkeydown="if(event.keyCode==13){pageNumber_goto()}"
		style="width:40px;">
	<button onclick="pageNumber_goto()">ת��</button>


</body>
</html>