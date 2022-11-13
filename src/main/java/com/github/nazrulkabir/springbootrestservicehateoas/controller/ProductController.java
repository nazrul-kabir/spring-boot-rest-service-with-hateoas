package com.github.nazrulkabir.springbootrestservicehateoas.controller;

import com.github.nazrulkabir.springbootrestservicehateoas.entity.Product;
import com.github.nazrulkabir.springbootrestservicehateoas.exception.ProductNotFoundException;
import com.github.nazrulkabir.springbootrestservicehateoas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    //The function receives a GET request, processes it and gives back a list of Products as a response.
    @GetMapping()
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //The function receives a GET request with Path variable, gets the data, and gives back the data as a response.
    @GetMapping("/{id}")
    public EntityModel<Product> getProduct(@PathVariable long id) {
        Optional<Product> student = productRepository.getProductById(id);

        if (!student.isPresent())
            throw new ProductNotFoundException("id-" + id);
        EntityModel<Product> resource = EntityModel.of(student.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllProducts());
        resource.add(linkTo.withRel("all-products"));
        return resource;
    }

    //The function receives a POST request, processes it, creates a new Product and saves it to the database.
    @PostMapping()
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //The function receives a DELETE request, deletes the Product with the specified Id.
    @DeleteMapping({"/{id}"})
    public void deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }
}
