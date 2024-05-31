import java.io.ObjectInputFilter.Config;.
public class TestConfigurationProps {

	 void main(String[] args) {
		ResouceBundle config = ResourceBundle("config");
		String driverName = config.getString("database.url");
		System.out.println(driverName);

	}

}
