package ec.edu.utpl.aa.manageoer.framework.adapters.output.firebase.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import ec.edu.utpl.aa.manageoer.domain.entity.Oer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OerFirebaseMapper {
    public static String oerDomainToJson(Oer oer){
        return new Gson().toJson(oer);
    }

    public static List<Oer> oerListDocumentsToDomainList(List<QueryDocumentSnapshot> oerDocuments){
        List<Oer> oers = new ArrayList<>();
        for(QueryDocumentSnapshot oerDocument: oerDocuments){
            oers.add(oerDocumentToDomain(oerDocument));
        }
        return oers;
    }

    public static Oer oerDocumentToDomain(QueryDocumentSnapshot oerDocument){
        return oerDocument.toObject(Oer.class);
    }

    public static Map<String, Object> oerDomainToMap(Oer oer){
        return new ObjectMapper().convertValue(oer, Map.class);
    }
}
