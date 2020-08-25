package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.FileInfo;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.service.FilesStorageService;
import com.kshrd.derphsar_api.service.implement.FilesStorageServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.kshrd.derphsar_api.rest.message.ResponseMessage;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api/v1")
//@CrossOrigin("http://localhost:8080")
//public class FilesRestController {
//
//    private FilesStorageServiceImp filesStorageServiceImp;
//    private MessageProperties message;
//
//    @Autowired
//    public void setMessage(MessageProperties message) {
//        this.message = message;
//    }
//
//    @Autowired
//    public void setFilesStorageServiceImp(FilesStorageServiceImp filesStorageServiceImp) {
//        this.filesStorageServiceImp = filesStorageServiceImp;
//    }
//
//
//
//
//
//    /**
//     * Post files
//     *
//     * @param files - Read all files selected
//     * @return - Returns response message
//     */
//    @PostMapping("/files")
//    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
//        String msg;
//        try {
//            List<String> fileNames = new ArrayList<>();
//            Arrays.asList(files).stream().forEach(file -> {
//
//                filesStorageServiceImp.save(file);
//                fileNames.add(file.getOriginalFilename());
//            });
//
//            msg = message.inserted("Files") + fileNames;
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(msg));
//        } catch (Exception e) {
//            msg = message.insertError("Files");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(msg));
//        }
//    }
//
//
//
//
//
//    /**
//     * Get all files
//     *
//     * @return - Returns response message
//     */
//    @GetMapping("/files")
//    public ResponseEntity<List<FileInfo>> getListFiles() {
//        List<FileInfo> fileInfos = filesStorageServiceImp.loadAll().map(path -> {
//            String filename = path.getFileName().toString();
//            String url = MvcUriComponentsBuilder.fromMethodName(FilesRestController.class, "getFile", path.getFileName().toString()).build().toString();
//
//            return new FileInfo(filename, url);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//    }
//
//
//
//
//
//    /**
//     * Get a file
//     *
//     * @param filename - Name of file to get
//     * @return - File
//     */
//    @GetMapping("/files/{filename:.+}")
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//        Resource file = filesStorageServiceImp.load(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//
//
//
//
//    /**
//     * Delete files
//     *
//     * @return - Returns response message
//     */
//    @DeleteMapping(value= "/files")
//    public ResponseEntity<String> deleteFile() {
//        filesStorageServiceImp.deleteAll();
//        final String response = message.deleted("Files");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}



@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("http://localhost:8080")
public class FilesRestController {
    @Value(value = "${file.upload.server.path}/")
    private String serverPath;

    @Value("${file.base.url}")
    private String imageUrl;

    @Autowired
    FilesStorageServiceImp storageService;



    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    //@ResponseBody
    @ApiOperation(value = "upload images")
    public ResponseEntity<Map<String,Object>> uploadFile(@RequestParam("files") MultipartFile[] files) {

        Map<String, Object> res = new HashMap<>();

        int i=0;
        try {
            for(MultipartFile file : files)
            {
                i++;
                String fileName = storageService.save(file);
                if(i==1){
                    res.put("message","Uploaded file successfully");
                    res.put("status",true);
                }
                res.put("data"+i,(imageUrl+fileName));
            }
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("message","Could not upload the file:");
            res.put("status",false);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(res);
        }
    }



    @GetMapping("/files")
    @ApiOperation(value = "get all images")
    public ResponseEntity<BaseApiResponse<ArrayList<String>>> getAllFiles() {
        try{
            BaseApiResponse<ArrayList<String>> baseApiResponse = new BaseApiResponse<>();
            Set<String> fileNames = storageService.listFilesUsingJavaIO("D:\\meeting31\\API\\derphsar-api-v6\\derphsar-api-v1\\src\\main\\resources\\files");
            ArrayList<String> nameWithAddress= new ArrayList<>();
            for(String string : fileNames){
                nameWithAddress.add("http://localhost:8080/image/"+string);
            }
            baseApiResponse.setData(nameWithAddress);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
            baseApiResponse.setStatus(HttpStatus.OK);
            baseApiResponse.setMessage("You have selected all the images");
            return ResponseEntity.ok(baseApiResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/files/{filename:.+}")
    @ApiOperation(value = "get a image by name")
    public ResponseEntity<BaseApiResponse<String>> getFileByName(@PathVariable String filename) {
        try{
            BaseApiResponse<String> baseApiResponse = new BaseApiResponse<>();
            Set<String> fileNames = storageService.listFilesUsingJavaIO("D:\\meeting31\\API\\derphsar-api-v6\\derphsar-api-v1\\src\\main\\resources\\files");
            String nameWithAddress = "";
            for(String string : fileNames){
                if(string.equals(filename))
                    nameWithAddress = "http://localhost:8080/image/"+string;
            }
            baseApiResponse.setData(nameWithAddress);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
            baseApiResponse.setStatus(HttpStatus.OK);
            baseApiResponse.setMessage("You have selected the images");
            return ResponseEntity.ok(baseApiResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
