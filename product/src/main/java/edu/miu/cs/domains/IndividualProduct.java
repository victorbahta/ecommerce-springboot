package edu.miu.cs.domains;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("individual")
public class IndividualProduct extends ComponentProduct {

}

