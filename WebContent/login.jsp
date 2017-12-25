<%@ page errorPage="error.jsp"%>
<HTML>
<HEAD>
<style>
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: ;
	color: white;
	text-align: center;
}

html {
	background: url(back2.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

td {
	font-family: 'Press Start 2P', serif;
	font-size: 15px;
	text-shadow: 5px 5px 5px #aaa;
}
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}
</style>
<TITLE>Public Web Chat Application</TITLE>
<META http-equiv="pragma" content="no-cache">
<meta name="Author" content="yacharenikrishnakanth@outlook.com">
<script language="JavaScript">
	
</script>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/chat.css">
</HEAD>
<BODY onLoad="document.login.nickname.focus();">
	<%@ include file="/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<TABLE width="40%" border="0" cellspacing="1" cellpadding="1"
		align="center">
		<%
			String d = request.getParameter("d");
			String n = request.getParameter("n");
			String ic = request.getParameter("ic");

			if (d != null && d.equals("t")) {
		%>
		<TR>
			<TD>
				<TABLE width="100%" border="0" cellspacing="1" cellpadding="1"
					align="center">
					<TR>
						<TD colspan="2" align="center"><SPAN class="error">Name
								exists</SPAN><BR></TD>
					</TR>
					<TR>
						<TD colspan="2">Name <B><%=n%></B> has already been taken
							please select some other name.
						</TD>
					</TR>
				</TABLE>
			</TD>
		</TR>
		<%
			} else if (ic != null && ic.equals("t")) {
		%>
		<TR>
			<TD colspan="2">
				<TABLE width="100%" border="0" cellspacing="1" cellpadding="1"
					align="center">
					<TR>
						<TD colspan="2" align="center"><SPAN class="error">Incomplete
								information</SPAN></TD>
					</TR>
					<TR>
						<TD colspan="2"><font class="error"> <b>Name</b> must
								be entered and Name must be atleast <b>4</b> characters long and
								must not contain any <b>space</b>. </TD>
						</font>
					</TR>
				</TABLE>
			</TD>
		</TR>
		<%
			}
		%>
		<center><strong> Enter your name</strong></center>
		<TR>
			<TD colspan="2" >
				<FORM name="login" method="post" action="servlet/login">
					<TABLE width="100%" border="0">
						<TR>
							<TD></TD>
							<TD width="100%"><INPUT type="text" name="nickname" size="15" placeholder="Enter your name..">
							</TD>
						</TR>
						<TD>&nbsp;  </TD>
						<TD><INPUT type="submit" name="Submit" value="Submit">
						</TD>
						</TR>
					</TABLE>
				</FORM>
			</TD>
		</TR>
	</TABLE>

	<div class="footer">
		<p>&copy; Copyright 2017 Krishnakanth Yachareni</p>
	</div>
</BODY>

</HTML>
