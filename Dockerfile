FROM maven:3.9.6-amazoncorretto-21-debian

WORKDIR /opt/app

ENV APP_HOME /opt/app

# Adicionando um usuário e suas permissões
RUN groupadd -r hexgroup && useradd -r -g hexgroup -d $APP_HOME -s /sbin/nologin hexuser

RUN chown -R hexuser:hexgroup $APP_HOME

RUN chmod -R 755 $APP_HOME

#Copiando o pom.xml
COPY pom.xml .

#Copiando a aplicação
COPY src ./src

#Buildando a aplicação
RUN mvn package -DskipTests

#Expondo a porta 28081
EXPOSE 28081

#Executando a aplicação
ENTRYPOINT ["java", "-jar", "/opt/app/target/hexburger-pagamento-0.0.1-SNAPSHOT.jar", "-Xms2048M", "-Xmx3072M"]