package ec.edu.utpl.aa.manageoer.framework.adapters.output.mongodb.mappers;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PersonalOerMongoDBMapper {

    public static Document oerDomainToDocument(Oer oer){ return Document.parse(new Gson().toJson(oer)); }

    public static List<Oer> oerListDocumentstoDomainList(FindIterable<Document> documentsOer){
        List<Oer> personalOers = new ArrayList<>();
        for(Document documentOer: documentsOer){
            personalOers.add(oerDocumentToDomain(documentOer));
        }

        return personalOers;
    }

    public static Oer oerDocumentToDomain(Document documentOer){
        return new Gson().fromJson(documentOer.toJson(), Oer.class);
    }
}
