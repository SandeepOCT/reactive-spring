build:
	mvn clean package -DskipTests

run:
	java -jar *.jar

debug:
	java \
	-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
	-jar target/*.jar \
	--debug

format:
	mvn fmt:format
