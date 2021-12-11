package edu.miu.waa.minimartecommerce.service.product.impl;

import edu.miu.waa.minimartecommerce.domain.product.Product;
import edu.miu.waa.minimartecommerce.domain.product.ProductImages;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.product.ProductRequestDto;
import edu.miu.waa.minimartecommerce.exceptionHandling.exceptions.ProductException;
import edu.miu.waa.minimartecommerce.repository.product.IProductRepository;
import edu.miu.waa.minimartecommerce.service.product.IProductService;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(IProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseMessage save(MultipartFile[] images, ProductRequestDto dto) {
        Product product = modelMapper.map(dto, Product.class);

        String folderRelativeLocation = "/images/products/";
        String folderLocation = System.getProperty("user.dir") + folderRelativeLocation;
        File imageFolder = new File(folderLocation);
        if (!imageFolder.exists())
            imageFolder.mkdirs();

        List<ProductImages> productImages = new ArrayList<>();
        boolean success = saveImages(productImages, images, folderLocation, folderRelativeLocation);

        if(success){
            product.setProductImages(productImages);
            productRepository.save(product);
            return new ResponseMessage("Product have been saved successfully.", HttpStatus.CREATED);
        }
        return new ResponseMessage("Extension doesn't match.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseMessage update(ProductRequestDto dto) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public ResponseMessage deleteById(long id) {
        return null;
    }

    private boolean saveImages(List<ProductImages> productImages, MultipartFile[] mFiles,
                               String folderLocation, String folderRelativeLocation){
        boolean success = true;
        for(MultipartFile image: mFiles){
            try{
                String extension = Objects
                        .requireNonNull(image.getOriginalFilename())
                        .substring(Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf('.')+1);

                if(extension.equalsIgnoreCase("jpeg") ||extension.equalsIgnoreCase("png") ||
                        extension.equalsIgnoreCase("jpg")) {
                    String filename = System.currentTimeMillis() + "_"
                            + image.getOriginalFilename().substring(0, image.getOriginalFilename().lastIndexOf("."));

                    String imageLoc = folderLocation + filename + "." + extension;
                    String relativeImageLoc = folderRelativeLocation +
                            filename + "." + extension;

                    File imageFile = new File(imageLoc);
                    image.transferTo(imageFile);

                    productImages.add(
                            new ProductImages(
                                    filename,
                                    extension,
                                    imageLoc,
                                    relativeImageLoc
                            )
                    );
                }
                else{
                    success = false;
                    break;
                }
            }
            catch (IOException ex){
                throw new ProductException("Problem occured while saving product.",ex);
            }
        }
        return success;
    }
}
