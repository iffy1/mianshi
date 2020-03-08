package com.iffy.mianshi.hotfix.chajianhua;

import android.content.Context;

import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * author : iffy
 * time   : 2020/03/04
 */
public class ChaJianHuaUtil {

    public static void loadChaJian(Context mContext) {
        try {
            //==============老师教的===============//
            //2.dexElements的Filed对象--》只与类相关，和对象不相关
            Class clazz = Class.forName("dalvik.system.DexPathList");
            Field dexElementsFiled = clazz.getDeclaredField("dexElements");
            dexElementsFiled.setAccessible(true);

            //4.pthList 的Field对象 只与类相关，和对象不相关
            Class baseClassLoderClass = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseClassLoderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            //5.BaseDexClassLoader的类的对象

            //宿主的类加载器PathClassLoder 和对象有关
            ClassLoader suzhuLoader = mContext.getClassLoader();
            //和对象有关
            Object suzhuPathList = pathListField.get(suzhuLoader);
            //得到了宿主的dexElements
            Object[] suzhuElements = (Object[])dexElementsFiled.get(suzhuPathList);

            System.out.println("+++++++++++"+suzhuElements);


            //插件的DexClassLoader
            String apkPath = "";
            DexClassLoader pluginDexClassLoader = new DexClassLoader(apkPath,
                    mContext.getCacheDir().getAbsolutePath(),null,suzhuLoader);
            Object pluginPathList = pathListField.get(pluginDexClassLoader);
            //拿到了plugiin的elements
            Object[] pluginElements = (Object[])dexElementsFiled.get(pluginPathList);

            //合并宿主和插件的elements
            Object[] newElemet = new Object[suzhuElements.length+pluginElements.length];
            //拷贝宿主的Elements
            System.arraycopy(suzhuElements,0,newElemet,0,suzhuElements.length);

            //拷贝插件的Elements
            System.arraycopy(pluginElements,0,newElemet,suzhuElements.length,pluginElements.length);

            //新数组赋值给宿主
            dexElementsFiled.set(suzhuLoader,newElemet);


            //=======================自己写的===================================================
            //宿主PathClassLoader
            System.out.println(mContext.getClassLoader());
            PathClassLoader suzhuClassLoader = (PathClassLoader) mContext.getClassLoader();
            Field dexPathListMethod = suzhuClassLoader.getClass().getDeclaredField("DexPathList");
            dexPathListMethod.setAccessible(true);

            //获取dexPathList
            Object dexPathList = dexPathListMethod.get(suzhuClassLoader);

            //获取宿主的dexElements
            Field dexElements = dexPathList.getClass().getDeclaredField("dexElements");
            dexElements.setAccessible(true);
            Object elements = dexElements.get(dexPathList);

            System.out.println(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
