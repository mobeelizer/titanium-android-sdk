package ti.mobeelizer.sdk;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerErrors;
import com.mobeelizer.mobile.android.api.MobeelizerDatabase;

@Kroll.proxy
public class MobeelizerDatabaseProxy extends KrollProxy {

    private final MobeelizerDatabase mobeelizerDatabase;

    private static final MobeelizerOrderProxy ORDER = new MobeelizerOrderProxy();

    private static final MobeelizerRestrictionProxy RESTRICTION = new MobeelizerRestrictionProxy();

    public MobeelizerDatabaseProxy(final MobeelizerDatabase mobeelizerDatabase) {
        this.mobeelizerDatabase = mobeelizerDatabase;
    }

    @Kroll.method
    public MobeelizerEntityProxy entity(final String model) {
        return new MobeelizerEntityProxy(model);
    }

    @Kroll.method
    public long count(final String model) {
        return mobeelizerDatabase.count(model);
    }

    @Kroll.method
    public MobeelizerErrorsProxy remove(final String model, final String guid) {
        return createResultProxy(mobeelizerDatabase.delete(model, guid));
    }

    @SuppressWarnings("unchecked")
    @Kroll.method
    public MobeelizerErrorsProxy removeObject(final MobeelizerEntityProxy entity) {
        return createResultProxy(mobeelizerDatabase.deleteMap(entity.getAsMap()));
    }

    @Kroll.method
    public MobeelizerErrorsProxy removeAll(final String model) {
        return createResultProxy(mobeelizerDatabase.deleteAll(model));
    }

    @Kroll.method
    public boolean exists(final String model, final String guid) {
        return mobeelizerDatabase.exists(model, guid);
    }

    @Kroll.method
    public MobeelizerCriteriaBuilderProxy find(final String model) {
        return new MobeelizerCriteriaBuilderProxy(mobeelizerDatabase.find(model));
    }

    @Kroll.method
    public MobeelizerEntityProxy findOne(final String model, final String guid) {
        Map<String, Object> result = mobeelizerDatabase.getAsMap(model, guid);
        if (result == null) {
            return null;
        }
        return new MobeelizerEntityProxy(result);
    }

    @Kroll.method
    public MobeelizerEntityProxy[] list(final String model) {
        List<MobeelizerEntityProxy> result = new LinkedList<MobeelizerEntityProxy>();
        for (Map<String, Object> entity : mobeelizerDatabase.listAsMaps(model)) {
            result.add(new MobeelizerEntityProxy(entity));
        }
        return result.toArray(new MobeelizerEntityProxy[result.size()]);
    }

    @Kroll.method
    public MobeelizerErrorsProxy save(final MobeelizerEntityProxy entity) {
        Map<String, Object> entityAsMap = entity.getAsMap();
        MobeelizerErrors result = mobeelizerDatabase.save(entityAsMap);
        if (result == null) {
            entity.updateFromMap(entityAsMap);
        }
        return createResultProxy(result);
    }

    @Kroll.getProperty(name = "Order")
    public MobeelizerOrderProxy getOrder() {
        return ORDER;
    }

    @Kroll.getProperty(name = "Restriction")
    public MobeelizerRestrictionProxy getRestriction() {
        return RESTRICTION;
    }

    private MobeelizerErrorsProxy createResultProxy(final MobeelizerErrors result) {
        if (result != null) {
            return new MobeelizerErrorsProxy(result);
        }
        return null;
    }
}
