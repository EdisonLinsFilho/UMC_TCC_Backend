package laboratorioPedag.UMC_TCC_BACKEND.usuario.service;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){ this.usuarioRepository = usuarioRepository; }

    public Usuario updateUsuario(@RequestBody Usuario newUsuario) {
        Usuario usuario = usuarioRepository.findById(newUsuario.getId()).orElse(null);

        ofNullable(newUsuario.getNome()).ifPresent(usuario :: setNome);
        ofNullable(newUsuario.getAcesso()).ifPresent(usuario :: setAcesso);
        ofNullable(newUsuario.getEmail()).ifPresent(usuario :: setEmail);
        ofNullable(newUsuario.getRgm()).ifPresent(usuario :: setRgm);
        ofNullable(newUsuario.getSenha()).ifPresent(usuario :: setSenha);
        ofNullable(newUsuario.getStatus()).ifPresent(usuario :: setStatus);

        return usuarioRepository.save(usuario);
    }

    public Usuario deleteUsuario(@RequestBody Usuario userDel){
        Usuario usuario = usuarioRepository.findById(userDel.getId()).orElse(null);

        usuario.setStatus(Usuario.Status.INACTIVE);

        return usuarioRepository.save(usuario);
    }

    public Usuario authenticate(String email, String senha) {
        if (usuarioRepository.findByEmail(email) == null) {
            log.error("Usuario não encontrado");
            return null;
        }

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (!usuario.getSenha().equals(senha)) {
            log.error("Senha incorreta");
            return null;
        }

        return usuario;
    }
}
