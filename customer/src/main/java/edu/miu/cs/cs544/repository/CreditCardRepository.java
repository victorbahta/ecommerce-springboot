package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("SELECT cc FROM  Customer c JOIN c.creditCards cc WHERE c.id =:customerId AND  cc.id = :cardId")
    public CreditCard findByCardId(Integer customerId, Integer cardId);
}
