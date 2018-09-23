# MessageEncryptor
Securely send private data with your friends, with your own passcode!
This application does not require any internet connections after first run.
The messages are encrypted with AES 256, which is one of the most secure encrypting technology in the world.
* The program name is DRMessageEncryptor when you execute it.

# Supported Desktop OS
Any OS with Java runtime installed are supported! (Windows, Mac, and all Linux distros)
- For Windows: Able to use either Universal and EXE version
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
- [YES] 1.7
- [NO] 1.6
- [NO] 1.5
- [NO] 1.4
- [NO] 1.3
- [NO] 1.21
- [NO] 1.2
- [NOSIGN] 1.1 / 1.0

# Desktop app preference manual
To change the preference, follow these steps.
1. Press option button. You will see the current preference.
2. Copy the text. 
3. Type /apply [option] and replace the option with the copied text.
4. Modify true / false
5. Press option button again.

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

1.6 (Jar / Mac)    THIS VERSION HAS A CRITICAL ERROR. WE STRONGLY RECOMMEND NOT TO USE THIS VERSION.
- New encryption engine (Double encryption)
    - Security strengthened
- New header
- **BUG FOUND**

1.7 (Jar / Mac)
- New encryption engine (Triple encryption)
    - 1.6 Decryption bug fixed
- New header
- Available to change some preferences (See the manual)
- Optional update at launch
- Emergency auto-downgrade
    - If there is a critical error, then the program will automatically downgrade to the latest signed version.

1.7 (Windows)
- Now fully supports windows exe!

# Mobile Update Log
1.0 (Android)
- Initial Release
- This release contains: Encrypt and decrypt text with custom passcode.

1.1 (Android)
- Changed icon

1.2 (Android)
- Added toast messages

1.3 (Android)
- Toast message bug fixed

# Known bugs
- 1.6 Desktop
    - Major bug: Users are not able to decrypt with new engine.

- 1.7 Desktop
    - Minor bug: If the user choose "No" on the launch update screen, then the program will ask the user again.
