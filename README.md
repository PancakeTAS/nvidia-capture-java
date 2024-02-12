# NVIDIA Capture SDK for Java
This project creates bindings to java for the NVIDIA Capture SDK.
## How to install
In order to use this library, you need to download [NVIDIA Capture SDK](https://developer.nvidia.com/capture-sdk) and copy the contained dll into src/main/resources/win32-x86-64 of either this project or the one you wish to use this in.
Please note that NvFBC is deprecated on windows and disabled on consumer cards on linux. To work around this, please check out [nvidia-patch](https://github.com/keylase/nvidia-patch)
## How to use
Refer to the [NVIDIA Capture SDK](https://developer.nvidia.com/capture-sdk) documentation for more information on how to use the library. The java function is a direct mapping to the capture sdk included header file.
