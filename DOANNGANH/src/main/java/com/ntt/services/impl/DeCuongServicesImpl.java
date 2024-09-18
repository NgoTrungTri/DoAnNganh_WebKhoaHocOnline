package com.ntt.services.impl;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.ntt.pojo.KhoahocDecuong;
import com.ntt.repositories.DeCuongRepository;
import com.ntt.services.DeCuongServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class DeCuongServicesImpl implements DeCuongServices {

    @Autowired
    private DeCuongRepository deCuongRepo;

    @Override
    public void createDeCuong(KhoahocDecuong dc) {
        if (dc.getFile() != null && !dc.getFile().isEmpty()) {
            try {
                Storage storage = StorageClient.getInstance().bucket().getStorage();
                String fileName = System.currentTimeMillis() + "_" + dc.getFile().getOriginalFilename();
                BlobId blobId = BlobId.of("doannganh-d5551.appspot.com", fileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/pdf").build();

                // Upload file
                try (InputStream inputStream = dc.getFile().getInputStream()) {
                    storage.create(blobInfo, inputStream);
                }

                // Tạo URL công khai cho file
                String fileUrl = String.format("https://firebasestorage.googleapis.com/v0/b/doannganh-d5551.appspot.com/o/%s?alt=media", fileName);
                dc.setUrlFile(fileUrl);
            } catch (IOException ex) {
                throw new RuntimeException("Error uploading file to Firebase Storage", ex);
            }
        }

        // Lưu đề cương vào cơ sở dữ liệu
        deCuongRepo.createDeCuong(dc);
    }

    @Override
    public List<KhoahocDecuong> getDeCuongByKhoaHocId(int idKhoaHoc) {
        return deCuongRepo.getDeCuongByKhoaHocId(idKhoaHoc);
    }
}
