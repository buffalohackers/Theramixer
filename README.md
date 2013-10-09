Theramixer
==============

Vision based Monome

Installation:

sudo apt-get install openjdk-6-jdk libjpeg-turbo8-dev build-essential ant

sudo add-apt-repository ppa:gillesg/ppa
sudo apt-get update
sudo apt-get install libv4l4j-java

1. In the Project Explorer pane, right-click on your project and select Build Path -> Configure build path.
2. Select the Libraries tab, and click Add external JARs...
3. Select /usr/share/java/v4l4j.jar
4. Expand the v4l4j.jar entry and double click on Native library location
5. Select /usr/lib/jni
6. Double click on Javadoc location, and select Javadoc URL
7. Enter http://v4l4j.googlecode.com/svn/www/v4l4j-api
8. Select OK
