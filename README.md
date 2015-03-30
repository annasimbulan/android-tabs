# Android: Using Navigation Tabs
This applications displays 2 tabs: a map view on the first tab and a list view on the second tab

For this application to work, the following changes need to be done:

**1. Get an Android certificate**

A debug certificate can be used.

For Linux or OS X, open a terminal window and enter the following:

`keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android`

For Windows Vista and Windows 7, run:

`keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android`

Take note of the SHA-1 fingerprint.

**2. Create an API project in the Google APIs Console**

Navigate to this [link](https://console.developers.google.com/) to create an API project.

**3. Obtain a Google Maps API key**

From the project created in step 2, navigate to APIs & auth > Credentials. 

Enter the certificate fingerprints and package names similar to this: `F9:DB:59:FD:EF:2A:DC:63:3D:AC:B2:CD:BD:09:67:B7:88:EB:DC:A9;simbulan.androidtabs`

**4. Add the API key to your application**

In the *AndroidManifest.xml*, add the API key generated in step 3 as a meta-data:
```
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="API_KEY"/>
```
