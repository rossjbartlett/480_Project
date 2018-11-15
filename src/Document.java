
public abstract class Document {
	protected String title;
	protected String authorName;
	protected String contents;
	protected String ISBN;
	protected double price;
	
	public Document(String title, String authorName, String contents, String iSBN, double price) {
		this.title = title;
		this.authorName = authorName;
		this.contents = contents;
		ISBN = iSBN;
		this.price = price;
	}
	
	
	public abstract String headerString();
	
}
