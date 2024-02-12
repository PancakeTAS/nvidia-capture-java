package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCReleaseContext() API call.
 */
public class NVFBC_RELEASE_CONTEXT_PARAMS extends Structure {
    public NVFBC_RELEASE_CONTEXT_PARAMS() { super(); }
    public NVFBC_RELEASE_CONTEXT_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_RELEASE_CONTEXT_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_RELEASE_CONTEXT_PARAMS(), 1);

    /**
     * [in] Must be set to NVFBC_RELEASE_CONTEXT_PARAMS_VER
     */
    public int dwVersion = NVFBC_RELEASE_CONTEXT_PARAMS_VER;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion");
    }

}
