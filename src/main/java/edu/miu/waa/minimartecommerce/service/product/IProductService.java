package edu.miu.waa.minimartecommerce.service.product;

import edu.miu.waa.minimartecommerce.domain.product.Product;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.product.ProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    ResponseMessage save(MultipartFile[] images, ProductRequestDto dto);

    ResponseMessage update(ProductRequestDto dto);

    List<Product> findAll();

    Optional<Product> findById(long id);

    ResponseMessage deleteById(long id);
}
