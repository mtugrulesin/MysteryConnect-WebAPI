package com.example.backend.service.phototransfer;

import com.example.backend.models.Photo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoTransferService {
    void sendRandomImage(MultipartFile imageFile,String userId) throws Exception;

    void sendRandomImageViaFilters(MultipartFile imageFile,String userId,String gender,int count) throws Exception;

    void removePhoto(String photoId);

    @Async
    void sendPhotoById(MultipartFile imageFile, String userId, String receiverId) throws Exception;

    byte[] getPhotoById(String id) throws IOException;

    List<Photo> getMyPhotos(String id);

    @Async
    String sendByIdFromMessage(MultipartFile imageFile, String userId, String receiverId, String chatId) throws Exception;

    byte[] getPhotoByUrl(String url) throws IOException;
}
