package matricula_maven;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class BancodeDados {
	 private static final String URI = "aqui você vai colocar o link do seu banco, como no react"; // URI local
	    private static final String DB_NAME = "aqui o nome que você deu ao seu banco de dados"; // Nome do seu banco de dados

	    private static MongoClient client = null;

	    public static MongoDatabase getDatabase() {
	        if (client == null) {
	            client = MongoClients.create(URI);
	        }
	        return client.getDatabase(DB_NAME);
	    }
	}


