package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.CreateProductDTO;
import pl.wsei.storespring.dto.ProductDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Product;
import pl.wsei.storespring.repository.BasketRepository;
import pl.wsei.storespring.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private BasketRepository basketRepository;

	@Autowired
	public ProductService(ProductRepository productRepository, BasketRepository basketRepository) {
		this.productRepository = productRepository;
		this.basketRepository = basketRepository;
	}

	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
			.map(ProductDTO::fromEntity)
			.toList();
	}

	public ProductDTO createProduct(CreateProductDTO createProductDTO) {
		Basket basket = basketRepository.findById(createProductDTO.getBasketId())
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

		Product product = new Product();
		product.setName(createProductDTO.getName());
		product.setQuantity(createProductDTO.getQuantity());
		product.setBasket(basket);

		Product savedProduct = productRepository.save(product);

		return ProductDTO.fromEntity(savedProduct);
	}
}