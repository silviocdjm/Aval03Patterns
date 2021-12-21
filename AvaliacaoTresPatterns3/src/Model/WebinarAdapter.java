package Model;

public class WebinarAdapter extends Disciplina{
	
	private Webinar webinar;

	public WebinarAdapter(String title, String id, String hoster, double price, long minutes) {
		super(title, id);
		this.webinar = new Webinar(id, title, hoster, price, minutes);
	}	
	
	@Override
	public String getNome() {
		return this.webinar.getTitle();
	}
	
	@Override
	public String getCodigo() {
		return this.webinar.getId();
	}
		
	@Override
	public double getPreco() {
		return this.webinar.getPrice();
	}
	
	@Override
	public double getChTotal() {
		return this.webinar.getMinutes()/60;
	}
	
	@Override
	public double getPctCumprido() {
		if(this.webinar.wasWatched()) {
			return 1;
		} else {
			return 0;
		}
	}
}
