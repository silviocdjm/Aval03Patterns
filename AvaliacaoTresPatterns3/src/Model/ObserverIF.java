package Model;

public interface ObserverIF {

	public void notificarCheckpoint(NotificacaoObserver dados);
	public void notificarRestore(NotificacaoObserver dados);
}
