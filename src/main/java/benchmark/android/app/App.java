package benchmark.android.app;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/* TODO slighty off-topic, but when printing on screen a big matrix appium fails to detect
   when it has finished. Is possible that printing a big matrix locks the main ui thread and
   that leaves appium without access to elements, but why when is finished it does not get
   access back?
*/
public class App {
    public static void main(String[] args) {

        Config config = Config.getConfig(args);
        AppiumSession session = new AppiumSession(
                config.getHub(),
                config.getDevice()
        );

        MainPage benchmarkApp = new MainPage(session);
        DrawerPage drawerPage = benchmarkApp.openDrawer();
        NASPage nasPage = (NASPage) drawerPage.openPage(NASPage.name);


        nasPage.fillData(config.getBenchmark(), config.getThreads(), config.getSize(), config.getHttpEndpoint());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");
        System.out.println("Job started at: " + dateFormat.format(new Date()));
        nasPage.compute();

        while (!nasPage.isFinished()) {
            // TODO I hate sleep, I want no sleep, sleep is bad, maybe a custom ExpectedCondition is better
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Job finished at: " + dateFormat.format(new Date()));
        System.out.println("Data from computation:");
        System.out.println(nasPage.getData());
        benchmarkApp.quit();
    }
}
