package com.example.backend.api;


import com.example.backend.models.Photo;
import com.example.backend.service.chat.ChatService;
import com.example.backend.service.phototransfer.PhotoTransferService;
import com.example.backend.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoAPI {

    private final PhotoTransferService photoTransferService;
    private final ChatService chatService;

    @PostMapping("v1/photo/send")
    public ResponseEntity<?> sendRandomPhoto(@RequestParam("imageFile") MultipartFile imageFile,@RequestParam("userId") String userId) throws Exception {

        photoTransferService.sendRandomImage(imageFile,userId);

        return null;
    }

    @PostMapping("v1/photo/sendViaFilters")
    public ResponseEntity<?> sendRandomPhotoViaFilters(@RequestParam("imageFile") MultipartFile imageFile,@RequestParam("userId") String userId,@RequestParam("gender") String gender,@RequestParam("count") int count) throws Exception {

        photoTransferService.sendRandomImageViaFilters(imageFile,userId,gender,count);

        return null;
    }

    @PostMapping("v1/photo/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("photoId") String photoId) throws Exception{

        photoTransferService.removePhoto(photoId);

        return ResponseEntity.ok(new GenericResponse("Delete is Success",true));
    }

    @PostMapping(
        path="v1/photo/sendById"
    )
    public ResponseEntity<?> sendPhotoById(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("userId") String userId,
            @RequestParam("receiverId") String receiverId
    ) throws Exception {

        photoTransferService.sendPhotoById(imageFile,userId,receiverId);
//        userChatService.setUserChat(chatId,userId);
//        userChatService.setUserChat(chatId,receiverId);
        return null;
    }

    @PostMapping("v1/photo/sendByIdFromMessage")
    public ResponseEntity<?> sendByIdFromMessage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("userId") String userId, @RequestParam("receiverId") String receiverId,@RequestParam("chatId") String chatId) throws Exception {

        String photoId = photoTransferService.sendByIdFromMessage(imageFile,userId,receiverId, chatId);


        return ResponseEntity.ok(photoId);
    }


    @GetMapping(
            value= "v1/photo/getPhotoById/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getPhotoById(@PathVariable String id) throws IOException {
        final byte[] imageFile = photoTransferService.getPhotoById(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE)).body(imageFile);
    }

    @GetMapping(
            value= "v1/photo/getPhotoByUrl/{url}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getPhotoByUrl(@PathVariable String url) throws IOException {
        final byte[] imageFile = photoTransferService.getPhotoByUrl(url);
        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE)).body(imageFile);
    }

    @GetMapping("v1/photo/getMyPhotos/{id}")
    public @ResponseBody ResponseEntity<List<Photo>> getMyPhotos(@PathVariable String id){

        final List<Photo> myPhotos = photoTransferService.getMyPhotos(id);

        return ResponseEntity.ok(myPhotos);
    }


}
