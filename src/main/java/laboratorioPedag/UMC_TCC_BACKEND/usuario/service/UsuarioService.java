package laboratorioPedag.UMC_TCC_BACKEND.usuario.service;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Optional.ofNullable;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){ this.usuarioRepository = usuarioRepository; }

    public Usuario updateUsuario(@RequestBody Usuario newUsuario) {
        Usuario usuario = usuarioRepository.findById(newUsuario.getId()).orElse(null);

        ofNullable(newUsuario.getAcesso()).ifPresent(usuario :: setAcesso);
        ofNullable(newUsuario.getEmail()).ifPresent(usuario :: setEmail);
        ofNullable(newUsuario.getRgm()).ifPresent(usuario :: setRgm);
        ofNullable(newUsuario.getSenha()).ifPresent(usuario :: setSenha);
        ofNullable(newUsuario.getStatus()).ifPresent(usuario :: setStatus);

        return usuarioRepository.save(usuario);
    }
}
