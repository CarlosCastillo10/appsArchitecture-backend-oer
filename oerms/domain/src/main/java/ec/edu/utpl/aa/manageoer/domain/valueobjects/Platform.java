package ec.edu.utpl.aa.manageoer.domain.valueobjects;

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
