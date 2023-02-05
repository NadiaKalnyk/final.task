package ua.hillel.tests.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;
import java.util.Base64;


public class CustomExtentReportListener extends ExtentITestListenerClassAdapter {
    @Override
    public synchronized void onTestFailure(ITestResult result){
        super.onTestFailure(result);
        ExtentTest test = ExtentTestManager.getTest(result);
        try {
            File file = getScreenShot(result);
            test.addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)),"Failed test image");
            file.delete();
            ;        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public synchronized void  onTestSkipped(ITestResult result){
        super.onTestSkipped(result);
        ExtentTest test = ExtentTestManager.getTest(result);
        try {
            File file =getScreenShot(result);
            test.addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)),"Failed test image");
            file.delete();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private File getScreenShot(ITestResult iTestResult){
        File fileForCopy = new File(iTestResult.getName() + ".jpg");
        Selenide.screenshot("reportScreenshot");
        File screenshotFile = new File("test-result/reports/reportScreenshot.jpg");
        try {
            FileUtils.copyFile(screenshotFile, fileForCopy);
        } catch (IOException e){
            e.printStackTrace();
        }
        return  fileForCopy;
    }
}