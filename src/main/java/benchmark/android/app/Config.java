package benchmark.android.app;

import java.net.URL;
import java.util.Map;

public class Config {
    private URL hub;
    private String size;
    private String benchmark;
    private String httpEndpoint;
    private DeviceCapabilities device;
    private String threads;

    public static Config getConfig(String[] args) {
        Map parsedArgs = Parser.parse(args);
        JSONConfig jsonConfig = new JSONConfig();
        DeviceCapabilities deviceCapabilities;

        if (parsedArgs.get("device") != null) {
            deviceCapabilities = new DeviceCapabilities(
                    parsedArgs.get("device").toString(),
                    (Integer) parsedArgs.get("system_port")
            );
        } else {
            deviceCapabilities = jsonConfig.getDevice();
        }

        String size = parsedArgs.get("size").toString();
        String threads = parsedArgs.get("threads").toString();
        String benchmark = parsedArgs.get("benchmark").toString();
        String httpEndpoint = parsedArgs.get("http_endpoint").toString();

        return new Config(jsonConfig.getHub(), benchmark, size, threads, httpEndpoint, deviceCapabilities);
    }

    public Config(URL hub, String benchmark, String size, String threads, String httpEndpoint, DeviceCapabilities device) {
        this.hub = hub;
        this.size = size;
        this.benchmark = benchmark;
        this.threads = threads;
        this.httpEndpoint = httpEndpoint;
        this.device = device;
    }

    public String getSize() {
        return size;
    }

    public String getBenchmark() { return benchmark; }

    public String getThreads() { return threads; }

    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    public URL getHub() {
        return this.hub;
    }

    public DeviceCapabilities getDevice() {
        return this.device;
    }
}
