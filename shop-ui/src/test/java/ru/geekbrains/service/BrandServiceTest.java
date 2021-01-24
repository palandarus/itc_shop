package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.BrandRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.repositories.BrandRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BrandServiceTest {

    private BrandService brandService;
    private BrandRepository brandRepository;

    @BeforeEach
    public void init() {
        brandRepository = mock(BrandRepository.class);
        BrandServiceImpl impl = new BrandServiceImpl(brandRepository);
        brandService = impl;
    }

    @Test
    public void testFindById() {
        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        when(brandRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedBrand));

        Optional<BrandRepr> opt = brandService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedBrand.getId(), opt.get().getId());
        assertEquals(expectedBrand.getName(), opt.get().getName());
    }

    @Test
    public void testDeleteById() {
        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");
        brandService.deleteById(1L);

        Optional<BrandRepr> opt = brandService.findById(1L);

        assertTrue(opt.isEmpty());
    }

    @Test
    public void testFindAll(){
        List<Brand> brandList=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            brandList.add(new Brand());
            brandList.get(i).setId(1L*i);
            brandList.get(i).setName("Brand name "+i);
        }

        List<BrandRepr> brandReprList=brandService.findAll();
        for (int i = 0; i < brandReprList.size(); i++) {
            assertEquals(brandList.get(i).getId(), brandReprList.get(i).getId());
            assertEquals(brandList.get(i).getName(), brandReprList.get(i).getName());
        }


    }

}
