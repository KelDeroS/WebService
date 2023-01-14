package com.kelderos.webservice;


import com.kelderos.ArithmeticExpression;
import com.kelderos.archivation.ZipArchiver;
import com.kelderos.calculator.Calculator;
import com.kelderos.encryprion.AESEncryptor;
import com.kelderos.factory.CodingFactory;
import com.kelderos.factory.JSONFactory;
import com.kelderos.factory.PlainTextFactory;
import com.kelderos.factory.XMLFactory;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/arithmetic")
public class WebServiceController {

    @PostMapping(path = "/calculate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<byte[]> create(@RequestPart("options") Download download, @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        FileType fileType = new FileType(multipartFile.getOriginalFilename(),multipartFile.getBytes());
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        switch (download.getInputType()){
            case "xml":
                CodingFactory xmlFactory = new XMLFactory();
                arithmeticExpression = xmlFactory.decode(fileType.getData());

                break;
            case "json":
                CodingFactory jsonFactory = new JSONFactory();
                arithmeticExpression = jsonFactory.decode(fileType.getData());
                break;
            case "plain":
                CodingFactory plainTextFactory = new PlainTextFactory();
                arithmeticExpression = plainTextFactory.decode(fileType.getData());
        }
        Calculator calculator = new Calculator();
        ArrayList<String> results = calculator.calculate(arithmeticExpression);
        fileType.filename = "results";
        switch (download.getOutputType()){
            case "xml":
                CodingFactory xmlFactory = new XMLFactory();
                fileType.data = xmlFactory.encode(results, "results.xml");
                fileType.filename += ".xml";
                break;
            case "json":
                CodingFactory jsonFactory = new JSONFactory();
                fileType.data = jsonFactory.encode(results, "results.json");
                fileType.filename += ".json";
                break;
            case "plain":
                CodingFactory plainTextFactory = new PlainTextFactory();
                fileType.data = plainTextFactory.encode(results, "results.txt");
                fileType.filename += ".txt";
        }
        ZipArchiver zip = new ZipArchiver();
        if (download.isOutputZip() && !download.isOutputEnc()){
            fileType.data = zip.zip(fileType.getFilename(), fileType.getData());
        }

        else if(!download.isOutputZip() && download.isOutputEnc()){
            fileType.data = AESEncryptor.encrypt(fileType.getData());
        }
        else if(download.isOutputZip() && download.isOutputEnc()){
            fileType.data = AESEncryptor.encrypt(fileType.getData());
            fileType.data = zip.zip(fileType.getFilename(), fileType.getData());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(fileType.data);
        String buffer = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
        fileType.data = buffer.getBytes();
        byteArrayOutputStream.close();
        return FileResponseBuilder.createResponse(fileType.getFilename(), fileType.getData());
    }
}

