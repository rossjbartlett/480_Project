
public class Magazine extends Document {

	public Magazine(String title, String authorName, String contents, String iSBN, double price) {
		super(title, authorName, contents, iSBN, price);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String headerString() {
		return ("Magazine: "+title+" by "+authorName+", $"+price);
	}
}
