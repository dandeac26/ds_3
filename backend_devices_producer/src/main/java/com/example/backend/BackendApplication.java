package com.example.backend;

import com.example.backend.rabbitmq.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.example.backend")
public class BackendApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        // Retrieve the MessageProducer bean from the application context
        MessageProducer messageProducer = context.getBean(MessageProducer.class);

        // Specify the path to your CSV file
        String csvFilePath = "C:\\Users\\Tudor\\Desktop\\My_UID_Project\\backend_devices_producer\\src\\main\\java\\com\\example\\backend\\sensor.csv";

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            int lineNumber = 0;

            // Read lines from the CSV file and send them as messages
            for (CSVRecord csvRecord : csvParser) {
                lineNumber++;

                // Assuming each line contains a single value
                String message = csvRecord.get(0);

                // Send a message to the RabbitMQ queue
                messageProducer.sendMessageFromMain(message);

                System.out.println("Sent message from line " + lineNumber + ": " + message);

                // Optional: Add a delay between sending messages
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
