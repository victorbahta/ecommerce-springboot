package edu.miu.cs.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@DiscriminatorValue("composite")
public class CompositeProduct extends ComponentProduct {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductItem",
    joinColumns = {@JoinColumn(name = "productId")},
    inverseJoinColumns = {@JoinColumn(name = "productItemId")})
    private Collection<ComponentProduct> productItems;

    public CompositeProduct(){
        this.productItems = new ArrayList<>();
    }

    public void addProductItem(ComponentProduct product) {
        this.productItems.add(product);
    }
}
