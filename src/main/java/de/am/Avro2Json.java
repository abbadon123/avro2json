package de.am;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

/**
 * Created by andreas.maier on 01.03.16.
 *
 * This project shows how to create classes from Avro schema with the Avro Maven plugin
 * and how to serialize and deserialize these classes into/from JSON.
 * This can be useful, when you are using Avro internally, but you have to interoperate
 * with systems which can only handle JSON. It is also useful, if you want to manage
 * the creation of JSON via a schema.
 *
 * Thanks to Jeff Li for his very good blog posts about Avro:
 *
 * http://blog.jeffli.me/blog/2014/02/06/avro-cookbook-part-i/
 * http://blog.jeffli.me/blog/2014/04/05/avro-cookbook-part-ii/
 * http://blog.jeffli.me/blog/2014/04/08/avro-cookbook-part-iii/
 *
 */
public class Avro2Json {

    public static void main(String... args) throws IOException {
        File file1 = new File();
        file1.setName("TestFile1.txt");
        // Avro has not support for Datetime types, yet. It is planned for 1.8.0
        file1.setDatetime(OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        // Using bytes to make the example less trivial
        file1.setData(ByteBuffer.wrap("TestData1".getBytes(StandardCharsets.UTF_8)));

        // create an empty file2 which we copy data into from file1
        File file2 = new File();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Encoder encoder = new EncoderFactory().jsonEncoder(File.getClassSchema(), outputStream);
            DatumWriter<File> fileDatumWriter = new SpecificDatumWriter<>(File.class);
            fileDatumWriter.write(file1, encoder);
            encoder.flush();
            System.out.println("File1 (JSON encoded): " + outputStream.toString());

            try(ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray())) {
                Decoder decoder = new DecoderFactory().jsonDecoder(File.getClassSchema(), inputStream);
                DatumReader<File> fileDatumReader = new SpecificDatumReader<>(File.class);
                fileDatumReader.read(file2, decoder);
            }
        }
        System.out.println("File2 (toString() method): " + file2);
    }
}
