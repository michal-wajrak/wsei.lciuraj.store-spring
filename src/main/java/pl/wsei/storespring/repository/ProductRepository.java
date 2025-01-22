package pl.wsei.storespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsei.storespring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
