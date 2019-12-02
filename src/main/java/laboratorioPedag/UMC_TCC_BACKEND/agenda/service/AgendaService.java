package laboratorioPedag.UMC_TCC_BACKEND.agenda.service;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.dto.AgendaDto;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.material.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class AgendaService {

    AgendaRepository agendaRepository;
    MaterialService materialService;

    public AgendaService(AgendaRepository agendaRepository, MaterialService materialService){
        this.agendaRepository = agendaRepository;
        this.materialService = materialService;
    }

    public Agenda updateAgenda(AgendaDto newAgenda) {
        Agenda agenda = agendaRepository.findById(newAgenda.id).orElse(null);

        ofNullable(newAgenda.coordenator).ifPresent(agenda :: setCoordenator);
        ofNullable(newAgenda.monitor).ifPresent(agenda :: setMonitor);
        ofNullable(newAgenda.professor).ifPresent(agenda :: setProfessor);
        ofNullable(newAgenda.escola).ifPresent(agenda::setEscola);
        ofNullable(newAgenda.criancas).ifPresent(agenda :: setCriancas);
        ofNullable(newAgenda.tipoEnsino).ifPresent(agenda::setTipoEnsino);
        ofNullable(newAgenda.resposaveis).ifPresent(agenda::setResposaveis);
//        for (int i=0; i <= newAgenda.materiais.size(); i++){
//
//            if (!materialService.verificaQuantidade(newAgenda.materiais.get(i).getId())){
//                log.error("Material: " + newAgenda.materiais.get(i).getNome() + "Não tem quantidade suficiente");
//                continue;
//            }
//            materialService.darBaixaMaterialBaseadoNaAgenda(newAgenda.quantidadeMaterialUtilizadoDto.get(i).QuantidadeUtilizada,
//                    newAgenda.quantidadeMaterialUtilizadoDto.get(i).materialId);
//        }
//        ofNullable(newAgenda.materiais).ifPresent(agenda::setMateriais);

        ofNullable(newAgenda.data).ifPresent(agenda::setData);
        ofNullable(newAgenda.descricao).ifPresent(agenda::setDescricao);

        return agendaRepository.save(agenda);
    }
}
