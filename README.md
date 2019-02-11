# SAR

Welcome to **SAR**, our social augmented reality application!

This project is designed for **SE3810** - Software Architecture at MSOE

Developed by **Group I**
 
 - Aaron Murphy
 - Ryley Powell
 - Leo Madda
 - Matt Karcz

---
## CLIENT SETUP

1) Download and install [Android Studio](https://developer.android.com/studio/)
   * Standard install settings were used when the project was created, so I recommend using these settings for installation

2) Clone the repository into the directory of your choice

3) Launch Android studio and load the project from the SARMobileClient folder in your cloned directory
   * When the project loads you should see SARMobileClient: syncing... in the build window at the bottom of at the screen.
     This will take a few minutes but just let it run.

4) After the project syncs and downloads, open build.gradle (Module: app) from the Gradle Scripts folder in your directory and ensure that compileSdkVersion and targetSdkVersion are 28, and the minSdkVersion is 26

5) Click on the run menu at the top of the screen and select Run... and select app in the popup menu

6) We now need to setup the emulator to run the program.  I recommend using the Google Pixel 2 emulator when running, as initial testing when the app was constructed was performed on this emulator
   * Select "Create New Virtual Device"
   * Select Pixel 2 from the table, and click next
   * Download Release name Pie (API Level 28) using the link in the menu.  This may take a few minutes.
   * Select pie after it has been downloaded, and click next
   * In the next menu, give the AVD a name if you would like, then click Finish

7) The SAR mobile client is now ready for development and testing.
 
 ---
## SERVER SETUP

    Note: Setup/Running assumes a Linux environment
    
    Note: A more detailed version of setup can be found in ~/SAR-Client/sar_server/README.md
    
    Note: A list of supported endpoints can be found in ~/SAR-Client/sar_server/end-points.md
    
---
### Quick Setup

1. Install *Ruby-2.5.1*
    
    - See *~SAR-Client/sar_server/README.md* for under section **Setting-up Ruby**, and sub-section **Installing Ruby via RVM (Linux)** for more detail via [Ruby Version Manager](https://rvm.io)
       
2. Run `/bin/bash --login` inside a terminal

    - All following steps should be run from the same terminal shell.

3. Run `rvm use 2.5.1`

4. Install [Bundler](https://bundler.io) by running `gem install bundler`
    
    - See *~/SAR-Client/sar_server/README.md* for under section **Setting-up Ruby**, and sub-section **Installing Gem Set via Bundler** for more detail

5. `cd` into *~/SAR-Client/sar_server*

6. Run `bundle install`
    
    - You'll see a bunch of output, don't worry about it for the most part
    - If you get an error, try running `bundle update`
    - If `bundle update` *still* doesn't work, then something is wrong and will need to be fixed

7. Check that *development.sqlite3* exists at *~/SAR-Client/sar_server/db/development.sqlite3*
    
    - If *development.sqlite3* **DOES NOT** exist already, follow the steps in the below section: **DB Setup**

---
### Running the Server

1. Inside the bash shell above and from anywhere inside the *~/SAR-Client/sar_server* directory, run `export RAILS_ENV=development`

2. Continuing from the above step, run `rails s`

    - This will run the server in development mode, which is the only recommended mode in the current state.
    
    - The server can now be access from *localhost:3000*

---
### DB Setup

    Note: This needs to be done if and only if development.sqlite3 does not exist as mentioned above.
    
    Note: A more detailed version of these steps can be found in ~/SAR-Client/sar_server/README.md
    
    Note: If you run into any issues in the steps below, there is likely an issue that needs to be addressed.
    
Inside the bash shell above and from anywhere inside the *~/SAR-Client/sar_server* directory do the following steps:

1. If you haven't already, run `export RAILS_ENV=development`

2. Run `rake db:create`

3. Run `rake db:migrate`

4. Run `rake db:seed`