package com.benzassignment.employee.producer.service.service;

import com.benzassignment.employee.producer.service.model.Employee;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    KafkaTemplate<String, Employee> KafkaJSONTemplate;


    @Value("${kafka.producer.topic}")
    public String topic = "";
    private GenericRecord value;

    private String SCHEMA_PATH = "src/main/resources/avro/avro.avsc";

    public Employee sendFileToKafka(String fileType, Employee employee) {
        System.out.println("Inside Service block");
        if (fileType.equals("CSV") || fileType.equals("XML"))
            employee.setFileType(fileType);
        employee.setId();
        sendFileAsJson(employee);
        return employee;
    }

    private void sendFileAsJson(Employee employee) {
        KafkaJSONTemplate.send(topic, "key",employee);
        System.out.println("Message published successfully");
    }

   /* private GenericRecord createGenericRecord(Employee employee) {

        try {
            Schema schema = new Schema.Parser().parse(new String(Files.readAllBytes(Paths.get(SCHEMA_PATH))));

            GenericRecord avroRecord = new GenericData.Record(schema);
            avroRecord.put("name",employee.getName());
            avroRecord.put("dob",employee.getDob());
            avroRecord.put("salary",employee.getSalary());
            avroRecord.put("age",employee.getAge());
            avroRecord.put("fileType",employee.getFileType());
            avroRecord.put("operation",employee.getOperation());

            return avroRecord;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}


