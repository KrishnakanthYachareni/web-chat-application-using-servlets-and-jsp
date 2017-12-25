<%@ page import="krishna.bean.*" errorPage="error.jsp"%>
<HTML>
<HEAD>
<TITLE>You are LogOut</TITLE>
<style>
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color:;
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
</style>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/chat.css">
<META http-equiv="pragma" content="no-cache">
<meta name="Author" content="Resm gigahertz@hotmail.com">
</HEAD>

<BODY>
	<%@ include file="header.jsp"%>
	<div align="center">
		<center>

			<%
				String nickname = (String) session.getAttribute("nickname");
				if (nickname != null && nickname.length() > 0) {
					ChatRoomList roomlist = (ChatRoomList) application.getAttribute("chatroomlist");
					ChatRoom chatRoom = roomlist.getRoomOfChatter(nickname);
					chatRoom.addMessage(
							new Message("system", nickname + " has logged out.", new java.util.Date().getTime()));
					if (chatRoom != null) {
						chatRoom.removeChatter(nickname);
						session.invalidate();
						out.write(
								"<br><br><br><br><br><br><br><br><td><font color=\"red\" style=\"font-family:\"Press Start 2P\";\">You successfully logged out</font><br>");
						out.write("<a href=\"login.jsp\">Login again</a></td>");
					} else {
						response.sendRedirect("login.jsp");
					}
				} else {
					response.sendRedirect("login.jsp");
				}
			%>
		</center>
	</div>
	<div class="footer">
		<p>&copy; Copyright 2017 Krishnakanth Yachareni</p>
	</div>
</BODY>
</HTML>
