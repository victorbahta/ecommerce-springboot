package edu.miu.cs.services;

import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDTO add(ProductDTO productDTO);
    ProductDTO addProductItem(int id, ProductDTO productDTO);
    boolean update(int id, ProductDTO productDTO);
    boolean delete(int id);
    Page<ProductDTO> getAll(Pageable pageable);
    public ProductDTO getById(int id);
    public Page<ProductDTO> getByNameOrDescription(String searchString, Pageable pageable);
}
