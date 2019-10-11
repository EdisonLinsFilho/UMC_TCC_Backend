package laboratorioPedag.UMC_TCC_BACKEND.agenda.rest;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;
import static java.util.Optional.ofNullable;

public class AgendaController {

    private AgendaRepository agendaRepository;


    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
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
            agendaRepository.save(newAgenda);
            return newAgenda;
        }

        Agenda agenda = agendaRepository.findById(newAgenda.getId()).orElse(null);

        ofNullable(newAgenda.getCoordenator()).ifPresent(agenda :: setCoordenator);
        agenda.setCoordenator(newAgenda.getCoordenator());
        agenda.setMonitor(newAgenda.getMonitor());
        agenda.setProfessor(newAgenda.getProfessor());
        agenda.setEscola(newAgenda.getEscola());
        agenda.setCriancas(newAgenda.getCriancas());
        agenda.setTipoEnsino(newAgenda.getTipoEnsino());
        agenda.setResposaveis(newAgenda.getResposaveis());
        agenda.setMateriais(newAgenda.getMateriais());
        agenda.setData(newAgenda.getData());
        agenda.setDescricao(newAgenda.getDescricao());

        return agendaRepository.save(agenda);
    }

    @GetMapping
    public Agenda get(Long agendaId) {
        Validate.notNull(agendaId,"Id da agenda não pode ser nulo");
        return agendaRepository.findById(agendaId).orElse(null);
    }
}