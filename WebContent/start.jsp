
<%@ page errorPage="error.jsp" import="java.util.Set,java.util.Iterator,java.util.Map,krishna.bean.*"%>
<%
    String roomname = request.getParameter("rn");
    String nickname = (String)session.getAttribute("nickname");
    ChatRoomList roomlist = (ChatRoomList) application.getAttribute("chatroomlist");
    if (nickname == null)
    {
        response.sendRedirect("login.jsp");
    }
    else if (roomname == null)
    {
        response.sendRedirect("listrooms.jsp");
    }
    else
    {
        ChatRoom chatRoom = roomlist.getRoom(roomname);
        if (chatRoom == null)
        {
            out.write("<font color=\"red\" size=\"+1\">Room " + roomname + " not found</font>");
            out.close();
            return;
        }
        ChatRoom chatRoomOld = roomlist.getRoomOfChatter(nickname);
        if (chatRoomOld != null && chatRoom != null)
        {
            Chatter chatter = chatRoomOld.getChatter(nickname);

            if (!chatRoomOld.getName().equals(chatRoom.getName()))
            {
                chatRoomOld.removeChatter(nickname);
                chatRoom.addChatter(chatter);
                if (!chatRoomOld.getName().equalsIgnoreCase("StartUp"))
                {
                    chatRoomOld.addMessage(new Message("system", nickname + " has left and joined " +   chatRoom.getName() + ".", new java.util.Date().getTime()));
                }
                chatRoom.addMessage(new Message("system", nickname + " has joined.", new java.util.Date().getTime()));
                chatter.setEnteredInRoomAt(new java.util.Date().getTime());

            }

            if (session.getAttribute("nickname") == null)
            {
                session.setAttribute("nickname", nickname);
            }
            response.sendRedirect("chat.jsp");
        }
        else
        {
            out.write("<span class=\"error\">Some error occured");
        }
    }
%>
