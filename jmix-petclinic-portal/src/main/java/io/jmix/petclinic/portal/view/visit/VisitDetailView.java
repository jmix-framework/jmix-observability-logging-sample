package io.jmix.petclinic.portal.view.visit;

import com.vaadin.flow.router.Route;
import io.jmix.core.Copier;
import io.jmix.core.EntityStates;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.petclinic.portal.entity.Visit;
import io.jmix.petclinic.portal.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "visits/:id", layout = MainView.class)
@ViewController(id = "Visit.detail")
@ViewDescriptor(path = "visit-detail-view.xml")
@EditedEntityContainer("visitDc")
public class VisitDetailView extends StandardDetailView<Visit> {


//    @Install(to = "visitDl", target = Target.DATA_LOADER)
//    private Visit customerDlLoadDelegate(final LoadContext<Visit> loadContext) {
//        Object id = loadContext.getId();
//        return visitService.loadVisit((UUID) id);
//    }

//    @Install(target = Target.DATA_CONTEXT)
//    private Set<Object> saveDelegate(final SaveContext saveContext) {
//        Visit entity = getEditedEntity();
//        // Make a copy and save it. Copying isolates the view from possible changes of the saved entity.
//        Visit saved = save(copier.copy(entity));
//        // If the new entity ID is assigned by the storage, set the ID to the original entity instance
//        // to let the framework match the saved instance with the original one.
//        if (EntityValues.getId(entity) == null) {
//            EntityValues.setId(entity, EntityValues.getId(saved));
//        }
//        // Set the returned entity to the not-new state.
//        entityStates.setNew(saved, false);
//        return Set.of(saved);
//    }

//    private Visit save(Visit entity) {
//        // Here you can save the entity to an external storage and return the saved instance.
//        return entity;
//    }
}
