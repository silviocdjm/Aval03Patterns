package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Curso extends Produto implements PrototipavelIF, CompositeIF {

	public class Situacao {

		private Curso curso;
		private List<Livro> situacaoLivros;
		private List<Disciplina> situacaoDisciplinas;
		private String situacaoNome;
		private String situacaoCodigo;	
				
		public Situacao(Curso curso) {
			this.curso = curso;
			this.situacaoNome = curso.getNome();
			this.situacaoCodigo = curso.getCodigo();
			this.situacaoLivros = new LinkedList<Livro>();
			this.situacaoDisciplinas = new LinkedList<Disciplina>();						
			for (Livro liv : curso.livros) {
				this.situacaoLivros.add((Livro) liv.prototipar());
			}			
			for (Disciplina disc : curso.disciplinas) {
				this.situacaoDisciplinas.add((Disciplina) disc.prototipar());
			}
		}

		public void restore() {
			this.curso.setLivros(situacaoLivros);
			this.curso.setDisciplinas(situacaoDisciplinas);
			this.curso.setNome(situacaoNome);
			this.curso.setCodigo(situacaoCodigo);
		}
		
		public List<Disciplina> getDisciplinas() {
			return this.situacaoDisciplinas;
		}

	}

	public List<Disciplina> disciplinas;
	private List<Livro> livros;
	public List<ObserverIF> observers = new ArrayList<ObserverIF>();	
	private CursoStateIF state;

	public Curso(List<Disciplina> disciplinas, List<Livro> livros, String nome, String codigo) {
		super(nome, codigo);
		this.disciplinas = disciplinas;
		this.livros = livros;
		this.observers = new ArrayList<ObserverIF>();		
		this.state = new CursoStateEmAndamento();
	}

	private Curso(Curso curso) {
		super(curso.getNome(), curso.getCodigo());
		this.disciplinas = new LinkedList<Disciplina>();
		for (Disciplina d : curso.disciplinas)
			this.disciplinas.add((Disciplina) d.prototipar());
		this.livros = new LinkedList<Livro>();
		for (Livro l : curso.livros)
			this.livros.add((Livro) l.prototipar());
		this.observers = new ArrayList<ObserverIF>();		
		this.state = new CursoStateEmAndamento();
	}

	public Curso(String nome, String codigo) {
		super(nome, codigo);
		this.disciplinas = new ArrayList<Disciplina>();
		this.livros = new ArrayList<Livro>();
	}
	
	public Curso() {
		super();
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public double getChTotal() {
		double chTotalCurso = 0;
		for (Disciplina d : this.disciplinas)
			chTotalCurso += d.getChTotal();
		return chTotalCurso;
	}

	public double getPctCumprido() {
		double pctCumpridoCurso = 0;
		for (Disciplina d : this.disciplinas) {
			pctCumpridoCurso = pctCumpridoCurso + d.getChTotal() * d.getPctCumprido();
		}
		pctCumpridoCurso = pctCumpridoCurso / this.getChTotal();
		return pctCumpridoCurso;
	}

	@Override
	public double getPreco() {
		double precoCurso = 0;
		for (Produto d : this.disciplinas)
			precoCurso += d.getPreco() * 0.8;
		for (Produto l : this.livros)
			precoCurso += l.getPreco() * 0.9;
		return precoCurso;
	}

	@Override
	public String toString() {
		return "\n\nNome do curso => " + nome 
				//+ "\nCodigo do Curso => " + codigo 
				+ "\n  Carga horaria do Curso => " + getChTotal() 
				+ "\n  Percentual da carga horaria cumprida do Curso => " + getPctCumprido() * 100 + "%"
				+ "\n  Preco do Curso => " + getPreco() 
				+ "\n  Disciplinas do Curso => " + disciplinas
				+ "\n\nLivros utilizados no Curso => " + livros;
	}

	@Override
	public PrototipavelIF prototipar() {
		return new Curso(this);
	}

	public Ementa getEmenta() {
		EmentaBuilder informacoes = EmentaBuilder.reset();
		informacoes.addNomeCurso(this.nome);
		informacoes.addCodigoCurso(this.codigo);
		for (Disciplina disciplina : this.disciplinas) {
			informacoes.addDisciplina(disciplina);
		}
		for (Livro livro : this.livros) {
			informacoes.addLivro(livro);
		}
		Ementa ementa = informacoes.build();
		return ementa;
	}

	public void avancar(String cdDisciplina, double pct) throws OperacaoInvalida {
		this.state.avancar(this.disciplinas, cdDisciplina,  pct);
	}

	public Situacao checkpoint() throws OperacaoInvalida {
		return this.state.checkpoint(this, this.disciplinas, this.observers);
	}

	public void restore(Situacao situacao) throws OperacaoInvalida {
		 this.state.restore(situacao, this.observers);
	}	
	
	public void adicionarObserver(ObserverIF observer) {
		this.observers.add(observer); 
	}
	
	public void removerObserver(ObserverIF observer) {
		this.observers.remove(observer); 
	}

	public void ativar() {
		this.state = this.state.ativar();
	}

	public void suspender() {
		this.state = this.state.suspender();
	}

	public void concluir() {
		this.state =  this.state.concluir(this.disciplinas);
	}

	public void cancelar() {
		this.state =  this.state.cancelar();	
	}
	
	public String emitirCertificado()  throws OperacaoInvalida{
		return this.state.getCertificado(this.getCodigo(), this.getNome(), this.disciplinas);
	}
	
	public String getEstado() {
		return this.state.getEstado();
	}

}
