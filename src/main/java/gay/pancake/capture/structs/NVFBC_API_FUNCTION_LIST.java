package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_VERSION;

/**
 * Structure populated with API function pointers.
 */
public class NVFBC_API_FUNCTION_LIST extends Structure {
    public NVFBC_API_FUNCTION_LIST() { super(); }
    public NVFBC_API_FUNCTION_LIST(Pointer p) { super(p); }

    /** [in] Must be set to NVFBC_VERSION. */
    public int dwVersion = NVFBC_VERSION;
    /** [out] Pointer to ::NvFBCGetLastErrorStr(). */
    public Pointer nvFBCGetLastErrorStr;
    /** [out] Pointer to ::NvFBCCreateHandle(). */
    public Pointer nvFBCCreateHandle;
    /** [out] Pointer to ::NvFBCDestroyHandle(). */
    public Pointer nvFBCDestroyHandle;
    /** [out] Pointer to ::NvFBCGetStatus(). */
    public Pointer nvFBCGetStatus;
    /** [out] Pointer to ::NvFBCCreateCaptureSession(). */
    public Pointer nvFBCCreateCaptureSession;
    /** [out] Pointer to ::NvFBCDestroyCaptureSession(). */
    public Pointer nvFBCDestroyCaptureSession;
    /** [out] Pointer to ::NvFBCToSysSetUp(). */
    public Pointer nvFBCToSysSetUp;
    /** [out] Pointer to ::NvFBCToSysGrabFrame(). */
    public Pointer nvFBCToSysGrabFrame;
    /** [out] Retired. Do not use. */
    public Pointer pad1;
    /** [out] Retired. Do not use. */
    public Pointer pad2;
    /** [out] Retired. Do not use. */
    public Pointer pad3;
    /** [out] Pointer to ::NvFBCBindContext(). */
    public Pointer nvFBCBindContext;
    /** [out] Pointer to ::NvFBCReleaseContext(). */
    public Pointer nvFBCReleaseContext;
    /** [out] Retired. Do not use. */
    public Pointer pad4;
    /** [out] Retired. Do not use. */
    public Pointer pad5;
    /** [out] Retired. Do not use. */
    public Pointer pad6;
    /** [out] Retired. Do not use. */
    public Pointer pad7;
    /** [out] Pointer to ::nvFBCToGLSetup(). */
    public Pointer nvFBCToGLSetUp;
    /** [out] Pointer to ::nvFBCToGLGrabFrame(). */
    public Pointer nvFBCToGLGrabFrame;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "nvFBCGetLastErrorStr", "nvFBCCreateHandle", "nvFBCDestroyHandle", "nvFBCGetStatus", "nvFBCCreateCaptureSession", "nvFBCDestroyCaptureSession", "nvFBCToSysSetUp", "nvFBCToSysGrabFrame", "pad1", "pad2", "pad3", "nvFBCBindContext", "nvFBCReleaseContext", "pad4", "pad5", "pad6", "pad7", "nvFBCToGLSetUp", "nvFBCToGLGrabFrame");
    }

}
