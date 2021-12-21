package Model;

import java.util.List;

public class CursoStateConcluido extends CursoStateAbstract implements CursoStateIF {
	
	@Override
	public String getCertificado(String cod, String nome,  List<Disciplina> disciplinas) throws OperacaoInvalida{
		return "\n                      Certificado:\n"
				+ "\nCertifico que Fulano de Tal concluiu o curso de "
				+ nome
				+ "\n                     Rawphi Dhionson"
				+ "\n                    Diretor de Curso"
				+ "\n                Escola Thanks, Wikipedia";
	}
	
	@Override
	public String getEstado(){
		return "CONCLUIDO";
	}	
}
