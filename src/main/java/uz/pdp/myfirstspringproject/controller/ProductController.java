package uz.pdp.myfirstspringproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.myfirstspringproject.entity.Product;
import uz.pdp.myfirstspringproject.model.ProductDto;
import uz.pdp.myfirstspringproject.repository.ProductRepository;
import uz.pdp.myfirstspringproject.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public HttpEntity<?>getAllProduct(@RequestParam Integer page,@RequestParam Integer size){
        return ResponseEntity.ok(productService.getAllProduct(page,size));
    }
    @GetMapping("/{id}")
    public HttpEntity<?>getOneProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getOneProduct(id));
    }
    @PostMapping
    public HttpEntity<?>addProduct(@RequestBody ProductDto dto){
        Product product = productService.addProduct(dto);
        return ResponseEntity.status(product!=null? 201:409).build();
    }
    @PutMapping("/{id}")
    public HttpEntity<?>editProduct(@PathVariable Integer id,@Valid @RequestBody ProductDto dto){
        Product product = productService.editProduct(id, dto);
        return ResponseEntity.status(product!=null?201:400).body(product);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteProduct(@PathVariable Integer id){
        boolean deleteProduct = productService.deleteProduct(id);
        return ResponseEntity.status(deleteProduct?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND).build();
    }


}
