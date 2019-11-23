package laboratorioPedag.UMC_TCC_BACKEND.agenda.rest;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.service.AgendaService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    private AgendaRepository agendaRepository;
    private AgendaService agendaService;



    public AgendaController(AgendaRepository agendaRepository, AgendaService agendaService) {
        this.agendaRepository = agendaRepository;
        this.agendaService = agendaService;
    }

    @GetMapping("/getAll")
    public List<Agenda> getAll() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas;
    }

    @PostMapping
    public Agenda saveOrUpdate(@RequestBody Agenda newAgenda)throws Exception {
        Validate.notNull(newAgenda, "O objeto da agenda não pode ser nulo");
        Long now = new Date().getTime();
        if (newAgenda.getData() <= now){
            throw new Exception("Data invalida");
        }

        //TODO tornar esta verificação performatica
        if (newAgenda.getId() == null) {
            agendaRepository.save(newAgenda);
            return newAgenda;
        }

        return agendaService.updateAgenda(newAgenda);
    }

    @GetMapping("/{agendaId}")
    public Agenda get(@PathVariable Long agendaId) {
        Validate.notNull(agendaId,"Id da agenda não pode ser nulo");
        return agendaRepository.findById(agendaId).orElse(null);
    }
}
