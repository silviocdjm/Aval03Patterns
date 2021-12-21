package Model;

import java.util.List;

public class CursoStateSuspenso extends CursoStateAbstract implements CursoStateIF  {
	
	@Override
	public Curso.Situacao checkpoint(Curso curso, List<Disciplina> disciplinas, List<ObserverIF> observer) throws OperacaoInvalida {
		this.notificarCheckpointState(disciplinas, observer);
		return  curso.new Situacao(curso);
	}

	@Override
	public CursoStateIF ativar() {
		return new CursoStateEmAndamento();
	}

	@Override
	public CursoStateIF cancelar() {
		return new CursoStateCancelado();
	}

	@Override
	public String getEstado() {
		return "SUSPENSO";
	}
	
}
