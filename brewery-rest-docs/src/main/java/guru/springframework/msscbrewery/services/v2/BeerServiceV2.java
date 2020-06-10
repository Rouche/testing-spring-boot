package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

/**
 * Created by jt on 2019-04-23.
 */
public interface BeerServiceV2 {

    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteById(UUID beerId);
}
