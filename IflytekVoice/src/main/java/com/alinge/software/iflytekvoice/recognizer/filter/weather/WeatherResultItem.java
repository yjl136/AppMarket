package com.alinge.software.iflytekvoice.recognizer.filter.weather;

import com.alinge.software.iflytekvoice.recognizer.filter.IResullt;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class WeatherResultItem extends IResullt {
    private String airQuality;
    private String sourceName;
    private String date;
    private String lastUpdateTime;
    private String dateLong;
    private String wind;
    private String city;
    private String humidity;
    private String windLevel;
    private String weather;
    private String tempRange;
    private String province;

    public WeatherResultItem() {
    }

    public WeatherResultItem(String airQuality, String sourceName, String date, String lastUpdateTime, String dateLong, String city, String wind, String humidity, String windLevel, String weather, String tempRange, String province) {
        this.airQuality = airQuality;
        this.sourceName = sourceName;
        this.date = date;
        this.lastUpdateTime = lastUpdateTime;
        this.dateLong = dateLong;
        this.city = city;
        this.wind = wind;
        this.humidity = humidity;
        this.windLevel = windLevel;
        this.weather = weather;
        this.tempRange = tempRange;
        this.province = province;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDateLong() {
        return dateLong;
    }

    public void setDateLong(String dateLong) {
        this.dateLong = dateLong;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTempRange() {
        return tempRange;
    }

    public void setTempRange(String tempRange) {
        this.tempRange = tempRange;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public IResullt getResult() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.airQuality)){
            airQuality=obj.getString(PropertyList.airQuality);
        }
        if(obj.has(PropertyList.sourceName)){
            sourceName=obj.getString(PropertyList.sourceName);
        }
        if(obj.has(PropertyList.date)){
            date=obj.getString(PropertyList.date);
        }

        if(obj.has(PropertyList.lastUpdateTime)){
            lastUpdateTime=obj.getString(PropertyList.lastUpdateTime);
        }
        if(obj.has(PropertyList.dateLong)){
            dateLong=obj.getString(PropertyList.dateLong);
        }
        if(obj.has(PropertyList.city)){
            city=obj.getString(PropertyList.city);
        }

        if(obj.has(PropertyList.wind)){
            wind=obj.getString(PropertyList.wind);
        }
        if(obj.has(PropertyList.humidity)){
            humidity=obj.getString(PropertyList.humidity);
        }
        if(obj.has(PropertyList.windLevel)){
            windLevel=obj.getString(PropertyList.windLevel);
        }
        if(obj.has(PropertyList.weather)){
            weather=obj.getString(PropertyList.weather);
        }
        if(obj.has(PropertyList.tempRange)){
            tempRange=obj.getString(PropertyList.tempRange);
        }
        if(obj.has(PropertyList.province)){
            province=obj.getString(PropertyList.province);
        }
    }
}
