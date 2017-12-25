package krishna.servlet;

import javax.servlet.http.HttpServletRequest;

public class SaveInfoServlet extends javax.servlet.http.HttpServlet { String nickname;
  int age;
  String email;
  String comment;
  javax.servlet.http.HttpSession session;
  String contextPath;
  
  public SaveInfoServlet() { jdMethod_this(); }
  
  private final void jdMethod_this() { nickname = null;
    age = -1;
    email = null;
    comment = null;
    session = null;
    contextPath = null;
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, javax.servlet.http.HttpServletResponse paramHttpServletResponse) throws java.io.IOException, javax.servlet.ServletException {
    nickname = paramHttpServletRequest.getParameter("nickname");
    contextPath = paramHttpServletRequest.getContextPath();
    try
    {
      Integer localInteger = Integer.valueOf(paramHttpServletRequest.getParameter("age"));
      age = localInteger.intValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      age = -1;
    }
    email = paramHttpServletRequest.getParameter("email");
    comment = paramHttpServletRequest.getParameter("comment");
    session = paramHttpServletRequest.getSession(true);
    try
    {
      krishna.bean.ChatRoomList localChatRoomList = (krishna.bean.ChatRoomList)getServletContext().getAttribute("chatroomlist");
      krishna.bean.ChatRoom localChatRoom = localChatRoomList.getRoomOfChatter(nickname);
      

      if (localChatRoom != null)
      {
        krishna.bean.Chatter localChatter = localChatRoom.getChatter(nickname);
        localChatter.setAge(age);
        localChatter.setEmail(email);
        localChatter.setComment(comment);
        paramHttpServletResponse.setContentType("text/html");
        java.io.PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
        localPrintWriter.write("<html>\n<head>\n<title>Information Saved</title>\n</head>\n<body>\n");
        localPrintWriter.write("<b>Information Saved</b>\n<div align=\"center\">\n<form name=\"closing\">\n");
        localPrintWriter.write("<input type=\"button\" onClick=\"window.close()\" value=\"Close\">\n");
        localPrintWriter.write("</form>\n</div>\n</body>\n</html>");
        localPrintWriter.close();
      }
      
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.out.println("Exception: " + localException.getMessage());
      paramHttpServletResponse.sendRedirect(contextPath + "/error.jsp");
    }
  }
  

  public void doPost(HttpServletRequest paramHttpServletRequest, javax.servlet.http.HttpServletResponse paramHttpServletResponse)
    throws java.io.IOException, javax.servlet.ServletException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}
