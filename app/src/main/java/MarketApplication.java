import android.app.Application;

import com.alinge.software.market.net.VolleyUtils;

/**
 * 作者： yejianlin
 * 日期：2016/1/14
 * 作用：
 */
public class MarketApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtils.createVolleyUtils(this);
    }
}
