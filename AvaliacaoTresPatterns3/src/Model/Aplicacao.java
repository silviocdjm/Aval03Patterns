package Model;

import java.lang.reflect.InvocationTargetException;

public class Aplicacao {
	
	public static TipoProduto PRODUTO = TipoProduto.DISCIPLINA;
	private ProdutoFactory factory;	
	
	public Aplicacao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {		
		this.factory = (ProdutoFactory) (Class.forName(Aplicacao.PRODUTO.getFactoryName()).getDeclaredConstructor().newInstance());		
	}
	
	//Questao 01 => Composite	
	public void questao01() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OperacaoInvalida {
		ProdutoFactory factory = (ProdutoFactory) (Class.forName(Aplicacao.PRODUTO.getFactoryName()).getDeclaredConstructor().newInstance());
		
		//Criacionais
		Disciplina disciplinaA = (Disciplina)factory.getProduto("Factory Method", "DISFAC");
		Disciplina disciplinaB = (Disciplina)factory.getProduto("Builder", "DISCBUI");
		Disciplina disciplinaC = (Disciplina)factory.getProduto("Singleton", "DISCSIN");
		Disciplina disciplinaD = (Disciplina)factory.getProduto("Prototype", "DISCPRO");
		
		Curso cursoCriacionais = CursoBuilder.reset()
				.addNomeCurso("Criacionais")
				.addCodigoCurso("CRI001")
				.addDisciplina(disciplinaA)
				.addDisciplina(disciplinaB)
				.addDisciplina(disciplinaC)
				.addDisciplina(disciplinaD)
				.addLivro(new Livro("LIVFAC", "Factory Method", 20, "ISBNFAC"))
				.addLivro(new Livro("LIVBUI", "Builder", 10, "ISBNBUI"))
				.addLivro(new Livro("LIVPRO", "Prototype", 5, "ISBPRO"))
				.build();
		
		//Comportamentais
		Disciplina disciplinaE = (Disciplina)factory.getProduto("Strategy", "DISCSTR");
		Disciplina disciplinaF = (Disciplina)factory.getProduto("Visitor", "DISCVIS");
		Disciplina disciplinaG = (Disciplina)factory.getProduto("State", "DISCSTA");
		
		Curso cursoComportamentais = CursoBuilder.reset()
				.addNomeCurso("Comportamentais")
				.addCodigoCurso("COM001")
				.addDisciplina(disciplinaE)
				.addDisciplina(disciplinaF)
				.addDisciplina(disciplinaG)				
				.addLivro(new Livro("LIVSTR", "Strategy", 20, "ISBNSTR"))
				.addLivro(new Livro("LIVVIS", "Visitor", 20, "ISBNVIS"))
				.addLivro(new Livro("LIVSTA", "State", 20, "ISBNSTA"))
				.build();
		
		//Estruturais
		Disciplina disciplinaH = (Disciplina)factory.getProduto("Bridge", "DISCBRI");
		Disciplina disciplinaI = (Disciplina)factory.getProduto("Composite", "DISCCOM");
		Disciplina disciplinaJ = (Disciplina)factory.getProduto("Proxy", "DISCPRO");
		
		Curso cursoEstruturais = CursoBuilder.reset()
				.addNomeCurso("Estruturais")
				.addCodigoCurso("EST001")
				.addDisciplina(disciplinaH)
				.addDisciplina(disciplinaI)
				.addDisciplina(disciplinaJ)				
				.addLivro(new Livro("LIVBRI", "Bridge", 20, "ISBNBRI"))
				.addLivro(new Livro("LIVCOM", "Composite", 20, "ISBNCOM"))
				.addLivro(new Livro("LIVPRO", "Proxy", 20, "ISBNPRO"))
				.build();
		
		Disciplina disciplinaL = (Disciplina)factory.getProduto("Filosofia", "DISCFIL");
		disciplinaL.setChTotal(60);
		disciplinaL.setPreco(100);
		
		Disciplina disciplinaM = (Disciplina)factory.getProduto("Educacao Fisica", "DISCEDF");
		disciplinaM.setChTotal(20);
		disciplinaM.setPreco(50);
		
		CompositeCurso cursoComposto = new CompositeCurso("Padroes de Projeto", "CURSOPADROES");		
		cursoComposto.adicionarItem(disciplinaL);
		cursoComposto.adicionarItem(disciplinaM);
		cursoComposto.adicionarItem(cursoCriacionais);
		cursoComposto.adicionarItem(cursoComportamentais);
		cursoComposto.adicionarItem(cursoEstruturais);
		System.out.println(cursoComposto.toString());		
	}
	
	//Questao 02 => Decorator	
	public void questao02() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OperacaoInvalida {
			
		Livro livro = new Livro("LIVTESTE", "Livro Teste", 100, "ISBNSTA");
		
		FormatoIF formato = new FormatoColorido(new FormatoDigital(new Formato()));
		//FormatoIF formato = new FormatoDigital(new Formato());
		//FormatoIF formato = new FormatoCapaDura(new Formato());
		//FormatoIF formato = new FormatoColorido(new FormatoCapaDura(new Formato()));
		
		livro.addFormato(formato);
		
		System.out.println(livro.getFormato());
		System.out.println(livro);

		
	}
	
	//Questao 03 => Adapter
	public void questao03() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OperacaoInvalida {
		
		//Criacionais
		Disciplina disciplinaA = (Disciplina)factory.getProduto("Factory Method", "DISFAC");
		Disciplina disciplinaB = (Disciplina)factory.getProduto("Builder", "DISCBUI");
		Disciplina disciplinaC = (Disciplina)factory.getProduto("Singleton", "DISCSIN");
		Disciplina disciplinaD = (Disciplina)factory.getProduto("Prototype", "DISCPRO");
		Disciplina webinarAdapter = (Disciplina) new WebinarAdapter("Webinario", "DISCWEB", "Fulano", 60, 3000);
		
		Curso cursoCriacionais = CursoBuilder.reset()
				.addNomeCurso("Criacionais")
				.addCodigoCurso("CRI001")
				.addDisciplina(disciplinaA)
				.addDisciplina(disciplinaB)
				.addDisciplina(disciplinaC)
				.addDisciplina(disciplinaD)
				.addDisciplina(webinarAdapter)
				.addLivro(new Livro("LIVFAC", "Factory Method", 20, "ISBNFAC"))
				.addLivro(new Livro("LIVBUI", "Builder", 10, "ISBNBUI"))
				.addLivro(new Livro("LIVPRO", "Prototype", 5, "ISBPRO"))
				.build();		
		
		System.out.println(cursoCriacionais.toString());
	}
	
	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OperacaoInvalida {
		Aplicacao app = new Aplicacao();
		app.questao03();
	}	
}
