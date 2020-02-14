package org.kitfox.springboot.sfgpetclinic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kitfox.springboot.sfgpetclinic.fauxspring.BindingResult;
import org.kitfox.springboot.sfgpetclinic.model.Owner;
import org.kitfox.springboot.sfgpetclinic.services.OwnerService;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    private OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        ownerController = new OwnerController(ownerService);
    }

    @Test
    void processCreationForm() {
        // Given
        Owner owner = new Owner();
        owner.setId(5L);
        given(ownerService.save(any(Owner.class))).willReturn(owner);

        //When
        String result = ownerController.processCreationForm(owner, bindingResult);

        //Then
        then(ownerService).should().save(any(Owner.class));
        assertThat(result).isEqualTo(REDIRECT_OWNERS_5);
    }

    @Test
    void processCreationFormFailed() {
        // Given
        Owner owner = new Owner(1L, "bob", "shmoe");
        given(bindingResult.hasErrors()).willReturn(true);

        //When
        String result = ownerController.processCreationForm(owner, bindingResult);

        //Then
        then(ownerService).shouldHaveNoInteractions();
        assertThat(result).isEqualTo(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void testFindWildcards() {
        // Given
        Owner owner = new Owner(1L, "bob", "shmoe");
        List<Owner> owners = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(owners);

        //When
        ownerController.processFindForm(owner, bindingResult, null);

        //Then
        assertThat(captor.getValue()).isEqualTo("%shmoe%");
    }

    @Test
    void testFindWildcardsAnnotedCaptor() {
        // Given
        Owner owner = new Owner(1L, "bob", "shmoe");
        List<Owner> owners = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringCaptor.capture())).willReturn(owners);

        //When
        ownerController.processFindForm(owner, bindingResult, null);

        //Then
        assertThat(stringCaptor.getValue()).isEqualTo("%shmoe%");
    }
}