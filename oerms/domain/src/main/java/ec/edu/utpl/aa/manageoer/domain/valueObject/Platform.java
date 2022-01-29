package ec.edu.utpl.aa.manageoer.domain.valueObject;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Platform {
    private String name;
    private String url;
}
