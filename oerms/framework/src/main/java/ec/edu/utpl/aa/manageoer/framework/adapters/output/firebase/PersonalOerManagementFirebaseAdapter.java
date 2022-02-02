package ec.edu.utpl.aa.manageoer.framework.adapters.output.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import ec.edu.utpl.aa.manageoer.application.ports.output.PersonalOerManagementOutputPort;
import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;
import ec.edu.utpl.aa.manageoer.framework.adapters.output.firebase.mappers.OerFirebaseMapper;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class PersonalOerManagementFirebaseAdapter implements PersonalOerManagementOutputPort {

    private static Firestore db;

    private PersonalOerManagementFirebaseAdapter() throws IOException { setupFirebaseDatabase(); }

    @Override
    public Oer createOer(String title, String description, Date creation_date, Date update_date,
                         List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                         List<Keyword> keywords, License license, Platform platform, State state){

        DocumentReference oerDocRef = db.collection("oers").document();
        var oer = new Oer(oerDocRef.getId(), title, description, creation_date, update_date, authors,
                category, collaborator, files, keywords, license, platform, state);
        oerDocRef.set(OerFirebaseMapper.oerDomainToJson(oer));
        return oer;
    }

    @SneakyThrows
    @Override
    public List<Oer> retrieveOers(String collaboratorEmail) {
        ApiFuture<QuerySnapshot> oerCollections = db.collection("oers")
                .whereEqualTo("collaborator.email", collaboratorEmail).get();
        List<QueryDocumentSnapshot> oerDocuments = oerCollections.get().getDocuments();

        return OerFirebaseMapper.oerListDocumentsToDomainList(oerDocuments);
    }

    @Override
    public Oer updateOer(String id, String title, String description, Date creation_date, Date update_date,
                  List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                  List<Keyword> keywords, License license, Platform platform, State state){

        DocumentReference oerDocRef = db.collection("oers").document(id);
        var oer = new Oer(id, title, description, creation_date, update_date, authors,
                category, collaborator, files, keywords, license, platform, state);
        oerDocRef.update(OerFirebaseMapper.oerDomainToMap(oer));
        return oer;
    }

    @Override
    public void deleteOer(String id){
        db.collection("oers").document(id).delete();
    }

    private void setupFirebaseDatabase() throws IOException {
        InputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        // Builder creational pattern
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }
}
