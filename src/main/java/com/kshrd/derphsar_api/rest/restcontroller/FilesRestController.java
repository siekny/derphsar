package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.FileInfo;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.service.implement.FilesStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.kshrd.derphsar_api.rest.message.ResponseMessage;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8080")
public class FilesRestController {

    private FilesStorageServiceImp filesStorageServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setFilesStorageServiceImp(FilesStorageServiceImp filesStorageServiceImp) {
        this.filesStorageServiceImp = filesStorageServiceImp;
    }





    /**
     * Post files
     *
     * @param files - Read all files selected
     * @return - Returns response message
     */
    @PostMapping("/files")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String msg;
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {

                filesStorageServiceImp.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            msg = message.inserted("Files") + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(msg));
        } catch (Exception e) {
            msg = message.insertError("Files");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(msg));
        }
    }





    /**
     * Get all files
     *
     * @return - Returns response message
     */
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = filesStorageServiceImp.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FilesRestController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }





    /**
     * Get a file
     *
     * @param filename - Name of file to get
     * @return - File
     */
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }





    /**
     * Delete files
     *
     * @return - Returns response message
     */
    @DeleteMapping(value= "/files")
    public ResponseEntity<String> deleteFile() {
        filesStorageServiceImp.deleteAll();
        final String response = message.deleted("Files");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
