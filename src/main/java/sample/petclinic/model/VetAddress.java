package sample.petclinic.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "vet_addresses")
public class VetAddress extends BaseEntity {

    @Column(name = "postal_code")
    @NotBlank
    private String postalCode;

    @Column(name = "city")
    @NotBlank
    private String city;

    @Column(name = "province")
    @NotBlank
    private String province;

    @OneToOne(optional = false)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Vet getVet() {
        return vet;
    }

    public void setPerson(Vet vet) {
        this.vet = vet;
    }
}
