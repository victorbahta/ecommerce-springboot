package edu.miu.cs.dto;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.IndividualProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductAdapter {
    @Autowired
    private static ModelMapper modelMapper;

    public static List<ProductDTO> getProductDTOsFromProducts(List<CompositeProduct> productDTOs){
        List<ProductDTO> result = new ArrayList<>();
        for(var item: productDTOs){
            result.add(modelMapper.map(item, ProductDTO.class));
        }
        return result;
    }
//    public static Collection<ComponentProduct> getComponentProductsFromProductDTOs(Collection<ProductDTO> productDTOs){
//        List<ComponentProduct> result = new ArrayList<>();
//        for(var item: productDTOs){
//            if (item.getProducts() != null && item.getProducts().size() > 0) {
//                result.add(modelMapper.map(item, CompositeProduct.class));
//            } else {
//                result.add(modelMapper.map(item, IndividualProduct.class));
//            }
//        }
//        return result;
//    }

//    public static Page<ProductDTO> getProductDTOsFromProducts(Page<Product> products){
//        return products.map(product -> modelMapper.map(product,ProductDTO.class ));
//    }
}
