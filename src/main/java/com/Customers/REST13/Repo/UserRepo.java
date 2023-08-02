package com.Customers.REST13.Repo;

import com.Customers.REST13.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
