package sit.int204.tue.tue204final;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.int204.tue.tue204final.utils.ListMapper;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ListMapper listMapper(){
        return new ListMapper();
    }

}
