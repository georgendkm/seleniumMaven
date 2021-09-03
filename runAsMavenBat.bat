set projectLocation=C:\Users\binds\eclipse-workspace\SrinivasmavenProject
cd %projectLocation%
mvn clean test -DsuiteXmlFile=testng.xml
pause