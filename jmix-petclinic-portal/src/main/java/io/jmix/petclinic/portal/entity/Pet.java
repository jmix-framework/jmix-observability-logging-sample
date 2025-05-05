package io.jmix.petclinic.portal.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.Store;
import io.jmix.restds.annotation.RestDataStoreEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// tag::class[]
@JmixEntity
@Store(name = "petclinic") // <1>
@RestDataStoreEntity(remoteName = "petclinic_Pet") // <2>
public class Pet {

    @JmixGeneratedValue
    @JmixId
    private UUID id;

    private List<Visit> visits;

    @InstanceName
    private String name;

    // ...

    // end::class[]

    private PetType type;

    private String identificationNumber;

    private LocalDate birthdate;

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

// tag::class[]
}
// end::class[]