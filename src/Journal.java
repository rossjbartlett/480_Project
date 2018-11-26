
public class Journal extends Document {

	public Journal(String title, String authorName, String contents, String iSBN, double price, int q) {
		super(title, authorName, contents, iSBN, price, q);
	}
	
	public Journal(Document document)
	{
		super(document);
	}

	@Override
	public String headerString() {
		return ("Journal: "+title+" by "+authorName+", $"+price+", QTY: "+quantity);
	}

}
