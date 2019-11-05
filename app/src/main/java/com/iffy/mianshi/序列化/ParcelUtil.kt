package com.iffy.mianshi.序列化

import android.content.Context
import android.os.Parcel
import java.io.*

class ParcelUtil {
    companion object {
        fun convertParcelableToByteArray(duckParcelable: DuckParcelable): ByteArray {
            //获得一个新的parcel ，相当于new一个对象
            val parcel = Parcel.obtain()
            //设置偏移量
            parcel.setDataPosition(0)

            //序列化 将对象写入parcel包 顺序不能错
            duckParcelable.writeToParcel(parcel, 0)

            //获取Parcel对象内存数据
            val data = parcel.marshall()

            //parcel的回收
            parcel.recycle()

            println("ParcelUtil 存入ByteArray时候的Size是${data.size}")
            return data
        }

        fun convertByteArrayToParcelable(byteArray: ByteArray): DuckParcelable {
            println("ParcelUtil 取出ByteArray时候的Size是${byteArray.size}")
            //获得一个新的parcel ，相当于new一个对象
            val parcel = Parcel.obtain()

            //字节数组转换为Parcel对象 得到的parcel就是一个正常的parcel对象，这时就可以将之前我们所存入的数据按照顺序进行获取
            parcel.unmarshall(byteArray, 0, byteArray.size)

            //设置偏移量 这步是必须的 不然读出来的对象参数是null
            parcel.setDataPosition(0)

            ////调用构造 把parcel还原为Parcelable对象
            val duckParcelable = DuckParcelable.createFromParcel(parcel)

            //回收
            parcel.recycle()
            return duckParcelable
        }


        //ByteArray >file
        fun saveByteArrayToFile(byteArray: ByteArray, c: Context) {
            //获取路径
            val path = c.getExternalFilesDir("parcel")
            //得到文件
            val file = File("$path/duckParcelable.iffy")
            println("保存路径为${file?.path}")
            //保存路径为/storage/emulated/0/Android/data/com.iffy.mianshi/files/parcel/duckParcelable.iffy
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()
            //File作为输出目标 FileOutputStream
            val os = FileOutputStream(file)
            val bos = BufferedOutputStream(os)

            //byteArray作为输入 ByteArrayInputStream
            val ins = ByteArrayInputStream(byteArray)
            val bis = BufferedInputStream(ins)

            //buffer读取/写入
            val buffer = ByteArray(1024)
            var len = 0
            while (bis.read(buffer).also { len = it } != -1) {
                bos.write(buffer, 0, len)
            }
            //关闭
            bis.close()
            bos.close()
            os.close()
            bis.close()
        }

        //File>byteArray
        fun readByteArrayFromFile(c: Context): ByteArray {
            //获取文件路径
            val path = c.getExternalFilesDir("parcel")
            //获取文件
            val file = File("$path/duckParcelable.iffy")
            println("读取路径为${file?.path}")

            //File作为输入 FileInputStream
            val ins = FileInputStream(file)
            val bis = BufferedInputStream(ins)

            //ByteArray作为输出(ByteArrayOutputStream)
            val os = ByteArrayOutputStream()
            val bos = BufferedOutputStream(os)

            //读写缓存
            val buffer = ByteArray(1024)
            var len = 0
            while (bis.read(buffer).also { len = it } != -1) {
                bos.write(buffer, 0, len)
            }
            bis.close()
            bos.close()
            ins.close()
            os.close()

            return os.toByteArray()
        }
    }

}