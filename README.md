# MessageEncryptor
Securely send private data with your friends, with your own passcode!
This application does not require any internet connections.
The messages are encrypted with AES 256, which is one of the most secure encrypting technology in the world.
* The program name is DRMessageEncryptor when you execute it.

# Supported Desktop OS
Any OS with Java runtime installed are supported! (Windows, Mac, and all Linux distros)
- For Windows: Download Universal
- For macOS: Able to use either Universal and Mac Only version (app format)
- For Linux: Download Universal

# Included packages for Desktop
This program includes 3 external packages:
- commons-codec-1.11
- DreamOS Launch Platform 0.9.5 For MessageEncryptor
- AES 256 Java code from http://aesencryption.net.

# Requirements for Desktop
Java runtime is required! (Not Java Development Kit)
Download Java runtime at: http://java.com

# Desktop app error codes
- VERF_FAIL_SIGFILE_EMPTY
    - Signature file is empty
- VERF_FAIL_SIGFILE_NOSIGFOUND
    - Signature not found
- VERF_FAIL_SIGFILE_VERNOTMATCH
    - Signature version does not match
- VERF_FAIL_SIGFILE_UNVERF
    - Unverified software
- VERF_FAIL_SIGFILE_UNSIG
    - Unsigned software
    - No longer supported version


# Supported Mobile OS
Android is now available!
Go to the Android folder in the master branch.

# Included packages for Mobile
- None

# Requirements for Mobile
- Android 4.1 or above

# Update Log
1.0 (Android/Universal/Mac)
- Initial release
- This release contains: Encrypt and decrypt text with custom passcode.

1.1 (Universal/Mac)
- Added scroller
- Added About this program
- Added auto line breaking

1.2 (Windows / Universal / Mac)
- Added Update system
- Added software verification system

1.21 (Windows / Universal / Mac)
- Stabilized Update system
- Fixed NullPointerException
