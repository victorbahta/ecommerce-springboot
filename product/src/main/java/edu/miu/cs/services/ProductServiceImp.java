package edu.miu.cs.services;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.IndividualProduct;
import edu.miu.cs.dto.ProductAdapter;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.repositories.CompositeProductRepository;
import edu.miu.cs.repositories.IndividualProductRepository;
import edu.miu.cs.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {
   @Autowired
    private ProductRepository productRepository;
   @Autowired
    private CompositeProductRepository compositeProductRepository;
   @Autowired
    private IndividualProductRepository individualProductRepository;

   @Autowired
   private ModelMapper modelMapper;

   @Transactional
    private ProductDTO addCompositeProduct(ProductDTO productDTO) {
        try {
            CompositeProduct product = modelMapper.map(productDTO, CompositeProduct.class);
            CompositeProduct result = compositeProductRepository.save(product);
            var resultDTO = modelMapper.map(result, ProductDTO.class);
            return resultDTO;
        } catch (Exception e){
            return new ProductDTO();
        }
    }

    public ProductDTO addIndividualProduct(ProductDTO productDTO) {
        try {
            IndividualProduct product = modelMapper.map(productDTO, IndividualProduct.class);
            IndividualProduct result = productRepository.save(product);
            return modelMapper.map(result, ProductDTO.class);
        } catch (Exception e){
            return new ProductDTO();
        }
    }

    @Override
    public ProductDTO add(ProductDTO productDTO) {
        if (productDTO.getProductType() == ProductType.composite) {
            return addCompositeProduct(productDTO);
        }
        return addIndividualProduct(productDTO);
    }

    @Override
    public ProductDTO addProductItem(int id, ProductDTO productDTO) {
       try {
           CompositeProduct productFromId = compositeProductRepository.getReferenceById(id);
           if (productDTO.getProductType() == ProductType.composite) {
               ComponentProduct product = modelMapper.map(productDTO, CompositeProduct.class);
               productFromId.addProductItem(product);
           } else {
               ComponentProduct product = modelMapper.map(productDTO, IndividualProduct.class);
               productFromId.addProductItem(product);
           }

           return modelMapper.map(compositeProductRepository.save(productFromId), ProductDTO.class);
       } catch (Exception e) {
           return new ProductDTO();
       }
    }

    @Override
    public boolean update(int id, ProductDTO productDTO) {
       if (id != productDTO.getId()) {
           return  false;
       }

        try {
            add(productDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Page<ProductDTO> getAll(Pageable pageable) {
//        try {
//            Page<Product> result = productRepository.findAll(pageable);
//            return ProductAdapter.getProductDTOsFromProducts(result);
//        } catch (Exception e){
//            List<ProductDTO> emptyList = new ArrayList<>();
//            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
//        }
        return null;
    }

    @Override
    public ProductDTO getById(int id) {
//        try {
//            Product result = productRepository.getById(id);
//            ProductDTO productDTO = modelMapper.map(result, ProductDTO.class);
//            return productDTO;
//        } catch (Exception e) {
//            return new ProductDTO();
//        }
        return null;
    }

    @Override
    @Transactional
    public Page<ProductDTO> getByNameOrDescription(String searchString) {
        return null;
    }

}
