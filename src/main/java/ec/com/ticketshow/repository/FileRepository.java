package ec.com.ticketshow.repository;

import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Repository
public class FileRepository {

    public Properties readPropertiesFile() throws IOException {
        String defaultConfigPath = "/home/rsoledispa/Documents/IdeaProjects/certification.properties";
        //String defaultConfigPath = "/certification.properties";
        Properties defaultProps = new Properties();
        defaultProps.load(new FileInputStream(defaultConfigPath));
        return defaultProps;
    }
}
