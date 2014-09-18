<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<h2>
		<s:url action="jasper" var="pdfLink">

		</s:url>
		<s:url action="jasper" var="excelLink">
			<s:param name="format" value="'XLS'" />
		</s:url>
		<s:url action="jasper" var="htmlLink">
			<s:param name="format" value="'HTML'" />
		</s:url>

		<a href='<s:property value="#pdfLink" />'> PDF </a><br> <a
			href='<s:property value="#excelLink" />'> Excel </a><br> <a
			href='<s:property value="#htmlLink" />'> HTML </a>

	</h2>
</body>
</html>
