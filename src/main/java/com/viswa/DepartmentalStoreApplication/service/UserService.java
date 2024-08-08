package com.viswa.DepartmentalStoreApplication.service;

import com.viswa.DepartmentalStoreApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Qualifier("departmentTemplate")
    @Autowired
    private MongoTemplate departmentMongoTemplate;

    public void addUser(User user){
        departmentMongoTemplate.save(user);
    }

    public void removeUser(String userId){
        Query query = new Query(Criteria.where("_id").is(userId));
        departmentMongoTemplate.remove(query, User.class);
    }

    public void updateUser(User user){
        departmentMongoTemplate.save(user);
    }

    public void deleteUser(String userId){
        Query query = Query.query(Criteria.where("_id").is(userId));
        departmentMongoTemplate.remove(query,User.class);
    }

    public Optional<User> findByUserName(String userName){
        Query query = new Query(Criteria.where("username").is(userName));
        User user =departmentMongoTemplate.findOne(query, User.class);
        return Optional.ofNullable(user);
    }
}
