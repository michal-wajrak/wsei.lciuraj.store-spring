package pl.wsei.storespring.dto;

import lombok.Getter;
import lombok.Setter;
import pl.wsei.storespring.model.Basket;

@Getter
@Setter
public class BasketDTO {
	
	long id;
	
	String item;

	public static BasketDTO fromEntity(Basket basket) {
		BasketDTO basketDTO = new BasketDTO();
		basketDTO.id = basket.getId();
		basketDTO.item = basket.getItem();
		return basketDTO;
	}

	public static Basket toEntity(BasketDTO basketDTO) {
		Basket basket = new Basket();
		basket.setId(basketDTO.id);
		basket.setItem(basketDTO.item);
		return basket;
	}
}
