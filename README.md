# SAR

Welcome to SAR, our social augmented reality application!
This project is designed for SE3810 - Software Architecture at MSOE
Developed by: Aaron Murphy, Ryley Powell, Leo Madda, and Matt Karcz

In order to run this project you will need to complete the following steps:

CLIENT SETUP

1) Download and install Android Studio: https://developer.android.com/studio/
   * Standard install settings were used when the project was created, so I recommend using these settings for installation
2) Clone the repository into the directory of your choice
3) Launch Android studio and load the project from the SARMobileClient folder in your cloned directory
   * When the project loads you should see SARMobileClient: syncing... in the build window at the bottom of at the screen.
     This will take a few minutes but just let it run.
4) After the project syncs and downloads, open build.gradle (Module: app) from the Gradle Scripts folder in your directory and ensure that compileSdkVersion and targetSdkVersion are 28, and the minSdkVersion is 26
5) Click on the run menu at the top of the screen and selectr Run... and select app in the popup menu
6) We now need to setup the emulator to run the program.  I recommend using the Google Pixel 2 emulator when running, as initial testing when the app was constructed was performed on this emulator
   * select "Create New Virtual Device"
   * select Pixel 2 from the table, and click next
   * download Release name Pie (API Level 28) using the link in the menu.  This may take a few minutes.
   * select pie after it has been downloaded, and click next
   * in the next menu, give the AVD a name if you would like, then click Finish
7) The SAR mobile client is now ready for development and testing.

SERVER SETUP

SEE README.md under sar_server for build instructions.

SEE end-points.md under sar_server for supported end-points.
