package com.example.backend.service.phototransfer;

import com.example.backend.exception.NotFoundException;
import com.example.backend.models.*;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.PhotoRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PhotoTransferServiceImpl implements PhotoTransferService{

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    private final MessageRepository messageRepository;
    final ChatService chatService ;


    String folder = "/home/ec2-user/photos/";
    @Async
    @Override
    public void sendRandomImage(MultipartFile imageFile,String userId) throws Exception {
        final Date date = new Date();
        byte[] bytes = imageFile.getBytes();
        //Path path = Paths.get(folder+imageFile.getOriginalFilename());
        Path path = Paths.get(folder+userId+"-"+date.getTime() / 1000 +".jpg");

        Files.write(path,bytes);

        final User user = randomUser(userId);

        photoRepository.save(
                new Photo(
                        userId,
                        user.getId(),
                        path.getFileName().toString(),
                        "PHOTO"
                )
        );


    }

    @Override
    public void sendRandomImageViaFilters(MultipartFile imageFile, String userId, String gender, int count) throws Exception {
        final Date date = new Date();
        byte[] bytes = imageFile.getBytes();
        //Path path = Paths.get(folder+imageFile.getOriginalFilename());
        Path path = Paths.get(folder+userId+"-"+date.getTime() / 1000 +".jpg");

        Files.write(path,bytes);

        List<User> selectedUsers = randomUserViaFilters(userId,gender,count);

        for(int i=0;i<selectedUsers.size();i++){
            final User user = selectedUsers.get(i);

            photoRepository.save(
                    new Photo(
                            userId,
                            user.getId(),
                            path.getFileName().toString(),
                            "PHOTO"
                    )
            );

        }


    }

    @Override
    public void removePhoto(String photoId) {
        Photo photo = photoRepository.findById(photoId);
        if(photo != null){
            photoRepository.delete(photo);
        }

    }

    @Async
    @Override
    public void sendPhotoById(MultipartFile imageFile, String userId, String receiverId) throws Exception {
        final Date date = new Date();
        byte[] bytes = imageFile.getBytes();

        //Path path = Paths.get(folder+imageFile.getOriginalFilename());
        //Get Date
        Path path = Paths.get(folder+userId+"-"+date.getTime() / 1000 +".jpg");

        Files.write(path,bytes);

        photoRepository.save(
                new Photo(
                        userId,
                        receiverId,
                        path.getFileName().toString(),
                        "PHOTO"
                )
        );

        chatService.createChat(userId,receiverId);




    }

    @Override
    public byte[] getPhotoById(String id) throws IOException {

        final Photo photo = photoRepository.findById(id);
        final ByteArrayResource img = new ByteArrayResource(Files.readAllBytes(Paths.get(folder+photo.getPhotoUrl())));
        //photoRepository.delete(photo);

        return img.getByteArray();
    }

    @Override
    public List<Photo> getMyPhotos(String id) {
        final List<Photo> allPhotos = photoRepository.findAll();
        final List<Photo> photos = new java.util.ArrayList<>(Collections.emptyList());

        if(allPhotos.size() != 0){
            for (Photo photo : allPhotos) {
                if (photo.getReceiverId().equals(id) && !photo.getSenderId().equals(id) && photo.getType().equals("PHOTO")){
                    photos.add(photo);
                }


            }

        }
        return photos;
    }

    @Override
    public String sendByIdFromMessage(MultipartFile imageFile, String userId, String receiverId, String chatId) throws Exception {
        final Date date = new Date();
        byte[] bytes = imageFile.getBytes();
        String name = imageFile.getOriginalFilename();

        //Path path = Paths.get(folder+imageFile.getOriginalFilename());
        Path path = Paths.get(folder+ name);

        Files.write(path,bytes);

        final Photo photo = new Photo(
                userId,
                receiverId,
                name,
                "CHAT"
        );

        photoRepository.save(
                photo
        );

        chatService.setUserUnReadMessage(receiverId,chatId);

        messageRepository.save(
                new Message(
                        userId,
                        receiverId,
                        path.getFileName().toString(),
                        "PHOTO",
                        date.getTime()/1000,
                        chatId
                )
        );

        return "true";
    }

    @Override
    public byte[] getPhotoByUrl(String url) throws IOException {

        final Photo photo = photoRepository.findByPhotoUrl(url);
        final ByteArrayResource img = new ByteArrayResource(Files.readAllBytes(Paths.get(folder+photo.getPhotoUrl())));
        //photoRepository.delete(photo);

        return img.getByteArray();
    }

    @Async
    private User randomUser(String userId)
    {
        User user = null;
        final Random rand = new Random();
        int userIndex = 0;

        final List<User> users = userRepository.findAll();
        User senderUser = userRepository.findById(userId);
        senderUser.setStats(new UserStat(senderUser.getStats().getMessageCount(),senderUser.getStats().getPhotoPoints()+1));
        userRepository.save(senderUser);
        users.remove(senderUser);

        userIndex = rand.nextInt(users.size());



        user = users.get(userIndex);
        while(user.getId().equals(userId)){
            userIndex = rand.nextInt(users.size());

            user = users.get(userIndex);
        }
        if(user.getId().equals(userId)){
            return null;
        }else{
            return user;
        }
    }

    @Async
    private List<User> randomUserViaFilters(String userId,String gender,int count)
    {
        User user = null;
        final Random rand = new Random();
        int userIndex = 0;
        List<User> selectedUsers= new ArrayList<>();

        final List<User> users = userRepository.findByGender(gender);
        try{
            User senderUser = userRepository.findById(userId);
            senderUser.setDiamondCount(senderUser.getDiamondCount()-5);
            senderUser.setStats(new UserStat(senderUser.getStats().getMessageCount(),senderUser.getStats().getPhotoPoints()+1));
            userRepository.save(senderUser);
            users.remove(senderUser);
        }catch (Exception e)
        {
            System.out.print(e.getMessage());
        }




        for (int i=0;i<count;i++){
            userIndex = rand.nextInt(users.size());
            user = users.get(userIndex);
            while(user.getId().equals(userId)){
                userIndex = rand.nextInt(users.size());

                user = users.get(userIndex);
            }
            if(user.getId().equals(userId)){
                return null;
            }else{
                selectedUsers.add(user);
            }
        }

        return selectedUsers;

    }

}
