package edu.miu.cs.configuration;

import edu.miu.cs.domains.Product;
import edu.miu.cs.domains.Review;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ReviewDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
                map().setReviewerFirstname(source.getReviewer().getFirstname());
                map().setReviewerLastname(source.getReviewer().getLastname());
            }
        });
        return  modelMapper();
    }
}
