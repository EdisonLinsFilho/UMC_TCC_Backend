package laboratorioPedag.UMC_TCC_BACKEND.agenda.rest;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaMateriaisRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.dal.AgendaRepository;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.dto.AgendaDto;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.AgendaMateriais;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.service.AgendaService;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
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
    private AgendaMateriaisRepository agendaMateriaisRepository;

    public AgendaController(AgendaRepository agendaRepository, AgendaService agendaService,
                            UsuarioRepository usuarioRepository, MaterialService materialService,
                            AgendaMateriaisRepository agendaMateriaisRepository) {
        this.agendaRepository = agendaRepository;
        this.agendaService = agendaService;
        this.usuarioRepository = usuarioRepository;
        this.materialService = materialService;
        this.agendaMateriaisRepository = agendaMateriaisRepository;
    }

    @GetMapping("/getAll")
    public List<Agenda> getAll() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas;
    }

    @PostMapping
    public Agenda saveOrUpdate(@RequestBody AgendaDto newAgenda) {
        Validate.notNull(newAgenda, "O objeto da agenda não pode ser nulo");
        Agenda agenda = newAgenda.toAgenda();
        AgendaMateriais agendaMateriais = new AgendaMateriais();

        if (newAgenda.id == null) {

            for (int i=0; i <= newAgenda.materiais.size(); i++){

                if (!materialService.verificaQuantidade(newAgenda.materiais.get(i).getId())){
                    log.error("Material: " + newAgenda.materiais.get(i).getNome() + "Não tem quantidade suficiente");
                    continue;
                }
                agendaMateriais.setQuantidadeUtilizada(newAgenda.quantidadeMaterialUtilizadoDto.get(i).QuantidadeUtilizada);
                materialService.darBaixaMaterialBaseadoNaAgenda(newAgenda.quantidadeMaterialUtilizadoDto.get(i).QuantidadeUtilizada,
                        newAgenda.quantidadeMaterialUtilizadoDto.get(i).materialId);
            }

            agendaMateriais.setAgenda(agenda);
            agendaMateriais.setMateriais(agenda.getMateriais());
            agendaMateriaisRepository.save(agendaMateriais);
            agendaRepository.save(agenda);
            return agenda;
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
