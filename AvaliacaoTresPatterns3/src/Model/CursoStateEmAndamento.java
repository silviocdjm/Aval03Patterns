package Model;

import java.util.List;

import Model.Curso.Situacao;

public class CursoStateEmAndamento extends CursoStateAbstract implements CursoStateIF  {

	@Override
	public void avancar(List<Disciplina> disciplinas, String cdDisciplina, double pct) throws OperacaoInvalida{
		Disciplina disciplina = null;
		for(Disciplina d : disciplinas)
			if(d.getCodigo().equalsIgnoreCase(cdDisciplina)) {
				disciplina = d;
				disciplina.avancar(pct);
			}
		
	}

	@Override
	public Curso.Situacao checkpoint(Curso curso, List<Disciplina> disciplinas, List<ObserverIF> observer) throws OperacaoInvalida{
		this.notificarCheckpointState(disciplinas, observer);
		return  curso.new Situacao(curso);
	}	
	
	@Override
	public void restore(Situacao situacao, List<ObserverIF> observer) throws OperacaoInvalida {
		situacao.restore();
		this.notificarRestoreState(situacao.getDisciplinas(), observer);
	}
	
	public CursoStateIF suspender() {
		return new CursoStateSuspenso();
	};
	
	public CursoStateIF cancelar() {
		return new CursoStateCancelado();
	}
	
	public CursoStateIF concluir(List<Disciplina> disciplinas) {
		if(this.disciplinasConcluidas(disciplinas))
			return new CursoStateConcluido();
		return this;
	}
	
	@Override
	public String getEstado() {
		return "EM ANDAMENTO";
	}
}
