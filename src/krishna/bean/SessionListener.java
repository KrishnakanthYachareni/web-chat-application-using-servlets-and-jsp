package krishna.bean;

import java.io.PrintStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import krishna.bean.ChatRoom;
import krishna.bean.ChatRoomList;
import krishna.bean.Chatter;

public class SessionListener implements HttpSessionAttributeListener {
	public void attributeAdded(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
	}

	public void attributeReplaced(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
	}

	public void attributeRemoved(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
		String str1 = paramHttpSessionBindingEvent.getName();
		HttpSession localHttpSession = paramHttpSessionBindingEvent.getSession();
		if ("nickname".equalsIgnoreCase(str1)) {
			String str2 = (String) paramHttpSessionBindingEvent.getValue();
			if (str2 != null) {
				ServletContext localServletContext = localHttpSession.getServletContext();
				if (localServletContext != null) {
					Object localObject1 = localServletContext.getAttribute("chatroomlist");
					if (localObject1 != null) {
						ChatRoomList localChatRoomList = (ChatRoomList) localObject1;
						ChatRoom localChatRoom = localChatRoomList.getRoomOfChatter(str2);
						if (localChatRoom != null) {
							Object localObject2 = localChatRoom.removeChatter(str2);
							if (localObject2 != null) {
								String str3 = ((Chatter) localObject2).getName();
							}

						}
					}
				} else {
					System.out.println("ServletContext is null");
				}
			}
		}
	}

	public SessionListener() {
	}
}
