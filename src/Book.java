
public class Book extends Document {

	public Book(String title, String authorName, String contents, String iSBN, double price) {
		super(title, authorName, contents, iSBN, price);
	}

	@Override
	public String headerString() {
		return ("Book: "+title+" by "+authorName+", $"+price);
	}

}
