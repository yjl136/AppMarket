package com.alinge.software.market.net.bean;

import com.alinge.software.market.net.ResultParse;
import com.alinge.software.market.net.utils.FieldUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：
 */
public class AppInfo implements ResultParse{

    private String AppIcon;
    private  double AvgScore;
    private String Developer;
    private String DownUrl;
    private  int DownloadCount;
    private String PackageName;

    private  int Id;
    private  int Toll;
    private  int InstallType;
    private String Md5Code;
    private String SoftwareName;
    private String Version;
    private String FileSize;

    public AppInfo() {
    }

    public AppInfo(String appIcon, double avgScore, String developer, String downUrl, int downloadCount, String packageName, int id, int installType, int toll, String softwareName, String md5Code, String version, String fileSize) {
        AppIcon = appIcon;
        AvgScore = avgScore;
        Developer = developer;
        DownUrl = downUrl;
        DownloadCount = downloadCount;
        PackageName = packageName;
        Id = id;
        InstallType = installType;
        Toll = toll;
        SoftwareName = softwareName;
        Md5Code = md5Code;
        Version = version;
        FileSize = fileSize;
    }

    public String getAppIcon() {
        return AppIcon;
    }

    public void setAppIcon(String appIcon) {
        AppIcon = appIcon;
    }

    public double getAvgScore() {
        return AvgScore;
    }

    public void setAvgScore(double avgScore) {
        AvgScore = avgScore;
    }

    public String getDeveloper() {
        return Developer;
    }

    public void setDeveloper(String developer) {
        Developer = developer;
    }

    public int getDownloadCount() {
        return DownloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        DownloadCount = downloadCount;
    }

    public String getDownUrl() {
        return DownUrl;
    }

    public void setDownUrl(String downUrl) {
        DownUrl = downUrl;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getToll() {
        return Toll;
    }

    public void setToll(int toll) {
        Toll = toll;
    }

    public int getInstallType() {
        return InstallType;
    }

    public void setInstallType(int installType) {
        InstallType = installType;
    }

    public String getMd5Code() {
        return Md5Code;
    }

    public void setMd5Code(String md5Code) {
        Md5Code = md5Code;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getSoftwareName() {
        return SoftwareName;
    }

    public void setSoftwareName(String softwareName) {
        SoftwareName = softwareName;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {

        if(obj.has(FieldUtils.DownloadCount)){
            DownloadCount=obj.getInt(FieldUtils.DownloadCount);
        }
        if(obj.has(FieldUtils.Id)){
            Id=obj.getInt(FieldUtils.Id);
        }
        if(obj.has(FieldUtils.Toll)){
            Toll=obj.getInt(FieldUtils.Toll);
        }
        if(obj.has(FieldUtils.InstallType)){
            InstallType=obj.getInt(FieldUtils.InstallType);
        }


        if(obj.has(FieldUtils.SoftwareName)){
            SoftwareName=obj.getString(FieldUtils.SoftwareName);
        }
        if(obj.has(FieldUtils.Version)){
            Version=obj.getString(FieldUtils.Version);
        }
        if(obj.has(FieldUtils.FileSize)){
            FileSize=obj.getString(FieldUtils.FileSize);
        }

        if(obj.has(FieldUtils.PackageName)){
            PackageName=obj.getString(FieldUtils.PackageName);
        }

        if(obj.has(FieldUtils.Developer)){
            Developer=obj.getString(FieldUtils.Developer);
        }
        if(obj.has(FieldUtils.DownUrl)){
            DownUrl=obj.getString(FieldUtils.DownUrl);
        }
        if(obj.has(FieldUtils.Md5Code)){
            Md5Code=obj.getString(FieldUtils.Md5Code);
        }
        if(obj.has(FieldUtils.AppIcon)){
            AppIcon=obj.getString(FieldUtils.AppIcon);
        }
        if(obj.has(FieldUtils.AvgScore)){
            AvgScore=obj.getDouble(FieldUtils.AvgScore);
        }
    }
}
