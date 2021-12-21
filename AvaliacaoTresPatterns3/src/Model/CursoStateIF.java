package Model;

import java.util.List;

import Model.Curso.Situacao;

public interface CursoStateIF {
	
	public CursoStateIF ativar();
	public CursoStateIF suspender();
	public CursoStateIF cancelar();	
	public void avancar(List<Disciplina> disciplinas, String cdDisciplina, double pct) throws OperacaoInvalida;
	public Situacao checkpoint(Curso curso, List<Disciplina> disciplinas, List<ObserverIF> observer) throws OperacaoInvalida;
	public void restore(Situacao situacao, List<ObserverIF> observer) throws OperacaoInvalida;
	public String getCertificado(String cod, String nome,  List<Disciplina> disciplinas) throws OperacaoInvalida;
	public CursoStateIF concluir(List<Disciplina> disciplinas);
	public String getEstado();
}
