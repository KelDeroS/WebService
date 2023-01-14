package com.kelderos.webservice;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Download {
    private String inputType;
    private String outputType;
    private boolean outputZip;
    private boolean outputEnc;
}