package com.hendisantika.example.demoactuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MessageChannelMetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class DemoActuatorApplication extends SpringBootServletInitializer {

	private static final Class<DemoActuatorApplication> applicationClass = DemoActuatorApplication.class;

	@Autowired
	MyMessageHandler myMessageHandler;


	public static void main(String[] args) {
		SpringApplication.run(DemoActuatorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Bean
	public MessageChannel metricsChannel() {
		return new DirectChannel();
	}

	@Bean
	@ExportMetricWriter
	public MessageChannelMetricWriter messageChannelMetricWriter() {
		return new MessageChannelMetricWriter(metricsChannel());
	}

//    @Bean
//    @ServiceActivator(inputChannel = "metricsChannel")
//    public MessageHandler metricsHandler() {
//        return System.out::println;
//    }

	@Bean
	@ServiceActivator(inputChannel = "metricsChannel")
	public MessageHandler metricsHandler() {
		return myMessageHandler;
	}

//    @ServiceActivator(inputChannel = "metricsChannel")
//    public MessageHandler handleMessage(org.springframework.messaging.Message<?> message) {
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                System.out.println("Message [" + message.toString() + "] is received...");
//            }
//        };
//    }
}
