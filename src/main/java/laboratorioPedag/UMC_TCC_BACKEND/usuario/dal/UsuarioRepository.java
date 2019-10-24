package laboratorioPedag.UMC_TCC_BACKEND.usuario.dal;

import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
