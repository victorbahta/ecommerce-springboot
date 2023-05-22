package edu.miu.cs.configuration;

import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.IndividualProduct;
import edu.miu.cs.domains.Review;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.dto.ReviewDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<CompositeProduct, ProductDTO>() {
            @Override
            protected void configure() {
                map().setProductType(ProductType.composite);
            }
        });
        modelMapper.addMappings(new PropertyMap<IndividualProduct, ProductDTO>() {
            @Override
            protected void configure() {
                map().setProductType(ProductType.individual);
            }
        });
        modelMapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
                map().setReviewerFirstname(source.getReviewer().getFirstname());
                map().setReviewerLastname(source.getReviewer().getLastname());
            }
        });
        return  modelMapper;
    }
}
