package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Product;

import java.math.BigDecimal;

public class BaseProductDTO {
    private long productId;
    private String name;
    private int quantity;
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected static void mapCommonFields(BaseProductDTO dto, Product product) {
        dto.setProductId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
    }
}