package com.example.backend.api;


import com.example.backend.dto.user.UserCreateDTO;
import com.example.backend.dto.user.UserUpdateDTO;
import com.example.backend.dto.user.UserViewDTO;
import com.example.backend.models.User;
import com.example.backend.service.user.UserService;
import com.example.backend.shared.AuthenticationResponse;
import com.example.backend.shared.GenericResponse;
import com.example.backend.shared.ListOfUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @GetMapping("v1/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello len aq");
    }

    @GetMapping("v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        final User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("v1/user/randomUsers")
    public ResponseEntity<?> randomUsers(@RequestParam("userId") String userId){

        ListOfUserResponse lor = userService.randomUserList(userId);
        return ResponseEntity.ok(lor);
    }

    @PostMapping("v1/user/getUser")
    public ResponseEntity<?> getUser(@RequestParam("userId") String userId){

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    @PostMapping("v1/user/updatePP")
    public ResponseEntity<?> updateUserProfilePhoto(
            @RequestParam("imageFile")MultipartFile imageFile,
            @RequestParam("userId") String userId) throws Exception{

        User user = userService.updateUserProfilePhoto(imageFile,userId);

        return ResponseEntity.ok(new AuthenticationResponse("Update is success",true,user));
    }


    @PostMapping("v1/user/updatePhoto")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("imageFile")MultipartFile imageFile,
            @RequestParam("userId") String userId) throws Exception{

        User user = userService.uploadPhoto(imageFile,userId);

        return ResponseEntity.ok(new AuthenticationResponse("Update is success",true,user));
    }




    @PostMapping(path="v1/user/create_user",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> createUser(@RequestParam Map<String,String> body){
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setFullName(body.get("fullName"));
        userCreateDTO.setEmail(body.get("email"));
        userCreateDTO.setBirthDay(body.get("birthDay"));
        userCreateDTO.setPassword(body.get("password"));
        userCreateDTO.setCountry(body.get("countryCode"));
        //userCreateDTO = (UserCreateDTO) body;
        userCreateDTO.setGender(body.get("gender"));

        GenericResponse res = userService.createUser(userCreateDTO);

        return ResponseEntity.ok(res);
    }

    @PostMapping(
            path = "v1/user/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> signInUser(@RequestParam Map<String,String> body){
        final AuthenticationResponse authRes = userService.Login(body.get("email"),body.get("password"));

        return ResponseEntity.ok(authRes);
    }


    @PostMapping(
            path = "v1/user/deletePhotoToList",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> deletePhotoToList(@RequestParam Map<String,String> body){

        User user = userService.deletePhotoToList(body.get("userId"),body.get("photoId"));


        return ResponseEntity.ok(new AuthenticationResponse("Delete Photo is Success",true, user));
    }

    @PostMapping(
            path = "v1/user/updateDiamondCount",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> updateDiamondCount(@RequestParam Map<String,String> body){

        User user = userService.updateUserDiamond(body.get("userId"),Integer.valueOf(body.get("count")));


        return ResponseEntity.ok(new AuthenticationResponse("Update Diamond is Success",true, user));
    }

}
