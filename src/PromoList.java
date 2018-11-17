import java.util.ArrayList;

public class PromoList implements Subject{
	
	private ArrayList<Document> promolist;
	private ArrayList<Observer> observers;

    public PromoList(){
        promolist = new ArrayList<>();
        observers = new ArrayList<>();
    }
    
    public ArrayList<Document> getPromolist() {
		return promolist;
	}

    @Override
    public void attach(Observer o){
        observers.add(o);
        o.update(promolist); // give them a view of the jobs
    }

    @Override
    public void detach(Observer o){
        observers.remove(o);
    }

    public void addDocument(Document d){
        promolist.add(d);
        notifyAllObservers();
    }

    public void removeJob(Document job){
        promolist.remove(job);
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers(){
        for(Observer o : observers)
            o.update(promolist);
    }

}
