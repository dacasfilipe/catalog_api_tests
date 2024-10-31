package com.catalog.repositories;

import com.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        //deletar deveria deletar objeto quando ele existir
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        repository.deleteById(existingId);
        //verificar se realmente deletou
        Optional<Product> result = repository.findById(existingId);
        //testa se o objeto está presente
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testSaveProduct(){
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("testando");
        product.setPrice(5000.00);
        product.setImgUrl("localhost/image");
        //product.setDate(Instant.parse("2024-10-25T19:44:00.00+03:00"));
        //salvar o produto
        Product savedProduct = repository.save(product);
        //Asserts são para certificar se tudo deu certo
        assertThat(savedProduct).isNotNull();//verifica se é não nulo
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
    }

    @Test
    @DisplayName("")
    public void updateShouldChangeAndPersistDataWhenIdExists(){
        //update deveria modificar e persistir os dados quando o id existir
        //preparar os dados
        Product product = new Product(
                1L,"Phone", "Smartphone",1200.00,
                "imgProduto",Instant.now());
        //executar a ação
        product.setName("Update Phone");
        product.setPrice(1500.00);
        product = repository.save(product);
        //verificar se a ação ocorreu como o esperado
        Assertions.assertEquals("Update Phone",product.getName() );
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenExists(){
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        Optional<Product> result = repository.findById(existingId);
        //certificar se deu certo
        //verifica se retornou um objeto
        Assertions.assertTrue(result.isPresent());

    }









}
