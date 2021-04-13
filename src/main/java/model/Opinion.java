package main.java.model;

public class Opinion {

	public Opinion(int _id, int _rate, String _text, int _userId, int _bookId) {
		this.id = _id;
		this.rate = _rate;
		this.text = _text;
		this.userId = _userId;
		this.bookId = _bookId;
	}

	public Opinion(){
		this.id = 0;
		this.rate = 0;
		this.text = "MISSING TEXT";
		this.userId = 0;
		this.bookId = 0;
	}

	public int id;
	public int rate;
	public String text;
	public int userId;
	public int bookId;

	@Override
	public String toString() {
		return "Opinion{" +
				"id=" + id +
				", rate=" + rate +
				", text='" + text + '\'' +
				", userId=" + userId +
				", bookId=" + bookId +
				'}';
	}
}
