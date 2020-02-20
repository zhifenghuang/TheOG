package com.yeemos.theog.utils

import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.regex.Pattern

class Utils {

    companion object {

        private val reg1 = "^[a-zA-Z_$][a-zA-Z0-9_]*"
        private val reg2 = "[^a-zA-Z_0-9]"
        private val reg3 = "[^a-zA-Z_$]"

        fun checkKey(key: String): String {
            var newKey = key
            val p1 = Pattern.compile(reg1)
            val p2 = Pattern.compile(reg2)
            val p3 = Pattern.compile(reg3)
            val m1 = p1.matcher(key)

            if (!m1.matches()) {
                for (i in 1 until key.length - 1) {
                    val c = key.substring(i, i + 1)
                    val m2 = p2.matcher(c)
                    if (m2.matches()) {
                        newKey = newKey.replace(c, " ")
                        continue
                    }
                }
                while (true) {
                    val c = newKey.substring(0, 1)
                    val m3 = p3.matcher(c)
                    if (m3.matches()) {
                        newKey = newKey.substring(1, newKey.length)
                        continue
                    }
                    break
                }
                if (newKey.contains(" ")) {
                    newKey = newKey.replace(" ", "")
                }
                return newKey
            }
            return key
        }

        fun checkValue(value: String, createXml: Boolean): String {
            var value = value
            val reg = ".*[%][sd].*"
            var count = 0
            if (value.contains("%@")) {
                value = value.replace("%@", "%s")
            }
            if (createXml) {
                if (value.contains("\n")) {
                    value = value.replace("\n", "\\n")
                }
            }
            val p = Pattern.compile(reg)
            val m = p.matcher(value)
            if (m.matches()) {
                for (j in 0 until value.length) {
                    val s = value.substring(j, j + 1)
                    if (s == "%") {
                        count++
                    }
                }
                if (count > 1) {
                    value = value.replace("%", "#")
                    for (i in 1..count) {
                        val regx = "[#]"
                        value = value.replaceFirst(regx.toRegex(), "%$i\\$")
                    }
                }
            }
            if (createXml) {
                if (value.contains("'")) {
                    value = value.replace("'", "\'")
                }
            }
            return value
        }

        fun saveXmlToSD(str: String) {
            //新建文件夹
            val folderName = "TheOG"
            val sdCardDir = File(Environment.getExternalStorageDirectory(), folderName)

            if (!sdCardDir.exists()) {
                if (!sdCardDir.mkdirs()) {

                    try {
                        sdCardDir.createNewFile()

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

            try {
                val saveFile = File(sdCardDir, "string.xml")
                if (!saveFile.exists()) {
                    saveFile.createNewFile()
                }
                var outStream: FileOutputStream? = FileOutputStream(saveFile)
                try {
                    outStream!!.write(str.toByteArray())
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    outStream!!.close()
                    outStream = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}