package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;
    
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }

        return storeFileResult;
    }

    // 파일 저장하고 우리가 만든 UploadFile로 반환하기
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        
        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        // 서버에 저장하는 파일명 (근데 확장자는 가져오고 싶다.)
        String storeFileName = createStoreFileName(originalFilename);
        // getFullPath 쓰면 절대경로로 만들어주는 등 경로 일관성 있게 해준다고 함
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);

    }

    private String createStoreFileName(String originalFilename) {
        String ext = extracted(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }

}
