package Model;

import java.util.ArrayList;
import java.util.List;

public class CompositeCurso extends Produto implements CompositeIF {
	
	private List<CompositeIF> itens;
	
	public CompositeCurso(String codigo, String nome) {
		super(codigo, nome);
		this.itens = new ArrayList<CompositeIF>();
	}
	
	@Override
	public double getPreco() {
		double precoComposite = 0;
		for(CompositeIF c : this.itens) {
			precoComposite += c.getPreco();
		}
		return precoComposite;
	}
	
	public void adicionarItem(CompositeIF composite) {
		this.itens.add(composite);
	}
	
	public List<CompositeIF> getItens() {
		return this.itens;
	}

	@Override
	public String toString() {
		return "\nNOME DO CURSO COMPOSTO => " + nome
				+ "\nPRECO => " + getPreco()
				+ "\nCODIGO => " + codigo
				+ "\nITENS DO CURSO =>" + itens;
	}
}
