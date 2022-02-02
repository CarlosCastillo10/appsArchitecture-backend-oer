package ec.edu.utpl.aa.manageoer.framework.adapters.input.rest;

import ec.edu.utpl.aa.manageoer.application.usecases.PersonalOerManagementUseCase;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PersonalOerManagementAdapter implements Service {
    @Inject
    PersonalOerManagementUseCase personalOerManagementUseCase;

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Override
    public void update(Routing.Rules rules) {
        rules.get("/", this::index)
                .get("/personal-oers/{email}", this::listOers);
    }

    public void listOers(ServerRequest request, ServerResponse response)  {
        String collaboratorEmail = request.path().param("email");
        sendResponse(response, personalOerManagementUseCase.retrieveOers(collaboratorEmail));
    }

    public void index(ServerRequest request, ServerResponse response){
        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", "Servicio correcto")
                .build();
        response.send(returnObject);
    }

    private void sendResponse(ServerResponse response, List<Oer> oers){
        response.send(JSON.createArrayBuilder(oers).build());
    }

}
