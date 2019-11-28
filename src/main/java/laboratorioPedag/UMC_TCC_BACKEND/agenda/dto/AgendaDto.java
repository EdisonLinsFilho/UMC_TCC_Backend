package laboratorioPedag.UMC_TCC_BACKEND.agenda.dto;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Responsavel;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import laboratorioPedag.UMC_TCC_BACKEND.usuario.model.Usuario;
import java.util.List;

public class AgendaDto {

    public Long id;
    public Usuario coordenator;
    public Usuario monitor;
    public Usuario professor;
    public String escola;
    public Long criancas;
    public Agenda.Ensino tipoEnsino;
    public List<Responsavel> resposaveis;
    public List<Material> materiais;
    public Long data;
    public String descricao;
    public Double quantidadeUtilizada;

    public AgendaDto(Agenda agenda, Double quantidadeUtilizada) {
        this.id = agenda.getId();
        this.coordenator = agenda.getCoordenator();
        this.monitor = agenda.getMonitor();
        this.professor = agenda.getProfessor();
        this.escola = agenda.getEscola();
        this.criancas = agenda.getCriancas();
        this.tipoEnsino = agenda.getTipoEnsino();
        this.resposaveis = agenda.getResposaveis();
        this.materiais = agenda.getMateriais();
        this.data = agenda.getData();
        this.descricao = agenda.getDescricao();
        this.quantidadeUtilizada = quantidadeUtilizada;
    }

    public Agenda toAgenda(){
        Agenda agenda = new Agenda();

        agenda.setId(this.id);
        agenda.setCoordenator(this.coordenator);
        agenda.setProfessor(this.professor);
        agenda.setMonitor(this.monitor);
        agenda.setResposaveis(this.resposaveis);
        agenda.setMateriais(this.materiais);
        agenda.setCriancas(this.criancas);
        agenda.setData(this.data);
        agenda.setDescricao(this.descricao);
        agenda.setEscola(this.escola);
        agenda.setTipoEnsino(this.tipoEnsino);

        return agenda;
    }

}
