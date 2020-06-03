package org.springframework.samples.petclinic.web;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class) // ROUCHE_DOCS: For Captor
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    private OwnerController ownerController;

    @Autowired
    private ClinicService clinicService;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    /**
     * ROUCHE_DOCS: This is due to  the use of xml config. And the clinicService is reused between calls.
     * So it makes the unique call of the captor gets called 2 time.
     */
    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void testFindByNameNotFound() throws Exception {

        mockMvc.perform(
                get("/owners")
                        .param("lastName", "Dont find me"))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testNameNull() throws Exception {
        given(clinicService.findOwnerByLastName("")).willReturn(Arrays.asList(new Owner(), new Owner()));

        mockMvc.perform(
                get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));

        // Unique call see comment.
        then(clinicService).should().findOwnerByLastName(stringCaptor.capture());

        assertThat(stringCaptor.getValue()).isEqualTo("");
    }

    @Test
    void testOneResult() throws Exception {

        // Given
        Owner owner = new Owner();
        owner.setId(100);
        given(clinicService.findOwnerByLastName("one")).willReturn(Arrays.asList(owner));

        // When then
        mockMvc.perform(
                get("/owners")
                        .param("lastName", "one"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/100"));
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM));

    }

    @Test
    void processCreationFormValid() throws Exception {

        mockMvc.perform(
                post("/owners/new")
                        .characterEncoding("utf-8")
                        .param("firstName", "Jimmy")
                        .param("lastName", "Buffett")
                        .param("address", "123 Duval St ")
                        .param("city", "Key West")
                        .param("telephone", "3151231234"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void processCreationFormNotValid() throws Exception {

        mockMvc.perform(
                post("/owners/new")
                        .characterEncoding("utf-8")
                        .param("firstName", "Jimmy")
                        .param("lastName", "Buffett")
                        .param("city", "Key West"))
                .andExpect(status().isOk())
                .andExpect(view().name(OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"));
    }
}