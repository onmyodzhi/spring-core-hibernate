package com.aleksandr.first_hm;


import com.aleksandr.first_hm.cfg.SpringConfig;
import com.aleksandr.first_hm.data.PrepareData;
import com.aleksandr.first_hm.data.Product;
import com.aleksandr.first_hm.data.User;
import com.aleksandr.first_hm.service_and_repositories.productSR.ProductService;
import com.aleksandr.first_hm.service_and_repositories.userSR.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class App {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);

        List<User> userList = userService.readAll();
        List<Product> productList = productService.readAll();

        System.out.println("Список пользователей:");
        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println("Список товаров:");
        for (Product product : productList) {
            System.out.println(product);
        }

        context.close();
    }


}
