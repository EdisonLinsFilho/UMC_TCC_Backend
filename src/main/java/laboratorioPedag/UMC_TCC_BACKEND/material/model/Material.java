package laboratorioPedag.UMC_TCC_BACKEND.material.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Material {

    public enum Classe{
        CIENCIA,
        CONSUMO,
        DESENVOLVIMENTO_COGNITIVO,
        EDUCACAO_FISICA,
        LINGUA_PORTUGUESA,
        MATEMATICA,
        NATUREZA_E_SOCIEDADE,
        OUTROS
    }

    public enum Categoria{
        CONSUMIVEL,
        DURAVEL,
        DOURADO,
        OUTROS
    }

    public enum Embalagem{
        CAIXA,
        METRO,
        PACOTE,
        UNIDADE,
        OUTROS
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;
    private String descricao;

    private double quantidade;
    private double quantidadeMinima;
    private Long dataLancamento;

    private Classe classe;
    private Categoria categoria;
    private Embalagem embalagem;

}
