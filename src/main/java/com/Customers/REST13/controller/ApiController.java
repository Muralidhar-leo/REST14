package com.Customers.REST13.controller;
import org.springframework.ui.Model;


import com.Customers.REST13.Repo.UserRepo;
import com.Customers.REST13.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping(value = "/fun")
    public String getmessage(){

        return "I Love tom";
    }
    @GetMapping(value ="/allcustomers")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    @PostMapping(value="/save")
    public  String saveuser(@RequestBody User user){
        userRepo.save(user);
        return "Success:201, Successfully Created";

    }

    @PutMapping(value="/update/{id}")
    public String updateUser(@PathVariable long id,@RequestBody User user) {
        User customer=userRepo.findById(id).get();
        customer.setFirst_name(user.getFirst_name());
        customer.setLast_name(user.getLast_name());
        customer.setStreet(user.getStreet());
        customer.setAddress(user.getAddress());
        customer.setCity(user.getCity());
        customer.setState(user.getState());
        customer.setEmail(user.getEmail());
        customer.setPhone(user.getPhone());
        userRepo.save(customer);

        return "Updated Successfully";

    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
               User custom=userRepo.findById(id).get();
               userRepo.delete(custom);
               return "Deleted Successfully";
    }


    @GetMapping("/")
    public String showLoginPage() {
        return "index"; // Returns the name of the HTML login page.
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Perform authentication here (e.g., checking credentials in the database).
        // For demonstration purposes, let's assume the username and password are valid.
        boolean isAuthenticated = false;

        if (isAuthenticated) {
            List<User> customers = userRepo.findAll(); // Fetching customers data from the database using Spring Data JPA.
            model.addAttribute("customers", customers); // Add the list of customers to the model to be displayed on the table page.
            return "customers"; // Returns the name of the HTML customers table page.
        } else {
            return "redirect:/"; // Redirect back to the login page if the login fails.
        }
    }



}
