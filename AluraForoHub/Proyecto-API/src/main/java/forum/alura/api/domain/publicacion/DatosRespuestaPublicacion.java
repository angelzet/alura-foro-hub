package forum.alura.api.domain.publicacion;

import java.time.LocalDateTime;

public record DatosRespuestaPublicacion(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaPublicacion,
        String autor,
        String nombreUsuario,
        String tema
) {
    public DatosRespuestaPublicacion(Publicacion publicacion) {
        this(publicacion.getId(), publicacion.getTitulo(), publicacion.getMensaje(), 
             publicacion.getFechaPublicacion(), publicacion.getAutor(), 
             publicacion.getNombreUsuario(), publicacion.getTema());
    }
}
