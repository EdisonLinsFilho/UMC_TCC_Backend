package laboratorioPedag.UMC_TCC_BACKEND.material.rest;

import laboratorioPedag.UMC_TCC_BACKEND.material.dal.MaterialRepository;
import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import laboratorioPedag.UMC_TCC_BACKEND.material.service.MaterialService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        Validate.notNull(newMaterial, "O objeto da agenda não pode ser nulo");
        if (newMaterial.getId() == null) {
            newMaterial.setDataLancamento(new Date().getTime());
            materialRepository.save(newMaterial);
            return newMaterial;
        }

        return materialService.updateMaterial(newMaterial);
    }

    @GetMapping
    public Material get(@RequestParam Long materialId) {
        Validate.notNull(materialId,"Id da agenda não pode ser nulo");
        return materialRepository.findById(materialId).orElse(null);
    }
}
