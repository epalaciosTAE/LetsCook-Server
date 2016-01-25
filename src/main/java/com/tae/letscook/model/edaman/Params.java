
package com.tae.letscook.model.edaman;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Params {

    @SerializedName("sane")
    @Expose
    private List<Object> sane = new ArrayList<Object>();
    @SerializedName("q")
    @Expose
    private List<String> q = new ArrayList<String>();
    @SerializedName("app_id")
    @Expose
    private List<String> appId = new ArrayList<String>();
    @SerializedName("app_key")
    @Expose
    private List<String> appKey = new ArrayList<String>();

    /**
     * 
     * @return
     *     The sane
     */
    public List<Object> getSane() {
        return sane;
    }

    /**
     * 
     * @param sane
     *     The sane
     */
    public void setSane(List<Object> sane) {
        this.sane = sane;
    }

    /**
     * 
     * @return
     *     The q
     */
    public List<String> getQ() {
        return q;
    }

    /**
     * 
     * @param q
     *     The q
     */
    public void setQ(List<String> q) {
        this.q = q;
    }

    /**
     * 
     * @return
     *     The appId
     */
    public List<String> getAppId() {
        return appId;
    }

    /**
     * 
     * @param appId
     *     The app_id
     */
    public void setAppId(List<String> appId) {
        this.appId = appId;
    }

    /**
     * 
     * @return
     *     The appKey
     */
    public List<String> getAppKey() {
        return appKey;
    }

    /**
     * 
     * @param appKey
     *     The app_key
     */
    public void setAppKey(List<String> appKey) {
        this.appKey = appKey;
    }

}
