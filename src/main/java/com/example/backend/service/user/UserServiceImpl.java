package com.example.backend.service.user;

import com.example.backend.dto.user.UserCreateDTO;
import com.example.backend.dto.user.UserViewDTO;
import com.example.backend.models.Photo;
import com.example.backend.models.User;
import com.example.backend.models.UserStat;
import com.example.backend.repository.PhotoRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.shared.AuthenticationResponse;
import com.example.backend.shared.GenericResponse;
import com.example.backend.shared.ListOfUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User getUserById(String id) {
        final User user = userRepository.findById(id);//.orElseThrow(() -> new NotFoundException("Not Found Exception"));

        return user;
    }

    @Override
    public User getUser(String id) {
        User user = userRepository.findById(id);
        return user;
    }


    @Override
    @Transactional
    public GenericResponse createUser(UserCreateDTO userCreateDTO) {
        User tempUser = userRepository.findByEmail(userCreateDTO.getEmail());
        if(tempUser == null){
            userRepository.save(
                    new User(
                            userCreateDTO.getFullName(),
                            userCreateDTO.getEmail(),
                            userCreateDTO.getPassword(),
                            "",
                            userCreateDTO.getCountry(),
                            false,
                            new ArrayList(),
                            userCreateDTO.getBirthDay(),
                            "",
                            ageCalculator(userCreateDTO.getBirthDay()),
                            new UserStat(0,0),
                            userCreateDTO.getGender(),
                            0
                    )
            );
            return new GenericResponse("User Created",true);
        }else{
            return new GenericResponse("User Already Exist!",false);
        }

    }

    @Override
    public ListOfUserResponse randomUserList(String userId) {


        List<User> users = new ArrayList<>();
        for(int i=0;i<10;i++){
            User u = randomUser(userId);
            if(!Objects.equals(u.getId(), userId)){
                users.add(u);
            }

        }

        return new ListOfUserResponse("Success Users Get",true,users);
    }

    @Async
    private User randomUser(String userId)
    {
        User user = null;
        final Random rand = new Random();
        int userIndex = 0;

        final List<User> users = userRepository.findAll();
        userIndex = rand.nextInt(users.size());
        user = users.get(userIndex);


        while(Objects.equals(user.getId(), userId)){
            userIndex = rand.nextInt(users.size());
            user = users.get(userIndex);
        }
        return user;
    }

    private int ageCalculator(String date){

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(Long.parseLong(date));
        LocalDate dateBurn = LocalDate.parse(myFormat.format(date1));

        long age = ChronoUnit.YEARS.between(dateBurn, LocalDateTime.now());
        return Integer.parseInt(String.valueOf(age));
    }


    @Override
    @Transactional
    public AuthenticationResponse Login(String email, String password) {
        boolean isLogin = false;
        String message = "";
            User user = userRepository.findByEmail(email);
            if(user != null){
                if(Objects.equals(user.getPassword(), password)){
                    isLogin = true;
                    message = "User Login is Success";
                }else{
                    message = "User Password Wrong";
                }
            }else {
                message = "User can not found";
            }
        return new AuthenticationResponse(message,isLogin,user);
    }

    @Override
    public User updateUserProfilePhoto(MultipartFile imageFile, String userId) throws IOException {
        final java.util.Date date = new java.util.Date();
        byte[] bytes = imageFile.getBytes();
        String folder = "/home/ec2-user/photos/";
        Path path = Paths.get(folder+userId+"-"+date.getTime() / 1000 +".jpg");

        Files.write(path,bytes);

        Photo photo = new Photo(
                userId,
                userId,
                path.getFileName().toString(),
                "PROFILE"
        );

        photoRepository.save(
                photo
        );
        User user = userRepository.findById(userId);
        user.setPhotoId(photo.getId());
        userRepository.save(user);
        return userRepository.findById(userId);
    }

    @Override
    public User uploadPhoto(MultipartFile imageFile, String userId) throws IOException {
        final java.util.Date date = new java.util.Date();
        byte[] bytes = imageFile.getBytes();
        String folder = "/home/ec2-user/photos/";
        Path path = Paths.get(folder+userId+"-"+date.getTime() / 1000 +".jpg");

        Files.write(path,bytes);

        Photo photo = new Photo(
                userId,
                userId,
                path.getFileName().toString(),
                "PRIVATE"
        );

        photoRepository.save(
                photo
        );

        User user = userRepository.findById(userId);
        ArrayList photoList = user.getPhotoList();
        photoList.add(photo);
        user.setPhotoList(photoList);
        userRepository.save(user);
        return userRepository.findById(userId);
    }

    @Override
    public User deletePhotoToList(String userId,String photoId) {

        Photo photo = photoRepository.findById(photoId);
        User user = userRepository.findById(userId);

        user.getPhotoList().remove(photo);

        userRepository.save(user);


        return user;
    }

    @Override
    public User updateUserDiamond(String userId,int count) {
        User user = userRepository.findById(userId);

        int currentDiamondCount = user.getDiamondCount();
        user.setDiamondCount(count);
        userRepository.save(user);


        return user;
    }
}
