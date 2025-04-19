package io.jmix.petclinic.portal.view.pet;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.portal.entity.Pet;
import io.jmix.petclinic.portal.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    @Install(to = "petsDl", target = Target.DATA_LOADER)
    protected List<Pet> petsDlLoadDelegate(LoadContext<Pet> loadContext) {
        List<Pet> pets = new ArrayList<>();
        cardWrapper.removeAll();
        pets.forEach(this::addPetCard);
        return pets;
    }

}
