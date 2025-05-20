package matricula_maven;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroAlunoGUI {
	 private AlunoDAO alunoDAO = new AlunoDAO(); // DAO para acesso ao MongoDB
	    private JFrame frame;
	    private JTextArea areaAlunos;
	    private String turmaSelecionada = "Turma A"; // valor padrão
	    
	    public CadastroAlunoGUI() {
	    	  frame = new JFrame("Cadastro de Alunos");
	          frame.setSize(600, 400);
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.setLayout(new BorderLayout());

	          // Painel superior de botões
	          JPanel panelTop = new JPanel();
	          JButton btnCadastrar = new JButton("Cadastrar Aluno");
	          JButton btnListar = new JButton("Listar Alunos");
	          JButton btnRemover = new JButton("Remover Aluno");
	          JButton btnTurma = new JButton("Turma");
	          JButton btnFechar = new JButton("Sair");

	          panelTop.add(btnCadastrar);
	          panelTop.add(btnListar);
	          panelTop.add(btnRemover);
	          panelTop.add(btnTurma);
	          panelTop.add(btnFechar);

	          frame.add(panelTop, BorderLayout.NORTH);

	          // Área de texto central
	          areaAlunos = new JTextArea();
	          areaAlunos.setEditable(false);
	          frame.add(new JScrollPane(areaAlunos), BorderLayout.CENTER);

	          // Ações dos botões
	          btnCadastrar.addActionListener(e -> cadastrarAluno());
	          btnListar.addActionListener(e -> listarAlunos());
	          btnRemover.addActionListener(e -> removerAluno());
	          btnTurma.addActionListener(e -> selecionarTurma());
	          btnFechar.addActionListener(e -> fecharAplicacao());

	          frame.setVisible(true);
	      }

	      private void cadastrarAluno() {
	    	  String nome = JOptionPane.showInputDialog(frame, "Nome do aluno:");
	    	    String matricula = JOptionPane.showInputDialog(frame, "Matrícula:");
	    	    String dataNascimento = JOptionPane.showInputDialog(frame, "Data de Nascimento:");

	    	    // Escolha da turma no cadastro
	    	    String[] opcoesTurma = {"Turma A", "Turma B", "Turma C", "Turma D"};
	    	    String turma = (String) JOptionPane.showInputDialog(
	    	            frame,
	    	            "Selecione a turma:",
	    	            "Selecionar Turma",
	    	            JOptionPane.PLAIN_MESSAGE,
	    	            null,
	    	            opcoesTurma,
	    	            opcoesTurma[0]  // valor padrão
	    	    );

	    	    if (nome != null && matricula != null && dataNascimento != null && turma != null &&
	    	        !nome.trim().isEmpty() && !matricula.trim().isEmpty() && !dataNascimento.trim().isEmpty()) {
	    	        alunoDAO.inserir(new Aluno(nome, matricula, dataNascimento, turma));
	    	        JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso na " + turma + "!");
	    	    } else {
	    	        JOptionPane.showMessageDialog(frame, "Todos os campos são obrigatórios.");
	    	    }
	    	}

	      private void listarAlunos() {
	    	  List<Aluno> alunos = alunoDAO.listar();
	    	    StringBuilder sb = new StringBuilder();

	    	    // Filtra alunos pela turma selecionada
	    	    List<Aluno> alunosFiltrados = alunos.stream()
	    	            .filter(a -> a.turma.equals(turmaSelecionada))
	    	            .toList();

	    	    if (alunosFiltrados.isEmpty()) {
	    	        sb.append("Nenhum aluno cadastrado na ").append(turmaSelecionada).append(".\n");
	    	    } else {
	    	        for (Aluno aluno : alunosFiltrados) {
	    	            sb.append(aluno.toString()).append("\n");
	    	        }
	    	    }

	    	    areaAlunos.setText(sb.toString());
	    	}
	      
	      private void removerAluno() {
	          String matricula = JOptionPane.showInputDialog(frame, "Digite a matrícula do aluno a remover:");
	          if (matricula != null && !matricula.trim().isEmpty()) {
	              boolean removido = alunoDAO.removerPorMatricula(matricula);
	              if (removido) {
	                  JOptionPane.showMessageDialog(frame, "Aluno removido com sucesso!");
	              } else {
	                  JOptionPane.showMessageDialog(frame, "Aluno não encontrado.");
	              }
	          }
	      }

	      private void selecionarTurma() {
	    	  String[] opcoes = {"Turma A", "Turma B", "Turma C", "Turma D"};
	    	    String turma = (String) JOptionPane.showInputDialog(
	    	            frame,
	    	            "Selecione a turma para filtrar:",
	    	            "Selecionar Turma",
	    	            JOptionPane.PLAIN_MESSAGE,
	    	            null,
	    	            opcoes,
	    	            turmaSelecionada // valor atual
	    	    );

	    	    if (turma != null) {
	    	        turmaSelecionada = turma;
	    	        JOptionPane.showMessageDialog(frame, "Filtro de turma selecionado: " + turmaSelecionada);
	    	    }
	    	}

	      private void fecharAplicacao() {
	          int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
	          if (confirm == JOptionPane.YES_OPTION) {
	              System.exit(0);
	          }
	      }

	      public static void main(String[] args) {
	          SwingUtilities.invokeLater(CadastroAlunoGUI::new);
	      }
	  }
