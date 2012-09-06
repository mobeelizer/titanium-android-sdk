package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerOperationError;

@Kroll.proxy
public class MobeelizerOperationErrorProxy extends KrollProxy {

    private final String code;

    private final String message;

    private final Object[] arguments;

    public MobeelizerOperationErrorProxy(final MobeelizerOperationError mobeelizerError) {
        code = mobeelizerError.getCode();
        arguments = mobeelizerError.getArguments().toArray();
        message = mobeelizerError.getMessage();
    }

    @Kroll.getProperty
    public String getCode() {
        return code;
    }

    @Kroll.getProperty
    public String getMessage() {
        return message;
    }

    @Kroll.getProperty
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    @Kroll.method
    public String toString() {
        return "MobeelizerOperationError: {code=" + code + ", message=" + message + "}";
    }
}
