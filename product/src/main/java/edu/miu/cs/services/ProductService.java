package edu.miu.cs.services;

import edu.miu.cs.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO add(ProductDTO productDTO);
    ProductDTO addProductItem(int id, ProductDTO productDTO);
    boolean update(int id, ProductDTO productDTO);
    boolean delete(int id);
    Page<ProductDTO> getAll(Pageable pageable);
    ProductDTO getById(int id);
    Page<ProductDTO> getByNameOrDescription(String searchString);
}
