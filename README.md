Cubition
========

* Builds of the project can be found [here](http://jenkins.jselby.net/job/Cubition/).

Cubition is a voxel-based game engine, inspired by Minecraft, that aims to
to ease the previously painful task of modding, network issues, and so on.
Any modifications made should be able to be easily implemented without having to look
at obscure code or other similar nasties.

While many similar projects exist, most don't make it off the ground, more
then often not due to lack of support or time on the developers behalf. We aim 
to fix all this, with a skilled team and a firm destination in mind.

Mod developers should compile against the API module, and users should grab the Bootstrap module.

Building
--------

We use [Apache Maven](http://maven.apache.org) to compile the project, as it has a simple
configuration format, and is cross-platform.

To compile:
- Install Maven / a Oracle JDK (if required).
- Clone, or [Download the .zip](https://github.com/Cubition/Cubition/archive/master.zip) of this project's source code.
- Windows: Run `/path/to/mvn` in the source root. Javadocs can be produced via `/path/to/mvn clean package javadoc:javadoc`.
- Linux/Mac/platforms with Bash: Run `./compile.sh` in the source root. This generates Javadoc for the project automatically.
- The packaged results will be in the `out/` directory.

**If you are wanting to compile the project, please remove the client module from the main pom.xml as it doesn't have a pom.xml, spitting out an error in your console when compiling it.

Contributions
-------------

We welcome contributions to the project, via pull requests.
