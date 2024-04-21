package gotlisted;

public class ToDoBean {
	private int id;
	private String title;
	private String descr;
	private boolean done;
	private int user_id;
	
	public ToDoBean(int id, String title, String descr, boolean done, int user_id) {
		this.id = id;
		this.title = title;
		this.descr = descr;
		this.done = done;
		this.user_id = user_id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
