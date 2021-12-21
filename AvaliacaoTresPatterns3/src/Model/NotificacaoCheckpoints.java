package Model;

import java.util.List;

public class NotificacaoCheckpoints implements ObserverIF{
	
	@Override
	public void notificarRestore(NotificacaoObserver dados) {
		System.err.println("\nRESTAURAÇÃO DE CHECKPOINT REALIZADO COM SUCESSO");
		this.printReport(dados.getDadosDisciplinas());
	}

	@Override
	public void notificarCheckpoint(NotificacaoObserver dados) {
		System.err.println("\nCHECKPOINT REALIZADO COM SUCESSO");
		this.printReport(dados.getDadosDisciplinas());
	}
	
	private void printReport(List<Disciplina> disciplinas) {
		for(Disciplina d : disciplinas)
			System.err.println("[" + d.getNome() + "] " + d.getPctCumprido()*100 + "%");
	}
}
