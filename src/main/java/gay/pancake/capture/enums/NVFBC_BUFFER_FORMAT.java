package gay.pancake.capture.enums;

/**
 * Buffer format.
 */
public interface NVFBC_BUFFER_FORMAT {

    /**
     * Data will be converted to ARGB8888 byte-order format. 32 bpp.
     */
    int NVFBC_BUFFER_FORMAT_ARGB = 0;

    /**
     * Data will be converted to RGB888 byte-order format. 24 bpp.
     */
    int NVFBC_BUFFER_FORMAT_RGB = 1;

    /**
     * Data will be converted to NV12 format using HDTV weights
     * according to ITU-R BT.709.  12 bpp.
     */
    int NVFBC_BUFFER_FORMAT_NV12 = 2;

    /**
     * Data will be converted to YUV 444 planar format using HDTV weights
     * according to ITU-R BT.709.  24 bpp
     */
    int NVFBC_BUFFER_FORMAT_YUV444P = 3;

    /**
     * Data will be converted to RGBA8888 byte-order format. 32 bpp.
     */
    int NVFBC_BUFFER_FORMAT_RGBA = 4;

    /**
     * Native format. No pixel conversion needed.
     * BGRA8888 byte-order format. 32 bpp.
     */
    int NVFBC_BUFFER_FORMAT_BGRA = 5;

    int NVFBC_BUFFER_FORMAT_YUV420P = NVFBC_BUFFER_FORMAT_NV12;

}
