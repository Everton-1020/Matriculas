package matricula_maven;

public class Aluno {
	 String nome;
	    String matricula;
	    String dataNascimento;
	    String turma;
	    
	    public Aluno(String nome, String matricula, String dataNascimento, String turma) {
	        this.nome = nome;
	        this.matricula = matricula;
	        this.dataNascimento = dataNascimento;
	        this.turma = turma;
	    }


	    @Override
	    public String toString() {
	    	  return "Nome: " + nome + ", Matrícula: " + matricula + ", Data de Nascimento: " + dataNascimento + ", Turma: " + turma;
	    }
	}


