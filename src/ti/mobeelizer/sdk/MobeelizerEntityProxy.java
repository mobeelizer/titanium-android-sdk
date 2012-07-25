package ti.mobeelizer.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerFile;

@Kroll.proxy
public class MobeelizerEntityProxy extends KrollProxy {

    private String model;

    private String guid;

    private String owner;
    
    private String group;

    private boolean conflicted;

    private boolean modified = true;

    private boolean deleted;

    private KrollDict fields = new KrollDict();

    public MobeelizerEntityProxy(final String model) {
        this.model = model;
    }

    public MobeelizerEntityProxy(final Map<String, Object> map) {
        updateFromMap(map);
    }

    @Kroll.method
    public String getModel() {
        return model;
    }

    @Kroll.method
    public void setModel(final String model) {
        this.model = model;
    }

    @Kroll.method
    public String getGuid() {
        return guid;
    }

    @Kroll.method
    public void setGuid(final String guid) {
        this.guid = guid;
    }

    @Kroll.method
    public String getOwner() {
        return owner;
    }

    @Kroll.method
    public void setOwner(final String owner) {
        this.owner = owner;
    }
    
    @Kroll.method
    public String getGroup() {
        return group;
    }

    @Kroll.method
    public void setGroup(final String group) {
        this.group = group;
    }

    @Kroll.method
    public boolean isConflicted() {
        return conflicted;
    }

    @Kroll.method
    public void setConflicted(final boolean conflicted) {
        this.conflicted = conflicted;
    }

    @Kroll.method
    public boolean isModified() {
        return modified;
    }

    @Kroll.method
    public void setModified(final boolean modified) {
        this.modified = modified;
    }

    @Kroll.method
    public boolean isDeleted() {
        return deleted;
    }

    @Kroll.method
    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    @Kroll.method
    public Object getField(final String name) {
        return this.fields.get(name);
    }

    @Kroll.method
    public void putField(final String name, final Object value) {
        this.fields.put(name, value);
    }

    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new HashMap<String, Object>(fields);
        for (Entry<String, Object> field : fields.entrySet()) {
            if (field.getValue() instanceof MobeelizerFileProxy) {
                MobeelizerFileProxy file = (MobeelizerFileProxy) field.getValue();
                map.put(field.getKey(), file.getAsMobeelizerFile());
            } else {
                map.put(field.getKey(), field.getValue());
            }
        }
        map.put("model", getModel());
        map.put("guid", getGuid());
        map.put("owner", getOwner());
        map.put("group", getGroup());
        map.put("conflicted", isConflicted());
        map.put("modified", isModified());
        map.put("deleted", isDeleted());
        return map;
    }

    public void updateFromMap(final Map<String, Object> map) {
        this.model = readString("model", map);
        this.guid = readString("guid", map);
        this.owner = readString("owner", map);
        this.group = readString("group", map);
        this.conflicted = readBoolean("conflicted", map);
        this.modified = readBoolean("modified", map);
        this.deleted = readBoolean("deleted", map);
        fields = new KrollDict();
        for (Entry<String, Object> field : map.entrySet()) {
            if (field.getValue() instanceof MobeelizerFile) {
                MobeelizerFile file = (MobeelizerFile) field.getValue();
                fields.put(field.getKey(), new MobeelizerFileProxy(file));
            } else {
                fields.put(field.getKey(), field.getValue());
            }
        }

    }

    @Override
    @Kroll.method
    public String toString() {
        return getAsMap().toString();
    }

    private String readString(final String name, final Map<String, Object> map) {
        String value = map.get(name).toString();
        map.remove(name);
        return value;
    }

    private boolean readBoolean(final String name, final Map<String, Object> map) {
        boolean value = Boolean.parseBoolean(map.get(name).toString());
        map.remove(name);
        return value;
    }

}
