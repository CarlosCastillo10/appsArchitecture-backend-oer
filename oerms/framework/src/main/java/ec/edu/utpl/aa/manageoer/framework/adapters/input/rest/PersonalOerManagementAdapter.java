package ec.edu.utpl.aa.manageoer.framework.adapters.input.rest;

import ec.edu.utpl.aa.manageoer.application.usecases.PersonalOerManagementUseCase;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import io.helidon.common.reactive.Single;
import io.helidon.webserver.*;

import java.net.URI;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class PersonalOerManagementAdapter implements Service {
    @Inject
    PersonalOerManagementUseCase personalOerManagementUseCase;

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Override
    public void update(Routing.Rules rules) {
        rules.get("/", this::index)
                .get("/personal-oers/{email}", this::listOers)
                .post("/oer", this::createOer)
                .put("/oer/{id}", this::updateOer)
                .delete("/oer/{id}", this::deleteOerById);
    }

    public void index(ServerRequest request, ServerResponse response){
        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", "Servicio correcto")
                .build();
        response.status(200);
        response.send(returnObject);
    }

    public void listOers(ServerRequest request, ServerResponse response)  {
        String collaboratorEmail = request.path().param("email");
        response.status(200);
        response.send(JSON.createArrayBuilder(personalOerManagementUseCase.retrieveOers(collaboratorEmail)).build());
    }

    public void createOer(ServerRequest request, ServerResponse response){
        request.content().as(Oer.class)
                .thenApply(data -> personalOerManagementUseCase.createOer(
                        data.getTitle(), data.getDescription(), data.getCreation_date(), data.getUpdate_date(),
                        data.getAuthors(), data.getCategory(), data.getCollaborator(), data.getFiles(),
                        data.getKeywords(), data.getLicense(), data.getPlatform(), data.getState())
                );
        sendResponse(response, 201, "Created");
    }

    public void updateOer(ServerRequest request, ServerResponse response){
        String oerId = request.path().param("id");
        request.content().as(Oer.class)
                .thenApply(data -> personalOerManagementUseCase.updateOer(
                        oerId, data.getTitle(), data.getDescription(), data.getCreation_date(), data.getUpdate_date(),
                        data.getAuthors(), data.getCategory(), data.getCollaborator(), data.getFiles(),
                        data.getKeywords(), data.getLicense(), data.getPlatform(), data.getState())
                );
        sendResponse(response, 204, "Updated");
    }

    public void deleteOerById(ServerRequest request, ServerResponse response){
        String oerId = request.path().param("id");
        personalOerManagementUseCase.deleteOer(oerId);
        sendResponse(response, 204,"Deleted");
    }

    private void sendResponse(ServerResponse response, int statusCode, String message){
        response.send(message);
    }

}
