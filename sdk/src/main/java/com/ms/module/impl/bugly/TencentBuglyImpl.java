package com.ms.module.impl.bugly;

import com.ms.module.supers.client.Modules;
import com.ms.module.supers.inter.bugly.ITencentBuglyAdapter;
import com.tencent.bugly.crashreport.CrashReport;


public class TencentBuglyImpl extends ITencentBuglyAdapter {

    @Override
    public void init() {
        try {


            CrashReport.initCrashReport(Modules.getDataModule().getAppData().getApplication());
            String packageName = Modules.getUtilsModule().getApkUtils().getPackageName();
            // 获取当前进程名
            String processName = Modules.getUtilsModule().getSystemUtils().getProcessName();
            // 设置是否为上报进程
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(Modules.getDataModule().getAppData().getApplication());
            strategy.setUploadProcess(processName == null || processName.equals(packageName));
            // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
            CrashReport.initCrashReport(Modules.getDataModule().getAppData().getApplication(), strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
