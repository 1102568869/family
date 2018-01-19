# family
家庭小账本啦啦啦

srpingboot+vue2.js+elementui

### Quick start
**Make sure you have Node version >= 5.0 and NPM >= 3 and Maven>=3** 

```bash
# clone our repo
# --depth 1 removes all but one .git commit history
git clone --depth 1 https://github.com/1102568869/family.git

# create database family and run sql/init.sql to create tables and insert data necessary;

# replace url/username/password with your database config in application.yml;

# change directory to websource
cd family/websource

# install the repo with npm
npm install

# if failed,change source
npm --registry https://registry.npm.taobao.org install

# build by webpack
npm run build

# change directory to project
cd ../

# package jar by Maven
mvn clean package -Dmaven.test.skip=true

# run it!
java -jar target/Demo.jar

```
go to [http://0.0.0.0:8888](http://0.0.0.0:8888) or [http://localhost:8888](http://localhost:8888) in your browser
