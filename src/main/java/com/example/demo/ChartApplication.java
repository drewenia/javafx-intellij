package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ChartApplication extends Application{
	
	private ConfigurableApplicationContext springContext;
	
	@Override 
	public void init() throws Exception {
		springContext = new SpringApplicationBuilder(MainApplication.class).run();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		springContext.publishEvent(new StageReadyEvent(stage));
	}
	
	@Override
	public void stop() throws Exception {
		springContext.stop();
		Platform.exit();
	}
	
	static class StageReadyEvent extends ApplicationEvent{
		public StageReadyEvent(Stage stage) {
			super(stage);
		}

		public Stage getStage() {
			return ((Stage) getSource());
		}
	}
}
