package benchmark.android.app;

import com.google.gson.JsonElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DeviceCapabilities {
    DesiredCapabilities desiredCap = new DesiredCapabilities();

    private void setCommonCapabilities() {
        this.desiredCap.setPlatform(Platform.ANDROID);
        this.desiredCap.setCapability("appPackage","es.ull.hpcg.benchmark");
        this.desiredCap.setCapability("appActivity", "MainActivity");
    }

    public DeviceCapabilities(JsonElement json) {
        setCommonCapabilities();
        this.desiredCap.setCapability(MobileCapabilityType.UDID, json.getAsJsonObject().get("udid"));
        this.desiredCap.setCapability(MobileCapabilityType.DEVICE_NAME, json.getAsJsonObject().get("name"));
        if (json.getAsJsonObject().get("systemPort") != null) {
            this.desiredCap.setCapability("systemPort", json.getAsJsonObject().get("systemPort"));
        }
    }

    public DeviceCapabilities(String device, Integer systemPort) {
        setCommonCapabilities();
        this.desiredCap.setCapability(MobileCapabilityType.UDID, device);
        this.desiredCap.setCapability(MobileCapabilityType.DEVICE_NAME, "default");
        if (systemPort != null){
            this.desiredCap.setCapability("systemPort", systemPort);
        }
    }

    public DesiredCapabilities getCapabilities() {
        return this.desiredCap;
    }

}
