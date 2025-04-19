
package io.jmix.petclinic.portal.view.visit;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.petclinic.portal.entity.Pet;
import io.jmix.petclinic.portal.entity.Visit;

@FragmentDescriptor("visit-card.xml")
public class VisitCard extends Fragment<HorizontalLayout> {

    @ViewComponent
    private InstanceContainer<Visit> visitDc;

    private Runnable detailsActionHandler;
    @ViewComponent
    private VerticalLayout typeBlock;


    public void setVisit(Visit visit) {
        visitDc.setItem(visit);

        removeEventClasses();
        if (visit.getType() != null) {
            typeBlock.addClassName(visit.getType().getStyleName());
        }
    }

    private void removeEventClasses() {
        typeBlock.removeClassName("event-red");
        typeBlock.removeClassName("event-green");
        typeBlock.removeClassName("event-yellow");
        typeBlock.removeClassName("event-purple");
        typeBlock.removeClassName("event-blue");
    }

    @Subscribe("detailsAction")
    public void onDetailsAction(final ActionPerformedEvent event) {
        detailsActionHandler.run();
    }

    public void setDetailsActionHandler(Runnable detailsActionHandler) {
        this.detailsActionHandler = detailsActionHandler;
    }
}