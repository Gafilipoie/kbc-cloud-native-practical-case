package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {
}
