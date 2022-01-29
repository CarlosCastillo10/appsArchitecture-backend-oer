package ec.edu.utpl.aa.manageoer.domain.valueObject;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class DistributionFormat {
    private String distribution;
    private Extension extension;
}
