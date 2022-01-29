package ec.edu.utpl.aa.manageoer.domain.valueObject;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class License {
    private String type;
    private String acronym;
    private String description;
    private String icon;
}
