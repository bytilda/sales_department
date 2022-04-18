package com.example.sales_department;

import com.example.sales_department.controller.HelloController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class ChartApplication extends Application {
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage stage) throws IOException {
        //applicationContext.publishEvent(new StageReadyEvent(stage));
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HelloController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init()  {
        applicationContext = new SpringApplicationBuilder(SalesDepartmentApplication.class).run();
    }

    @Override
    public void stop()  {
        applicationContext.stop();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
