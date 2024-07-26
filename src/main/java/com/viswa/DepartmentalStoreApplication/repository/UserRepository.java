package com.viswa.DepartmentalStoreApplication.repository;



import com.viswa.DepartmentalStoreApplication.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository<User, ObjectId> {
    //    @Query(value = "SELECT * FROM USERS u WHERE u.username = ?1",nativeQuery = true)
    @Query("{'username': ?0}")
    User findByUsername(String username);
}