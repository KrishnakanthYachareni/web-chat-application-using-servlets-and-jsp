package krishna.bean;

public class Message {
	private String chatterName;
	private String message;
	private long timeStamp;

	private final void jdMethod_this() {
		chatterName = null;

		message = null;
	}

	public Message(String paramString1, String paramString2, long paramLong) {
		jdMethod_this();
		chatterName = paramString1;
		message = paramString2;
		timeStamp = paramLong;
	}

	public String getChatterName() {
		return chatterName;
	}

	public String getMessage() {
		return message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
}
