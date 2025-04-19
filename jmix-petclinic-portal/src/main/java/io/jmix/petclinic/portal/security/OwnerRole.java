package io.jmix.petclinic.portal.security;

import io.jmix.petclinic.portal.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "OwnerRole", code = OwnerRole.CODE)
public interface OwnerRole {
    String CODE = "owner-role";

    @MenuPolicy(menuIds = {"Pet.list", "Visit.list"})
    @ViewPolicy(viewIds = {"Pet.list", "Visit.list", "Pet.detail", "PetCard", "Visit.detail", "VisitCard", "changePasswordView", "MainView"})
    void screens();

    @EntityAttributePolicy(entityClass = Owner.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Owner.class, actions = EntityPolicyAction.READ)
    void owner();

    @EntityAttributePolicy(entityClass = Pet.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Pet.class, actions = EntityPolicyAction.READ)
    void pet();

    @EntityAttributePolicy(entityClass = PetType.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetType.class, actions = EntityPolicyAction.READ)
    void petType();

    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void user();

    @EntityAttributePolicy(entityClass = Visit.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Visit.class, actions = EntityPolicyAction.READ)
    void visit();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specific();
}