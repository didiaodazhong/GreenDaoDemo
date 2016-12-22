# GreenDaoDemo
对于数据库框架greenDao3.2的集成使用
首先在项目的build.gradle中添加：

```
buildscript {
    repositories {

        mavenCentral()
//        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.0'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.0'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


//apply plugins: 'org.greenrobot.greendao'

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```
之后是在项目app的build.gradle中添加：

```
apply plugin: 'org.greenrobot.greendao'

greendao{
schemaVersion 1
daoPackage '包名'
targetGenDir 'src/main/java'


dependencies {
complie 'greendao版本'
}

}
```

之后点击build下面的make project，我们就可以看到自动创建出的gen目了。


创建User Bean，创建GreenDaoManager,使用增删改查方法，处理数据。


