package matricula_maven;

public class Aluno {
	  String nome;
	    String matricula;
	    String dataNascimento;

	    public Aluno(String nome, String matricula, String dataNascimento) {
	        this.nome = nome;
	        this.matricula = matricula;
	        this.dataNascimento = dataNascimento;
	    }

	    @Override
	    public String toString() {
	        return "Nome: " + nome + ", Matrícula: " + matricula + ", Data de Nascimento: " + dataNascimento;
	    }
	}


