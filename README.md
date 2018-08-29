# MessageEncryptor
Securely send private data with your friends, with your own passcode!
This application does not require any internet connections after first run.
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

# Version signing status
- [YES] 1.6
- [YES] 1.5
- [NO] 1.4
- [NO] 1.3
- [NO] 1.21
- [NO] 1.2
- [NOSIGN] 1.1 / 1.0

# Desktop Update Log
1.0 (Universal/Mac)
- Initial release
- This release contains: Encrypt and decrypt text with custom passcode.

1.1 (Universal/Mac)
- Added scroller
- Added About this program
- Added auto line breaking

1.2 (Jar / Mac)
- Added Update system
- Added software verification system

1.21 (Jar / Mac)
- Stabilized Update system
- Fixed NullPointerException

1.3 (Jar / Mac)
- Enhanced signing security
- Added messages in between the buttons (PicoMessage)
	- Encrypt / Decrypt successful
	- Wrong passcode
	- Downloading update
	- No header found

1.4 (Jar / Mac)
- Fixed PicoMessage error
- Fixed signature server access address mistake

1.5 (Jar / Mac)
- Added AutoUpdate at launch

1.6 (Jar / Mac)
- New encryption engine (Double encryption)
    - Security strengthened
- New header

# Mobile Update Log
1.0 (Android)
- Initial Release
- This release contains: Encrypt and decrypt text with custom passcode.

1.1 (Android)
- Changed icon

1.2 (Android)
- Added toast messages
