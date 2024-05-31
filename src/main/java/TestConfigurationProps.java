import java.util.ResourceBundle;

public class TestConfigurationProps {

	public static void main(String[] args) {
		ResourceBundle config = ResourceBundle.getBundle("config");
		String driverName = config.getString("database.url");
		System.out.println(driverName);

	}

}
