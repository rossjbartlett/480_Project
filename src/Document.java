
public abstract class Document {
	private String title;
	private String authorName;
	private String contents;
	private String ISBN;
	private double price;
	
	public Document(String title, String authorName, String contents, String iSBN, double price) {
		this.title = title;
		this.authorName = authorName;
		this.contents = contents;
		ISBN = iSBN;
		this.price = price;
	}
	
	
}
