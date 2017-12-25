
<%@ page isErrorPage="false" errorPage="error.jsp" import="java.util.Set,java.util.Iterator,java.util.Map,java.util.Date,java.text.DateFormat,krishna.bean.*"%>
<%@ include file="header.jsp" %>
<%
    String roonName = null;
    String nickname = (String)session.getAttribute("nickname");
    ChatRoomList roomList = null;
    ChatRoom chatRoom = null;
    Chatter chatter = null;
    Message[] messages = null;

    if (nickname != null)
    {
        try
        {
            roomList = (ChatRoomList) application.getAttribute("chatroomlist");
            roonName = roomList.getRoomOfChatter(nickname).getName();
            if (roonName != null && roonName != "")
            {
                chatRoom = roomList.getRoom(roonName);
                chatter = chatRoom.getChatter(nickname);
                if (chatRoom != null)
                {
                    long enteredAt = chatter.getEnteredInRoomAt();
                    if (enteredAt != -1)
                    {
                        messages = chatRoom.getMessages(enteredAt);
                    }
                    else
                    {
                        messages = chatRoom.getMessages(chatter.getLoginTime());
                    }

                }
                else
                {
                    out.write("<b>Room " + roonName + " not found</b>");
                    out.close();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception: "+ e.getMessage());
            throw new ServletException("Unable to get handle to ServletContext");
        }

%>
<HTML>
<HEAD>
    <style>
        html {
        background-color:#F0FFFF;
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
      input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 8px 8px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
    </style>

<link rel="stylesheet" type="text/css" href="chat.css">
<%
    int refreshAfter = 100; // 5 seconds
    String t = application.getInitParameter("refreshAfter");
    if (t != null)
    {
        try
        {
            refreshAfter = Integer.parseInt(t);
            refreshAfter = refreshAfter * 1000; // convert to mili seconds
        }
        catch (NumberFormatException nfe)
        {
        }
    }
%>
<script language="JavaScript" type="text/javascript">
<!--
function reload()
{
    window.location.reload();
}

setInterval('reload()', <%=refreshAfter%>);

function winopen(path)
{
    chatterinfo = window.open(path,"chatterwin","scrollbars=no,resizable=yes, width=400, height=300, location=no, toolbar=no, status=no");
    chatterinfo.focus();
}
</script>
</HEAD>
<BODY onLoad="window.location.hash='#current'">

<table width="100%" border="0">
<tr>
<td width="70%" valign="top">
<table>
<tr>
<td>
<h3>Welcome <%=(String)session.getAttribute("nickname")%>!</h3>
<%

    if(messages != null && messages.length > 0)
    {
        for (int i = 0; i < messages.length; i++)
        {
            Message message = (Message)messages[i];
            String chatterName = message.getChatterName();
            String strmsg = message.getMessage();
            long time = message.getTimeStamp();
            Date date = new Date(time);
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");  
            String strDate= formatter.format(date);  
            out.print("["+strDate);
            if (chatterName.equalsIgnoreCase((String)session.getAttribute("nickname")))
            {
                out.write("<font face=\"Arial\" size=\"2\" color=\"blue\"><b>" + " "+ DateFormat.getTimeInstance().format(date)+ "] "+chatterName+": "+"</b></font> <font face=\"Press Start 2P\" size=\"2\" color=#009966>" +  strmsg+"</font><br>\n");
            }
            else if (chatterName.equalsIgnoreCase("system"))
            {
                out.write("<span class=\"error\"><font face=\"Henny Penny\" size=\"2\" color=\"green\">" + strmsg+"</font></span><br>\n");
            }
            else
            {
                out.write("<font face=\"Arial\" color=\"red\" size=\"2\"><b>"+" "+ DateFormat.getTimeInstance().format(date)+ "] "+chatterName+": "+"</b></font> <font face=\"Press Start 2P\" size=\"2\" color=\"#58d244\">" + strmsg + "</font><br>\n");
            }
        }
        out.write("<a name=\"current\"></a>");
    }
    else
    {
        out.write("<font color=\"red\" face=\"Arial\" size=\"2\">There are currently no messages in this room</font>");
    }
    out.write("<a name=\"current\"></a>");
    %>
</td>
</tr>
</table>
    </td>
  <td width="30%" valign="top">
    <table width="100%" border="1" cellpadding="2" cellspacing="0">
        <tr>
            <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#2C259C">
        <tr>
            <td>
    <span class="white"><%=chatRoom.getNoOfChatters()%> People are online </span><br>
    </td>
    </tr>
    </table>
    <%
    Chatter[] chatters = chatRoom.getChattersArray();
    for(int i = 0; i < chatters.length; i++)
    {
        if (chatters[i].getName().equals(session.getAttribute("nickname")))
        {
    %>
    <font face="Arial" size="2" color="blue"><%=chatters[i].getName() + "<br>"%></font>
    <%
        }
        else
        {
            out.write(chatters[i].getName()+"<br>");
        }
    }

}
else
{
    response.sendRedirect("login.jsp");
}
%>
        </td>
    </tr>
    
    <TABLE border="0" cellpadding="3" cellspacing="0" class="panel">
					<TR align="left" valign="top">
						<FORM name="refresh">
							<TD><INPUT type="submit" value="Refresh"
								onClick="top.frames[0].location.reload()"></TD>
						</FORM>
						<FORM name="logout" action="logout.jsp" method="post"
							target="_top">
							<TD width="10%"><INPUT type="Submit" value="Logout">
							</TD>
						</FORM>
						<TD>&nbsp;</TD>
					</TR>
				</TABLE>
</table> 
</td>

</tr> 
</table>
</BODY>
</HTML>
