package edu.miu.cs.services;

import edu.miu.cs.domains.Product;
import edu.miu.cs.dto.ProductAdapter;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.repositories.ProductRepository;
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
    private ProductRepository productRepository;

   @Autowired
   private ModelMapper modelMapper;
    @Override
    public ProductDTO add(ProductDTO productDTO) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            Product result = productRepository.save(product);
            return modelMapper.map(result, ProductDTO.class);
        } catch (Exception e){
            return new ProductDTO();
        }
    }

    @Override
    public boolean update(ProductDTO productDTO, int id) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            productRepository.save(product);
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
        try {
            Page<Product> result = productRepository.findAll(pageable);
            return ProductAdapter.getProductDTOsFromProducts(result);
        } catch (Exception e){
            List<ProductDTO> emptyList = new ArrayList<>();
            return new PageImpl<ProductDTO>(emptyList, pageable, 0);
        }
    }

    @Override
    public ProductDTO getById(int id) {
        try {
            Product result = productRepository.getById(id);
            ProductDTO productDTO = modelMapper.map(result, ProductDTO.class);
            return productDTO;
        } catch (Exception e) {
            return new ProductDTO();
        }
    }

    @Override
    @Transactional
    public Page<ProductDTO> getByNameOrDescription(String searchString) {
        return null;
    }
}
