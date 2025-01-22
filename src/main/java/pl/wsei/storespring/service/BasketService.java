package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.BasketDTO;
import pl.wsei.storespring.dto.PromotionDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Product;
import pl.wsei.storespring.model.Promotion;
import pl.wsei.storespring.repository.BasketRepository;
import pl.wsei.storespring.repository.ProductRepository;
import pl.wsei.storespring.repository.PromotionRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketService {

	private final BasketRepository basketRepository;
	private final ProductRepository productRepository;
	private final PromotionRepository promotionRepository;

	@Autowired
	public BasketService(BasketRepository basketRepository, ProductRepository productRepository, PromotionRepository promotionRepository) {
		this.basketRepository = basketRepository;
		this.productRepository = productRepository;
		this.promotionRepository = promotionRepository;
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

	public BigDecimal calculateBasketValue(Long basketId) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new RuntimeException("Basket not found"));

		BigDecimal totalValue = basket.getProducts().stream()
			.map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		Promotion promotion = promotionRepository.findByBasketId(basketId).orElse(null);

		if (promotion == null || promotion.getDiscountPercentage() <= 0) {
			return  totalValue;
		}

		BigDecimal discount = totalValue.multiply(BigDecimal.valueOf(promotion.getDiscountPercentage() / 100));

		return totalValue.subtract(discount);
	}

	public PromotionDTO setPromotion(Long basketId, double discountPercentage) {
		if (discountPercentage < 0.01 || discountPercentage > 99.9) {
			throw new IllegalArgumentException("Discount percentage must be between 0.01 and 99.9");
		}

		Basket basket = basketRepository.findById(basketId)
			.orElseThrow(() -> new RuntimeException("Basket not found"));

		Promotion promotion = promotionRepository.findByBasketId(basketId)
			.orElse(new Promotion());

		promotion.setBasket(basket);
		promotion.setDiscountPercentage(discountPercentage);

		return PromotionDTO.fromEntity(promotionRepository.save(promotion));
	}
}