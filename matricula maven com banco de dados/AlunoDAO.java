package matricula_maven;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {
	  private final MongoCollection<Document> collection;

	    public AlunoDAO() {
	        MongoDatabase db = BancodeDados.getDatabase();
	        collection = db.getCollection("alunos");
	    }

	    public void inserir(Aluno aluno) {
	    	 Document doc = new Document("nome", aluno.nome)
	                 .append("matricula", aluno.matricula)
	                 .append("dataNascimento", aluno.dataNascimento)
	                 .append("turma", aluno.turma);
	         collection.insertOne(doc);
	     }

	     public List<Aluno> listar() {
	    	 List<Aluno> lista = new ArrayList<>();
	    	    for (Document doc : collection.find()) {
	    	        Aluno aluno = new Aluno(
	    	            doc.getString("nome"),
	    	            doc.getString("matricula"),
	    	            doc.getString("dataNascimento"),
	    	            doc.containsKey("turma") ? doc.getString("turma") : "Turma A"
	    	        );
	    	        lista.add(aluno);
	    	    }
	    	    return lista;
	    	}

	     public boolean removerPorMatricula(String matricula) {
	         return collection.deleteOne(Filters.eq("matricula", matricula)).getDeletedCount() > 0;
	     }
	 }

