package Model;

import java.util.HashMap;
import java.util.Map;

public class Livro extends Produto implements PrototipavelIF{
	
	private String isbn;
	private double preco;	
	private Map<String, FormatoIF> formatos;
			
	public Livro(String nome, String codigo, double preco, String isbn) {
		super(nome, codigo);
		this.setIsbn(isbn);
		this.setPreco(preco);
		this.formatos = new HashMap<String, FormatoIF>();		
	}

	public Livro(Livro livro) {
		super(livro.getNome(), livro.getCodigo());
		this.setIsbn(livro.getIsbn());
		this.setPreco(livro.getPreco());		
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	private void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}
	
	private void setPreco(double preco) {
		this.preco = preco;		
	}

	@Override
	public String toString() {
		return "\n - ISBN => " + isbn 
				+ " |Nome do Livro => " + nome 
				+ " |Codigo do Livro => " + codigo 
				+ " |Preco do Livro => " + preco;
	}
	
	@Override
	public PrototipavelIF prototipar() {
		return new Livro(this);
	}
	
	public void addFormato(FormatoIF formato) {				
		this.formatos.put(formato.getFormatacao(), formato);
		this.preco = this.preco * formato.getPercentLivro();		
	}
	
	public String getFormato() {
		String format = "";
		for (String key : formatos.keySet()) {
		    format = format + key;
		}		
		return "Livro " + format;
	}
	
}
