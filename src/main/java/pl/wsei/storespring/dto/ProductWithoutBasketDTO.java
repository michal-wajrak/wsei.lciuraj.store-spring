package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Product;

public class ProductWithoutBasketDTO extends BaseProductDTO {
    public static ProductWithoutBasketDTO fromEntity(Product product) {
        ProductWithoutBasketDTO productDTO = new ProductWithoutBasketDTO();
        mapCommonFields(productDTO, product);
        return productDTO;
    }
}
