package edu.miu.cs.cs544.domain.address;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ShippingAddress extends Address {
    private boolean isDefault;

}
