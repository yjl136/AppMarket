package com.alinge.software.market.net.bean;

import com.alinge.software.market.net.utils.FieldUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/12
 * 作用：
 */
public class QualityResultInfo extends Info {
    private int GiftsCount;
    private  String GiftsList;
    private int NewestCount;
    private String NewestList;
    private int RecommendCount;
    private String RecommendList;
    private String BannerList;

    public QualityResultInfo() {
    }

    public QualityResultInfo(String result, int giftsCount, String giftsList, int newestCount, String newestList, int recommendCount, String recommendList, String bannerList) {
        super(result);
        GiftsCount = giftsCount;
        GiftsList = giftsList;
        NewestCount = newestCount;
        NewestList = newestList;
        RecommendCount = recommendCount;
        RecommendList = recommendList;
        BannerList = bannerList;
    }

    public String getGiftsList() {
        return GiftsList;
    }

    public void setGiftsList(String giftsList) {
        GiftsList = giftsList;
    }

    public int getNewestCount() {
        return NewestCount;
    }

    public void setNewestCount(int newestCount) {
        NewestCount = newestCount;
    }

    public String getNewestList() {
        return NewestList;
    }

    public void setNewestList(String newestList) {
        NewestList = newestList;
    }

    public int getRecommendCount() {
        return RecommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        RecommendCount = recommendCount;
    }

    public String getRecommendList() {
        return RecommendList;
    }

    public void setRecommendList(String recommendList) {
        RecommendList = recommendList;
    }

    public String getBannerList() {
        return BannerList;
    }

    public void setBannerList(String bannerList) {
        BannerList = bannerList;
    }

    public int getGiftsCount() {
        return GiftsCount;
    }

    public void setGiftsCount(int giftsCount) {
        GiftsCount = giftsCount;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        super.fromJson(obj);
        if(obj.has(FieldUtils.GiftsList)){
            GiftsList=obj.getString(FieldUtils.GiftsList);
        }
        if(obj.has(FieldUtils.RecommendList)){
            RecommendList=obj.getString(FieldUtils.RecommendList);
        }
        if(obj.has(FieldUtils.BannerList)){
            BannerList=obj.getString(FieldUtils.BannerList);
        }
        if(obj.has(FieldUtils.NewestList)){
            NewestList=obj.getString(FieldUtils.NewestList);
        }
        if(obj.has(FieldUtils.NewestCount)){
            NewestCount=obj.getInt(FieldUtils.NewestCount);
        }
        if(obj.has(FieldUtils.RecommendCount)){
            RecommendCount=obj.getInt(FieldUtils.RecommendCount);
        }
        if(obj.has(FieldUtils.GiftsCount)){
            GiftsCount=obj.getInt(FieldUtils.GiftsCount);
        }
    }
}
