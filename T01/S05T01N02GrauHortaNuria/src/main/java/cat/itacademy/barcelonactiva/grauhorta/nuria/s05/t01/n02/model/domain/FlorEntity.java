package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="flors")
public class FlorEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_FlorID;

    @Column(name="nomFlor")
    private String nomFlor;

    @Column(name="paisFlor")
    private String paisFlor;

    public FlorEntity(String nomFlor, String paisFlor) {
        this.nomFlor = nomFlor;
        this.paisFlor = paisFlor;
    }

    public int getPk_FlorID() {
        return pk_FlorID;
    }


    public String getNomFlor() {
        return nomFlor;
    }

    public void setNomFlor(String nomFlor) {
        this.nomFlor = nomFlor;
    }

    public String getPaisFlor() {
        return paisFlor;
    }

    public void setPaisFlor(String paisFlor) {
        this.paisFlor = paisFlor;
    }
}
