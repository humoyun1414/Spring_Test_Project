package uz.pdp.myfirstspringproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.myfirstspringproject.entity.Product;
import uz.pdp.myfirstspringproject.model.ProductDto;
import uz.pdp.myfirstspringproject.repository.ProductRepository;
import uz.pdp.myfirstspringproject.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @PreAuthorize(value = "hasRole('USER1')")
    public HttpEntity<?> getAllProduct(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(productService.getAllProduct(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('USER1','USER2')")
    public HttpEntity<?> getOneProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getOneProduct(id));
    }

    @PreAuthorize(value = "hasRole('USER1')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto dto) {
        Product product = productService.addProduct(dto);
        return ResponseEntity.status(product != null ? 201 : 409).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        Product product = productService.editProduct(id, dto);
        return ResponseEntity.status(product != null ? 201 : 400).body(product);
    }

    @PreAuthorize(value = "hasRole('USER2')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id) {
        boolean deleteProduct = productService.deleteProduct(id);
        return ResponseEntity.status(deleteProduct ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
