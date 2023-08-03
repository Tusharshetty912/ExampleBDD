package com.examplebdd.test;

import android.os.Bundle;
import androidx.test.runner.MonitoringInstrumentation;
import org.junit.runner.RunWith;
import java.io.File;
import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
        glue = "com.examplebdd",
        monochrome = true)
public class Instrumentation extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        arguments.putString("plugin", "json:" + getAbsoluteFilesPath() + "/cucumber.json"); //programmatically create the plugin configuration
//        arguments.putString("plugin", "html:" + getAbsoluteFilesPath() + "/cucumber.html");
//        arguments.putString("plugin", "junit:" + getAbsoluteFilesPath() + "/cucumber.xml");

        //it crashes on Android R without it
        new File(getAbsoluteFilesPath()).mkdirs();
        super.onCreate(arguments);

        // String tags = BuildConfig.TEST_TAGS;
        String tags = "";
        if (!tags.isEmpty()) {
            arguments.putString("tags", tags.replaceAll(",", "--").replaceAll("\\s",""));
        }

        instrumentationCore.create(arguments);
        start();
    }
    @Override
    public void onStart() {
        super.onStart();

        waitForIdleSync();
        instrumentationCore.start();
    }
    private String getAbsoluteFilesPath() {
        File directory = getTargetContext().getExternalFilesDir(null);
        return new File(directory, "reports").getAbsolutePath();
//      path to report - sdcard/Android/data/.../file_name
    }

}
