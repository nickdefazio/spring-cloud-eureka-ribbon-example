package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UserApplication {

  @LoadBalanced
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;


  @RequestMapping("/user")
  public String hi(@RequestParam(value="name", defaultValue="Artaban") String name) {


    //For example only. Shows our list of instances
    final List<ServiceInstance> instances = this.discoveryClient.getInstances("sayhello");
    for(final ServiceInstance instance : instances){
      System.out.println("Instance: " + instance.getHost().toString());
      System.out.println("Port: " + instance.getPort());
      System.out.println("URI: " + instance.getUri().toString());
    }

    //TODO: Wrap this with Hystrix
    //=====================================
    final String greeting = this.restTemplate.getForObject("http://sayhello/greeting", String.class);
    //=====================================


    return String.format("%s, %s!", greeting, name);
  }

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}

