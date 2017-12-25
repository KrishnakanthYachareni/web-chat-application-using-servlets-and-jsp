<%@ page isErrorPage="false" errorPage="error.jsp"
	import="java.util.Set,java.util.Iterator,java.util.Map,krishna.bean.*"%>
<%
	String nickname = (String) session.getAttribute("nickname");

	if (nickname != null && nickname.length() > 0) {
		ChatRoomList roomList = (ChatRoomList) application.getAttribute("chatroomlist");
		ChatRoom chatRoom = roomList.getRoomOfChatter(nickname);
		if (chatRoom != null) {
			String msg = request.getParameter("messagebox");

			if (msg != null && msg.length() > 0) {
				msg = msg.trim();
				chatRoom.addMessage(new Message(nickname, msg, new java.util.Date().getTime()));
			}
%>
<HTML>
<HEAD>
<LINK rel="stylesheet" type="text/css" href="chat.css">
<style>
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color:;
	color: black;
	text-align: center;
}
input[type=text], select {
    width: 100%;
    padding: 8px 10px;
    margin: 5px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 8px 10px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=submit]:hover {
    background-color: #45a049;
}
.container {
  height: 100%;
  width: 100%;
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: center;
}
</style>
</HEAD>
<BODY onLoad="document.msg.messagebox.focus();" background="back2.jpg">
	<TABLE margin    align = "center" width="100%" cellpadding="3" cellspacing="0">
		<TR>
			<TD width="50%" align="center" valign="top">
				<TABLE>
					<TR>
						<FORM name="msg" action="sendMessage.jsp" method="post">
							<TD width="100%"><INPUT type="text" name="messagebox" placeholder="Enter message to send"
								maxlength="300" size="35"> <INPUT type="hidden"
								name="nickname" value="<%=session.getAttribute("nickname")%>">
								<INPUT name="submit" type="submit" value="Send"></TD>
						</FORM>
					</TR>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
	<div class="footer">
		<p>&copy; Copyright 2017 Krishnakanth Yachareni</p>
	</div>
</BODY>
</HTML>
<%
	} else {
			out.write("<h2 class=\"error\">Your room couldn't be found. You can't send message</h2>");
		}
	} else {
		response.sendRedirect("login.jsp");
	}
%>
