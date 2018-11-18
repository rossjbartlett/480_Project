
public class Journal extends Document {

	public Journal(String title, String authorName, String contents, String iSBN, double price) {
		super(title, authorName, contents, iSBN, price);
		// TODO Auto-generated constructor stub
	}
	
	public Journal(Document document)
	{
		super(document);
	}

	@Override
	public String headerString() {
		return ("Journal: "+title+" by "+authorName+", $"+price);
	}
}
