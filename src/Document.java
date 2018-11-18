
public abstract class Document {
	protected String title;
	protected String authorName;
	protected String contents;
	protected String ISBN;
	protected double price;
	protected int quantity;
	
	public Document(String title, String authorName, String contents, String iSBN, double price) {
		this.title = title;
		this.authorName = authorName;
		this.contents = contents;
		ISBN = iSBN;
		this.price = price;
		quantity = 2;
	}
	
	
	public abstract String headerString();
	
}
