package ec.edu.utpl.aa.manageoer.framework.adapters.output.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import ec.edu.utpl.aa.manageoer.application.ports.output.PersonalOerManagementOutputPort;
import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;
import org.bson.Document;
import ec.edu.utpl.aa.manageoer.framework.adapters.output.mongodb.mappers.PersonalOerMongoDBMapper;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.util.List;

@ApplicationScoped
public class PersonalOerManagementMongoDBAdapter implements PersonalOerManagementOutputPort {
    private static MongoDatabase db;

    PersonalOerManagementMongoDBAdapter(){ setupMongoDB(); }

    @Override
    public Oer createOer(String title, String description, Date creation_date, Date update_date,
                         List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                         List<Keyword> keywords, License license, Platform platform, State state){

        MongoCollection<Document> oerCollection = db.getCollection("oers");

        var oer = new Oer(getOerIdNextSequence(oerCollection), title, description, creation_date, update_date, authors,
                category, collaborator, files, keywords, license, platform, state);
        oerCollection.insertOne(PersonalOerMongoDBMapper.oerDomainToDocument(oer));
        return oer;
    }

    @Override
    public List<Oer> retrieveOers(String collaboratorEmail) {
        MongoCollection<Document> oerCollection = db.getCollection("oers");
        FindIterable<Document> documentsOer = oerCollection.find(
                Filters.eq("collaborator.email", collaboratorEmail)
        );
        System.out.println(documentsOer);
        return PersonalOerMongoDBMapper.oerListDocumentstoDomainList(documentsOer);
    }

    @Override
    public Oer updateOer(String id, String title, String description, Date creation_date, Date update_date,
                         List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                         List<Keyword> keywords, License license, Platform platform, State state){

        MongoCollection<Document> oerCollection = db.getCollection("oers");

        var oer = new Oer(getOerIdNextSequence(oerCollection), title, description, creation_date, update_date, authors,
                category, collaborator, files, keywords, license, platform, state);

        oerCollection.updateOne(Filters.eq("oerId", id),
                new Document("$set", PersonalOerMongoDBMapper.oerDomainToDocument(oer))
        );
        return oer;
    }

    @Override
    public void deleteOer(String id){
        MongoCollection<Document> oerCollection = db.getCollection("oers");
        oerCollection.deleteOne(Filters.eq("oerId", id));
    }


    private void setupMongoDB(){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://carloscastillo10:EmxKR4ztRbQfDs8e@cluster0.oheul.mongodb.net/appsarchitect-oer?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        db = mongoClient.getDatabase("appsarchitect-oer");
    }

    private String getOerIdNextSequence(MongoCollection<Document> oerCollection){
        return String.valueOf(Integer.parseInt(String.valueOf(oerCollection.estimatedDocumentCount())) + 1);

    }
}
