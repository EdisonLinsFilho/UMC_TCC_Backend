package laboratorioPedag.UMC_TCC_BACKEND.usuario.dal;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findAllByNomeContains(String name);
    List<Usuario> findByRgm(Double rgm);
    Usuario findByEmail(String email);
}
