package com.kelderos.encoders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Encoder {
    byte[] writeToFile(ArrayList<String> results, String filename) throws IOException;
}
