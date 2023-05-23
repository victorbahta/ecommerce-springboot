package edu.miu.cs.services;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.IndividualProduct;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.repositories.ComponentProductRepository;
import edu.miu.cs.repositories.CompositeProductRepository;
import edu.miu.cs.repositories.IndividualProductRepository;
import jakarta.transaction.Transactional;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
   @Autowired
   private ComponentProductRepository componentProductRepository;
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
            product.setActive(true);
            CompositeProduct result = componentProductRepository.save(product);
            var resultDTO = modelMapper.map(result, ProductDTO.class);
            return resultDTO;
        } catch (Exception e){
            return null;
        }
    }

    public ProductDTO addIndividualProduct(ProductDTO productDTO) {
        try {
            IndividualProduct product = modelMapper.map(productDTO, IndividualProduct.class);
            product.setActive(true);
            IndividualProduct result = componentProductRepository.save(product);
            return modelMapper.map(result, ProductDTO.class);
        } catch (Exception e){
            return null;
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
           CompositeProduct productFromId = getCompositeProduct(id);
           if (productFromId != null) {
               if (productDTO.getProductType() == ProductType.composite) {
                   ComponentProduct product = modelMapper.map(productDTO, CompositeProduct.class);
                   product.setActive(true);
                   productFromId.getProductItems().add(product);
               } else {
                   ComponentProduct product = modelMapper.map(productDTO, IndividualProduct.class);
                   product.setActive(true);
                   productFromId.getProductItems().add(product);
               }

               return modelMapper.map(componentProductRepository.save(productFromId), ProductDTO.class);
           } else {
               return null;
           }
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
            ComponentProduct componentProduct = componentProductRepository.getReferenceById(id);
            componentProduct.setActive(false);
            componentProductRepository.save(componentProduct);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<ProductDTO> getAll(Pageable pageable) {
        try {
            Page<ComponentProduct> result = componentProductRepository.findByIsActiveTrue(pageable);
            return result.map(product -> modelMapper.map(product, ProductDTO.class));
        } catch (Exception e){
            List<ProductDTO> emptyList = new ArrayList<>();
            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
        }
    }

    private CompositeProduct getCompositeProduct(int id) {
       try {
           Optional<CompositeProduct> result = compositeProductRepository.findProductAndItemsById(id);
           if (result.isPresent()) {
               return result.get();
           } else {
               return compositeProductRepository.getReferenceById(id);
           }
       } catch (Exception e) {
           return  null;

       }    }
    @Override
    public ProductDTO getById(int id) {
        try {
            var productTypeById = componentProductRepository.findProductTypeById(id);
            if(productTypeById.isPresent()) {
                if (productTypeById.equals(ProductType.individual.toString())) {
                    IndividualProduct result = individualProductRepository.getReferenceById(id);
                    return modelMapper.map(result, ProductDTO.class);
                } else {
                    CompositeProduct compositeProduct = getCompositeProduct(id);
                    var productList = compositeProduct.getProductItems().stream().filter(p -> p.isActive()).map(product -> modelMapper.map(product, ProductDTO.class)).toList();
                    ProductDTO response = modelMapper.map(compositeProduct, ProductDTO.class);
                    response.setProducts(productList);
                    return response;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<ProductDTO> getByNameOrDescription(String searchString, Pageable pageable) {
        try {
            var result = componentProductRepository.findByIsActiveTrueAndNameContainingOrIsActiveTrueAndDescriptionContaining(searchString, searchString, pageable);
            return result.map(product -> modelMapper.map(product, ProductDTO.class));
        } catch (Exception e) {
            List<ProductDTO> emptyList = new ArrayList<>();
            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
        }
    }

}
