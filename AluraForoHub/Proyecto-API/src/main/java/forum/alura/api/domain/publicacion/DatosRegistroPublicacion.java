package forum.alura.api.domain.publicacion;

public record DatosRegistroPublicacion(
        String titulo,
        String mensaje,
        String autor,
        String nombreUsuario,
        String tema
) {
}
