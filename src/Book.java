
public class Book extends Document {

	public Book(String title, String authorName, String contents, String iSBN, double price, int q) {
		super(title, authorName, contents, iSBN, price, q);
	}

	public Book(Document document)
	{
		super(document);
	}
	
	@Override
	public String headerString() {
		return ("Book: "+title+" by "+authorName+", $"+price+", QTY: "+quantity);
	}


}
