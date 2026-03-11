package forum.alura.api.domain.publicacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "publicaciones")
@Entity(name = "Publicacion")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaPublicacion;
    private String autor;
    private String nombreUsuario;
    private String tema;

    public Publicacion(DatosRegistroPublicacion datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaPublicacion = LocalDateTime.now();
        this.autor = datos.autor();
        this.nombreUsuario = datos.nombreUsuario();
        this.tema = datos.tema();
    }
    
    public void actualizarInformacion(DatosActualizarPublicacion datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.tema() != null) {
            this.tema = datos.tema();
        }
    }
}
