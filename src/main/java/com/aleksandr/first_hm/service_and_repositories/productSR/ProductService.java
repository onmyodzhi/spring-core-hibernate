package com.aleksandr.first_hm.service_and_repositories.productSR;

import com.aleksandr.first_hm.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product) {
        productRepository.addProduct(product);
    }

    public Product read(Long id) {
        return productRepository.readProductById(id);
    }

    public List<Product> readAll() {
        return productRepository.readAllProducts();
    }

    public void update(Long id, String title, int price) {
        productRepository.updateProductById(id, title, price);
    }

    public void delete(Long id) {
        productRepository.deleteProductById(id);
    }
}
