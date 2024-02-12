package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCDestroyCaptureSession() API call.
 */
public class NVFBC_DESTROY_CAPTURE_SESSION_PARAMS extends Structure {
    public NVFBC_DESTROY_CAPTURE_SESSION_PARAMS() { super(); }
    public NVFBC_DESTROY_CAPTURE_SESSION_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_DESTROY_CAPTURE_SESSION_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_DESTROY_CAPTURE_SESSION_PARAMS(), 1);

    /**
     * [in] Must be set to NVFBC_DESTROY_CAPTURE_SESSION_PARAMS_VER
     */
    public int dwVersion = NVFBC_DESTROY_CAPTURE_SESSION_PARAMS_VER;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion");
    }

}
