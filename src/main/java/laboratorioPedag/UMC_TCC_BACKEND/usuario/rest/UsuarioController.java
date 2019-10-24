package laboratorioPedag.UMC_TCC_BACKEND.usuario.rest;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.service.UsuarioService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/getAll")
    public List<Usuario> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @PostMapping
    public Usuario saveOrUpdate(@RequestBody Usuario newUsuario) {
        Validate.notNull(newUsuario, "O objeto do usuario não pode ser nulo");

        if (newUsuario.getId() == null) {
            usuarioRepository.save(newUsuario);
            return newUsuario;
        }

        return usuarioService.updateUsuario(newUsuario);
    }

    @GetMapping(value="/{usuarioId}")
    public Usuario get(@PathVariable("usuarioId") Long usuarioId) {
        Validate.notNull(usuarioId,"Id do usuario não pode ser nulo");
        return usuarioRepository.findById(usuarioId).orElse(null);
    }
}