import com.kelderos.Parameter;
import com.kelderos.factory.CodingFactory;
import com.kelderos.factory.JSONFactory;
import com.kelderos.factory.PlainTextFactory;
import com.kelderos.factory.XMLFactory;
import com.kelderos.webservice.FileType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kelderos.ArithmeticExpression;
import com.kelderos.archivation.ZipArchiver;
import com.kelderos.calculator.Calculator;
import com.kelderos.decoders.Decoder;
import com.kelderos.decoders.JSONDecoder;
import com.kelderos.decoders.PlainTextDecoder;
import com.kelderos.decoders.XMLDecoder;
import com.kelderos.encoders.JSONEncoder;
import com.kelderos.encoders.PlainTextEncoder;
import com.kelderos.encoders.XMLEncoder;
import com.kelderos.encryprion.AESDecryptor;
import com.kelderos.encryprion.AESEncryptor;
import com.kelderos.encryprion.AESFunctions;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTesting {
    @Test
    public void decodersTesting() throws IOException {
        File file1 = new File("input.xml");
        File file2 = new File("input.json");
        File file3 = new File("input.txt");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();

        CodingFactory xmlProcessor = new XMLFactory();
        CodingFactory jsonProcessor = new JSONFactory();
        CodingFactory plainTextProcessor = new PlainTextFactory();

        arithmeticExpression = xmlProcessor.decode(Files.readAllBytes(file1.toPath()));

        Assert.assertEquals("a+b+10", arithmeticExpression.getExpressions().get(0));
        Assert.assertEquals("a+7", arithmeticExpression.getExpressions().get(1));

        Assert.assertEquals("a", arithmeticExpression.getParameters().get(0).name);
        Assert.assertEquals("b", arithmeticExpression.getParameters().get(1).name);

        Assert.assertEquals(3, arithmeticExpression.getParameters().get(0).value);
        Assert.assertEquals(6, arithmeticExpression.getParameters().get(1).value);


        arithmeticExpression = jsonProcessor.decode(Files.readAllBytes(file2.toPath()));

        Assert.assertEquals("a+b+10", arithmeticExpression.getExpressions().get(0));
        Assert.assertEquals("a+7", arithmeticExpression.getExpressions().get(1));

        Assert.assertEquals("a", arithmeticExpression.getParameters().get(0).name);
        Assert.assertEquals("b", arithmeticExpression.getParameters().get(1).name);

        Assert.assertEquals(3, arithmeticExpression.getParameters().get(0).value);
        Assert.assertEquals(6, arithmeticExpression.getParameters().get(1).value);


        arithmeticExpression = plainTextProcessor.decode(Files.readAllBytes(file3.toPath()));

        Assert.assertEquals("a+b+10", arithmeticExpression.getExpressions().get(0));
        Assert.assertEquals("a+7", arithmeticExpression.getExpressions().get(1));

        Assert.assertEquals("a", arithmeticExpression.getParameters().get(0).name);
        Assert.assertEquals("b", arithmeticExpression.getParameters().get(1).name);

        Assert.assertEquals(3, arithmeticExpression.getParameters().get(0).value);
        Assert.assertEquals(6, arithmeticExpression.getParameters().get(1).value);
    }

    @Test
    public void calculatorTesting() {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        ArrayList<String> expressions = new ArrayList<>();
        expressions.add("a+b+10");
        expressions.add("a+7");
        arithmeticExpression.setExpressions(expressions);
        ArrayList<Parameter> parameters = new ArrayList<>();
        Parameter parameter1 = new Parameter(), parameter2 = new Parameter();
        parameter1.name = "a";
        parameter1.value = 3;
        parameters.add(parameter1);
        parameter2.name = "b";
        parameter2.value = 6;
        parameters.add(parameter2);
        arithmeticExpression.setParameters(parameters);

        Calculator calculator = new Calculator();
        ArrayList<String> results = calculator.calculate(arithmeticExpression);
        Assert.assertEquals("19.0", results.get(0));
        Assert.assertEquals("10.0", results.get(1));
    }

    @Test
    public void encodersTesting() throws IOException {
        String file1 = "results.xml";
        String file2 = "results.json";
        String file3 = "results.txt";
        ArrayList<String> results = new ArrayList<>();
        results.add("19.0");
        results.add("10.0");

        CodingFactory xmlProcessor = new XMLFactory();
        CodingFactory jsonProcessor = new JSONFactory();
        CodingFactory plainTextProcessor = new PlainTextFactory();

        Scanner scanner1 = new Scanner(new String(xmlProcessor.encode(results, file1)));
        Assert.assertEquals("<results>", scanner1.nextLine());
        Assert.assertEquals("\t<result>19.0</result>", scanner1.nextLine());
        Assert.assertEquals("\t<result>10.0</result>", scanner1.nextLine());
        Assert.assertEquals("</results>", scanner1.nextLine());
        scanner1.close();


        Scanner scanner2 = new Scanner(new String(jsonProcessor.encode(results, file2)));
        Assert.assertEquals("{", scanner2.nextLine());
        Assert.assertEquals("\t\"results\": [", scanner2.nextLine());
        Assert.assertEquals("\t\t19.0,", scanner2.nextLine());
        Assert.assertEquals("\t\t10.0", scanner2.nextLine());
        Assert.assertEquals("\t]", scanner2.nextLine());
        Assert.assertEquals("}", scanner2.nextLine());
        scanner2.close();

        Scanner scanner3 = new Scanner(new String(plainTextProcessor.encode(results, file3)));
        Assert.assertEquals("| 19.0 |", scanner3.nextLine());
        Assert.assertEquals("| 10.0 |", scanner3.nextLine());

    }

    @Test
    public void archivationTesting() throws IOException {
        ZipArchiver zipArchiver = new ZipArchiver();
        byte[] data = "test".getBytes();
        byte[] zipped = zipArchiver.zip("test.txt", data);
        FileType fileType = zipArchiver.unzip(zipped);

        assertArrayEquals(data, fileType.getData());
        assertEquals("test.txt", fileType.getFilename());
    }
}