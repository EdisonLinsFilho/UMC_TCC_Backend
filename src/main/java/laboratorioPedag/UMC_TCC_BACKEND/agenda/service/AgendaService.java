package laboratorioPedag.UMC_TCC_BACKEND.agenda.service;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class AgendaService {

    AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository){
        this.agendaRepository = agendaRepository;
    }

    public Agenda updateAgenda(Agenda newAgenda) {

//      TODO implementar metodos da nova estrutura de baixa de material

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
