package fi.sasu.hackernewapp.itemObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Model(
        @SerializedName("id") @Expose  var idObject:Int? =null,
        @SerializedName("title") @Expose var titleObject:String?=null,
        @SerializedName("score") @Expose var scoreObject:Int? =null,
        @SerializedName("by") @Expose var byObject:String? =null,
        @SerializedName("time") @Expose var timeObject:Long?=null,
        @SerializedName("type") @Expose var typeObject:String?=null,
        @SerializedName("url") @Expose var urlObject:String?=null) {
    @SerializedName("descendants")
    @Expose
    var descendantsObject:Int? =null
    //Actually kids is not a List, it's an Array, so declare kids like this
    //Integer[] kids;
    @SerializedName("kids")
    @Expose
    var kidsObject:List<Int>? = null
    val convert ={timeObject:Long->Date(timeObject)}
}
/*
{
    "by" : "dhouston",
    "descendants" : 71,
    "id" : 8863,
    "kids" : [ 8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958,
    9005, 9671, 8940, 9067, 8908, 9055, 8865, 8881, 8872,
    8873, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902,
    8907, 8894, 8878, 8870, 8980, 8934, 8876 ],
    "score" : 111,
    "time" : 1175714200,
    "title" : "My YC app: Dropbox - Throw away your USB drive",
    "type" : "story",
    "url" : "http://www.getdropbox.com/u/2/screencast.html"
}
        */