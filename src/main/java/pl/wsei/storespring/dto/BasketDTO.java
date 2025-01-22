package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Basket;

import java.util.List;

public class BasketDTO {
	
	long id;
	private List<ProductWithoutBasketDTO> products;

	public static BasketDTO fromEntity(Basket basket) {
		BasketDTO basketDTO = new BasketDTO();
		basketDTO.id = basket.getId();
		basketDTO.products = basket.getProducts().stream()
				.map(ProductWithoutBasketDTO::fromEntity)
				.toList();
		return basketDTO;
	}

	public static Basket toEntity(BasketDTO basketDTO) {
		Basket basket = new Basket();
		basket.setId(basketDTO.id);
		return basket;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ProductWithoutBasketDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductWithoutBasketDTO> products) {
		this.products = products;
	}
}
