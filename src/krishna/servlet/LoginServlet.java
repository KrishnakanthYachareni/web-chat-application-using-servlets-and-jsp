package krishna.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import krishna.bean.ChatRoomList;

public class LoginServlet extends javax.servlet.http.HttpServlet {
	private String contextPath;

	public LoginServlet() {
		jdMethod_this();
	}

	private final void jdMethod_this() {
		contextPath = "";
	}

	public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws javax.servlet.ServletException, java.io.IOException {
		contextPath = paramHttpServletRequest.getContextPath();
		paramHttpServletResponse.sendRedirect(contextPath + "/login.jsp");
	}

	public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws javax.servlet.ServletException, java.io.IOException {
		contextPath = paramHttpServletRequest.getContextPath();
		String str1 = paramHttpServletRequest.getParameter("nickname");
		str1 = str1.trim().toLowerCase();
		if ((str1 != null) && (str1.length() > 3) && (str1.indexOf(" ") == -1)) {
			try {
				ChatRoomList localChatRoomList = (ChatRoomList) getServletContext().getAttribute("chatroomlist");
				boolean bool = localChatRoomList.chatterExists(str1);
				if (bool) {
					paramHttpServletResponse.sendRedirect(contextPath + "/login.jsp?d=t&n=" + str1);
				} else {
					HttpSession localHttpSession = paramHttpServletRequest.getSession(true);
					int i = 1800;
					String str3 = getServletContext().getInitParameter("sessionTimeout");
					if (str3 != null) {
						try {
							i = Integer.parseInt(str3);
							i *= 60;
						} catch (NumberFormatException localNumberFormatException) {
						}
					}

					localHttpSession.setMaxInactiveInterval(i);
					localHttpSession.setAttribute("nickname", str1);

					krishna.bean.ChatRoom localChatRoom = localChatRoomList.getRoom("StartUp");
					str1 = str1.toLowerCase();
					krishna.bean.Chatter localChatter = null;

					localChatter = new krishna.bean.Chatter(str1, new java.util.Date().getTime());
					localChatRoom.addChatter(localChatter);
					paramHttpServletResponse.sendRedirect(contextPath + "/chat.jsp");
				}

			} catch (Exception localException) {
				System.out.println("Exception thrown in LoginServlet: " + localException.getMessage());
				localException.printStackTrace();
				paramHttpServletResponse.sendRedirect(contextPath + "/error.jsp");
			}

		} else {
			paramHttpServletResponse.sendRedirect(contextPath + "/login.jsp?ic=t");
		}
	}
}
