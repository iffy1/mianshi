package com.iffy.mianshi.storage.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri


//（应用于<provider>标记）限制谁可以访问ContentProvider中的数据。
// （内容提供程序有重要的附加安全工具可用，称为 URI 权限，将在后面介绍。）
// 与其他组件不同，您可以设置两个单独的权限属性：android:readPermission限制谁可以读取提供程序，android:writePermission限制谁可以写入提供程序。
//请注意，如果提供程序有读取和写入权限保护，仅拥有写入权限并不表示您可以读取提供程序。
// 第一次检索提供程序时将会检查权限（如果没有任何权限，将会抛出 SecurityException），
// 对提供程序执行操作时也会检查权限。使用 ContentResolver.query() 需要拥有读取权限；
// 使用 ContentResolver.insert()、ContentResolver.update()、ContentResolver.delete()需要写入权限。
// 在所有这些情况下，没有所需的权限将导致调用抛出 SecurityException。
//android:grantUriPermissions 临时权限标识，true时，意味着该provider下所有数据均可被临时使用；
// false时，则反之，但可以通过设置标签来指定哪些路径可以被临时使用。
class IffyContentProvider : ContentProvider() {


    private val PERSON = 1 // 操作单行记录
    private val PERSON_ID = 2 // 操作多行记录

    companion object {
        val PERSON_AUTHORITY = "com.iffy.mianshi.storage.provider.PersonContentProvider"
    }

    var URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH)

    // 而对于ContentProvider而言，Uri也是有固定格式的：<srandard_prefix>://<authority>/<path>/<id>
    //Authority：授权信息，用以区别不同的ContentProvider；
    //Path：表名，用以区分ContentProvider中不同的数据表；
    //Id：Id号，用以区别表中的不同数据；

    //我们根据path的不同，来区别对不同的数据库表进行操作
    //在Android中，Uri是一种比较常见的资源访问方式。

    //matcher.addURI(ContactsContract.AUTHORITY, "contacts", CONTACTS);
    //matcher.addURI(ContactsContract.AUTHORITY, "contacts/#", CONTACTS_ID);
    //matcher.addURI(ContactsContract.AUTHORITY, "contacts/#/data", CONTACTS_ID_DATA);
    //matcher.addURI(ContactsContract.AUTHORITY, "data/phones/#", PHONES_ID);
    init {
        // 添加两个URI筛选
        URI_MATCHER.addURI(PERSON_AUTHORITY, "person", PERSON)
        // 使用通配符#，匹配任意数字
        URI_MATCHER.addURI(PERSON_AUTHORITY, "person/#", PERSON_ID)
    }


    override fun insert(p0: Uri, contentValue: ContentValues?): Uri? {
        when (URI_MATCHER.match(p0)) {
            PERSON -> {
                println("IffyContentProvider 要插入联系人 db:${getDB()} dao:${getDB()?.getPersonDao()}")
                //折腾 还需要把contentValue转化成Person对象
                getDB()?.getPersonDao()?.insert(
                    Person(
                        null,
                        contentValue?.getAsString("name"),
                        contentValue?.getAsInteger("age")
                    )
                )
                //向外界通知该ContentProvider里的数据发生了变化 ,以便ContentObserver作出相应
                context?.contentResolver?.notifyChange(Uri.parse("content://${IffyContentProvider.PERSON_AUTHORITY}/person"), null)
            }
        }
        return null
    }

    private fun getDB(): PersonDatabase? {
        return PersonDatabase.getDB(context?.applicationContext)
    }


    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        println("IffyContentProvider 要查询联系人")
        var data = getDB()?.getPersonDao()?.getAll()
        //将data(List<Person>)转换成cursor
        var cursor = MatrixCursor(arrayOf("p_id", "name", "age"))
        data?.forEach {
            cursor.addRow(arrayOf(it.p_id,it.name,it.age))
        }

        println("IffyContentProvider 返回联系人")
        return cursor
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, p1: String?, p2: Array<out String>?): Int {
        when(URI_MATCHER.match(uri)){
            PERSON_ID->{
               var id =  ContentUris.parseId(uri)
                println("有人要删除 id是$id 的人")
                getDB()?.getPersonDao()?.deleteAPerson(id.toInt())
                context?.contentResolver?.notifyChange(Uri.parse("content://${PERSON_AUTHORITY}/person/$id"), null)
            }
        }
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}