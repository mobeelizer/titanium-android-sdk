package ti.mobeelizer.sdk;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerError;
import com.mobeelizer.java.api.MobeelizerErrors;

@Kroll.proxy
public class MobeelizerErrorsProxy extends KrollProxy {

    private final MobeelizerErrors errors;

    public MobeelizerErrorsProxy(final MobeelizerErrors errors) {
        this.errors = errors;
    }

    @Kroll.method
    public MobeelizerErrorProxy[] getGlobalErrors() {
        return convertToProxyErrors(errors.getGlobalErrors());
    }

    @Kroll.method
    public boolean isFieldValid(final String field) {
        return errors.isFieldValid(field);
    }

    @Kroll.method
    public MobeelizerErrorProxy[] getFieldErrors(final String field) {
        return convertToProxyErrors(errors.getFieldErrors(field));
    }

    @Kroll.method
    public String[] getInvalidFields() {
        Set<String> result = errors.getInvalidFields();
        return result.toArray(new String[result.size()]);
    }

    @Override
    @Kroll.method
    public String toString() {
        return errors.toString();
    }

    private MobeelizerErrorProxy[] convertToProxyErrors(final List<MobeelizerError> errors) {
        List<MobeelizerErrorProxy> result = new LinkedList<MobeelizerErrorProxy>();
        for (MobeelizerError error : errors) {
            result.add(new MobeelizerErrorProxy(error));
        }
        return result.toArray(new MobeelizerErrorProxy[result.size()]);
    }

}
