package com.example.backend.service.user;

import com.example.backend.dto.user.UserCreateDTO;
import com.example.backend.dto.user.UserViewDTO;
import com.example.backend.models.User;
import com.example.backend.shared.AuthenticationResponse;
import com.example.backend.shared.GenericResponse;
import com.example.backend.shared.ListOfUserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User getUserById(String id);

    User getUser(String id);

    GenericResponse createUser(UserCreateDTO userCreateDTO);

    ListOfUserResponse randomUserList(String userId);

    AuthenticationResponse Login(String username, String password);

    User updateUserProfilePhoto(MultipartFile imageFile, String userId) throws IOException;

    User uploadPhoto(MultipartFile imageFile, String userId) throws IOException;

    User deletePhotoToList(String userId,String photoId);

    User updateUserDiamond(String userId,int count);
}
