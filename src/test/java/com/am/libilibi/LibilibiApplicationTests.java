package com.am.libilibi;

import org.junit.jupiter.api.Test;
import org.python.core.Py;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
class LibilibiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(1);
        String py = "D:/WorkSpace/libilibi/src/test/java/com/am/crawl.py";

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(py);

        System.out.println(2);
    }

}
