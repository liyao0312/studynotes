# 输入输出流IO

## java文件读取

FileReader 类是将文件按字符流的方式读取char数组或者String

FileInputStream 则按字节流的方式读取文件 byte 数组

1. 首先获得一个文件句柄。File file = new File()；file 即为文件句柄。两人之间连通电话网络了，接下来就可以开始打电话了。
2. 通过这条线路读取甲方的信息： New FileInputStream(file) 目前这个信息已经读进来内存当中了。接下来需要解读成乙方可以理解的东西
3. 既然你使用了 FileInputStream()。那么对应的需要使用 InputStreamReader() 这个方法进行解读刚才撞进来内存当中的数据
4. 解读完成后要输出。那当然要转换成IO可以识别的数据。那就需要调用字节码读取的方法 BufferedReader()。同时使用 bufferedReader()的readline()方法读取txt文件中的每一行数据。