package laboratorioPedag.UMC_TCC_BACKEND.material.rest;

import laboratorioPedag.UMC_TCC_BACKEND.material.dal.MaterialRepository;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import laboratorioPedag.UMC_TCC_BACKEND.material.service.MaterialService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material")
public class MaterialController {

    private MaterialRepository materialRepository;
    private MaterialService materialService;

    public MaterialController(MaterialRepository materialRepository, MaterialService materialService){
        this.materialRepository = materialRepository;
        this.materialService = materialService;
    }

    @GetMapping("/getAll")
    public List<Material> getAll() {
        List<Material> agendas = materialRepository.findAll();
        return agendas;
    }

    @PostMapping
    public Material saveOrUpdate(@RequestBody Material newMaterial) {
        Validate.notNull(newMaterial, "O objeto do material não pode ser nulo");

        if (newMaterial.getId() == null) {
            materialRepository.save(newMaterial);
            return newMaterial;
        }

        return materialService.updateMaterial(newMaterial);
    }

    @GetMapping("/{id}")
    public Material get(@PathVariable Long id) {
        Validate.notNull(id,"Id do material não pode ser nulo");
        return materialRepository.findById(id).orElse(null);
    }

    @GetMapping("/getByName/{materialNome}")
    public Material getByName(@PathVariable String materialNome){
        Validate.notNull(materialNome,"nome do não pode ser nulo");
        return materialRepository.findByNome(materialNome);
    }

    @GetMapping("/getByClasse/{classe}")
    public List<Material> getByClasse(@PathVariable String classe) throws Exception{
        Material.Classe enumClasse = materialService.buildMaterialClasse(classe);
        if (enumClasse == null){
            throw new Exception("Classe não encontrada");
        }
        return materialRepository.findAllByClasse(enumClasse);
    }

}
