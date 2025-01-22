package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.BasketDTO;
import pl.wsei.storespring.dto.CreateProductDTO;
import pl.wsei.storespring.dto.ProductDTO;
import pl.wsei.storespring.dto.ProductUpdateDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Product;
import pl.wsei.storespring.repository.BasketRepository;
import pl.wsei.storespring.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final BasketRepository basketRepository;

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

	public ProductDTO getProductById(Long id) {
		return ProductDTO.fromEntity(productRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found")));
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

	public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		product.setName(productUpdateDTO.getName());
		product.setQuantity(productUpdateDTO.getQuantity());

		return ProductDTO.fromEntity(productRepository.save(product));
	}

	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		productRepository.delete(product);
	}
}