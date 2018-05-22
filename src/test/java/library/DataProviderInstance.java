package library;

public class DataProviderInstance {

	private static DataProviderBean datafile;
	private DataProviderInstance() {
		
	}
	public static DataProviderBean getInstance() {
		if(datafile ==null) {
			initData();
		}
		return datafile;
	}
	
	private static void initData() {
		datafile = new DataProviderBean();
		String workingDir = System.getProperty("user.dir");
		try {
			datafile.readPropertiesFile(workingDir + "\\src\\test\\Resources\\properties\\config.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
