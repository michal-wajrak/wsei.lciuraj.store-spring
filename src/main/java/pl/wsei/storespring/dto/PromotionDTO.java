package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Promotion;

public class PromotionDTO {
    private Long basketId;
    private double discountPercentage;

    public PromotionDTO(Long basketId, double discountPercentage) {
        this.basketId = basketId;
        this.discountPercentage = discountPercentage;
    }

    public PromotionDTO() {}

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public static PromotionDTO fromEntity(Promotion promotion) {
        return new PromotionDTO(
            promotion.getBasket().getId(),
            promotion.getDiscountPercentage()
        );
    }
}