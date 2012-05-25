package ti.mobeelizer.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import com.mobeelizer.java.api.MobeelizerFile;
import com.mobeelizer.mobile.android.MobeelizerFileImpl;

@Kroll.proxy
public class MobeelizerFileProxy extends KrollProxy {

    private final String name;

    private final String guid;

    private TiBlob stream;

    private final File originalFile;

    public MobeelizerFileProxy(final MobeelizerFile mobeelizerFile) {
        name = mobeelizerFile.getName();
        guid = mobeelizerFile.getGuid();
        originalFile = mobeelizerFile.getFile();
    }

    MobeelizerFile getAsMobeelizerFile() {
        return new MobeelizerFileImpl(name, guid);
    }

    @Kroll.method
    public String getName() {
        return name;
    }

    @Kroll.method
    public String getGuid() {
        return guid;
    }

    @Kroll.method
    public TiBlob getStream() {
        if (stream == null) {
            readStream();
        }
        return stream;
    }

    @Override
    @Kroll.method
    public String toString() {
        return "MobeelizerFile: {name=" + name + ", guid=" + guid + "}";
    }

    private void readStream() {
        InputStream is = null;
        try {
            is = new FileInputStream(originalFile);
            long length = originalFile.length();
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }
            byte[] bytes = new byte[(int) length];

            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + name);
            }

            stream = TiBlob.blobFromData(bytes);

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                // ignore
            }
        }
    }

}
