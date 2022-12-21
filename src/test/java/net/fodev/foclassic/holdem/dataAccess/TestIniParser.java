package net.fodev.foclassic.holdem.dataAccess;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class TestIniParser {
    IniParser parser;

    @Before
    public void init() {
        parser = new IniParser();
    }

    @Test
    public void parseFromFile_Test_Valid() {
        try {
            Map<String, String> map = parser.parseFromFile("src/main/resources/mohave-holdem.ini");
            map.forEach((k, v) -> System.out.println(k + ", " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
