import KWIC.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ResultTest {

    private static final String PARENT_DIRECTORY = "testcase";



    @Test
    void gernerateFileTest() throws IOException, InterruptedException {
        File createdFile = Main.generateFile("Titles", "Ignored", "Required");
        Thread.sleep(2000);
        checkContentIsEqual(getRealFile("answer.txt"), createdFile);

    }

    @Test
    void gernerateFileTest3() throws IOException, InterruptedException {

        File createdFile1 = Main.generateFile("Titles3", "Ignored3", "Required3");
        Thread.sleep(2000);
        checkContentIsEqual(getRealFile("answer3.txt"), createdFile1);
    }
    private File getRealFile(String fileName) {
        return new File(PARENT_DIRECTORY, fileName);
    }

    private void checkContentIsEqual(File file, File result) throws IOException {

        Assertions.assertTrue(result.exists());

        List<String> rightAnswer = Files.readAllLines(file.toPath());
        List<String> answer = Files.readAllLines(result.toPath());

        Assertions.assertEquals(rightAnswer, answer);
    }


}
