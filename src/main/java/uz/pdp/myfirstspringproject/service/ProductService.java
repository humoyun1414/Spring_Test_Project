package uz.pdp.myfirstspringproject.service;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.pdp.myfirstspringproject.entity.Product;
import uz.pdp.myfirstspringproject.model.ProductDto;
import uz.pdp.myfirstspringproject.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public Product getOneProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Product addProduct(ProductDto dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setExpirationDate(dto.getExpirationDate());
        product.setType(dto.getType());
        return productRepository.save(product);
    }

    public Product editProduct(Integer id, ProductDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(dto.getName());
            product.setExpirationDate(dto.getExpirationDate());
            product.setType(dto.getType());
            return productRepository.save(product);
        }
         return null;
    }

    public boolean deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.isPresent();
    }

}
