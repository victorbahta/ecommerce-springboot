package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
