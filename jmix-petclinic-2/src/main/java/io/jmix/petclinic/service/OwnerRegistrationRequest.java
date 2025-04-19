package io.jmix.petclinic.service;

public record OwnerRegistrationRequest(
        String firstName,
        String lastName
) {
}
