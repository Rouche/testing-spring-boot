package org.kitfox.springboot.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kitfox.springboot.sfgpetclinic.model.Owner;
import org.kitfox.springboot.sfgpetclinic.model.PetType;
import org.kitfox.springboot.sfgpetclinic.services.PetService;
import org.kitfox.springboot.sfgpetclinic.services.PetTypeService;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Nested tests have its own context.
 * The whole hierarchy of BeforeEach is executing for each tests nested or not.
 */
@DisplayName("Owner Map Service Test - ")
@Slf4j
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        log.info("Inside BeforeEach of OwnerMapServiceTest");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);

            log.info("Inside BeforeEach of nested TestCreatePetTypes");
        }

        @DisplayName("Test Pet Count")
        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();

            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));

                log.info("Inside BeforeEach of nested nested SaveOwnersTests");
            }

            @DisplayName("Save Owner")
            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                Owner savedOwner = ownerMapService.save(owner);

                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Find Owners Tests - ")
            @Nested
            class FindOwnersTests {

                @BeforeEach
                void setUp() {
                    log.info("Inside BeforeEach of nested nested and nested FindOwnersTests");
                }

                @DisplayName("Find Owner")
                @Test
                void findOwner() {

                    Owner foundOwner = ownerMapService.findById(1L);

                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {

                    Owner foundOwner = ownerMapService.findById(2L);

                    assertThat(foundOwner).isNull();
                }
            }
        }
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }
}