package ec.edu.utpl.aa.application.ports.input;

import ec.edu.utpl.aa.application.ports.output.PersonalOerManagementOutputPort;
import ec.edu.utpl.aa.application.usecases.PersonalOerManagementUseCase;
import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.sql.Date;
import java.util.List;

@ApplicationScoped
public class PersonalOerManagementInputPort implements PersonalOerManagementUseCase {
    @Inject
    PersonalOerManagementOutputPort personalOerManagementOutputPort;

    @Override
    public Oer createOer(String title, String description, Date creation_date, Date update_date,
                            List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                            List<Keyword> keywords, License license, Platform platform, State state) {

        return new Oer(title, description, creation_date, update_date, authors,
                category, collaborator, files, keywords, license, platform, state);
    }

    @Override
    public Oer retrieveOer(){ return personalOerManagementOutputPort.retrieveOer(); }

    @Override
    public Oer updateOer(){ return personalOerManagementOutputPort.updateOer(); }

    @Override
    public Oer deleteOer(){ return personalOerManagementOutputPort.updateOer(); }

}
