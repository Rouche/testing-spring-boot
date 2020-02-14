package org.kitfox.springboot.sfgpetclinic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kitfox.springboot.sfgpetclinic.fauxspring.BindingResult;
import org.kitfox.springboot.sfgpetclinic.fauxspring.Model;
import org.kitfox.springboot.sfgpetclinic.model.Owner;
import org.kitfox.springboot.sfgpetclinic.services.OwnerService;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    public static final String OWNERS_FIND_OWNERS = "owners/findOwners";
    public static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";
    public static final String OWNERS_OWNERS_LIST = "owners/ownersList";

    private OwnerController ownerController;

    @Mock(lenient = true)
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        ownerController = new OwnerController(ownerService);
        given(ownerService.findAllByLastNameLike(stringCaptor.capture()))
                .willAnswer(invocationOnMock -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocationOnMock.getArgument(0);
                    if (name.equals("%shmoe%")) {
                        owners.add(new Owner(1L, "bob", "shmoe"));
                        return owners;
                    }
                    if (name.equals("%nofound%")) {
                        return owners;
                    }
                    if (name.equals("%many%")) {
                        owners.add(new Owner(1L, "bob", "shmoe"));
                        owners.add(new Owner(2L, "bob", "shmoe"));
                        return owners;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
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

        //When
        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        //Then
        assertThat(stringCaptor.getValue()).isEqualTo("%shmoe%");
        assertThat(viewName).isEqualTo(REDIRECT_OWNERS_1);
        verifyNoInteractions(model);
    }

    @Test
    void testFindWildcardsMany() {
        // Given
        Owner owner = new Owner(1L, "bob", "many");
        InOrder inOrder = Mockito.inOrder(ownerService, model);

        //When
        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        //Then
        assertThat(stringCaptor.getValue()).isEqualTo("%many%");
        assertThat(viewName).isEqualTo(OWNERS_OWNERS_LIST);

        // InOrdedr assertions
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(eq("selections"), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void testNothingFound() {
        // Given
        Owner owner = new Owner(1L, "bob", "nofound");
        //List<Owner> owners = new ArrayList<>();
        //final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(owners);

        //When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //Then
        assertThat(stringCaptor.getValue()).isEqualTo("%nofound%");
        assertThat(viewName).isEqualTo(OWNERS_FIND_OWNERS);
    }

    @Test
    void testFindWildcardsAnnotedCaptor() {
        // Given
        Owner owner = new Owner(1L, "bob", "shmoe");
        List<Owner> owners = new ArrayList<>();
        //given(ownerService.findAllByLastNameLike(stringCaptor.capture())).willReturn(owners);

        //When
        ownerController.processFindForm(owner, bindingResult, null);

        //Then
        assertThat(stringCaptor.getValue()).isEqualTo("%shmoe%");
    }
}