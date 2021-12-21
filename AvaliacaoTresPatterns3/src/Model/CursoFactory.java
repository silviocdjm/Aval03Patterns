package Model;

public class CursoFactory extends ProdutoFactory{

	public CursoFactory(String nome, String codigo) {
		super(nome, codigo);
	}
	
	public CursoFactory() {
		super();
	}
	
	public Produto createProduto() {
		return new Curso(this.nome, this.codigo);
	}
}
