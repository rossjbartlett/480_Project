
public abstract class Document {
	protected String title;
	protected String authorName;
	protected String contents;
	protected String ISBN;
	protected double price;
	protected int quantity;
	
	public Document(String title, String authorName, String contents, String iSBN, double price, int q) {
		this.title = title;
		this.authorName = authorName;
		this.contents = contents;
		ISBN = iSBN;
		this.price = price;
		quantity = q;
	}

	public Document(Document document)
	{
		title = document.title;
		authorName = document.authorName;
		contents = document.contents;
		ISBN = document.ISBN;
		price = document.price;
		quantity = document.quantity;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public abstract String headerString();
	
	
}
