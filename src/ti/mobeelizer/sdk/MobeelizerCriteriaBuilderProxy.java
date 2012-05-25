package ti.mobeelizer.sdk;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.mobile.android.api.MobeelizerCriteriaBuilder;

@Kroll.proxy
public class MobeelizerCriteriaBuilderProxy extends KrollProxy {

    private final MobeelizerCriteriaBuilder<Map<String, Object>> criteriaBuilder;

    public MobeelizerCriteriaBuilderProxy(final MobeelizerCriteriaBuilder<Map<String, Object>> criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    @Kroll.method
    public long count() {
        return criteriaBuilder.count();
    }

    @Kroll.method
    public MobeelizerEntityProxy[] list() {
        List<MobeelizerEntityProxy> result = new LinkedList<MobeelizerEntityProxy>();
        for (Map<String, Object> entity : criteriaBuilder.listAsMaps()) {
            result.add(new MobeelizerEntityProxy(entity));
        }
        return result.toArray(new MobeelizerEntityProxy[result.size()]);
    }

    @Kroll.method
    public MobeelizerEntityProxy uniqueResult() {
        Map<String, Object> uniqueResult = criteriaBuilder.uniqueResultAsMap();
        if (uniqueResult != null) {
            return new MobeelizerEntityProxy(uniqueResult);
        }
        return null;
    }

    @Kroll.method
    MobeelizerCriteriaBuilderProxy setMaxResults(final int maxResults) {
        criteriaBuilder.setMaxResults(maxResults);
        return this;
    }

    @Kroll.method
    MobeelizerCriteriaBuilderProxy setFirstAndMaxResults(final int firstResult, final int maxResults) {
        criteriaBuilder.setFirstResult(firstResult).setMaxResults(maxResults);
        return this;
    }

    @Kroll.method
    public MobeelizerCriteriaBuilderProxy addOrder(final MobeelizerOrderProxy order) {
        criteriaBuilder.addOrder(order.getMobeelizerOrder());
        return this;
    }

    @Kroll.method
    public MobeelizerCriteriaBuilderProxy add(final withGetMobeelizerCriterion restriction) {
        criteriaBuilder.add(restriction.getMobeelizerCriterion());
        return this;
    }

}
