package com.example.dto;

import com.example.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


public class RegisterDto extends User{

    private List<User> register = new ArrayList<>();


}
