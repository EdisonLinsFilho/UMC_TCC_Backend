package laboratorioPedag.UMC_TCC_BACKEND.agenda.rest;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.dto.AgendaDto;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.dto.QuantidadeMaterialUtilizadoDto;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.service.AgendaService;
import laboratorioPedag.UMC_TCC_BACKEND.material.dal.MaterialRepository;
import laboratorioPedag.UMC_TCC_BACKEND.material.service.MaterialService;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.dal.UsuarioRepository;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    private AgendaRepository agendaRepository;
    private AgendaService agendaService;
    private UsuarioRepository usuarioRepository;
    private MaterialService materialService;
    private MaterialRepository materialRepository;

    public AgendaController(AgendaRepository agendaRepository, AgendaService agendaService,
                            UsuarioRepository usuarioRepository, MaterialService materialService){
        this.agendaRepository = agendaRepository;
        this.agendaService = agendaService;
        this.usuarioRepository = usuarioRepository;
        this.materialService = materialService;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/getAll")
    public List<Agenda> getAll() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas;
    }

    @GetMapping("/getAll/{status}")
    public List<Agenda> getAll(@PathVariable String status) throws Exception {
        Agenda.Status realStatus = agendaService.buildStatus(status);
        List<Agenda> agendas = agendaRepository.findAllByStatus(realStatus);
        return agendas;
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody AgendaDto newAgenda) throws Exception {
        Validate.notNull(newAgenda, "O DTO da agenda não pode ser nulo");
        Agenda agenda = newAgenda.toAgenda();

        if (newAgenda.id == null) {

            if (newAgenda.quantidadeMaterialUtilizadoDto.isEmpty()){
                log.warn("Agenda sem materiais");
            }
            
            for (QuantidadeMaterialUtilizadoDto dto : newAgenda.quantidadeMaterialUtilizadoDto) {
                materialService.verificaQuantidade(dto.materialId);
                materialService.darBaixaMaterialBaseadoNaAgenda(newAgenda.quantidadeMaterialUtilizadoDto);
            }

            agendaRepository.save(agenda);
            return;
        }
        agendaService.updateAgenda(newAgenda);
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
    public List<Agenda> getByProfessor(@PathVariable String nomeProfessor) throws Exception {

        Usuario professor = usuarioRepository.findByNome(nomeProfessor);
        if (professor == null) {
            throw new Exception("Professor não encontrado.");
        }

        List<Agenda> agendas = agendaRepository.findByProfessor(professor);

        if (agendas.isEmpty()) {
            throw new Exception("Agenda não encontrada");
        }

        return agendas;
    }

}
