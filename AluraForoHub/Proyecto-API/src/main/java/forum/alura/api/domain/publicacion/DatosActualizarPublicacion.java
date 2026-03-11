package forum.alura.api.domain.publicacion;

public record DatosActualizarPublicacion(
        Long id,
        String titulo,
        String mensaje,
        String tema
) {
}
