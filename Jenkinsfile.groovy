#!/usr/bin/env groovy
// 镜像仓库地址需要时打开
// 使用需要下载pipline
def registry = "xxx"
def project = "max"
def app_name = "java-demo"
def image_name = "springboot-node"
def git_address = "https://github.com/appmocca/CONT8.git"
pipeline {
    agent any
    stages {
        stage('拉取代码'){
            steps {
             sh 'git clone https://github.com/appmocca/CONT8.git -b max'
            // 拉取代码
            }
        }

        stage('代码编译'){
           steps {
             sh """
                pwd
                ls
                JAVA_HOME=/usr/local/jdk
                PATH=$JAVA_HOME/bin:/usr/local/maven/bin:$PATH
                mvn clean package -Dmaven.test.skip=true
                # 开始编译nodejs代码  这里是vue
                cd client && npm install
                npm run build
                """ 
           }
        }

        stage('构建镜像'){
           steps {
                sh """
                  echo '
                    FROM  ascdc/jdk8
                    RUN echo "java -jar /apps/java.jar" >> /start.sh
                    # 启动java命令
                    RUN echo "npm start" >> /start.sh
                    ADD target/*jar /apps/java.jar
                    COPY ./*       /app/
                    # 复制所有文件进容器
                    CMD ["bash","-x","/start"]
                  ' > Dockerfile
                  # 自生成dockerfile
                  docker build -t ${image_name} .
                  # 需要仓库时打开添加变量即可
                  #docker login -u ${username} -p '${password}' ${registry}
                  #docker push ${image_name}
                """
                }
           } 
        }

        stage('部署到Docker'){
           steps {
              sh """
              REPOSITORY=${image_name}
              docker rm -f tomcat-java-demo |true
              # 8080 java-demo
              # 80 nginx vue-demo
              docker container run -d --name java-spring-boot-node -p 8081:80 -p 88:8080 ${image_name}
              """
            }
        }
    }
}