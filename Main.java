import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ReadCSV reader = new ReadCSV();
		reader.ReadCSV(args[0],args[1]);
	}

}
