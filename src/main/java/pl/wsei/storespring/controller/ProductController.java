package pl.wsei.storespring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import pl.wsei.storespring.dto.CreateProductDTO;
import pl.wsei.storespring.dto.ProductDTO;
import pl.wsei.storespring.service.ProductService;

import java.util.List;

@Tag(name = "Product", description = "Product management APIs")
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Create a new product")
    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        ProductDTO createdProduct = productService.createProduct(createProductDTO);
        return ResponseEntity.status(201).body(createdProduct);
    }
}