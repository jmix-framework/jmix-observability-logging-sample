package io.jmix.petclinic.portal.view.pet;

import org.springframework.context.ApplicationEvent;

public class PetsOpenedEvent extends ApplicationEvent {

    public PetsOpenedEvent(Object source) {
        super(source);
    }
}