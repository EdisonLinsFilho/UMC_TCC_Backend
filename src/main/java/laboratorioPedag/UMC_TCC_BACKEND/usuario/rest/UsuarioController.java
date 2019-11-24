package laboratorioPedag.UMC_TCC_BACKEND.usuario.rest;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.dto.LoginDto;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.dto.UsuarioSimplesDto;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.service.UsuarioService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
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

    @GetMapping("/getByName/{name}")
    public List<Usuario> getName(@PathVariable String name){
        List<Usuario> usuarios = usuarioRepository.findAllByNomeContains(name);
        return usuarios;
    }

    @GetMapping("/getByRgm/{rgm}")
    public List<Usuario> getByRgm(@PathVariable String rgm){
        double RGM = Double.parseDouble(rgm);
        List<Usuario> usuarios = usuarioRepository.findByRgm(RGM);
        return usuarios;
    }

    @PostMapping("/saveOrUpdate")
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

    @PostMapping("/delete")
    public Usuario deleteUser(@RequestBody Usuario userDelete){
        Validate.notNull(userDelete, "O objeto de usuario não pode ser nulo");
        return usuarioService.deleteUsuario(userDelete);
    }

    @PostMapping("/authenticate")
    public UsuarioSimplesDto authenticate(@RequestBody LoginDto login) throws Exception {
        Validate.notNull(login.getEmail(), "Email não pode ser nulo");
        Validate.notNull(login.getSenha(), "Senha não pode ser nula");
        UsuarioSimplesDto usuario = usuarioService.authenticate(login.getEmail(), login.getSenha());
        if (usuario == null){
            throw new Exception("Usuario não encontrado");
        }
        return usuario;
    }
}
