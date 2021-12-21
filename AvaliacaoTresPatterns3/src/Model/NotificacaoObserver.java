package Model;

import java.util.List;

public class NotificacaoObserver {

	private List<Disciplina> disciplinas;
	
	public NotificacaoObserver(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public List<Disciplina> getDadosDisciplinas() {
		return this.disciplinas;
	}
}

