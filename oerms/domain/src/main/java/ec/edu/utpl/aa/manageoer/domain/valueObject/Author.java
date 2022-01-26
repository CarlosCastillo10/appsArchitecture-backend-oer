package ec.edu.utpl.aa.manageoer.domain.valueObject;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Author {
    private String name;
    private Type type;
}
