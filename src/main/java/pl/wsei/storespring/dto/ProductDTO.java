package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Product;

public class ProductDTO extends BaseProductDTO {
    private long basketId;

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public static ProductDTO fromEntity(Product product) {
        ProductDTO productDTO = new ProductDTO();
        mapCommonFields(productDTO, product);
        productDTO.setBasketId(product.getBasket().getId());
        return productDTO;
    }
}