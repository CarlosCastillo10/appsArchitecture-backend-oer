package ec.edu.utpl.aa.manageoer.domain.valueobjects;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public enum Language {
    CHINESE("Chino"),
    SPANISH("Español"),
    ENGLISH("Inglés"),
    PORTUGUESE("Portugués"),
    RUSSIAN("Ruso"),
    JAPANESE("Japonés"),
    FRENCH("Francés"),
    GERMAN("Alemán"),
    ITALIAN("Italiano");

    private final String name;
}
