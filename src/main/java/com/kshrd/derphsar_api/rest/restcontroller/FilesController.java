package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.FileInfo;
import com.kshrd.derphsar_api.service.FilesStorageService;
import com.kshrd.derphsar_api.service.implement.FilesStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.kshrd.derphsar_api.rest.message.ResponseMessage;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8080")
public class FilesController {

    FilesStorageServiceImp filesStorageServiceImp;

    @Autowired
    public void setFilesStorageServiceImp(FilesStorageServiceImp filesStorageServiceImp) {
        this.filesStorageServiceImp = filesStorageServiceImp;
    }

    //upload files

    @PostMapping("/files")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {

                filesStorageServiceImp.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "You have uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "You have failed to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //get all files
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() throws IOException {
        List<FileInfo> fileInfos = filesStorageServiceImp.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    //get one file
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    //delete all files
    @DeleteMapping(value= "/delete")
    public ResponseEntity<String> deleteFile() {
        filesStorageServiceImp.deleteAll();
        final String response = "You have deleted all files successfully.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
