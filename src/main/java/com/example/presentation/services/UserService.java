package com.example.presentation.services;

import com.example.presentation.dto.UserDto;
import com.example.presentation.enums.PresentationStatus;
import com.example.presentation.enums.Role;
import com.example.presentation.enums.Status;
import com.example.presentation.exceptions.PresentationException;
import com.example.presentation.exceptions.UserException;
import com.example.presentation.models.Presentation;
import com.example.presentation.models.User;
import com.example.presentation.repositories.PresentationRepository;
import com.example.presentation.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PresentationRepository presentationRepository;
    public User addUser(UserDto userDto) {
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user.setStatus(Status.ACTIVE);
        return  userRepository.save(user);


    }

    public String updateUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new UserException("user not found "));
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return "updated!";
    }


    public boolean login(String email, String password) {
         User user=userRepository.findByEmail(email).orElseThrow(()->new UserException("user not found!"));
         if(user.getPassword().equals(password))
         {
             return true;
         }else
         {
             return false;
         }

    }

    public User getDetails(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found ! "));
        return user;
    }



    public String updatePresentationStatus(int id, int index) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> new PresentationException("presentation id not valid"));
        if(index==1)
        {
            presentation.setPresentationStatus(PresentationStatus.ONGOING);
        } else if (index==2) {
            presentation.setPresentationStatus(PresentationStatus.COMPLETED);
        } else if (index==3) {
            presentation.setPresentationStatus(PresentationStatus.ASSIGNED);
        }
        Presentation save = presentationRepository.save(presentation);
        return "status updated: "+save.getPresentationStatus();
    }
}
