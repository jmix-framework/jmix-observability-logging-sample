package io.jmix.petclinic.portal.view.visit;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.portal.entity.User;
import io.jmix.petclinic.portal.entity.Visit;
import io.jmix.petclinic.portal.view.main.MainView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This view uses the regular Jmix data store, because it leverages the Jmix REST Data Store integration:
 * <a href="https://docs.jmix.io/jmix/rest-ds/index.html">Jmix REST Data Store integration</a>.
 *
 * The Visit entities are loaded using the standard {@link CollectionLoader} and data container mechanism.
 * These entities are then rendered into a custom card layout using Vaadin components.
 */
// tag::start-class[]
@Route(value = "visits", layout = MainView.class)
@ViewController(id = "Visit.list")
@ViewDescriptor(path = "visit-list-view.xml")
@DialogMode(width = "50em")
public class VisitListView extends StandardListView<Visit> {

    // ...
    // end::start-class[]

    private static final Logger log = LoggerFactory.getLogger(VisitListView.class);
    @ViewComponent
    private VerticalLayout cardWrapper;
    @Autowired
    private Fragments fragments;
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionContainer<Visit> visitsDc;
    @ViewComponent
    private CollectionLoader<Visit> visitsDl;

    // tag::on-init[]
    @Subscribe
    public void onInit(final InitEvent event) {
        String ownerId = currentUser().getOwnerId();
        MDC.put("ownerId", ownerId);
        log.info("Loading visits for owner");

        visitsDl.setParameter("ownerId", ownerId);
    }
    // end::on-init[]

    private User currentUser() {
        return (User) currentAuthentication.getUser();
    }

    @Subscribe(id = "visitsDc", target = Target.DATA_CONTAINER)
    public void onVisitsDcCollectionChange(final CollectionContainer.CollectionChangeEvent<Visit> event) {
        cardWrapper.removeAll();
        visitsDc.getItems()
                .forEach(this::addVisitCard);
        MDC.remove("ownerId");
    }

    private void addVisitCard(Visit visit) {
        VisitCard visitCard = fragments.create(this, VisitCard.class);
        visitCard.setVisit(visit);
        visitCard.setDetailsActionHandler(() ->
                viewNavigators.detailView(this, Visit.class)
                        .editEntity(visit)
                        .navigate()
        );
        cardWrapper.add(visitCard);
    }

// tag::end-class[]
}
// end::end-class[]