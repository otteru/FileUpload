package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    //  고객이 업로드한 파일명
    private String uploadFileName;
    // 서버 내부에서의 파일명 - 파일 이름이 같은 경우에 덮어씌워지기 때문에 UUID 같은 걸로 구분할 수 있게 하기 위함
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
