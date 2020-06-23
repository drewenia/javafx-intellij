package com.example.demo;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.demo.ChartApplication.StageReadyEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	private final ApplicationContext applicationContext;
	private final Resource fxml;
	
	public StageInitializer(ApplicationContext applicationContext,@Value("classpath:/main.fxml") Resource resource) {
		this.applicationContext = applicationContext;
		this.fxml = resource;
	}
	
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		try {
            Stage stage = event.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            new RuntimeException(e);
        }
	}
}
