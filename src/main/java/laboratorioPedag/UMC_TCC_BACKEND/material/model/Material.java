package laboratorioPedag.UMC_TCC_BACKEND.material.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Material {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

}
