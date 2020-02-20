package com.yeemos.theog.presenter

import com.common.lib.mvp.BasePresenter
import com.yeemos.theog.contract.MainContract


class MainPresenter(rootView: MainContract.View) : BasePresenter<MainContract.View>(rootView),
    MainContract.Presenter {
//    override fun getStringResource() {
//        val map: HashMap<String, Any> = HashMap()
//        map.put("appId", "qpid")
//        map.put("lang", "en_US")
//        map.put("timestamp", 0L)
//
//
//        OkHttpManager.getInstance()
//            .post(Constants.SOURCE_MANAGER_URL, map, callback = object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    var jsonObject = JSONObject(response.body?.string());
//                    jsonObject = jsonObject.getJSONObject("data")
//                    val iterator = jsonObject.keys()
//                    val outputStream = ByteArrayOutputStream()
//                    val serializer = Xml.newSerializer()
//                    try {
//                        serializer.setOutput(outputStream, "UTF-8")
//                        serializer.startDocument("UTF-8", true)
//                        serializer.startTag(null, "resources")
//                        var key: String
//                        var value: String
//                        while (iterator.hasNext()) {
//                            key = iterator.next()
//                            value = jsonObject.getString(key)
//                            key = Utils.checkKey(key)
//                            value = Utils.checkValue(value, true)
//                            serializer.startTag(null, "string");
//                            serializer.attribute(null, "name", key);
//                            serializer.text(value);
//                            serializer.endTag(null, "string");
//                        }
//                        serializer.endTag(null, "resources");
//                        serializer.endDocument();
//                        Utils.savexmlToSD(outputStream.toString())
//                    } catch (e: Exception) {
//                    } finally {
//                        outputStream.flush()
//                        outputStream.close()
//                    }
//                }
//            })
//    }

    override fun getUserInfo() {

    }
}