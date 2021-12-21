package Model;

public enum TipoProduto {

	LIVRO("Model.LivroFactory"),
	DISCIPLINA("Model.DisciplinaFactory"),
	CURSO("Model.CursoFactory");
	
	String className;
	
	private TipoProduto(String className) {
		this.className = className;
	}
	
	public String getFactoryName() {
		return this.className;
	}
	
}
