# 介绍
使用 开源Tesseract-OCR ,识别一般纯文本

# 部署
Tesseract 无Linux 编译好的版本, 建议git获取 源码编译 https://github.com/tesseract-ocr/tesseract/tree/4.1.0 

jar 和 编译好的 Tesseract 在同一个目录下

# 常见问题
1. centerOS gcc 如果不支持 c++17 git上下载Tesseract 5.0版本以下 源码 
2. Tesseract 编译步骤：  执行 autogen.sh  ，再执行 ./configure
如果缺失 Leptonica ，单独下载 https://github.com/DanBloomberg/leptonica/releases/download/1.82.0/leptonica-1.82.0.tar.gz
4. Leptonica 编译步骤：  执行 ./configure 
5. 系统找不到 libtesseract.so（Tesseract 的共享库）  
    先找到文件位置
   find / -name "libtesseract.so*" 2>/dev/null
   启动前：执行export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
6. 要确保运行环境有 tesseract-ocr-eng.traineddata 语言包  (tessdata目录下)

