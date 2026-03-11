package forum.alura.api.controller;

import forum.alura.api.domain.publicacion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPublicacion> registrarPublicacion(
            @RequestBody DatosRegistroPublicacion datosRegistroPublicacion,
            UriComponentsBuilder uriComponentsBuilder) {
        
        Publicacion publicacion = publicacionRepository.save(new Publicacion(datosRegistroPublicacion));
        DatosRespuestaPublicacion datosRespuestaPublicacion = new DatosRespuestaPublicacion(publicacion);
        
        URI url = uriComponentsBuilder.path("/api/v1/publicaciones/{id}").buildAndExpand(publicacion.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPublicacion);
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaPublicacion>> listadoPublicaciones() {
        List<DatosRespuestaPublicacion> publicaciones = publicacionRepository.findAll().stream()
                .map(DatosRespuestaPublicacion::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(publicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPublicacion> retornaPublicacion(@PathVariable Long id) {
        Publicacion publicacion = publicacionRepository.getReferenceById(id);
        var datosPublicacion = new DatosRespuestaPublicacion(publicacion);
        return ResponseEntity.ok(datosPublicacion);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> actualizarPublicacion(
            @PathVariable Long id,
            @RequestBody DatosActualizarPublicacion datosActualizarPublicacion) {
            
        Publicacion publicacion = publicacionRepository.getReferenceById(id);
        publicacion.actualizarInformacion(datosActualizarPublicacion);
        
        return ResponseEntity.ok(new DatosRespuestaPublicacion(publicacion));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        Publicacion publicacion = publicacionRepository.getReferenceById(id);
        publicacionRepository.delete(publicacion);
        return ResponseEntity.noContent().build();
    }
}
