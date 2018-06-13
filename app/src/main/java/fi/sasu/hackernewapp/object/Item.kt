package fi.sasu.hackernewapp.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Item {
    @SerializedName("by")
    @Expose
    var byObject:String? =null
    @SerializedName("descendants")
    @Expose
    var descendantsObject:String? =null
    @SerializedName("id")
    @Expose
    var idObject:Int? =null
    @SerializedName("kids")
    @Expose
    var kidsObject:ArrayList<ID_List>? = null
    @SerializedName("score")
    @Expose
    var scoreObject:String? =null
    @SerializedName("time")
    @Expose
    var timeObject:String?=null
    @SerializedName("title")
    @Expose
    var titleObject:String?=null
    @SerializedName("type")
    @Expose
    var typeObject:String?=null
    @SerializedName("url")
    @Expose
    var urlObject:String?=null
}

