package edu.miu.cs.configuration;

import edu.miu.cs.domains.*;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.dto.ReviewDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<CompositeProduct, ProductDTO>() {
            @Override
            protected void configure() {
                map().setProductType(ProductType.composite);
                map().setProducts(new ArrayList<>());
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
        modelMapper.addMappings(new PropertyMap<ReviewDTO, Review>() {
            @Override
            protected void configure() {
                map();
                map().getReviewer().setId(source.getReviewerId());
                map().getReviewer().setFirstname(source.getReviewerFirstname());
                map().getReviewer().setLastname(source.getReviewerLastname());
            }
        });
        return  modelMapper;
    }
}
