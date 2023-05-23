package edu.miu.cs.services;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.IndividualProduct;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.repositories.ComponentProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
   @Autowired
    private ComponentProductRepository componentProductRepository;

   @Autowired
   private ModelMapper modelMapper;

   @Transactional
    private ProductDTO addCompositeProduct(ProductDTO productDTO) {
        try {
            CompositeProduct product = modelMapper.map(productDTO, CompositeProduct.class);
            CompositeProduct result = componentProductRepository.save(product);
            var resultDTO = modelMapper.map(result, ProductDTO.class);
            return resultDTO;
        } catch (Exception e){
            return new ProductDTO();
        }
    }

    public ProductDTO addIndividualProduct(ProductDTO productDTO) {
        try {
            IndividualProduct product = modelMapper.map(productDTO, IndividualProduct.class);
            IndividualProduct result = componentProductRepository.save(product);
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
           CompositeProduct productFromId = modelMapper.map(componentProductRepository.getReferenceById(id), CompositeProduct.class);
           if (productDTO.getProductType() == ProductType.composite) {
               ComponentProduct product = modelMapper.map(productDTO, CompositeProduct.class);
               productFromId.addProductItem(product);
           } else {
               ComponentProduct product = modelMapper.map(productDTO, IndividualProduct.class);
               productFromId.addProductItem(product);
           }

           return modelMapper.map(componentProductRepository.save(productFromId), ProductDTO.class);
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
            componentProductRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Page<ProductDTO> getAll(Pageable pageable) {
        try {
            Page<ComponentProduct> result = componentProductRepository.findByIsActiveTrue(pageable);
            return result.map(component -> modelMapper.map(component, ProductDTO.class));
        } catch (Exception e){
            List<ProductDTO> emptyList = new ArrayList<>();
            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
        }
    }

    @Override
    public ProductDTO getById(int id) {
        try {
            ComponentProduct result = componentProductRepository.findProductItemsById(id);
            return modelMapper.map(result, ProductDTO.class);
        } catch (Exception e) {
            return new ProductDTO();
        }
    }

    @Override
    @Transactional
    public Page<ProductDTO> getByNameOrDescription(String searchString, Pageable pageable) {
        try {
            var result = componentProductRepository.findByNameOrDescription(searchString, searchString);
            return result.map(product -> modelMapper.map(product, ProductDTO.class));
        } catch (Exception e) {
            List<ProductDTO> emptyList = new ArrayList<>();
            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
        }
    }

}
