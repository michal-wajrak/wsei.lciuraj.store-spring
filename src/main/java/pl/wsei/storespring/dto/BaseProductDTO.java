package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Product;

public class BaseProductDTO {
    private long productId;
    private String name;
    private int quantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected static void mapCommonFields(BaseProductDTO dto, Product product) {
        dto.setProductId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
    }
}