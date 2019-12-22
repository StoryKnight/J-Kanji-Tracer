import java.io.*;
import java.nio.charset.Charset;

public class WriteF {

    WriteF() {

    }

    public void writeFile() throws IOException {
        File file = new File("vocab.txt");
        file.createNewFile();
        Charset charset = Charset.forName("UTF-8");


        BufferedWriter out = new BufferedWriter
                (new OutputStreamWriter
                        (new FileOutputStream(file),
                                charset));
        out.write(Text.dictionary.display());
        out.close();

    }
}