1. set up allure report
2. cmd , run : npm install -g allure-commandline --save-dev
terminal run: Set-ExecutionPolicy -ExecutionPolicy Bypass -Scope CurrentUser
3. Add file

Nếu bạn chưa biết mô hình đối tượng trang (Page Object Model) thì trước khi bắt đầu bài viết này mình khuyên bạn nên xem lại bài viết mô hình POM trong Selenium Java của An. Tại vì những ví dụ sẽ chạy test case hoàn chỉnh nên An chạy code theo dạng page object cho nhanh á. Nội dung liên quan là TestListener


✅ Cài đặt Allure Report trong Selenium Java

Trước tiên, mình sẽ thêm biến môi trường Allure-2.21.0 vào máy tính của mình cũng giống như cài đặt biến môi trường cho Java JDK và Maven.

Mình đặt tên biến môi trường là MAVEN_HOME

Tải gói Allure-2.21.0 cho Window tại đây

Vì An chỉ dạy trên môi trường máy window nên có gì các bạn kiếm bản cho MacOS hoặc Linux như hình hướng dẫn bên dưới:

Link download theo phiên bản: https://github.com/allure-framework/allure-java/releases


Tiếp theo thì các bạn giải nén ra để nó vào ổ C (ổ chứa hệ điều hành) để tạo biến môi trường trong máy ổn định.


[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester


Nó giống với setup biến môi trường Java JDK á. Các bạn xem video An hướng dẫn luôn phần này cho nhé. Ghi blog dài dòng khó hiểu nữa.


Tiếp theo là dô vấn đề cấu hình Allure Report nào !!


Bước 1: Thêm thư viện Allure Reports vào pom.xml trong Maven Project
 <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-testng -->
<dependency>
	<groupId>io.qameta.allure</groupId>
	<artifactId>allure-testng</artifactId>
	<version>2.21.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-attachments -->
<dependency>
	<groupId>io.qameta.allure</groupId>
	<artifactId>allure-attachments</artifactId>
	<version>2.21.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
	<groupId>org.aspectj</groupId>
	<artifactId>aspectjweaver</artifactId>
	<version>1.9.19</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<version>1.18.26</version>
	<scope>provided</scope>
</dependency>
Markup
(Cập nhật ngày 31/03/2023)

Hoặc vào link này để download bản tùy ý:

https://mvnrepository.com/artifact/io.qameta.allure/allure-testng


Tiếp theo là thêm đoạn build dùng để thực thi và config allure trong maven. Các bạn chú ý là chúng ta sẽ để đoạn build ngoài thẻ dependencies nhé.


<build>
	<pluginManagement>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>3.0.0</version>
				<configuration>
					<suiteXmlFiles>
						<!-- Call Suite name -->
						<suiteXmlFile>suites/SuiteLoginTest.xml</suiteXmlFile>
					</suiteXmlFiles>
					<argLine>
                            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.19/aspectjweaver-1.9.19.jar"
                        </argLine>
					<testFailureIgnore>true</testFailureIgnore>
					<systemPropertyVariables>
						<!--Đường dẫn xuất ra report-->
						<allure.results.directory>target/allure-results</allure.results.directory>
					</systemPropertyVariables>
				</configuration>
			</plugin>
		</plugins>
	</pluginManagement>
</build>
Markup
- Chổ <suiteXmlFile>suites/SuiteLoginTest.xml</suiteXmlFile>  chỉ đường dẫn đến file XML khi chạy trực tiếp từ pom.xml

- Chổ <allure.results.directory>target/allure-results</allure.results.directory>  đoạn này chỉ đường dẫn xuất report. Nằm trong folder target. Nếu muốn mang ra ngoài thì sửa lại là allure-results thôi là xong.


Đây là tất cả các thư viện An đã add vào pom.xml của cả project đây:

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>anhtester.com</groupId>
    <artifactId>anhtester-selenium-java-092022</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.8.3</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.3.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.6</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-simple -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.6</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.3</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.3</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.github.stephenc.monte/monte-screen-recorder -->
        <dependency>
            <groupId>com.github.stephenc.monte</groupId>
            <artifactId>monte-screen-recorder</artifactId>
            <version>0.7.7.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.20.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.20.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.0.9</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-testng -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-testng</artifactId>
            <version>2.21.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-attachments -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-attachments</artifactId>
            <version>2.21.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.19</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.26</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.0.0</version>
                    <configuration>
                        <suiteXmlFiles>
                            <!-- Call Suite name -->
                            <suiteXmlFile>suites/SuiteLoginTest.xml</suiteXmlFile>
                        </suiteXmlFiles>
                        <argLine>
                            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.19/aspectjweaver-1.9.19.jar"
                        </argLine>
                        <testFailureIgnore>true</testFailureIgnore>
                        <systemPropertyVariables>
                            <!--Đường dẫn xuất ra report-->
                            <allure.results.directory>target/allure-results</allure.results.directory>
                        </systemPropertyVariables>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>

</project>
Markup


Bước 2: Tạo class AllureManager để config allure reports
Tạo class AllureManager để vào package reports chung với class ExtentReportManager buổi hôm trước. Hoặc muốn để đâu cũng được. Gọn ràng là được.

package anhtester.com.reports;

import anhtester.com.drivers.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureManager {
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
Java


Các ghi chú trong Allure Report:
@Epic
@Features
@Stories/@Story
@Severity(SeverityLevel.BLOCKER)
@Description("In this cool test we will check cool thing")
@Step
@Attachment
@Link
Markup


Cái ghi chú quan trọng của mình là @Step. Nó đại diện cho từng @Test và chúng ta sẽ config nó sau. Còn cái Ghi chú còn lại thì bổ trợ làm rõ ràng hơn cho Suite test case.

Các bạn nghiên cứu thêm tại đây: https://docs.qameta.io/allure-report/frameworks/java/testng#features

Mô tả chi tiết hơn tại đây: https://www.seleniumeasy.com/selenium-tutorials/allure-report-example-with-annotations

Demo: https://demo.qameta.io/allure/#behaviors



Bước 3: Thêm Allure Reports vào TestListener class
Chúng ta sẽ thêm 2 hàm từ AllureManager vào phương thức onTestFailue trong TestListener

@Override
public void onTestFailure(ITestResult result) {
    LogUtils.error("Test case " + result.getName() + " is failed.");
    //Screenshot khi fail
    CaptureHelper.captureScreenshot(result.getName());
    LogUtils.error(result.getThrowable().toString());

    //Extent Report
    ExtentTestManager.addScreenShot(result.getName());
    ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
    ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

    //Allure Report
    AllureManager.saveTextLog(result.getName() + " is failed.");
    AllureManager.saveScreenshotPNG();
}
Java
[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester


Bước 4: Mở Allure Reports sau khi chạy test
Sau khi chạy test case các bạn thấy là nó sẽ tự sinh ra cho mình folder "allure-results" nằm trong target/allure-results nó chứa các file json. Đó cũng là data của report.

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Từ giao diện IntelliJ IDEA, sau khi các bạn chạy test case trên xong thì mở "Terminal" lên để chạy lệnh sau.

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Hoặc

[Selenium Java] Bài 31: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Hoặc  dùng phím tắt Alt + F12


Tiếp theo là chạy lệnh trong Terminal:  allure serve allure-results

Sau khi chạy nó sẽ sinh ra cho mình folder tạm và host tạm để display Report.

[Selenium Java] Bài 31: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Đợi tầm 3 giây nó sinh ra cho mình 1 đường link tự động mở trên Browser luôn. Không thì các bạn click link trong vùng Terminal như hình trên cũng được.

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Giao diện từng Step

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Nó đính kèm hình ảnh và câu text log mà mình ghi ở phần TestListener khi nảy.


Khi không cần xem report nữa thì mình tắt Terminal đi với phím tắt Ctrl +C sau đó chọn Y (đồng ý tắt) và Enter


🔆 Đính kèm hình ảnh vào Allure Report khi Test case Fail

Các bạn chú ý: phải dùng bản TestNG 7.4.0 thì Allure report nó mới hiểu Attachment cái file hình vào khi test cases Fail

Thêm các thư viện sau vào pom.xml: Allure Attachments, Aspectjweaver và Project Lombok

Chổ này Allure Report các bạn cập nhật lên phiên bản mới nhất là 2.21.0  (cập nhật ngày 30/03/2023)

Tiếp theo là tạo thẻ build trong maven của file pom.xml như bên trên mình đã thêm từ đầu.



🔆 Mở Allure Report bằng Maven
Để mở Allure Report bằng Maven trong pom.xml thì chúng ta mở tab Maven lên và gõ lệnh: mvn clean test

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester
[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester


Chạy xong chúng ta gõ lệnh trong Terminal: allure serve target/allure-results







🔆 Tạo các steps detail trong Allure Report
Để thêm các bước chi tiết của test cases vào trong Allure Report thì chúng ta cần thêm các @Step vào trong custom keyword của mình. Cụ thể là WebUI keyword.


[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

🔆 Add step detail vào từng bước của test cases thì các bạn cũng làm tương tự như Extent Report bên trên là gọi nó vào trong WebUI keyword

Ví dụ hàm openURL trong WebUI keyword

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        Log.info("Open: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);

        waitForPageLoaded();
        if (PropertiesHelpers.getValue("screenshot_step").equals("yes")) {
            CaptureHelpers.takeScreenshot("openURL_" + Helpers.makeSlug(url));
        }
    }
Java
Mình thêm cái @Step là để gắn step detail vào report. Các bạn ghi mô tả tuỳ ý.

{0}, {1},...đại diện cho giá trị của các tham số bên dưới phương thức. Theo thứ tự luôn nhé. Và bắt đầu từ 0 (tham số thứ nhất)

Và các bạn có thể thêm chi tiết hơn thông qua hàm ghi log vào Allure report bằng cách gọi hàm saveTextLog như của Extent Report

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        Log.info("Open: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        AllureManager.saveTextLog("Open URL: " + url);
        waitForPageLoaded();
        if (PropertiesHelpers.getValue("screenshot_step").equals("yes")) {
            CaptureHelpers.takeScreenshot("openURL_" + Helpers.makeSlug(url));
        }
    }
Java
Sau khi chạy thì nó sẽ như này

[Selenium Java] Bài 33: Cài đặt và sử dụng Extent Report Allure Report | Anh Tester

Chắc nhiều bạn cần mức độ như thế đúng không 😜

Source code tham khảo từ khoá học 04/2022: https://github.com/anhtester/SeleniumMaven42022Parallel