package com.catalog.repositories;

import com.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        repository.deleteById(existingId);
        //verificar se realmente deletou
        Optional<Product> result = repository.findById(existingId);
        //testa se o objeto está presente
        Assertions.assertFalse(result.isPresent());
    }


}
