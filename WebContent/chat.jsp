
<%@ page session="true"
	import="krishna.bean.ChatRoomList, krishna.bean.ChatRoom"
	errorPage="error.jsp"%>
<%
	String nickname = (String) session.getAttribute("nickname");
	if (nickname != null && nickname.length() > 0) {
		ChatRoomList roomList = (ChatRoomList) application.getAttribute("chatroomlist");
		ChatRoom room = roomList.getRoomOfChatter(nickname);
		String roomname = room.getName();
%>

<HTML>
<HEAD>
<TITLE>Chat DashBoard <%=nickname%>
</TITLE>

<style>
td {
	font-family: 'Press Start 2P', serif;
	font-size: 15px;
	text-shadow: 5px 5px 5px #aaa;
}
</style>
</HEAD>
<FRAMESET rows="70%,30%">
	<FRAME SRC="displayMessages.jsp#current" name="MessageWin">
	<FRAME SRC="sendMessage.jsp" name="TypeWin">
</FRAMESET>

</HTML>
<%
	} else {
		response.sendRedirect("login.jsp");
	}
%>
