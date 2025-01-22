package pl.wsei.storespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import pl.wsei.storespring.dto.BasketDTO;
import pl.wsei.storespring.dto.PromotionDTO;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Promotion;
import pl.wsei.storespring.service.BasketService;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Basket", description = "Basket management APIs")
@RestController
@RequestMapping("/api")
public class BasketController {

	private final BasketService basketService;

	@Autowired
	public BasketController(BasketService basketService) {
		this.basketService = basketService;
	}

	@Operation(summary = "Get all baskets")
	@GetMapping("/baskets")
	public ResponseEntity<List<BasketDTO>> getAllBaskets() {
		List<BasketDTO> baskets = basketService.getAllBaskets();
		return ResponseEntity.ok(baskets);
	}

	@Operation(summary = "Get basket by ID")
	@GetMapping("/basket/{id}")
	public ResponseEntity<BasketDTO> getBasketById(@PathVariable Long id) {
		BasketDTO basket = basketService.getBasketById(id);
		return ResponseEntity.ok(basket);
	}

	@Operation(summary = "Create a new basket")
	@PostMapping("/basket")
	public ResponseEntity<BasketDTO> createBasket() {
		BasketDTO createdBasket = basketService.createBasket();
		return ResponseEntity.status(201).body(createdBasket);
	}

	@Operation(summary = "Update an existing basket")
	@PutMapping("/basket/{id}")
	public ResponseEntity<BasketDTO> updateBasket(@PathVariable Long id, @RequestBody List<Long> productIds) {
		BasketDTO updatedBasket = basketService.updateBasket(id, productIds);
		return ResponseEntity.ok(updatedBasket);
	}

	@Operation(summary = "Delete a basket")
	@DeleteMapping("/basket/{id}")
	public ResponseEntity<Void> deleteBasket(@PathVariable Long id) {
		basketService.deleteBasket(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Get total value of a basket")
	@GetMapping("/basket/{id}/value")
	public ResponseEntity<BigDecimal> getBasketValue(@PathVariable Long id) {
		BigDecimal totalValue = basketService.calculateBasketValue(id);
		return ResponseEntity.ok(totalValue);
	}

	@Operation(summary = "Set promotion to basket")
	@PostMapping("/basket/{id}/promotion")
	public ResponseEntity<PromotionDTO> createPromotion(@PathVariable Long id, @RequestBody Integer discountPercentage) {
		PromotionDTO createdPromotion = basketService.setPromotion(id, discountPercentage);
		return ResponseEntity.status(201).body(createdPromotion);
	}
}