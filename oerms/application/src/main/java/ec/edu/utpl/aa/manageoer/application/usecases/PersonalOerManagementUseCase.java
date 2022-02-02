package ec.edu.utpl.aa.manageoer.application.usecases;

import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;
import java.sql.Date;
import java.util.List;

public interface PersonalOerManagementUseCase {
    Oer createOer(String title, String description, Date creation_date, Date update_date, List<Author> authors,
                  Category category, Collaborator collaborator, List<File> files, List<Keyword> keywords, License license,
                  Platform platform, State state);

    List<Oer> retrieveOers(String collaboratorEmail);
    Oer updateOer(String id, String title, String description, Date creation_date, Date update_date, List<Author> authors,
                  Category category, Collaborator collaborator, List<File> files, List<Keyword> keywords, License license,
                  Platform platform, State state);
    void deleteOer(String id);
}
