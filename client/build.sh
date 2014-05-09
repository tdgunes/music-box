mkdir ./build/
rm -R -f ./build/app.nw 
zip ./build/app.nw index.html package.json raphael-min.js
/Applications/node-webkit.app/Contents/MacOS/node-webkit ./build/app.nw 