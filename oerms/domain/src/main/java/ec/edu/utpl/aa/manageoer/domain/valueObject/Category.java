package ec.edu.utpl.aa.manageoer.domain.valueObject;
import lombok.*;


@AllArgsConstructor
@Getter
@ToString
public enum Category {
    LABORATORY("Laboratorio/Práctica"),
    DATASET("Dataset"),
    FULL_COURSE("Curso completo"),
    TASK("Tarea"),
    MASTER_CLASS("Clase magistral"),
    REPOSITORY("Repositorio"),
    NOTES("Notas/Apuntes"),
    LESSON("Lección"),
    MODULE("Módulo"),
    READING("Lectura"),
    SYLLABUS("Programa de estudios"),
    TEXT_BOOK("Libro de texto");

    private final String description;
}
