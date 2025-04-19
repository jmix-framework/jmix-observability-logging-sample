package io.jmix.petclinic.portal.view.owner;

import com.vaadin.flow.router.Route;
import io.jmix.core.Copier;
import io.jmix.core.EntityStates;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.core.entity.EntityValues;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.portal.entity.Owner;
import io.jmix.petclinic.portal.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.UUID;

@Route(value = "owners/:id", layout = MainView.class)
@ViewController(id = "Owner.detail")
@ViewDescriptor(path = "owner-detail-view.xml")
@EditedEntityContainer("ownerDc")
public class OwnerDetailView extends StandardDetailView<Owner> {

    @Autowired
    private Copier copier;
    @Autowired
    private EntityStates entityStates;
//    @Autowired
//    private OwnerService ownerService;

    @Install(to = "ownerDl", target = Target.DATA_LOADER)
    private Owner customerDlLoadDelegate(final LoadContext<Owner> loadContext) {
        Object id = loadContext.getId();
        // Here you can load the entity by id from an external storage.
        // Set the loaded entity to the not-new state using EntityStates.setNew(entity, false).
        return new Owner();
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        Owner entity = getEditedEntity();
        // Make a copy and save it. Copying isolates the view from possible changes of the saved entity.
        Owner saved = save(copier.copy(entity));
        // If the new entity ID is assigned by the storage, set the ID to the original entity instance
        // to let the framework match the saved instance with the original one.
        if (EntityValues.getId(entity) == null) {
            EntityValues.setId(entity, EntityValues.getId(saved));
        }
        // Set the returned entity to the not-new state.
        entityStates.setNew(saved, false);
        return Set.of(saved);
    }

    private Owner save(Owner entity) {
        // Here you can save the entity to an external storage and return the saved instance.
        return entity;
    }
}
