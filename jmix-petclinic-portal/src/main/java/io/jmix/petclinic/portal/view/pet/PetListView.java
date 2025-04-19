package io.jmix.petclinic.portal.view.pet;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.portal.entity.Pet;
import io.jmix.petclinic.portal.entity.User;
import io.jmix.petclinic.portal.entity.Visit;
import io.jmix.petclinic.portal.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "pets", layout = MainView.class)
@ViewController(id = "Pet.list")
@ViewDescriptor(path = "pet-list-view.xml")
@DialogMode(width = "50em")
public class PetListView extends StandardListView<Pet> {

    @ViewComponent
    private HorizontalLayout cardWrapper;
    @Autowired
    private Fragments fragments;
    @Autowired
    private ViewNavigators viewNavigators;
    @ViewComponent
    private CollectionLoader<Pet> petsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionContainer<Pet> petsDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        petsDl.setParameter("ownerId", currentUser().getOwnerId());
    }

    private User currentUser() {
        return (User) currentAuthentication.getUser();
    }

    @Subscribe(id = "petsDc", target = Target.DATA_CONTAINER)
    public void onPetsDcCollectionChange(final CollectionContainer.CollectionChangeEvent<Visit> event) {
        cardWrapper.removeAll();
        petsDc.getItems()
                .forEach(this::addPetCard);
    }

    private void addPetCard(Pet pet) {
        PetCard petCard = fragments.create(this, PetCard.class);
        petCard.setPet(pet);
        petCard.setDetailsActionHandler(() ->
                viewNavigators.detailView(this, Pet.class)
                .editEntity(pet)
                .navigate()
        );
        cardWrapper.add(petCard);
    }

}
