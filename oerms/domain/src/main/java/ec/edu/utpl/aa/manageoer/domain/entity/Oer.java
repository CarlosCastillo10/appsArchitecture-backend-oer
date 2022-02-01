package ec.edu.utpl.aa.manageoer.domain.entity;

import ec.edu.utpl.aa.manageoer.domain.valueobjects.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class Oer {
    private int id;
    private String title;
    private String description;
    private Date creation_date;
    private Date update_date;
    private List<Author> authors;
    private Category category;
    private Collaborator collaborator;
    private List<File> files;
    private List<Keyword> keywords;
    private License license;
    private Platform platform;
    private State state;
}
