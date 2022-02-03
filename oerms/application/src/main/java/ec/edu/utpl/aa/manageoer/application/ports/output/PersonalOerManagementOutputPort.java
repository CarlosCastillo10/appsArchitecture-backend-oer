package ec.edu.utpl.aa.manageoer.application.ports.output;

import ec.edu.utpl.aa.manageoer.domain.entity.Oer;
import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;

import java.sql.Date;
import java.util.List;

public interface PersonalOerManagementOutputPort {
    Oer createOer(String title, String description, Date creation_date, Date update_date,
                  List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                  List<Keyword> keywords, License license, Platform platform, State state);
    Oer retrieveOer(int id);
    Oer updateOer(int id, String title, String description, Date creation_date, Date update_date,
                  List<Author> authors, Category category, Collaborator collaborator, List<File> files,
                  List<Keyword> keywords, License license, Platform platform, State state);
    void deleteOer(int id);
}
