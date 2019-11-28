package laboratorioPedag.UMC_TCC_BACKEND.agenda.rest;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.service.AgendaMateriaisService;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.service.AgendaService;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    private AgendaRepository agendaRepository;
    private AgendaService agendaService;
    private UsuarioRepository usuarioRepository;
    private AgendaMateriaisService agendaMateriaisService;

    public AgendaController(AgendaRepository agendaRepository, AgendaService agendaService,
                            UsuarioRepository usuarioRepository, AgendaMateriaisService agendaMateriaisService) {
        this.agendaRepository = agendaRepository;
        this.agendaService = agendaService;
        this.usuarioRepository = usuarioRepository;
        this.agendaMateriaisService = agendaMateriaisService;
    }

    @GetMapping("/getAll")
    public List<Agenda> getAll() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas;
    }

    @PostMapping
    public Agenda saveOrUpdate(@RequestBody Agenda newAgenda) {
        Validate.notNull(newAgenda, "O objeto da agenda não pode ser nulo");

        if (newAgenda.getId() == null) {
//            TODO implementar metodos da nova estrutura de baixa de material
            agendaRepository.save(newAgenda);
            return newAgenda;
        }

        return agendaService.updateAgenda(newAgenda);
    }

    @GetMapping("/{agendaId}")
    public Agenda get(@PathVariable Long agendaId) throws Exception {
        Agenda agenda = agendaRepository.findById(agendaId).orElse(null);

        if (agenda == null) {
            throw new Exception("Agenda não encontrada");
        }

        return agenda;
    }

    @GetMapping("/getByData/{data}")
    public Agenda getByData(@PathVariable Long data) throws Exception {
        Agenda agenda = agendaRepository.findByData(data);

        if (agenda == null) {
            throw new Exception("Agenda não encontrada");
        }

        return agenda;
    }

    @GetMapping("/getByProfessor/{nomeProfessor}")
    public List<Agenda> getByData(@PathVariable String nomeProfessor) throws Exception {

        Usuario professor = usuarioRepository.findByNome(nomeProfessor);
        if (professor == null){
            throw new Exception("Professor não encontrado.");
        }

        List<Agenda> agendas = agendaRepository.findByProfessor(professor);

        if (agendas.isEmpty()) {
            throw new Exception("Agenda não encontrada");
        }

        return agendas;
    }

}
