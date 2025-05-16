package matricula_maven;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroAlunoGUI {
	 private AlunoDAO alunoDAO = new AlunoDAO(); // DAO para acesso ao MongoDB
	    private JFrame frame;
	    private JTextArea areaAlunos;

	    public CadastroAlunoGUI() {
	        frame = new JFrame("Cadastro de Alunos");
	        frame.setSize(500, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new BorderLayout());

	        // Painel superior de botões
	        JPanel panelTop = new JPanel();
	        JButton btnCadastrar = new JButton("Cadastrar Aluno");
	        JButton btnListar = new JButton("Listar Alunos");
	        JButton btnRemover = new JButton("Remover Aluno");
	        JButton btnFechar = new JButton("Sair");

	        panelTop.add(btnCadastrar);
	        panelTop.add(btnListar);
	        panelTop.add(btnRemover);
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
	        btnFechar.addActionListener(e -> fecharAplicacao());

	        frame.setVisible(true);
	    }

	    private void cadastrarAluno() {
	        String nome = JOptionPane.showInputDialog(frame, "Nome do aluno:");
	        String matricula = JOptionPane.showInputDialog(frame, "Matrícula:");
	        String dataNascimento = JOptionPane.showInputDialog(frame, "Data de Nascimento:");

	        if (nome != null && matricula != null && dataNascimento != null) {
	            alunoDAO.inserir(new Aluno(nome, matricula, dataNascimento));
	            JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso!");
	        }
	    }

	    private void listarAlunos() {
	        List<Aluno> alunos = alunoDAO.listar();
	        if (alunos.isEmpty()) {
	            areaAlunos.setText("Nenhum aluno cadastrado.\n");
	        } else {
	            StringBuilder sb = new StringBuilder();
	            for (Aluno aluno : alunos) {
	                sb.append(aluno.toString()).append("\n");
	            }
	            areaAlunos.setText(sb.toString());
	        }
	    }

	    private void removerAluno() {
	        String matricula = JOptionPane.showInputDialog(frame, "Digite a matrícula do aluno a remover:");
	        boolean removido = alunoDAO.removerPorMatricula(matricula);
	        if (removido) {
	            JOptionPane.showMessageDialog(frame, "Aluno removido com sucesso!");
	        } else {
	            JOptionPane.showMessageDialog(frame, "Aluno não encontrado.");
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

