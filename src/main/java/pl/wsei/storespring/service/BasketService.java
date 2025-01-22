package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.BasketDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Product;
import pl.wsei.storespring.repository.BasketRepository;
import pl.wsei.storespring.repository.ProductRepository;

import java.util.List;

@Service
public class BasketService {

	private final BasketRepository basketRepository;
	private final ProductRepository productRepository;

	@Autowired
	public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
		this.basketRepository = basketRepository;
		this.productRepository = productRepository;
	}

	public BasketDTO createBasket() {
		 Basket createdBasket = basketRepository.save(new Basket());

		 return BasketDTO.fromEntity(createdBasket);
	}

	public BasketDTO getBasketById(Long id) {
		return BasketDTO.fromEntity(basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found")));
	}

	public List<BasketDTO> getAllBaskets() {
		return basketRepository.findAll().stream().map(BasketDTO::fromEntity).toList();
	}

	public BasketDTO updateBasket(Long id, List<Long> productIds) {
		Basket basket = basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

		List<Product> products = productRepository.findAllById(productIds);

		if (products.size() != productIds.size()) {
			throw new ResourceNotFoundException("One or more products not found");
		}

		basket.setProducts(products);

		return BasketDTO.fromEntity(basketRepository.save(basket));
	}

	public void deleteBasket(Long id) {
		Basket basket = basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
		basketRepository.delete(basket);
	}
}