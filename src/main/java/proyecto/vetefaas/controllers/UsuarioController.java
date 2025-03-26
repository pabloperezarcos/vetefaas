package proyecto.vetefaas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyecto.vetefaas.services.UsuarioService;
import proyecto.vetefaas.dtos.UsuarioDto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        String resultado = usuarioService.crearUsuario(usuarioDto);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/asignar-rol")
    public ResponseEntity<String> asignarRol(@RequestParam String userId, @RequestParam String rolId) {
        String resultado = usuarioService.asignarRol(userId, rolId);
        return ResponseEntity.ok(resultado);
    }
}
