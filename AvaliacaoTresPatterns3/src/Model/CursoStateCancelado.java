package Model;

public class CursoStateCancelado extends CursoStateAbstract implements CursoStateIF {
	
	@Override
	public String getEstado(){
		return "CANCELADO";
	}

}

