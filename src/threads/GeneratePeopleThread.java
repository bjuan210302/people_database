package threads;

import javafx.application.Platform;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Database;
import ui.GenerateController;

public class GeneratePeopleThread  {

	private Database db;
	private GenerateController ui;
	private DoublePropertyBase observableDouble;
	
	public GeneratePeopleThread(Database db, GenerateController ui, int amount) {
		this.db = db;
		this.ui = ui;
		observableDouble = intAsObservable(amount);

		observableDouble.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					ui.updateCounters(newValue.doubleValue());
				});
			}
		});
	}

	public boolean mergeTempList() {
		new Thread(() -> {
			db.mergeTempList(observableDouble);
			Platform.runLater(() -> {
				ui.updateCounters(0);
			});
		}).start();
		
		return true;
	}
	
	public void generateTempList() {
		new Thread(() -> {
			db.generateTempList(observableDouble);
			Platform.runLater(() -> {
				ui.updateCounters(0);
			});
		}).start();
	}
	public void run() {
		
	}
	
	public static DoublePropertyBase intAsObservable(int i) {
		DoublePropertyBase observableInt = new DoublePropertyBase() {
			@Override
			public String getName() {
				return "thisDoesNotMatter";
			}
			
			@Override
			public Object getBean() {
				return this;
			}
		};
		observableInt.set(i);
		return observableInt;
    }
	
}
