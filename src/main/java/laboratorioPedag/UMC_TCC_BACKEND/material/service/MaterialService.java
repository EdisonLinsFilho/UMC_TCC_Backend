package laboratorioPedag.UMC_TCC_BACKEND.material.service;
import laboratorioPedag.UMC_TCC_BACKEND.material.dal.MaterialRepository;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class MaterialService {

    private MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }
    public Material updateMaterial(Material newMaterial) {
        Material material = materialRepository.findById(newMaterial.getId()).orElse(null);

        ofNullable(newMaterial.getNome()).ifPresent(material :: setNome);
        ofNullable(newMaterial.getDescricao()).ifPresent(material::setDescricao);
        ofNullable(newMaterial.getQuantidade()).ifPresent(material::setQuantidade);
        ofNullable(newMaterial.getQuantidadeMinima()).ifPresent(material::setQuantidadeMinima);
        ofNullable(newMaterial.getDataLancamento()).ifPresent(material :: setDataLancamento);
        ofNullable(newMaterial.getClasse()).ifPresent(material :: setClasse);
        ofNullable(newMaterial.getCategoria()).ifPresent(material :: setCategoria);
        ofNullable(newMaterial.getEmbalagem()).ifPresent(material::setEmbalagem);

        return materialRepository.save(material);
    }

    public Material.Classe buildMaterialClasse(String classe){
        classe = classe.toUpperCase().trim();
        switch (classe) {
            case "CIENCIA":
                return Material.Classe.CIENCIA;
            case "CONSUMO":
                return Material.Classe.CONSUMO;
            case "DESENVOLVIMENTOCOGNITIVO":
                return Material.Classe.DESENVOLVIMENTO_COGNITIVO;
            case "EDUCACAOFISICA":
                return Material.Classe.EDUCACAO_FISICA;
            case "LINGUAPORTUGUESA":
                return Material.Classe.LINGUA_PORTUGUESA;
            case "MATEMATICA":
                return Material.Classe.MATEMATICA;
            case "NATUREZAESOCIEDADE ":
                return Material.Classe.NATUREZA_E_SOCIEDADE;
            case "OUTROS":
                return Material.Classe.OUTROS;
            default:
                log.error("Materia invalida.");
                return null;
        }
    }
}
