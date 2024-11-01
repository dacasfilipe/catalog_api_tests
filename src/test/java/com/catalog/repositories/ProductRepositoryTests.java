package com.catalog.repositories;

import com.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test") //usa o application-test.properties
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repository;



    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        // Preparar os dados
        Product product = new Product(null, "Phone", "Smartphone", 1200.0, "https://img.com/img.png", Instant.now());

        // Contar o número de registros existentes antes de salvar o novo produto
        long existingRecordsCount = repository.count();

        // Executar a ação
        product = repository.save(product);

        // Verificar se o ID foi gerado automaticamente e é o próximo ID esperado
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(existingRecordsCount + 1, product.getId());
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        Optional<Product> result = repository.findById(existingId);
        //verificar se o objeto está presente
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist() {
        //preparar os dados
        long nonExistingId = 1000L;
        //executar a ação
        Optional<Product> result = repository.findById(nonExistingId);
        //verificar se o objeto não está presente
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void updateShouldChangeAndPersistDataWhenIdExists() {
        //preparar os dados
        Product product = new Product(1L, "Phone", "Smartphone", 1200.0, "https://img.com/img.png", Instant.now());
        //executar a ação
        product.setName("Updated Phone");
        product.setPrice(1500.0);
        product = repository.save(product);
        //verificar se os dados foram atualizados
        Assertions.assertEquals("Updated Phone", product.getName());
        Assertions.assertEquals(1500.0, product.getPrice());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
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