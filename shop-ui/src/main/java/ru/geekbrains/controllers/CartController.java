package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.model.LineItem;

@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/update", method = RequestMethod.POST)
    public String updateCartLineItem(Long productId, Integer quantity) {
        LineItem lineItem=new LineItem();
        lineItem.setProductId(productId);
        lineItem.setQty(quantity);
        return updateCart(lineItem);
    }

    @RequestMapping("/cart/remove/{id}")
    public String cartPage(@PathVariable Long id) {
        LineItem lineItem=new LineItem();
        lineItem.setProductId(id);
       cartService.removeLineItemFromCart(lineItem);
        return "redirect:/cart";
    }





}
