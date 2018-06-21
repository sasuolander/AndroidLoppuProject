package fi.sasu.hackernewapp.ItemObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model(
        @SerializedName("id") @Expose var idObject:Int? =null,
        @SerializedName("title") @Expose var titleObject:String?=null,
        @SerializedName("score") @Expose var scoreObject:Int? =null,
        @SerializedName("by") @Expose var byObject:String? =null,
        @SerializedName("time") @Expose var timeObject:Int?=null,
        @SerializedName("type") @Expose var typeObject:String?=null,
        @SerializedName("url") @Expose var urlObject:String?=null) {

    @SerializedName("descendants")
    @Expose
    var descendantsObject:Int? =null

    @SerializedName("kids")
    @Expose
    var kidsObject:ArrayList<Id>? = null
}
/*
{
    "by" : "dhouston",
    "descendants" : 71,
    "id" : 8863,
    "kids" : [ 8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005, 9671, 8940, 9067, 8908, 9055, 8865, 8881, 8872, 8873, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8878, 8870, 8980, 8934, 8876 ],
    "score" : 111,
    "time" : 1175714200,
    "title" : "My YC app: Dropbox - Throw away your USB drive",
    "type" : "story",
    "url" : "http://www.getdropbox.com/u/2/screencast.html"
}
        */