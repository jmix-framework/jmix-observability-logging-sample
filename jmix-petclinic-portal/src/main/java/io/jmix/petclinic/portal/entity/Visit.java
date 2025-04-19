package io.jmix.petclinic.portal.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.Store;
import io.jmix.restds.annotation.RestDataStoreEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Store(name = "petclinic")
@JmixEntity
@RestDataStoreEntity(remoteName = "petclinic_Visit")
public class Visit {

    @JmixGeneratedValue
    @JmixId
    private UUID id;

    private String type;
    @InstanceName
    private String description;

    private Pet pet;

    private LocalDateTime visitStart;

    private LocalDateTime visitEnd;

    public VisitType getType() {
        return type == null ? null : VisitType.fromId(type);
    }

    public void setType(VisitType type) {
        this.type = type == null ? null : type.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(LocalDateTime visitStart) {
        this.visitStart = visitStart;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(LocalDateTime visitEnd) {
        this.visitEnd = visitEnd;
    }

}