1.生成增量文件 old-to-new.patch
./bsdiff old.apk new.apk old-to-new.patch

</p>
<p>2.增量文件和old.apk合并成新的new2.apk
./bspatch old.apk new2.apk old-to-new.patch

