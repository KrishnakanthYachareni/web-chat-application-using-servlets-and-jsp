package krishna.bean;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChatRoom {
	private String name;
	private String description;
	private Map chatters;
	private List messages;
	private int messages_size;

	private final void jdMethod_this() {
		name = null;

		description = null;

		chatters = new java.util.HashMap();

		messages = new java.util.LinkedList();

		messages_size = 25;
	}

	public ChatRoom(String paramString1, String paramString2) {
		jdMethod_this();
		name = paramString1;
		description = paramString2;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public synchronized void addChatter(Chatter paramChatter) {
		chatters.put(paramChatter.getName(), paramChatter);
	}

	public synchronized Object removeChatter(String paramString) {
		return chatters.remove(paramString);
	}

	public Chatter getChatter(String paramString) {
		return (Chatter) chatters.get(paramString);
	}

	public boolean chatterExists(String paramString) {
		return chatters.containsKey(paramString);
	}

	public int getNoOfChatters() {
		return chatters.size();
	}

	public Set getChatters() {
		return chatters.entrySet();
	}

	public Chatter[] getChattersArray() {
		Chatter[] arrayOfChatter = new Chatter[chatters.size()];
		Set localSet = getChatters();
		Iterator localIterator = localSet.iterator();
		int i = 0;
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			String str = (String) localEntry.getKey();
			arrayOfChatter[i] = ((Chatter) localEntry.getValue());
			i++;
		}
		return arrayOfChatter;
	}

	public synchronized void addMessage(Message paramMessage) {
		if (messages.size() == messages_size) {
			((java.util.LinkedList) messages).removeFirst();
		}
		messages.add(paramMessage);
	}

	public ListIterator getMessages() {
		return messages.listIterator();
	}

	public Message[] getMessages(long paramLong) {
		ListIterator localListIterator = messages.listIterator();
		java.util.ArrayList localArrayList = new java.util.ArrayList();

		while (localListIterator.hasNext()) {
			Message localMessage = (Message) localListIterator.next();
			if (localMessage.getTimeStamp() >= paramLong) {
				localArrayList.add(localMessage);
			}
		}
		Object[] arrayOfObject = localArrayList.toArray();
		Message[] arrayOfMessage = new Message[arrayOfObject.length];
		for (int i = 0; i < arrayOfMessage.length; i++) {
			arrayOfMessage[i] = ((Message) arrayOfObject[i]);
		}
		return arrayOfMessage;
	}

	public int getNoOfMessages() {
		return messages.size();
	}

	public void setMaximumNoOfMessages(int paramInt) {
		messages_size = paramInt;
	}

	public int getMaxiumNoOfMessages() {
		return messages_size;
	}
}
