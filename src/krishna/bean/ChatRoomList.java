package krishna.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChatRoomList {
	private Map roomList;

	public ChatRoomList() {
		roomList = new HashMap();
	}

	public synchronized void addRoom(ChatRoom paramChatRoom) {
		roomList.put(paramChatRoom.getName(), paramChatRoom);
	}

	public synchronized void removeRoom(String paramString) {
		roomList.remove(paramString);
	}

	public ChatRoom getRoom(String paramString) {
		return (ChatRoom) roomList.get(paramString);
	}

	public ChatRoom getRoomOfChatter(String paramString) {
		ChatRoom[] arrayOfChatRoom = getRoomListArray();
		for (int i = 0; i < arrayOfChatRoom.length; i++) {
			boolean bool = arrayOfChatRoom[i].chatterExists(paramString);
			if (bool) {
				return arrayOfChatRoom[i];
			}
		}
		return null;
	}

	public Set getRoomList() {
		return roomList.entrySet();
	}

	public ChatRoom[] getRoomListArray() {
		ChatRoom[] arrayOfChatRoom = new ChatRoom[roomList.size()];
		Set localSet = getRoomList();
		Iterator localIterator = localSet.iterator();
		int i = 0;
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			String str = (String) localEntry.getKey();
			arrayOfChatRoom[i] = ((ChatRoom) localEntry.getValue());
			i++;
		}
		return arrayOfChatRoom;
	}

	public boolean chatterExists(String paramString) {
		boolean bool = false;
		ChatRoom[] arrayOfChatRoom = getRoomListArray();
		for (int i = 0; i < arrayOfChatRoom.length; i++) {
			bool = arrayOfChatRoom[i].chatterExists(paramString);
			if (bool) {
				break;
			}
		}

		return bool;
	}
}
