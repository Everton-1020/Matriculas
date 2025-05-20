package matricula_maven;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroAlunos {
	 public static void main(String[] args) {
	        ArrayList<Aluno> alunos = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("Escolha uma opção:");
	            System.out.println("1. Cadastrar aluno");
	            System.out.println("2. Listar alunos");
	            System.out.println("3. Remover aluno");
	            System.out.println("4. Sair");

	            int opcao = scanner.nextInt();
	            scanner.nextLine(); // Consumir a quebra de linha

	            switch (opcao) {
	                case 1:
	                    System.out.println("Nome: ");
	                    String nome = scanner.nextLine();
	                    System.out.println("Matrícula: ");
	                    String matricula = scanner.nextLine();
	                    System.out.println("Data de Nascimento: ");
	                    String dataNascimento = scanner.nextLine();
	                    System.out.println("Turma: ");
	                    String turma = scanner.nextLine();
	                    alunos.add(new Aluno(nome, matricula, dataNascimento, turma));
	                    System.out.println("Aluno Cadastrado com Sucesso");
	                    break;
	                case 2:
	                    if (alunos.isEmpty()) {
	                        System.out.println("Nenhum aluno cadastrado");
	                    } else {
	                        System.out.println("Lista de alunos:");
	                        for (Aluno aluno : alunos) {
	                            System.out.println(aluno);
	                        }
	                    }
	                    break;
	                case 3:
	                    System.out.println("Digite a matrícula do aluno a ser removido: ");
	                    String matriculaRemover = scanner.nextLine();
	                    boolean removido = false;
	                    for (int i = 0; i < alunos.size(); i++) {
	                        if (alunos.get(i).matricula.equals(matriculaRemover)) {
	                            alunos.remove(i);
	                            System.out.println("Aluno removido com sucesso!");
	                            removido = true;
	                            break;
	                        }
	                    }
	                    if (!removido) {
	                        System.out.println("Aluno não encontrado.");
	                    }
	                    break;
	                case 4:
	                    System.out.println("Saindo do programa...");
	                    scanner.close();
	                    return;
	                default:
	                    System.out.println("Opção inválida");
	            }
	        }
	    }
	}


