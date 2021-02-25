The project is in the folder "LabBank". In this folder, if you want to see the files about:
- Gradle properties and the implementations : LabBank -> app -> build.gradle | url = https://github.com/samyb99/MobileAppDev-TD/blob/master/LabBank/app/build.gradle
- AndroidManifest.xml : LabBank -> app -> src -> main -> AndroidManifest.xml | url = https://github.com/samyb99/MobileAppDev-TD/blob/master/LabBank/app/src/main/AndroidManifest.xml
- The java classes : LabBank -> app -> src -> main -> java/com/example/labbank | url = https://github.com/samyb99/MobileAppDev-TD/tree/master/LabBank/app/src/main/java/com/example/labbank
- The layout xml : LabBank -> app -> src -> main -> res -> layout | url = https://github.com/samyb99/MobileAppDev-TD/tree/master/LabBank/app/src/main/res/layout

My application apk : https://github.com/samyb99/MobileAppDev-TD/blob/master/labbank.apk

Requirements
- This application must be available offline. (Done)
- A refresh button allows the user to update its accounts. (Done)
- Access to the application is restricted. (Done)
- Exchanges with API must be secure (with TLS) (Done)

1) Explain how you ensure user is the right one starting the app : 
To ensure that the user starting the application is the right, we do a double authentication. 
In fact, it is a mobile application, so, the first authentication is with the fingerprint registered of the mobile phone. Don't forget to register a fingerprint if you don't already have it on your phone else you couldn't access to the application.
Then, the user has to put his informations (name, last name) to do the second authentication. 
When he puts his informations, it will check if they are correct, and if it is the case, the user can finally access to the differents accounts.

2) How do you securely save user's data on your phone ?
We use the library Retrofit to make the API calling, and this library provide cache mechanism, by using it, the cache data is keeping into our application. So, when we will make any API call when our application is offline, the Retrofit cache data will be retrieve and will show the data to the user. 
Moreover, we add SharedPreferences to store the data (the bank accounts and the users). It is automatically set with the mode "MODE_PRIVATE" constant that means that the data can only be accessed by our application. By this way, the data is securely stored.

3) How did you hide the API url ?
First, how we connect to our API url ? For this, we use retrofit (it is a type-safe HTTP client for Android and Java), it allows to turn our HTTP API into a Java interface and then we can provide the request method (GET, POST, PUT, ...) and relative URL (here /config/{id}, /accounts, ...) that we want. 
But in this case, we only have an HTTP connection and not a secure HTTP. So, to secure the connection to our API (and by the way, hide the API url), we will add the protocol SSL/TLS.
For that, we use OkHttp that allows to have the connectivity to as many hosts as possible and mostly the security of the connection (by verificate the remote webserver with certificates) and the privacy of data exchanged with strong ciphers.
We use SSLContext that we implement with the specified secure socket protocol "TLS" and then that we initialize with an optional set of key (here key = null) and trust managers (here X509 certificates) and source of secure random bytes (here we use SecureRandom() that construct a secure random number with a random number algorithm).
After that, we use SSLSocketFactory to tunnel SSL through a proxy/or negotiate the use of SSL over an existing socket (here the sslContext).
And we create the OkHttp to exchange data in a secure way thanks to the SSL protocol.
To put it in simple terms, we create a client with OkHttp and we add to it the TLS/SSL protocol to secure our communications by ciphering the data exchanged (with algorithms, certificates, ...).

4) Screenshots of your application (See the pdf file in the repository)