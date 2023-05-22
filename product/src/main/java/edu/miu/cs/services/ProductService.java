package edu.miu.cs.services;

import edu.miu.cs.domains.Product;
import edu.miu.cs.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO add(ProductDTO productDTO);
    boolean update(ProductDTO productDTO, int id);
    boolean delete(int id);
    Page<ProductDTO> getAll(Pageable pageable);
    ProductDTO getById(int id);
    Page<ProductDTO> getByNameOrDescription(String searchString);


}
