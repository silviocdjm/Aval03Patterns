package Model;

import java.util.List;

import Model.Curso.Situacao;


public abstract class CursoStateAbstract implements CursoStateIF {
	
	/*private List<Double> getPctConclusaoDisciplinas(List<Disciplina> disciplinas){
		List<Double> pctConclusao = new ArrayList<Double>();
		for(Disciplina disciplina : disciplinas)
			pctConclusao.add(disciplina.getPctCumprido());
		return pctConclusao;
	}	*/
	
	protected boolean disciplinasConcluidas(List<Disciplina> disciplinas){
		for(Disciplina disciplina : disciplinas)
			if (disciplina.getPctCumprido() < 1)
				return false;
		return true;
	}		
		
	public void notificarCheckpointState(List<Disciplina> disciplinas, List<ObserverIF> observer) {
		NotificacaoObserver dados = new NotificacaoObserver(disciplinas);
		for(ObserverIF obs : observer)
			obs.notificarCheckpoint(dados);
	}
	
	public void notificarRestoreState(List<Disciplina> disciplinas, List<ObserverIF> observer) {
		NotificacaoObserver dados = new NotificacaoObserver(disciplinas);
		for(ObserverIF obs : observer)
			obs.notificarRestore(dados);
	}	
		
	@Override
	public abstract String getEstado();
	
	@Override
	public void avancar(List<Disciplina> disciplinas, String cdDisciplina, double pct) throws OperacaoInvalida{
		throw new OperacaoInvalida("O Estado " + this.getEstado() + " não suporta a operação avançar");
	}	

	@Override
	public Situacao checkpoint(Curso curso, List<Disciplina> disciplinas, List<ObserverIF> observer) throws OperacaoInvalida{
		throw new OperacaoInvalida("O Estado " + this.getEstado() + " não suporta a operação checkpoint");
	}

	@Override
	public void restore(Situacao situacao, List<ObserverIF> observer) throws OperacaoInvalida{
		throw new OperacaoInvalida("O Estado " + this.getEstado() + " não suporta a operação restore");
	}
	
	@Override
	public String getCertificado(String cod, String nome,  List<Disciplina> disciplinas) throws OperacaoInvalida{
		throw new OperacaoInvalida("O Estado " + this.getEstado() + " não suporta a operação emitir certificado");
	}
	
	@Override
	public CursoStateIF ativar() {
		return this;
	}

	@Override
	public CursoStateIF suspender() {
		return this;
	}

	@Override	
	public CursoStateIF concluir(List<Disciplina> disciplinas) {
		return this;
	}

	@Override
	public CursoStateIF cancelar() {
		return this;
	}

}
