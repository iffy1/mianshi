package com.iffy.mianshi.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
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
class IffyContentProvider:ContentProvider() {

    private val INSERTNUMBER = 100
    private val INSERTADDRESS = 200
    //Authority：授权信息，用以区别不同的ContentProvider；
    //Path：表名，用以区分ContentProvider中不同的数据表；
    //Id：Id号，用以区别表中的不同数据；

    //我们根据path的不同，来区别对不同的数据库表进行操作
    //matcher.addURI(ContactsContract.AUTHORITY, "contacts", CONTACTS);
    //matcher.addURI(ContactsContract.AUTHORITY, "contacts/#", CONTACTS_ID);
    //matcher.addURI(ContactsContract.AUTHORITY, "contacts/#/data", CONTACTS_ID_DATA);
    //matcher.addURI(ContactsContract.AUTHORITY, "data/phones/#", PHONES_ID);
    fun buildURImatcher():UriMatcher{
        var matcher =UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI("com.iffy.mianshi","test",INSERTNUMBER)
        return matcher
    }
    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        when(buildURImatcher().match(p0)) {
            INSERTNUMBER -> insertNumber()
            INSERTADDRESS-> insertAddress()
        }
        return null
    }

    private fun insertAddress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun insertNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(p0: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor? {
        return null
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}