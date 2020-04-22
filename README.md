![Java Logo](img/java-logo.png)
# Arisen's Java SDK
Arisen's Java SDK (software development kit) is an API for integrating with Arisen-based blockchains using the Arisen's RPC-based API. For a high-level introduction to our Swift and Java SDKs, please refer to the official release notes [here](https://medium.com/arisencoin/arisen-native-sdks-for-swift-and-java).

To date, Arisen's Java SDK has only been tested on Android. The goal, however, is for the core library to run anywhere Java runs, adding other targets (server, desktop, embedded) as the library matures.

***All product and company names are trademarks or registered trademarks of their respective holders. Use of them does not imply any affiliation with or endorsement by them.***

## Table Of Contents 
- [Installation](#installation)
- [Helpful Utilities](#helpful-utilities)
- [Basic Usage](#basic-usage)
- [Android Example App](#android-example-app)
- [Provider Interface Architecture](#provider-interface-architecture)
- [Want to Help?](#want-to-help)
- [License](#license)
- [Disclaimer](#disclaimer)

## Installation
### Prerequisites 
- Java JDK 1.8+ (1.7 source compatibility is targeted)
- Gradle 4.10.1+
- For Android, Android 6 (Marshmallow)+

* **Note:** Android 6 (Marshmallow) was selected as the minimum target level due to Keystore security concerns in older versions of Android.

Since Arisen's Java SDK is not an Android specific project, we recommend using IntelliJ if you are going to work on it. You can use Android Studio but be aware that some of the menu options under Build like ```Rebuild Project``` and ```Clean Project``` will not work correctly. You may still compile within Android Studio using ```Make Project``` under the Build menu, or by using Gradle from the command line.

### Instructions
To use Arisen's Java SDK in your app, add the following modules to your build.gradle file:
```
implementation 'satoshi.labs:arisenjava:0.1.0'
implementation 'satoshi.labs:arisenjavasoftkeysignatureprovider:0.1.0'
implementation 'satoshi.labs:arisenjavaandroidabirsnserializationprovider:0.1.0'
implementation 'satoshi.labs:arisenjavarpcprovider:0.1.0'
```

For developers who are utilizing Arisen's Java SDK or any Arisen Java SDK dependency within an Android-based application, the following must be added to your Android application's ```build.gradle``` file in the ```android``` section:

```
// Needed to get bitcoin-j to produce a valid apk for Android
packagingOptions {
       exclude 'lib/x86_64/darwin/libscrypt.dylib'
       exclude 'lib/x86_64/freebsd/libscrypt.so'
       exclude 'lib/x86_64/linux/libscrypt.so'
}
```

The ```build.gradle``` files for your project currently include configurations for publishing the project to Artifactory and Bintray which should be removed if you're Artifactory or BinTray are not being used in your project. Failure to remove these will result in several different build errors. 

* **Note:** To remove Artifactory or BinTray configurations, make the changes marked by comments throughout the files. Then refresh your gradle project. Then you will be ready for the [Basic Usage](#basic-usage) example.

## Helpful Utilities
One of the most complicated and time consuming tasks about encryption can be figuring out how to transform keys into a format that works on the target blockchain. This library includes two utilities that make that process painless. The ```RSNFormatter``` and ```PEMProcessor``` classes include methods that allow you to convert PEM or DER encoded public or private keys to and from the standard Arisen formats. The ```PEMProcessor``` wraps a key and gives you the ability to extract the type, the DER format, the algorithm used to generate the key, and to perform a checksum validation. The ```RSNFormatter``` formats transactions into an Arisen compliant format as well. There is an abundance of documentation on the Internet about converting keys and signatures to a DER encoded or PEM (Privacy Enhanced Mail) format. If you can get your key into one of these formats we provide a simple transition to the Arisen format.

## Basic Usage
Transactions are instantiated via a ```TransactionSession()``` which must be configured with a number of providers and a ```TransactionProcessor()```, which manipulates and performs actions on a Transaction, prior to use. The code below show a very barebones flow. Error handling has been omitted for clarity but should be handled in normal usage. (See [Provider Interface Architecture](#provider-interface-architecture) below for more information about providers.)

```
IRPCProvider rpcProvider = new ArisenJavaRpcProviderImpl("https://baseurl.com");
ISerializationProvider serializationProvider = new AbiRsnSerializationProvider();
IABIProvider abiProvider = new ABIProviderImpl(rpcProvider, serializationProvider);
ISignatureProvider signatureProvider = new SoftKeySignatureProviderImpl();

signatureProvider.importKey(privateKeyK1RSN);
// or...
signatureProvider.importKey(privateKeyR1RSN);

TransactionSession session = new TransactionSession (
             serializationProvider,
             rpcProvider,
             abiProvider,
             signatureProvider
);

TransactionProcessor processor = session.getTransactionProcessor();

String jsonData = "{\n" +
           "\"from\": \"person1\", \n" +
           "\"to\": \"person2\", \n" +
           "\"quantity\": \"10.0000 RSN\", \n" +
           "\"memo\" : \"Something\"\n" +
           "}";

List<Authorization> authorizations = new ArrayList<>();
authorizations.add(new Authorization("myaccount", "active"));
List<Action> actions = new ArrayList<>();
actions.add(new Action("arisen.token", "transfer", authorizations, jsonData)

processor.prepare(actions);

PushTransactionResponse pushTransactionResponse = processor.signAndBroadcast();
``` 

## Android Example App
If you'd like to see Arisen's Java SDK in action, check out our open source [Android-Example-App](https://github.com/arisenio/android-example-app). This example Android application is a working application that fetches an account's RSN balance and pushes a ```transfer``` action.

## Provider Interface Architecture
The core Arisen Java SDK library uses a provider-interface-driven architecture to provide maximum flexibility in a variety of environments and use cases. ```TransactionSession``` and ```TransactionProcessor``` leverages those providers to prepare and process transactions. Arisen's Java SDK exposes four interfaces. You, the developer, get to choose which conforming implementations to use.

### Signature Provider Protocol
The Signature Provider abstraction is arguably the most useful of all the providers. It is responsible for a) finding out what keys are available for signing and b) requesting and obtaining transaction signatures with a subset of the available keys. 

By simply switching out the signature provider on a transaction, signature requests can be routed any number of ways. Need a signature from keys in the platform's Keystore or hardware backed security module such as Titan M? Configure the ```TransactionSession``` with a conforming signature provider that exposes that functionality. Need signatures from a wallet on the user's device? A signature provider can do that too!

Arisen's Java SDK ***does not include***  a signature provider implementation; one must be installed separately.

One could use ISignatureProvider or Softkey Signature Provider (Example signature provider for signing transactions using SECP256R1 and SECP256R1 keys in memory.*
 
*** *Softkey Signature Provider stores keys in memory and is therefore not secure. It should only be used for development purposes. In production, we strongly recommend using a signature provider that interfaces with a secure vault, authenticator or wallet.***

### RPC Provider Protocol
The RPC Provider is responsible for all RPC calls to aOS (ArisenOS), as well as general network handling (offline detection, retry logic, etc.)

Arisen's Java SDK ***does not include*** an RPC provider implementation; one must be installed separately. 

Some RPC provider implementations to consider:

- IRPCProvider
- Default RPC Provider - Currently supports Android 6 (Marshmallow)+

You can also view the [aOS RPC Reference Documentation](https://developers.arisen.network/#)

*** *Alternate RPC providers can be used assuming they conform to the minimal RPC Provider Interface. The core Arisen Java SDK library depends on the five RPC endpoints set forth in that Interface. Other endpoints, however, are planned to be exposed in the default RPC provider.***

### Serialization Provider Protocol
The Serialization Provider is responsible for ABI-driven transaction and action serialization and deserialization between JSON and binary data representations. These implementations often contain platform-sensitive C++ code and larger dependencies. For those reasons, Arisen's Java SDK ***does not include*** a serialization provider implementation; one must be installed separately.

Some Serialization provider implementations to consider:
- ISerializationProvider
- [ABIRSN Serialization Provider Implementation](https://github.com/arisenio/abirsn-serialization-provider-implementation) - Currently supports Android 6 (Marshmallow)+

### ABI Provider Protocol
The ABI Provider is responsible for fetching and caching ABIs for use during serialization and deserialization. One must be explicitly  set on the ```TransactionSession``` with the other providers. Arisen's Java SDK provides a default ABIProviderImpl that can be used (The default implementation suffices for most use cases).

If it does not suffice for your particular use-case, please consider the following ABI provider implementation:
- IABIProvider

## Want To Help?
Interested in contributing to Arisen's Java SDK? Please view our [Contribution Guidelines](CONTRIBUTING.md) and [Code Of Conduct](CODE-OF-CONDUCT.md).

We're always looking for ways to improve Arisen's Java SDK. 

## License
[MIT](LICENSE.md)

(C) 2019 - Block.One
(C) 2020 - Arisen Foundation
(C) 2020 - Satoshi Labs

* Originally developed by blockone-devops. This library has officially been adopted by Arisen Foundation, Satoshi Labs and the Arisen community. 

## Disclaimer
See LICENSE for copyright and license terms. Arisen Foundation and Satoshi Labs make their contribution on a voluntary basis as a member of the Arisen community and is not responsible for ensuring the overall performance of the software or any related applications. We make no representation, warranty, guarantee or undertaking in respect to the software or any related documentation, whether expressed or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall we be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or documentation. Any test results or performance figures are indicative and will not reflect performance under all conditions. Any reference to any third party or third-party product, service or other resource is not an endorsement or recommendation by Arisen Foundation or Satoshi Labs. We are not responsible, and disclaim any and all responsibility and liability, for your use of or reliance on any of these resources. Third-party resources may be updated, changed or terminated at any time, so the information here may be out of date or inaccurate. Any person using or offering this software in connection with providing software, goods or services to third parties shall advise such third parties of these license terms, disclaimers and exclusions of liability. Arisen Foundation, Arisen, Arisen Labs, Satoshi Labs, RSN, the Arisen logo and associated logos are trademarks of Arisen Foundation.

Wallets and related components  are complex software that requires the highest levels of security. If incorrectly built or used, they may compromised users' private keys and digital assets. Wallet applications and related components should undergo thorough security evaluations before being used. Only experienced developers should work with this software.