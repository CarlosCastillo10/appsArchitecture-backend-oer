package ec.edu.utpl.aa.manageoer.domain.valueObject;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class File {
    private String location;
    private DistributionFormat distributionFormat;
}
