package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Long> {


}
