package laboratorioPedag.UMC_TCC_BACKEND.agenda.service;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Optional.ofNullable;

@Service
public class AgendaService {

    AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository){
        this.agendaRepository = agendaRepository;
    }

    public Agenda updateAgenda(@RequestBody Agenda newAgenda) {
        Agenda agenda = agendaRepository.findById(newAgenda.getId()).orElse(null);

        ofNullable(newAgenda.getCoordenator()).ifPresent(agenda :: setCoordenator);
        ofNullable(newAgenda.getMonitor()).ifPresent(agenda :: setMonitor);
        ofNullable(newAgenda.getProfessor()).ifPresent(agenda :: setProfessor);
        ofNullable(newAgenda.getEscola()).ifPresent(agenda::setEscola);
        ofNullable(newAgenda.getCriancas()).ifPresent(agenda :: setCriancas);
        ofNullable(newAgenda.getTipoEnsino()).ifPresent(agenda::setTipoEnsino);
        ofNullable(newAgenda.getResposaveis()).ifPresent(agenda::setResposaveis);
        ofNullable(newAgenda.getMateriais()).ifPresent(agenda::setMateriais);
        ofNullable(newAgenda.getData()).ifPresent(agenda::setData);
        ofNullable(newAgenda.getDescricao()).ifPresent(agenda::setDescricao);

        return agendaRepository.save(agenda);
    }
}
