
public class Magazine extends Document {

	public Magazine(String title, String authorName, String contents, String iSBN, double price, int q) {
		super(title, authorName, contents, iSBN, price, q);
	}
	
	public Magazine(Magazine document)
	{
		super(document);
	}
	
	@Override
	public String headerString() {
		return ("Magazine: "+title+" by "+authorName+", $"+price+", QTY: "+quantity);
	}


}
