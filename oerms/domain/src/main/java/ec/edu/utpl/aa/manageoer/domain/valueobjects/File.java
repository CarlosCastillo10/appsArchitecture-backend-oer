package ec.edu.utpl.aa.manageoer.domain.valueobjects;
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
