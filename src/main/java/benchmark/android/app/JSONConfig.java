package benchmark.android.app;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONConfig {
    private JsonElement json = null;

    public JSONConfig() {
        try {
            this.json = new JsonParser().parse(
                    (Reader) new FileReader("appium-conf.json")
            );
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file for appium not found.");
            System.exit(1);
        }
    }

    public URL getHub() {
        URL hub = null;
        try {
            hub = new URL(
                    this.json.getAsJsonObject().get("hub").getAsString()
            );

        } catch (MalformedURLException e) {
            System.out.println("Something wrong with the hub url");
            System.exit(1);
        }
        return hub;
    }

    public DeviceCapabilities getDevice() {
        JsonElement device = this.json.getAsJsonObject().get("devices").getAsJsonArray().get(0);
        return new DeviceCapabilities(device);
    }
}
