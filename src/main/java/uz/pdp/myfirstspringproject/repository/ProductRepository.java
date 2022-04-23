package uz.pdp.myfirstspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.myfirstspringproject.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
