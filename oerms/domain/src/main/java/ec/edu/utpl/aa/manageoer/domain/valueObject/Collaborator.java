package ec.edu.utpl.aa.manageoer.domain.valueObject;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Collaborator {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String avatar;
}
