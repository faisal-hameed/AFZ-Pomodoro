package com.afz.pomodoro.ui;

import com.afz.pomodoro.config.PomodoroSetting;
import com.afz.pomodoro.constants.AppConstants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PomodoroSettingUI {

	private static final Font LBL_FONT = Font.font("Varela Round", FontWeight.BOLD, 12);
	String spinnerStyle = Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL;
	String buttonStyle = "-fx-border-style: none;-fx-background-color: #555555;-fx-text-fill: white;-fx-pref-width: 70.0px;-fx-cursor: pointer;-fx-padding: 0.0px 5.0px 0.0px 5.0px;-fx-font-size: 16.0px;";

	private AnchorPane root = new AnchorPane();
	Spinner<Integer> spPomodoroDuration = new Spinner<Integer>(15, 25, 45);
	Spinner<Integer> spShortBrakDuration = new Spinner<Integer>(3, 5, 10);
	Spinner<Integer> spLongBreakDuration = new Spinner<Integer>(10, 15, 20);
	Spinner<Integer> spSessionsBeforeLongBreak = new Spinner<Integer>(2, 5, 5);
	Spinner<Integer> spSessionsPerDay = new Spinner<Integer>(5, 20, 25);

	public void createPomodoroSettingDialog() {

		GridPane gridpane = new GridPane();
		gridpane.setHgap(20);
		gridpane.setVgap(20);
		gridpane.setPadding(new Insets(20, 20, 20, 20));

		Label lblPomodoroDuration = new Label("Pomodoro Duration :");
		Label lblShortBreakDuration = new Label("Short Break Duration :");
		Label lblLongBreakDuration = new Label("Long Break Duration :");
		Label lblSessionsBeforeLongBreak = new Label("Sessions Before Long Break :");
		Label lblSessionsPerDay = new Label("Sessions Per Day :");

		lblPomodoroDuration.setFont(LBL_FONT);
		lblShortBreakDuration.setFont(LBL_FONT);
		lblLongBreakDuration.setFont(LBL_FONT);
		lblSessionsBeforeLongBreak.setFont(LBL_FONT);
		lblSessionsPerDay.setFont(LBL_FONT);

		spPomodoroDuration.getStyleClass().add(spinnerStyle);
		spShortBrakDuration.getStyleClass().add(spinnerStyle);
		spLongBreakDuration.getStyleClass().add(spinnerStyle);
		spSessionsBeforeLongBreak.getStyleClass().add(spinnerStyle);
		spSessionsPerDay.getStyleClass().add(spinnerStyle);
		spPomodoroDuration.setMaxWidth(100);
		spShortBrakDuration.setMaxWidth(100);
		spLongBreakDuration.setMaxWidth(100);
		spSessionsBeforeLongBreak.setMaxWidth(100);
		spSessionsPerDay.setMaxWidth(100);

		JFXButton btnSave = new JFXButton("Save");
		final JFXButton btnCancel = new JFXButton("Cancel");
		btnSave.setFont(LBL_FONT);
		btnCancel.setFont(LBL_FONT);
		try {
			btnSave.setStyle(buttonStyle);
			btnCancel.setStyle(buttonStyle);
		} catch (Exception e) {
			// TODO: handle exception
		}

		HBox hbBtn = new HBox(30);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(btnSave);
		hbBtn.getChildren().add(btnCancel);

		gridpane.add(lblPomodoroDuration, 1, 0);
		gridpane.add(lblShortBreakDuration, 1, 1);
		gridpane.add(lblLongBreakDuration, 1, 2);
		gridpane.add(lblSessionsBeforeLongBreak, 1, 3);
		gridpane.add(lblSessionsPerDay, 1, 4);

		gridpane.add(spPomodoroDuration, 2, 0);
		gridpane.add(spShortBrakDuration, 2, 1);
		gridpane.add(spLongBreakDuration, 2, 2);
		gridpane.add(spSessionsBeforeLongBreak, 2, 3);
		gridpane.add(spSessionsPerDay, 2, 4);

		gridpane.add(hbBtn, 1, 5, 2, 1);

		root.getChildren().add(gridpane);

		loadPomodoroSettings();

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				System.err.println("Save button pressed");
				savePomodoroSettings();
				btnCancel.getScene().getWindow().hide();
			}
		});

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				System.err.println("Cancel Button Pressed");
				btnCancel.getScene().getWindow().hide();
			}
		});

		Stage stage = new Stage();
		Scene scene = new Scene(root, 350, 300);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(AppConstants.POMODORO_TITLE);
		// scene.getStylesheets().add(getClass().getResource("/gui/Dashboard.fxml").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();

	}

	private void loadPomodoroSettings() {
		PomodoroSetting setting = PomodoroSetting.INSTANCE;
		spPomodoroDuration.getValueFactory().setValue(Integer.parseInt(String.valueOf(setting.getFocusTime())));
		spPomodoroDuration.getValueFactory().setValue(Integer.parseInt(String.valueOf(setting.getShortBreak())));
		spPomodoroDuration.getValueFactory()
				.setValue(Integer.parseInt(String.valueOf(setting.getSessionsBeforeLongBreak())));
		spPomodoroDuration.getValueFactory()
				.setValue(Integer.parseInt(String.valueOf(setting.getSessionsBeforeLongBreak())));
		spPomodoroDuration.getValueFactory().setValue(Integer.parseInt(String.valueOf(setting.getSessionsPerDay())));

	}

	private void savePomodoroSettings() {
		// Get new values to save in file.
		int dummyVal = 4;
		PomodoroSetting.INSTANCE.update(dummyVal, dummyVal, dummyVal, dummyVal, dummyVal);
		PomodoroSetting.INSTANCE.saveSetting();
	}

}
