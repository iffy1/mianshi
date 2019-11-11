1.安装old.apk 1.73

2.生成差分包
./bsdiff-4.3/bsdiff old.apk new.apk patch.diff


3.把old.apk 和 patch.diff放入下面路径
/storage/emulated/0/Android/data/com.example.myapplication/files

4.用app合并差分包并且 安装合并出来的new.apk 1.74

