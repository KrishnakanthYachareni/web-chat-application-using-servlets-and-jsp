package krishna.bean;

public class Chatter {
	private String name;
	private String comment;
	private String email;
	private long loginTime;
	private long enteredInRoomAt;
	private int age;

	private final void jdMethod_this() {
		name = null;
		comment = null;
		email = null;
		loginTime = -1;
		enteredInRoomAt = -1;
		age = -1;
	}

	public Chatter(String paramString1, long paramLong) {
		jdMethod_this();
		name = paramString1;
		loginTime = paramLong;
	}

	public String getName() {
		return name;
	}

	public void setComment(String paramString) {
		comment = paramString;
	}

	public String getComment() {
		return comment;
	}

	public void setAge(int paramInt) {
		age = paramInt;
	}

	public int getAge() {
		return age;
	}

	public void setEmail(String paramString) {
		email = paramString;
	}

	public String getEmail() {
		return email;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setEnteredInRoomAt(long paramLong) {
		enteredInRoomAt = paramLong;
	}

	public long getEnteredInRoomAt() {
		return enteredInRoomAt;
	}
}
