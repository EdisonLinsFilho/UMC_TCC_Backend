package laboratorioPedag.UMC_TCC_BACKEND.material.service;
import laboratorioPedag.UMC_TCC_BACKEND.material.dal.MaterialRepository;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

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
}
