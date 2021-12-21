package Model;

public class FormatoDecorator implements FormatoIF {

	protected FormatoIF formatado;
	
	public FormatoDecorator(FormatoIF formatado) {
		this.formatado = formatado;
	}
	
	@Override
	public double getPercentLivro() {		
		return this.formatado.getPercentLivro();
	}

	@Override
	public String getFormatacao() {		
		return this.formatado.getFormatacao();
	}

}
