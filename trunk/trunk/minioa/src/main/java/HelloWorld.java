public class HelloWorld {

	private String message;

	public void setMessage(String data) {
		message = data;
	}

	public String getMessage() {
		return message;
	}

	private int times;

	public void setTimes(int data) {
		times = data;
	}

	public int getTimes() {
		return times;
	}

	public HelloWorld() {

	}

	public void setNewMessage() {
		times++;
		this.setMessage("You click me " + times + " times!");
		System.out.println("You click me " + times + " times!");
	}
}