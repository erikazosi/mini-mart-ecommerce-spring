package edu.miu.waa.minimartecommerce.controller.product;

import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.product.ProductRequestDto;
import edu.miu.waa.minimartecommerce.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> save(@RequestPart("images") MultipartFile[] images,
                                                @RequestPart("product")ProductRequestDto product){
        ResponseMessage response = productService.save(images, product);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
